package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.VideoData;
import org.apache.ibatis.annotations.Param;

public interface VideoDataBaseMapper {
    int deleteByPrimaryKey(@Param("av") Long av, @Param("issue") Short issue);

    int insert(VideoData record);

    int insertSelective(VideoData record);

    VideoData selectByPrimaryKey(@Param("av") Long av, @Param("issue") Short issue);

    int updateByPrimaryKeySelective(VideoData record);

    int updateByPrimaryKey(VideoData record);
}