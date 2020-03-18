package com.touhou.video.rank.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class BilibiliApi {

	private final RestTemplate restTemplate;
	private StringRedisTemplate redis;

	@Autowired
	public BilibiliApi(RestTemplate restTemplate, StringRedisTemplate redis) {
		this.restTemplate = restTemplate;
		this.redis = redis;
	}

	public JSONObject getData(Long av) {
		String key = av + "data";
		if (Objects.equals(redis.opsForValue().size(key), 0L)){
			String url = "https://api.bilibili.com/x/web-interface/view?aid=" + av;
			ResponseEntity<String> string = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
			redis.opsForValue().setIfAbsent(key, string.getBody());
		}
		return JSONObject.parseObject(redis.opsForValue().get(key));
	}

}
