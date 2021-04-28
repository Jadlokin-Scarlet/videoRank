package com.tilitili.service;

import com.tilitili.common.entity.Recommend;
import com.tilitili.common.entity.VideoData;
import com.tilitili.common.entity.VideoInfo;
import com.tilitili.common.entity.query.RecommendQuery;
import com.tilitili.common.entity.query.VideoDataQuery;
import com.tilitili.common.entity.query.VideoInfoQuery;
import com.tilitili.common.manager.VideoDataManager;
import com.tilitili.common.mapper.RecommendMapper;
import com.tilitili.common.mapper.VideoInfoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final VideoDataManager videoDataManager;
    private final VideoInfoMapper videoInfoMapper;
    private final RecommendMapper recommendMapper;

    @Autowired
    public MainService(VideoDataManager videoDataManager, VideoInfoMapper videoInfoMapper, RecommendMapper recommendMapper) {
        this.videoDataManager = videoDataManager;
        this.videoInfoMapper = videoInfoMapper;
        this.recommendMapper = recommendMapper;
    }

    public List<VideoData> getRankList() {
        Integer newIssue = videoDataManager.getNewIssue();
        VideoDataQuery query = new VideoDataQuery().setIssue(newIssue).setStatus(0).setIsDelete(false).setSorter("point", "desc").setStart(0).setPageSize(10);
        return videoDataManager.list(query);
    }

    public List<VideoData> getRecommendList() {
        ArrayList<VideoData> videoDataList = new ArrayList<>();
        // 上榜
        Integer newIssue = videoDataManager.getNewIssue();
        VideoDataQuery query = new VideoDataQuery().setIssue(newIssue).setPageSize(2);
        List<VideoData> rankList = videoDataManager.randomRanked(query);
        videoDataList.addAll(rankList);
        // 随机
        List<VideoInfo> randomVideoInfoList = videoInfoMapper.random(new VideoInfoQuery().setPageSize(4));
        List<VideoData> randomList = randomVideoInfoList.parallelStream().map(videoInfo -> {
            VideoData videoData = videoDataManager.getVideoNewDataByAvOrDefault(videoInfo.getAv());
            BeanUtils.copyProperties(videoInfo, videoData);
            return videoData;
        }).collect(Collectors.toList());
        videoDataList.addAll(randomList);
        //推荐
        List<Recommend> recommendList = recommendMapper.random(new RecommendQuery().setPageSize(2));
        List<VideoData> recommendVideoList = recommendList.parallelStream().map(Recommend::getAv).map(videoDataManager::getVideoNewDataByAv).collect(Collectors.toList());
        videoDataList.addAll(recommendVideoList);

        videoDataList.sort((a, b) -> Math.toIntExact(a.getAv() - b.getAv()));

        return videoDataList;
    }

}
