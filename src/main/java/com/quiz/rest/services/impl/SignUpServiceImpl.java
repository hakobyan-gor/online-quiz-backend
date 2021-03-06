package com.quiz.rest.services.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.quiz.rest.services.AuthenticationTokenService;
import com.quiz.rest.services.ConfirmationTokenService;
import com.quiz.models.request.RegistrationRequest;
import com.quiz.rest.repositories.RoleRepository;
import com.quiz.security.mail.EmailSenderService;
import org.springframework.stereotype.Service;
import com.quiz.models.response.JwtResponse;
import com.quiz.rest.services.SignUpService;
import com.quiz.rest.services.UserService;
import com.quiz.models.ConfirmationToken;
import com.quiz.security.UserPrincipal;
import javax.mail.MessagingException;
import com.quiz.models.AuthToken;
import com.quiz.models.Email;
import com.quiz.enums.Status;
import com.quiz.models.Role;
import com.quiz.models.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SignUpServiceImpl implements SignUpService {

	private final AuthenticationTokenService authenticationTokenService;
	private final ConfirmationTokenService confirmationTokenService;
	private final EmailSenderService emailSenderService;
    private final UserDetailsService userDetailsService;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public SignUpServiceImpl(
    		@Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService,
    		AuthenticationTokenService authenticationTokenService,
    		ConfirmationTokenService confirmationTokenService,
    		EmailSenderService emailSenderService,
    		RoleRepository roleRepository,
            UserService userService
    ) {
    	this.authenticationTokenService = authenticationTokenService;
    	this.confirmationTokenService = confirmationTokenService;
    	this.emailSenderService = emailSenderService;
    	this.userDetailsService = userDetailsService;
    	this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public User signUp(RegistrationRequest registrationRequest) throws MessagingException {

        try {
            userDetailsService.loadUserByUsername(registrationRequest.getEMail());
            return null;
        } catch (UsernameNotFoundException e){
            User user = new User();
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setPassword(registrationRequest.getPassword());
            user.setUsername(registrationRequest.getEMail());
            user.setEMail(registrationRequest.getEMail());
            user.setStatus(Status.PENDING);

            List<Role> roleSet = roleRepository.findRolesByRole("USER");
            user.setRoles(roleSet);

            user = userService.createUser(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user.getId());
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            
            Email email = new Email();
        	email.setEmail(registrationRequest.getEMail());
        	email.setSubject("Email verification");
        	
        	Map<String, Object> props = new HashMap<>();
        	props.put("name", registrationRequest.getFirstName());
        	props.put("surname", registrationRequest.getLastName());
        	props.put("confirmationToken", confirmationToken.getConfirmationToken());
        	
        	email.setProps(props);

        	emailSenderService.sendEmail(email);

            return user;
        }
    }

    @Override
    public JwtResponse verifyEMail(ConfirmationToken confirmationToken){

        JwtResponse response = null;
        System.out.println(confirmationToken);

        if (confirmationTokenService.checkConfirmationToken(confirmationToken)){
            User user = userService.getUserById(confirmationToken.getUserId());
            user.setStatus(Status.VERIFIED);
            user.setUsername(user.getEMail());
            user = userService.updateUser(user);

            userService.updateUserStatusBeforeLogIn(user.getId());

            confirmationTokenService.deleteConfirmationTokenByUserId(user.getId());
            UserDetails userDetails = new UserPrincipal(user);
            AuthToken authToken = authenticationTokenService.createToken(userDetails, user.getId());

            response = new JwtResponse(authToken.getToken(), "Bearer", user.getUsername());
        }

        return response;
    }

}
