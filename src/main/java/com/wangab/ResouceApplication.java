package com.wangab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class ResouceApplication extends ResourceServerConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger( ResouceApplication.class );
    @Bean
    public Docket testOAuthApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Files")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(regex("/files.*"))//过滤的接口
                .build()
                .apiInfo(new ApiInfo(
                        "Oauth2资源服务接口",
                        "Oauth2资源服务接口说明",
                        "1.0",
                        "NO terms of service",
                        new Contact("王安邦","", ""),
                        "westar project",
                        "https://github.com/Wangab"));
    }

    @Bean(name = "redisConnectionFactory")
    RedisConnectionFactory getRedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(ResouceApplication.class, args);
    }

}
