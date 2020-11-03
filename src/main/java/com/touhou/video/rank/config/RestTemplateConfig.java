package com.touhou.video.rank.config;


import org.springframework.cache.annotation.CachingConfigurerSupport;

//@Configuration
//@EnableCaching
public class RestTemplateConfig extends CachingConfigurerSupport {

//	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//		RedisCacheConfiguration redisCacheConfiguration=RedisCacheConfiguration.defaultCacheConfig();
////				.entryTtl(Duration.ofDays(1)
////				);
//		return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
//				.cacheDefaults(redisCacheConfiguration)
//				.build();
//	}

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
//	@Override
//	@Bean
//	@Primary
//	public KeyGenerator keyGenerator() {
//		return (target, method, params) -> {
//			if(params==null){
//				return null;
//			}
//			String join = Arrays.stream(params)
//					.map(Object::toString)
//					.collect(Collectors.joining(","));
//			return String.format("%s.%s(%s)",
//					target.getClass().getName(),
//					method.getName(),
//					join);
//		};
//	}
}