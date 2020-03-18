package com.touhou.video.rank.entity;

import lombok.*;
import lombok.experimental.Accessors;

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

	private Short issue;

	private Long view;

	private Long reply;

	private Long favorite;

	private Long coin;

	private Long page;

	private Long point;

	private Long rank;

	private List<String> tags;

}
