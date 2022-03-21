package com.devcommunity.junyharang.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * API 문서 관리 Swagger 설정
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.09 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.09 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@EnableSwagger2 @EnableWebMvc @Configuration public class SwaggerConfig {

    @Bean public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(this.apiInfo());

    } // api() 끝

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("JUNY Company")
                .version("1.0.0")
                .description(
                        "<h1> 주니하랑 개인 프로젝트!! </h1> "
                ).build();

    } // apiInfo() 끝

} // class 끝
