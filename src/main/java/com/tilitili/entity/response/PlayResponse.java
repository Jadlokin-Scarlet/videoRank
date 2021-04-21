package com.tilitili.entity.response;

import com.tilitili.common.entity.Owner;
import com.tilitili.common.entity.VideoData;
import lombok.Data;

import java.util.List;

@Data
public class PlayResponse {
    VideoData videoData;
    List<String> tagList;
    Owner owner;
    List<VideoData> recommendList;
}
