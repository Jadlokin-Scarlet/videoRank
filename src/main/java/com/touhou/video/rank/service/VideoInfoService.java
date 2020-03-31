package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.Video;
import com.touhou.video.rank.entity.VideoInfo;
import com.touhou.video.rank.mapper.VideoInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VideoInfoService {
	private VideoInfoMapper videoInfoMapper;

	@Autowired
	public VideoInfoService(VideoInfoMapper videoInfoMapper) {
		this.videoInfoMapper = videoInfoMapper;
	}

	@Cacheable(cacheNames = "videoInfo", key = "#av")
	public VideoInfo selectByPrimaryKey(Long av) {
		VideoInfo videoInfo = videoInfoMapper.selectByPrimaryKey(av);
		return changeDateFormat(videoInfo);
	}

	public Stream<VideoInfo> listVideoInfoThatDeleted() {
		return listVideoInfo(	true);
	}

	private Stream<VideoInfo> listVideoInfo(boolean isDelete) {
		return videoInfoMapper.selectAll(isDelete).stream()
				.map(this::changeDateFormat);
	}

	private VideoInfo changeDateFormat(VideoInfo videoInfo) {
		String pubTime = videoInfo.getPubTime();
		return videoInfo.setPubTime(pubTime.split(" ")[0]);
	}

	@CacheEvict(value = "videoInfo", key = "#av")
	public boolean updateStartTimeByPrimaryKey(long av, int startTime) {
		return videoInfoMapper.updateStartTimeByPrimaryKey(av, startTime) == 1;
	}
	@CacheEvict(value = "videoInfo", key = "#av")
	public boolean falseDeleteVideoByPrimaryKey(long av) {
		return videoInfoMapper.updateIsDeleteByPrimaryKey(av, true) == 1;
	}

	public Boolean recoveryVideoByPrimaryKey(long av) {
		return videoInfoMapper.updateIsDeleteByPrimaryKey(av, false) == 1;
	}
}
