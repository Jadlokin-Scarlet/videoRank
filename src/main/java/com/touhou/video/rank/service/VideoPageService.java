package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.VideoPage;
import com.touhou.video.rank.mapper.VideoPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoPageService {

	private VideoPageMapper videoPageMapper;

	@Autowired
	public VideoPageService(VideoPageMapper videoPageMapper) {
		this.videoPageMapper = videoPageMapper;
	}

	// @Cacheable(cacheNames = "videoPage")
	public List<VideoPage> list(long av) {
		return videoPageMapper.selectAllByAv(av);
	}
}
