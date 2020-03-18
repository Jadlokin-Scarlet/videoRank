package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.VideoInfo;
import org.springframework.stereotype.Component;

@Component
public interface VideoInfoMapper extends VideoInfoBaseMapper{

	int updateStartTimeByPrimaryKey(long av, int startTime);
	int updateIsDeleteByPrimaryKey(long av, boolean isDelete);
}