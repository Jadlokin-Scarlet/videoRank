package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.Video;
import com.touhou.video.rank.entity.VideoData;
import com.touhou.video.rank.entity.VideoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class VideoService {
	private VideoDataService videoDataService;
	private VideoInfoService videoInfoService;
	private VideoTagService videoTagService;

	@Autowired
	public VideoService(VideoDataService videoDataService, VideoInfoService videoInfoService, VideoTagService videoTagService) {
		this.videoDataService = videoDataService;
		this.videoInfoService = videoInfoService;
		this.videoTagService = videoTagService;
	}

	private Video getVideoByVideoData(VideoData videoData) {
		VideoInfo videoInfo = videoInfoService.selectByPrimaryKey(videoData.getAv());
		List<String> tags = videoTagService.getTags(videoData.getAv());
		return new Video(videoData)
				.copyProperties(videoInfo)
				.setTags(tags);
	}
	private Video getVideoByVideoInfo(VideoInfo videoInfo) {
		return new Video(videoInfo);
	}

	public Video getVideoByAv(long av, short issue){
		VideoData videoData = videoDataService.getVideoDataByAv(av, issue);
		return getVideoByVideoData(videoData);
	}

	public Long getVideoRankByAv(Long av, short issue) {
		return getVideoByAv(av, issue).getRank();
	}

	public Stream<Video> listVideoTop(Short issue) {
		return listVideo(issue, 30);
	}

	public Stream<Video> listVideo(Short issue, int limit) {
		return videoDataService.selectAll(issue, limit)
				.map(this::getVideoByVideoData);
	}

	public Stream<Video> listVideoThatDeleted() {
		return videoInfoService.listVideoInfoThatDeleted()
				.map(this::getVideoByVideoInfo);
	}

	public Boolean deleteVideo(long av) {
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
}
