package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.VideoData;
import com.touhou.video.rank.mapper.VideoDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class VideoDataService {

	private final VideoDataMapper videoDataMapper;
	private TypeService typeService;

	@Autowired
	public VideoDataService(VideoDataMapper videoDataMapper, TypeService typeService) {
		this.videoDataMapper = videoDataMapper;
		this.typeService = typeService;
	}


	public VideoData getVideoDataByAv(long av, short issue) {
		VideoData videoData = videoDataMapper.selectByPrimaryKey(av, issue);
		if (videoData == null) {
			insertEmptyVideoData(av, issue);
			videoData = videoDataMapper.selectByPrimaryKey(av, issue);
		}
		return videoData;
	}

	public Stream<VideoData> selectAll(Short issue, int limit, String type) {
		List<VideoData> videoData = videoDataMapper
				.selectAll(issue, limit, typeService.listByFatherType(type));
		return updateRank(videoData);
	}

	private void insertEmptyVideoData(long av, short issue) {
		VideoData emptyVideoData = getEmptyVideoData(av, issue);
		videoDataMapper.insertSelective(emptyVideoData);
	}

	private VideoData getEmptyVideoData(long av, short issue) {
		return new VideoData().setAv(av).setIssue(issue);
	}


	public Short getNewIssue() {
		return videoDataMapper.getNewIssue();
	}

	private Stream<VideoData> updateRank(List<VideoData> videoData) {
		return IntStream.range(0, videoData.size())
				.mapToObj(index -> videoData.get(index).setRank(index + 1L));
	}

	public boolean deleteVideo(long av) {
		return videoDataMapper.deleteByPrimaryKey(av) > 0;
	}
}
