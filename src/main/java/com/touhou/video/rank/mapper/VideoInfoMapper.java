package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Video;
import com.touhou.video.rank.entity.VideoInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoInfoMapper extends VideoInfoBaseMapper{

	int updateStartTimeByPrimaryKey(long av, int startTime);
	int updateIsDeleteByPrimaryKey(long av, boolean isDelete);

	List<VideoInfo> selectAll(boolean isDelete);
}