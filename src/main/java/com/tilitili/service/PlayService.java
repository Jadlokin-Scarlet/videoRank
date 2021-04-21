package com.tilitili.service;

import com.tilitili.common.entity.Owner;
import com.tilitili.common.entity.VideoData;
import com.tilitili.common.entity.VideoInfo;
import com.tilitili.common.entity.VideoTagRelation;
import com.tilitili.common.entity.query.VideoDataQuery;
import com.tilitili.common.entity.query.VideoInfoQuery;
import com.tilitili.common.entity.query.VideoTagRelationQuery;
import com.tilitili.common.manager.VideoDataManager;
import com.tilitili.common.mapper.OwnerMapper;
import com.tilitili.common.mapper.VideoInfoMapper;
import com.tilitili.common.mapper.VideoTagRelationMapper;
import com.tilitili.common.utils.Asserts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayService {
    private final VideoTagRelationMapper videoTagRelationMapper;
    private final OwnerMapper ownerMapper;
    private final VideoDataManager videoDataManager;
    private final VideoInfoMapper videoInfoMapper;

    @Autowired
    public PlayService(VideoTagRelationMapper videoTagRelationMapper, OwnerMapper ownerMapper, VideoDataManager videoDataManager, VideoInfoMapper videoInfoMapper) {
        this.videoTagRelationMapper = videoTagRelationMapper;
        this.ownerMapper = ownerMapper;
        this.videoDataManager = videoDataManager;
        this.videoInfoMapper = videoInfoMapper;
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
        List<VideoInfo> randomVideoInfoList = videoInfoMapper.random(new VideoInfoQuery().setPageSize(10));
        return randomVideoInfoList.parallelStream().map(videoInfo -> {
            VideoData videoData = videoDataManager.getVideoNewDataByAvOrDefault(videoInfo.getAv());
            BeanUtils.copyProperties(videoInfo, videoData);
            return videoData;
        }).collect(Collectors.toList());
    }
}
