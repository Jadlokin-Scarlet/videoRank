package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.VideoInfo;

public interface VideoInfoBaseMapper {
    int deleteByPrimaryKey(Long av);

    int insert(VideoInfo record);

    int insertSelective(VideoInfo record);

    VideoInfo selectByPrimaryKey(Long av);

    int updateByPrimaryKeySelective(VideoInfo record);

    int updateByPrimaryKey(VideoInfo record);
}