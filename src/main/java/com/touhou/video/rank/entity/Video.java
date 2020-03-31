package com.touhou.video.rank.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class Video {
	private Long av;
	private String name;
	private String img;
	private String type;
	private String owner;
	private Boolean copyright;
	private String pubTime;
	private Long startTime;
	private String bv;

	private Short issue;
	private Long view;
	private Long reply;
	private Long favorite;
	private Long coin;
	private Long page;
	private Long point;
	private Long rank;

	private List<String> tags;

	private Long hisRank;
	private Long isLen;

	public Video(Object o) {
		copyProperties(o);
	}

	public Video copyProperties(Object o) {
		BeanUtils.copyProperties(o, this);
		return this;
	}

	public Video setIsLen(long rank, long hisRank, long moreHisRank) {
		boolean isLen = rank > 0 && hisRank > 0 && moreHisRank > 0;
		isLen &= rank <= 30 && hisRank <= 30 && moreHisRank <=30;
		return setIsLen(isLen? 1L : 0L);
	}
}
