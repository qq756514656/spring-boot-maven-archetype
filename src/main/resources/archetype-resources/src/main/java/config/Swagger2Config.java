package ${package}.config;


import ${package}.util.Constants;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author liusy
 */
@Slf4j
@EnableSwagger2
@Configuration
public class Swagger2Config {
#[[
    @Value("${swagger.config.apiInfoTitle:CODE API 文档}")
    private String apiInfoTitle = "CODE API 文档";
    @Value("${swagger.config.termsOfServiceUrl}")
    private String termsOfServiceUrl = "http://www.demo.com.cn";
    @Value("${swagger.config.apiVersion:1.0}")
    private String apiVersion = "1.0";
    @Value("${swagger.config.enable:true}")
    private Boolean enable = true;
    @Value("${swagger.config.isMobile:false}")
    private Boolean isMobile = false;
    @Value("${spring.profiles.active:dev}")
    private String profiles = "dev";
]]#
    private final List<ResponseMessage> globalResponses = Arrays.asList(
            new ResponseMessageBuilder().code(200).message("Success").build(),
            new ResponseMessageBuilder().code(400).message("Bad request").build(),
            new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
            new ResponseMessageBuilder().code(403).message("Forbidden").build(),
            new ResponseMessageBuilder().code(404).message("Not found").build(),
            new ResponseMessageBuilder().code(500).message("Internal server error").build());

    @Bean
    public Docket createRestApi() {
        log.info("------------->spring.profiles.active:{}", profiles);
        if ("test".equals(profiles)) {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                    .paths(PathSelectors.any())
                    .build()
                    .useDefaultResponseMessages(false)
                    .globalResponseMessage(RequestMethod.GET, globalResponses)
                    .globalResponseMessage(RequestMethod.POST, globalResponses)
                    .globalResponseMessage(RequestMethod.DELETE, globalResponses)
                    .globalResponseMessage(RequestMethod.PUT, globalResponses)
                    .globalResponseMessage(RequestMethod.PATCH, globalResponses)
                    .directModelSubstitute(Temporal.class, String.class)
                    .securitySchemes(apiKeys())
                    .securityContexts(securityContext())
                    .enable(enable);
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalResponses)
                .globalResponseMessage(RequestMethod.POST, globalResponses)
                .globalResponseMessage(RequestMethod.DELETE, globalResponses)
                .globalResponseMessage(RequestMethod.PUT, globalResponses)
                .globalResponseMessage(RequestMethod.PATCH, globalResponses)
                .directModelSubstitute(Temporal.class, String.class)
                .securitySchemes(apiKeys())
                .securityContexts(securityContext())
                .enable(enable);
    }

    private List<ApiKey> apiKeys() {
        if (isMobile) {
            return Arrays.asList(
                    new ApiKey("Mobile Token from header", Constants.MOBILE_AUTHORIZATION_TOKEN, In.HEADER.name()),
                    new ApiKey("Mobile Request-Id from header", Constants.MOBILE_REQUEST_ID, In.HEADER.name())
            );
        }
        return Arrays.asList(
                new ApiKey("Token from header", Constants.AUTHORIZATION_TOKEN, In.HEADER.name()),
                new ApiKey("Request-Id from header", Constants.REQUEST_ID, In.HEADER.name())
        );
    }

    private List<SecurityContext> securityContext() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.any())
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        if (isMobile) {
            AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
            return Arrays.asList(new SecurityReference("Mobile Token from header", authorizationScopes),
                    new SecurityReference("Mobile Request-Id from header", authorizationScopes));
        }
        AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
        return Arrays.asList(new SecurityReference("Token from header", authorizationScopes),
                new SecurityReference("Request-Id from header", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiInfoTitle)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact("test", termsOfServiceUrl, "demo@email.com.cn"))
                .version(apiVersion)
                .build();
    }
}
