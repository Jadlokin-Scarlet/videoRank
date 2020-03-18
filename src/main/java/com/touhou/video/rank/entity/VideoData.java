package com.touhou.video.rank.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class VideoData implements Serializable {
    private Long av;

    private Short issue;

    private Long view;

    private Long reply;

    private Long favorite;

    private Long coin;

    private Long page;

    private Long point;

    private Long rank;

    private static final long serialVersionUID = 1L;
}