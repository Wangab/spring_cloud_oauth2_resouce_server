package com.wangab;

import com.wangab.tokenservice.CassandraTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableResourceServer
@RestController
public class ResouceApplication extends ResourceServerConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger( ResouceApplication.class );

    @Resource(name = "redisConnectionFactory")
    private RedisConnectionFactory redisConnectionFactory;

    @Bean(name = "redisConnectionFactory")
    RedisConnectionFactory getRedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(ResouceApplication.class, args);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(new RedisTokenStore(redisConnectionFactory));
        resources.tokenServices(new CassandraTokenService());
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> getUser(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "whhyshsh");
        return map;

    }

}
