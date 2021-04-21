package com.tilitili.service;

import com.tilitili.common.entity.VideoData;
import com.tilitili.common.entity.query.VideoDataQuery;
import com.tilitili.common.manager.VideoDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {
    private final VideoDataManager videoDataManager;

    @Autowired
    public RankService(VideoDataManager videoDataManager) {
        this.videoDataManager = videoDataManager;
    }

    public List<VideoData> getVideoRank(VideoDataQuery videoDataQuery) {
        Integer newIssue = videoDataManager.getNewIssue();
        videoDataQuery.setIssue(newIssue).setHasRank(true).setSorter("point", "desc").setPageSize(100);
        return videoDataManager.list(videoDataQuery);
    }
}
