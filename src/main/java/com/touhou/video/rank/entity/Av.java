package com.touhou.video.rank.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Av implements Serializable {
    private Long av;

    private String bv;

    private static final long serialVersionUID = 1L;
}