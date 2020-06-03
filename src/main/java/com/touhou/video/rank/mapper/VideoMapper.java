package com.touhou.video.rank.mapper;

import com.touhou.video.rank.entity.Type;
import com.touhou.video.rank.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoMapper {

	List<Video> search(
			@Param("issue") short issue,
			@Param("top") int top,
			@Param("typeList") List<Type> typeList,
			@Param("searchKey") String searchKey,
			@Param("sortKey") String sortKey
	);

}
