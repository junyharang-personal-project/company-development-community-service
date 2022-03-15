package com.devcommunity.junyharang.common.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS Error 관련 설정 Class
 * <pre>
 * <b>History:</b>
 *    주니하랑, 1.0.0, 2022.03.15 최초 작성
 * </pre>
 *
 * @author 주니하랑
 * @version 1.0.0, 2022.03.15 최초 작성
 * @See ""
 * @see <a href=""></a>
 */

@Configuration @Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        log.info("WebMvcConfigurer을 구현한 WebConfig의 addCorsMappings(CorsRegistry registry)가 호출 되었습니다!");
        log.info("지정된 Web Service의 CORS Error를 검토하고, 해결하겠습니다!");

        registry
                .addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST","DELETE");
    } // addCorsMappings(CorsRegistry registry) 끝
} // class 끝
