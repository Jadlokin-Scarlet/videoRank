package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.VideoData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoDataMapper extends VideoDataBaseMapper{

	List<VideoData> selectAll(Short issue, int limit, String type);

	Short getNewIssue();
}