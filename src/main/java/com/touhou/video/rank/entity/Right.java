package com.touhou.video.rank.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Right implements Serializable {
    private Long av;

    private Short bp;

    private Short elec;

    private Short download;

    private Short movie;

    private Short pay;

    private Short hd5;

    private Short noReprint;

    private Short autoplay;

    private Short ugcPay;

    private Short isCooperation;

    private Short ugcPayPreview;

    private Short noBackground;

    private static final long serialVersionUID = 1L;
}