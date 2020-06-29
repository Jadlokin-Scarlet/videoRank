package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Av;

public interface AvBaseMapper {
    int insert(Av record);

    int insertSelective(Av record);
}