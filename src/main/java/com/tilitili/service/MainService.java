package com.tilitili.service;

import com.tilitili.common.entity.VideoData;
import com.tilitili.common.entity.VideoInfo;
import com.tilitili.common.entity.query.VideoDataQuery;
import com.tilitili.common.entity.query.VideoInfoQuery;
import com.tilitili.common.manager.VideoDataManager;
import com.tilitili.common.mapper.VideoInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final VideoDataManager videoDataManager;
    private final VideoInfoMapper videoInfoMapper;

    @Autowired
    public MainService(VideoDataManager videoDataManager, VideoInfoMapper videoInfoMapper) {
        this.videoDataManager = videoDataManager;
        this.videoInfoMapper = videoInfoMapper;
    }

    public List<VideoData> getRankList() {
        Integer newIssue = videoDataManager.getNewIssue();
        VideoDataQuery query = new VideoDataQuery().setIssue(newIssue).setStatus(0).setIsDelete(false).setSorter("point", "desc").setStart(0).setPageSize(10);
        return videoDataManager.list(query);
    }

    public List<VideoData> getRecommendList() {
        List<VideoInfo> randomVideoInfoList = videoInfoMapper.random(new VideoInfoQuery().setPageSize(8));
        return randomVideoInfoList.parallelStream().map(videoInfo -> {
            VideoData videoData = videoDataManager.getVideoNewDataByAvOrDefault(videoInfo.getAv());
            BeanUtils.copyProperties(videoInfo, videoData);
            return videoData;
        }).collect(Collectors.toList());
    }

}
