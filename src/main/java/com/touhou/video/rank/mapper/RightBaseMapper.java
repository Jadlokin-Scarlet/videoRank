package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Right;

public interface RightBaseMapper {
    int deleteByPrimaryKey(Long av);

    int insert(Right record);

    int insertSelective(Right record);

    Right selectByPrimaryKey(Long av);

    int updateByPrimaryKeySelective(Right record);

    int updateByPrimaryKey(Right record);
}