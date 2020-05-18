package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Type;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TypeMapper extends TypeBaseMapper{

    List<Type> selectAll();

}