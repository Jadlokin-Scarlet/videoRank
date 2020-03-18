package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.VideoTag;

public interface VideoTagBaseMapper {
    int deleteByPrimaryKey(Long av);

    int insert(VideoTag record);

    int insertSelective(VideoTag record);

    VideoTag selectByPrimaryKey(Long av);

    int updateByPrimaryKeySelective(VideoTag record);

    int updateByPrimaryKey(VideoTag record);
}