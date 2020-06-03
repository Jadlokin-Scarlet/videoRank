package com.touhou.video.rank.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class VideoInfo implements Serializable {
    private Long av;

    private String name;

    private String img;

    private String type;

    private String owner;

    private Boolean copyright;

    private String pubTime;

    private Long startTime;

    private Boolean isDelete;

    private String bv;

    private String description;

    private Long state;

    private Long attribute;

    private Long duration;

    private Long missionId;

    private String dynamic;

    private static final long serialVersionUID = 1L;
}