package com.quiz.security.config.swagger;

import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.RequestHandlerSelectors;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.*;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket v2tDocumentationPlugin() {
        return new VersionedDocket("2.0");
    }

    @Bean
    public Docket v10DocumentationPlugin() {
        return new VersionedDocket("1.0");
    }

    static class VersionedDocket extends Docket {
        public VersionedDocket(String version) {
            super(DocumentationType.SWAGGER_2);
            super.groupName(version)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.quiz.rest.controllers"))
                    .paths(PathSelectors.any())
                    .build()
                    .securitySchemes(Lists.newArrayList(apiKey()))
                    .securityContexts(Lists.newArrayList(securityContext()))
                    .apiInfo(apiInfo(version))
                    .useDefaultResponseMessages(true)
                    .enableUrlTemplating(true);
        }

        private ApiInfo apiInfo(String version) {
            return new ApiInfo(
                    "Online Quiz",
                    null,
                    version,
                    null,
                    null,
                    null,
                    null,
                    Collections.emptyList()
            );
        }

        @Bean
        public SecurityContext securityContext() {
            return SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.any())
                    .build();
        }

        List<SecurityReference> defaultAuth() {
            AuthorizationScope authorizationScope
                    = new AuthorizationScope("global", "accessEverything");
            AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
            authorizationScopes[0] = authorizationScope;
            return Lists.newArrayList(
                    new SecurityReference("JWT", authorizationScopes));
        }

        private ApiKey apiKey() {
            return new ApiKey("JWT", "Authorization", "Header");
        }
    }
/*
    static class BasePathAwareRelativePathProvider extends AbstractPathProvider {

        private final String basePath;

        public BasePathAwareRelativePathProvider(String basePath) {
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return basePath;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
        }
    }*/

    @Bean
    String string(){
        return "";
    }

}