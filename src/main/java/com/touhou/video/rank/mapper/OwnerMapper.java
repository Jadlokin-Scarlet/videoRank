package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Owner;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface OwnerMapper extends OwnerBaseMapper{

    @Select("select * from owner where name = #{name}")
    Owner selectByOwnerName(String name);

}