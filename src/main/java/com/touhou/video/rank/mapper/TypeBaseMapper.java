package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Type;

public interface TypeBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}