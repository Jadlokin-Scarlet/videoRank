package com.touhou.video.rank.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Owner implements Serializable {
    private Long uid;

    private String name;

    private String face;

    private Short vipType;

    private static final long serialVersionUID = 1L;
}