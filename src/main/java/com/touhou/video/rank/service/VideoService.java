package com.touhou.video.rank.service;

import com.touhou.video.rank.api.BilibiliApi;
import com.touhou.video.rank.entity.Video;
import com.touhou.video.rank.entity.VideoData;
import com.touhou.video.rank.mapper.VideoDataMapper;
import com.touhou.video.rank.mapper.VideoInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {
	private VideoDataMapper videoDataMapper;
	private VideoInfoService videoInfoService;
	private VideoTagService videoTagService;

	@Autowired
	public VideoService(VideoDataMapper videoDataMapper, VideoInfoMapper videoInfoMapper, VideoInfoService videoInfoService, BilibiliApi bilibiliApi, VideoTagService videoTagService, StringRedisTemplate redis) {
		this.videoDataMapper = videoDataMapper;
		this.videoInfoService = videoInfoService;
		this.videoTagService = videoTagService;
	}

	private Video getVideoByVideoData(VideoData videoData) {
		Video video = new Video();
		BeanUtils.copyProperties(videoData, video);
		BeanUtils.copyProperties(videoInfoService.selectByPrimaryKey(videoData.getAv()), video);
		return video.setTags(videoTagService.getTags(videoData.getAv()));
	}

	public List<Video> listTop(Short issue) {
		return videoDataMapper.selectTop(issue).stream()
				.map(this::getVideoByVideoData)
				.collect(Collectors.toList());
	}

	public Boolean deleteVideo(long av) {
		return videoInfoService.falseDeleteVideoByPrimaryKey(av);
	}

	public Boolean updateStartTime(long av, int startTime) {
		return videoInfoService.updateStartTimeByPrimaryKey(av, startTime);
	}
}
