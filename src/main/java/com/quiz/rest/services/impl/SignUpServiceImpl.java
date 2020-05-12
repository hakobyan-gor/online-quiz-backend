package com.quiz.rest.services.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.quiz.rest.services.AuthenticationTokenService;
import com.quiz.rest.services.ConfirmationTokenService;
import com.quiz.models.request.RegistrationRequest;
import org.springframework.mail.SimpleMailMessage;
import com.quiz.rest.repositories.RoleRepository;
import com.quiz.security.mail.EmailSenderService;
import org.springframework.stereotype.Service;
import com.quiz.models.response.JwtResponse;
import com.quiz.rest.services.SignUpService;
import com.quiz.rest.services.UserService;
import com.quiz.models.ConfirmationToken;
import com.quiz.security.UserPrincipal;
import com.quiz.models.AuthToken;
import com.quiz.enums.Status;
import com.quiz.models.Role;
import com.quiz.models.User;
import java.util.List;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final EmailSenderService emailSenderService;
    private final ConfirmationTokenService confirmationTokenService;
    private final AuthenticationTokenService authenticationTokenService;
    private final RoleRepository roleRepository;

    @Autowired
    public SignUpServiceImpl(
            UserService userService,
            @Qualifier("UserDetailsServiceImpl") UserDetailsService userDetailsService,
            EmailSenderService emailSenderService,
            ConfirmationTokenService confirmationTokenService,
            AuthenticationTokenService authenticationTokenService,
            RoleRepository roleRepository
    ) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenService = confirmationTokenService;
        this.authenticationTokenService = authenticationTokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public User signUp(RegistrationRequest registrationRequest) {

        try {
            userDetailsService.loadUserByUsername(registrationRequest.getEMail());
            return null;
        } catch (UsernameNotFoundException e){
            User user = new User();
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setPassword(registrationRequest.getPassword());
            user.setEMail(registrationRequest.getEMail());
            user.setStatus(Status.PENDING);

            List<Role> roleSet = roleRepository.findRolesByRole("USER");

            user.setRoles(roleSet);

            user = userService.createUser(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user.getId());
            confirmationTokenService.saveConfirmationToken(confirmationToken);

            sendEMail(registrationRequest, confirmationToken.getConfirmationToken());

            return user;
        }
    }

    private void sendEMail(RegistrationRequest registrationRequest, int confirmationToken){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(registrationRequest.getEMail());
        simpleMailMessage.setSubject("Complete Registration!");
        simpleMailMessage.setText(
                registrationRequest.getFirstName() + "\n" +
                        "Welcome to Online Quiz\n" +
                        "Confirm your email to continue\n" +
                        "Confirmation Token : " +
                        confirmationToken + "\n\n" +
                        "-Best Regards\n" +
                        "Online Quiz Theme");

        emailSenderService.sendEmail(simpleMailMessage);

    }

    @Override
    public JwtResponse verifyEMail(ConfirmationToken confirmationToken){

        JwtResponse response = null;

        if (confirmationTokenService.checkConfirmationToken(confirmationToken)){
            User user = userService.getUserById(confirmationToken.getUserId());
            user.setStatus(Status.VERIFIED);
            user.setUsername(user.getEMail());
            user = userService.updateUser(user);

            confirmationTokenService.deleteConfirmationTokenByUserId(user.getId());
            UserDetails userDetails = new UserPrincipal(user);
            AuthToken authToken = authenticationTokenService.createToken(userDetails, user.getId());

            response = new JwtResponse(authToken.getToken(), "Bearer", user.getUsername());
        }

        return response;
    }

}
