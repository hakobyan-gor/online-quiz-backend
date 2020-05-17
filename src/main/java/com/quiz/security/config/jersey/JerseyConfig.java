package com.quiz.security.config.jersey;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import io.swagger.jaxrs.config.BeanConfig;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import com.quiz.rest.controllers.*;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    @Value("${base.path}")
    private String apiPath;

    public JerseyConfig() {
        this.registerEndpoints();
    }

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void registerEndpoints() {
        this.register(AnswerController.class);
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-docker-example");
        config.setVersion("v1");
        config.setContact("Gor Hakobyan");
        config.setSchemes(new String[]{"http", "https"});
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.quiz.rest.controllers");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}