package com.touhou.video.rank.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
public class RestTemplateConfig extends CachingConfigurerSupport {
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration=RedisCacheConfiguration.defaultCacheConfig();
//				.entryTtl(Duration.ofDays(1)
//				);
		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
				.cacheDefaults(redisCacheConfiguration)
				.build();
	}

//	public Cache cache() {
//		return RedisCache
//	}


//	@Bean
//	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//		return new RedisCacheManager(
//				RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
//				this.getRedisCacheConfigurationWithTtl(24*60*60), // 默认策略，未配置的 key 会使用这个
//				this.getRedisCacheConfigurationMap() // 指定 key 策略
//		);
//	}

//	private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
//		Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
//		//DayCache和SecondsCache进行过期时间配置
//		redisCacheConfigurationMap.put("DayCache", this.getRedisCacheConfigurationWithTtl(24*60*60));
//		redisCacheConfigurationMap.put("SecondsCache", this.getRedisCacheConfigurationWithTtl(2));
//		return redisCacheConfigurationMap;
//	}
//
//	private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
//		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jackson2JsonRedisSerializer.setObjectMapper(om);
//
//		return RedisCacheConfiguration.defaultCacheConfig()
//				.serializeValuesWith(RedisSerializationContext
//						.SerializationPair
//						.fromSerializer(jackson2JsonRedisSerializer))
//				.entryTtl(Duration.ofSeconds(seconds));
//	}
	@Override
	@Bean
	@Primary
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			if(params==null){
				return null;
			}
			String join = Arrays.stream(params)
					.map(Object::toString)
					.collect(Collectors.joining(","));
			return String.format("%s.%s(%s)",
					target.getClass().getName(),
					method.getName(),
					join);
		};
	}
}