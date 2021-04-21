package com.tilitili.controller;

import com.tilitili.common.entity.VideoData;
import com.tilitili.common.entity.query.VideoDataQuery;
import com.tilitili.common.entity.view.BaseModel;
import com.tilitili.common.manager.VideoDataManager;
import com.tilitili.common.mapper.VideoTagRelationMapper;
import com.tilitili.common.utils.Asserts;
import com.tilitili.entity.response.PlayResponse;
import com.tilitili.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/play")
public class PlayController {
    private final PlayService playService;
    private final VideoDataManager videoDataManager;

    @Autowired
    public PlayController(PlayService playService, VideoDataManager videoDataManager) {
        this.playService = playService;
        this.videoDataManager = videoDataManager;
    }

    @GetMapping("")
    @ResponseBody
    public BaseModel getVideoData(VideoDataQuery videoDataQuery) {
        Asserts.notNull(videoDataQuery.getAv(), "av号");
        PlayResponse response = new PlayResponse();
        VideoData videoData = videoDataManager.getVideoNewDataByAv(videoDataQuery.getAv());
        response.setVideoData(videoData);
        response.setTagList(playService.listTagList(videoDataQuery.getAv()));
        response.setOwner(playService.getOwner(videoData.getOwner()));
        response.setRecommendList(playService.listRecommend());
        return BaseModel.success(response);
    }
}
