package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Owner;

public interface OwnerBaseMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(Owner record);

    int insertSelective(Owner record);

    Owner selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(Owner record);

    int updateByPrimaryKey(Owner record);
}