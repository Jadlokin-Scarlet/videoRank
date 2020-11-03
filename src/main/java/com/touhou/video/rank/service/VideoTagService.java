package com.touhou.video.rank.service;

import com.touhou.video.rank.mapper.VideoTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class VideoTagService {
	private VideoTagMapper videoTagMapper;

	@Autowired
	public VideoTagService(VideoTagMapper videoTagMapper) {
		this.videoTagMapper = videoTagMapper;
	}

	// @Cacheable(cacheNames = "videoTag", key = "#av")
	public List<String> getTags(long av) {
		Map<String, String> map = videoTagMapper.selectForTagsByPrimaryKey(av);
		if (map == null) {
			return Collections.emptyList();
		}
		return IntStream.range(1, 20)
				.mapToObj(i -> "tag" + i)
				.map(map::get)
				.filter(tag -> tag != null && !tag.isEmpty())
				.collect(Collectors.toList());
	}
}
