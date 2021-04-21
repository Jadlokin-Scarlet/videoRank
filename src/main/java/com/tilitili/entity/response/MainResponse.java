package com.tilitili.entity.response;

import com.tilitili.common.entity.VideoData;
import lombok.Data;

import java.util.List;

@Data
public class MainResponse {
    private List<VideoData> rankList;
}
