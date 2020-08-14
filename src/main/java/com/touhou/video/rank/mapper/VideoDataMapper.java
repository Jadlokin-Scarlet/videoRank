package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Type;
import com.touhou.video.rank.entity.VideoData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoDataMapper extends VideoDataBaseMapper{

	List<VideoData> selectAll(Short issue, int limit, @Param("typeList") List<Type> typeList);

	long selectRankByAvAndIssue(long av, short issue);

	Short getNewIssue();

	int deleteByPrimaryKey(long av);
}