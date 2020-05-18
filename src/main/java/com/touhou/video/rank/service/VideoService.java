package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.Type;
import com.touhou.video.rank.entity.Video;
import com.touhou.video.rank.entity.VideoData;
import com.touhou.video.rank.entity.VideoInfo;
import com.touhou.video.rank.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

@Service
public class VideoService {
	private VideoDataService videoDataService;
	private VideoInfoService videoInfoService;
	private VideoTagService videoTagService;
	private TypeService typeService;
	private VideoMapper videoMapper;

	@Autowired
	public VideoService(VideoDataService videoDataService, VideoInfoService videoInfoService, VideoTagService videoTagService, TypeService typeService, VideoMapper videoMapper) {
		this.videoDataService = videoDataService;
		this.videoInfoService = videoInfoService;
		this.videoTagService = videoTagService;
		this.typeService = typeService;
		this.videoMapper = videoMapper;
	}

	private Video getVideoByVideoData(VideoData videoData) {
		VideoInfo videoInfo = videoInfoService.selectByPrimaryKey(videoData.getAv());
		List<String> tags = videoTagService.getTags(videoData.getAv());
		return new Video(videoData)
				.copyProperties(videoInfo)
				.setTags(tags);
	}

	public Video getVideoByAv(long av, short issue){
		VideoData videoData = videoDataService.getVideoDataByAv(av, issue);
		return getVideoByVideoData(videoData);
	}

	public Long getVideoRankByAv(Long av, short issue) {
		return getVideoByAv(av, issue).getRank();
	}

	public Stream<Video> listVideoTop30(Short issue) {
		return listVideo(issue, 30);
	}

	public Stream<Video> listVideoTop100(Short issue) {
		return listVideo(issue, 100);
	}

	public Stream<Video> listVideo(Short issue, int limit) {
		Type allType = typeService.getFirstType();
		return listVideo(issue, limit, allType.getName());
	}

	public Stream<Video> listVideo(Short issue, int limit, String type) {
		return videoDataService.selectAll(issue, limit, type)
				.map(this::getVideoByVideoData);

	}

	public List<Video> search(Short issue, int top, List<Type> typeList, String searchKey, String sortKey) {
		return videoMapper.list(issue, top, typeList, searchKey, sortKey);
	}

	public List<Video> search(Short issue, int top, String type, String searchKey, String sortKey) {
		List<Type> typeList = typeService.listByFatherType(type);
		return search(issue, top, typeList, searchKey, sortKey);
	}

	public List<Video> search(Short issue, int top, Type type, String searchKey, String sortKey) {
		return search(issue, top, type.getName(), searchKey, sortKey);
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
