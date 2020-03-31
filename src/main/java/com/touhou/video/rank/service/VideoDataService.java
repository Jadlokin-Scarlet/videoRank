package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.VideoData;
import com.touhou.video.rank.mapper.VideoDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.BitSet;
import java.util.List;
import java.util.stream.Stream;

@Service
public class VideoDataService {

	private final VideoDataMapper videoDataMapper;

	@Autowired
	public VideoDataService(VideoDataMapper videoDataMapper) {
		this.videoDataMapper = videoDataMapper;
	}


	public VideoData getVideoDataByAv(long av, short issue) {
		VideoData videoData = videoDataMapper.selectByPrimaryKey(av, issue);
		if (videoData == null) {
			insertEmptyVideoData(av, issue);
			videoData = videoDataMapper.selectByPrimaryKey(av, issue);
		}
		return videoData;
	}

	private void insertEmptyVideoData(long av, short issue) {
		VideoData emptyVideoData = getEmptyVideoData(av, issue);
		videoDataMapper.insertSelective(emptyVideoData);
	}

	private VideoData getEmptyVideoData(long av, short issue) {
		return new VideoData().setAv(av).setIssue(issue);
	}

	public Stream<VideoData> selectAll(Short issue, int limit) {
		return videoDataMapper.selectAll(issue, limit).stream();
	}
}
