package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.VideoPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoPageMapper extends VideoPageBaseMapper{

    @Select("select * from page where av = #{av}")
    List<VideoPage> selectAllByAv(long av);

}