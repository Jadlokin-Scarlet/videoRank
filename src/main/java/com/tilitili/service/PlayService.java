package com.tilitili.service;

import com.tilitili.common.entity.*;
import com.tilitili.common.entity.query.RecommendQuery;
import com.tilitili.common.entity.query.VideoDataQuery;
import com.tilitili.common.entity.query.VideoInfoQuery;
import com.tilitili.common.entity.query.VideoTagRelationQuery;
import com.tilitili.common.manager.VideoDataManager;
import com.tilitili.common.mapper.OwnerMapper;
import com.tilitili.common.mapper.RecommendMapper;
import com.tilitili.common.mapper.VideoInfoMapper;
import com.tilitili.common.mapper.VideoTagRelationMapper;
import com.tilitili.common.utils.Asserts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayService {
    private final VideoTagRelationMapper videoTagRelationMapper;
    private final OwnerMapper ownerMapper;
    private final VideoDataManager videoDataManager;
    private final VideoInfoMapper videoInfoMapper;
    private final RecommendMapper recommendMapper;

    @Autowired
    public PlayService(VideoTagRelationMapper videoTagRelationMapper, OwnerMapper ownerMapper, VideoDataManager videoDataManager, VideoInfoMapper videoInfoMapper, RecommendMapper recommendMapper) {
        this.videoTagRelationMapper = videoTagRelationMapper;
        this.ownerMapper = ownerMapper;
        this.videoDataManager = videoDataManager;
        this.videoInfoMapper = videoInfoMapper;
        this.recommendMapper = recommendMapper;
    }

    public List<String> listTagList(Long av) {
        List<VideoTagRelation> tagRelationList = videoTagRelationMapper.list(new VideoTagRelationQuery().setAv(av));
        return tagRelationList.parallelStream().map(VideoTagRelation::getTagName).collect(Collectors.toList());
    }

    public Owner getOwner(String ownerName) {
        Owner owner = ownerMapper.getByName(ownerName);
        Asserts.notNull(owner, "作者");
        return owner;
    }

    public List<VideoData> listRecommend() {
        ArrayList<VideoData> videoDataList = new ArrayList<>();
        // 上榜
        Integer newIssue = videoDataManager.getNewIssue();
        VideoDataQuery query = new VideoDataQuery().setIssue(newIssue).setPageSize(3);
        List<VideoData> rankList = videoDataManager.randomRanked(query);
        videoDataList.addAll(rankList);
        // 随机
        List<VideoInfo> randomVideoInfoList = videoInfoMapper.random(new VideoInfoQuery().setPageSize(5));
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
