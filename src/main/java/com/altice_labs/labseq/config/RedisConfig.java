package com.altice_labs.labseq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Slf4j
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Integer, Integer> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Integer, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new GenericToStringSerializer<>(Integer.class));
        template.setValueSerializer(new GenericToStringSerializer<>(Integer.class));

        template.setHashKeySerializer(new GenericToStringSerializer<>(Integer.class));
        template.setHashValueSerializer(new GenericToStringSerializer<>(Integer.class));

        return template;
    }
}