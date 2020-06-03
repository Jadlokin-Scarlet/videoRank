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
	private TypeService typeService;

	@Autowired
	public VideoInfoService(VideoInfoMapper videoInfoMapper, TypeService typeService) {
		this.videoInfoMapper = videoInfoMapper;
		this.typeService = typeService;
	}

	@Cacheable(cacheNames = "videoInfo", key = "#av")
	public VideoInfo selectByPrimaryKey(Long av) {
		return videoInfoMapper.selectByPrimaryKey(av);
//		return changeDateFormat(videoInfo);
	}

	public Stream<VideoInfo> listVideoInfoThatDeleted() {
		return listVideoInfo(	true);
	}

	private Stream<VideoInfo> listVideoInfo(boolean isDelete) {
		return videoInfoMapper.selectAll(isDelete).stream();
	}

	public Stream<VideoInfo> listVideoInfoRandom(String type, int number) {
		return videoInfoMapper.listVideoInfoRandom(typeService.listByFatherType(type), number).stream();
	}

	@CacheEvict(cacheNames = "videoInfo", key = "#av")
	public boolean updateStartTimeByPrimaryKey(long av, int startTime) {
		return videoInfoMapper.updateStartTimeByPrimaryKey(av, startTime) == 1;
	}
	@CacheEvict(cacheNames = "videoInfo", key = "#av")
	public boolean falseDeleteVideoByPrimaryKey(long av) {
		return videoInfoMapper.updateIsDeleteByPrimaryKey(av, true) == 1;
	}

	@CacheEvict(cacheNames = "videoInfo", key = "#av")
	public Boolean recoveryVideoByPrimaryKey(long av) {
		return videoInfoMapper.updateIsDeleteByPrimaryKey(av, false) == 1;
	}

	@CacheEvict(cacheNames = "videoInfo", key = "#av")
	public Boolean deleteVideo(long av) {
		return videoInfoMapper.deleteByPrimaryKey(av) == 1;
	}
}
