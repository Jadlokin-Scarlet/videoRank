package com.touhou.video.rank.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface VideoTagMapper extends VideoTagBaseMapper {
	Map<String, String> selectForTagsByPrimaryKey(@Param("av") long av);
}
