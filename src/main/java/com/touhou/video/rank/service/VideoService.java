package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.*;
import com.touhou.video.rank.mapper.VideoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Slf4j
@Service
public class VideoService {
	private VideoDataService videoDataService;
	private VideoInfoService videoInfoService;
	private VideoTagService videoTagService;
	private TypeService typeService;
	private VideoPageService videoPageService;
	private OwnerService ownerService;
	private VideoMapper videoMapper;

	@Autowired
	public VideoService(VideoDataService videoDataService, VideoInfoService videoInfoService, VideoTagService videoTagService, TypeService typeService, VideoPageService videoPageService, OwnerService ownerService, VideoMapper videoMapper) {
		this.videoDataService = videoDataService;
		this.videoInfoService = videoInfoService;
		this.videoTagService = videoTagService;
		this.typeService = typeService;
		this.videoPageService = videoPageService;
		this.ownerService = ownerService;
		this.videoMapper = videoMapper;
	}

//	private Video getVideoByVideoData(VideoData videoData) {
//		VideoInfo videoInfo = videoInfoService.selectByPrimaryKey(videoData.getAv());
//		Assert.notNull(videoInfo, "video info no found");
//		Owner owner = ownerMapper.selectByOwnerName(videoInfo.getOwner());
//		List<String> tags = videoTagService.getTags(videoData.getAv());
//		List<VideoPage> pages = videoPageMapper.selectAllByAv(videoData.getAv());
//		return new Video(videoData)
//				.copyProperties(videoInfo)
//				.setFace(owner == null? null: owner.getFace())
//				.setTags(tags)
//				.setPages(pages);
//	}

	public Video getVideoByAv(long av, short issue){
		VideoData videoData = videoDataService.getVideoDataByAv(av, issue);
		VideoInfo videoInfo = videoInfoService.selectByPrimaryKey(av);
		Owner owner = ownerService.get(videoInfo.getOwner());
		Video video = new Video(videoData)
				.copyProperties(owner)
				.copyProperties(videoInfo);
		video = updatePageList(video);
		video = updateTagList(video);
		return video;
	}

	public Long getVideoRankByAv(Long av, short issue) {
		return videoDataService.getVideoDataByAv(av, issue).getRank();
	}

	public Stream<Video> listVideoTop100(Short issue) {
		return search(issue, 100, typeService.ALL(), "", "point").stream();
	}


	public List<Video> search(Short issue, int top, String typeName, String searchKey, String sortKey) {
		Type type = typeService.getTypeByName(typeName);
		return search(issue, top, type, searchKey, sortKey);
	}

	public List<Video> search(Short issue, int top, Type type, String searchKey, String sortKey) {
		List<Video> videoList;
		if (type.equals(typeService.ALL())) {
			videoList = videoMapper.searchAllType(issue, top, searchKey, sortKey);
		} else {
			List<Type> typeList = typeService.listByFatherType(type);
			videoList = videoMapper.search(issue, top, typeList, searchKey, sortKey);
		}
		return videoList.stream()
				.map(this::updateOwner)
				.map(this::updateTagList)
//				.map(this::getAndSetIsLen)
				.map(this::updatePageList)
				.collect(Collectors.toList());
	}

	private Video updateOwner(Video video) {
		if (video == null || StringUtils.isEmpty(video.getOwner())) {
			return video;
		}
		Owner owner = ownerService.get(video.getOwner());
		if (owner == null) {
			return video;
		}
		return video.setFace(owner.getFace());
	}

	private Video updatePageList(Video video) {
		return video.setPages(videoPageService.list(video.getAv()));
	}

	private Video updateTagList(Video video) {
		return video.setTags(videoTagService.getTags(video.getAv()));
	}


	public Boolean falseDeleteVideo(long av) {
		return videoInfoService.falseDeleteVideoByPrimaryKey(av);
	}

	public Boolean updateStartTime(long av, int startTime) {
		return videoInfoService.updateStartTimeByPrimaryKey(av, startTime);
	}

	public Video getAndSetHisRank(Video video) {
		return video.setHisRank(getVideoRankByAv(video.getAv(), (short) (video.getIssue() - 1)));
	}

	public Video getAndSetIsLen(Video video) {
		Long rank = video.getRank();
		Long hisRank = getVideoRankByAv(video.getAv(), (short) (video.getIssue() - 1));
		Long moreHisRank = getVideoRankByAv(video.getAv(), (short) (video.getIssue() - 2));
		return video.setIsLen(rank, hisRank, moreHisRank);
	}

	public Boolean recoveryVideo(long av) {
		return videoInfoService.recoveryVideoByPrimaryKey(av);
	}

	//	@Cacheable(value = "VideoService")
	public Short getNewIssue() {
		return videoDataService.getNewIssue();
	}

	Video changeToShortPubTime(Video video) {
		String shortPubTime = video.getPubTime().split(" ")[0];
		return video.setPubTime(shortPubTime);
	}

	@Transactional
	public Boolean deleteVideo(long av) {
		if (!videoInfoService.selectByPrimaryKey(av).getIsDelete()) {
			return false;
		}
		boolean result;
		result = videoInfoService.deleteVideo(av);
		result &= videoDataService.deleteVideo(av);
		return result;
	}


}
