package com.touhou.video.rank.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class Video implements Serializable {
	private Long av;
	private String name;
	private String img;
	private String type;
	private Boolean copyright;
	private String pubTime;
	private Long startTime;
	private String bv;
	private String description;
	private Long state;
	private Long attribute;
	private Long duration;
	private Long missionId;
	private String dynamic;

	private Short issue;
	private Long view;
	private Long reply;
	private Long favorite;
	private Long coin;
	private Long page;
	private Long point;
	private Long rank;
	private Long danmaku;
	private Long share;
	private Long like;
	private Long dislike;
	private String evaluation;

	private String owner;
	private String face;

	private List<String> tags;
	private List<VideoPage> pages;

	private Long hisRank;
	private Long isLen;

	private static final long serialVersionUID = 1L;

	public Video(Object o) {
		copyProperties(o);
	}

	public Video copyProperties(Object o) {
		if (o != null) {
			BeanUtils.copyProperties(o, this);
		}
		return this;
	}

	public Video setIsLen(long rank, long hisRank, long moreHisRank) {
		boolean isLen = rank > 0 && hisRank > 0 && moreHisRank > 0;
		isLen &= rank <= 30 && hisRank <= 30 && moreHisRank <=30;
		return setIsLen(isLen? 1L : 0L);
	}
}
