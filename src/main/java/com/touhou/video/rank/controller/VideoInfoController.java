package com.touhou.video.rank.controller;

import com.touhou.video.rank.entity.Video;
import com.touhou.video.rank.entity.VideoInfo;
import com.touhou.video.rank.service.VideoInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "api/video/info",produces = "application/json")
@Validated
@Slf4j
public class VideoInfoController {
	private VideoInfoService videoInfoService;

	@Autowired
	public VideoInfoController(VideoInfoService videoService) {
		this.videoInfoService = videoService;
	}

	@GetMapping(value = "/av/{av}",produces = "application/json")
	public ResponseEntity<VideoInfo> list(@PathVariable long av) {
		VideoInfo videoInfo = videoInfoService.selectByPrimaryKey(av);
		return ResponseEntity.ok(videoInfo);
	}

	@GetMapping(value = "/type/{type}/random/{number}")
	public ResponseEntity<List<VideoInfo>> listRandom(@PathVariable int number, @PathVariable String type){
		List<VideoInfo> videoList = videoInfoService.listVideoInfoRandom(type, number)
				.collect(Collectors.toList());
		return ResponseEntity.ok(videoList);
	}

	@GetMapping(value = "/deleted")
	public ResponseEntity<List<VideoInfo>> listVideoThatDeleted() {
		List<VideoInfo> videoList = videoInfoService.listVideoInfoThatDeleted()
				.collect(Collectors.toList());
		return ResponseEntity.ok(videoList);
	}

	@PatchMapping(value = "/{av}/startTime/{startTime}")
	public ResponseEntity<Boolean> updateStartTime(@PathVariable long av, @PathVariable int startTime){
		return ResponseEntity.ok(videoInfoService.updateStartTimeByPrimaryKey(av, startTime));
	}

	@PostMapping(value = "/deleted/{av}")
	public ResponseEntity<Boolean> recoveryVideo(@PathVariable long av) {
		return ResponseEntity.ok(videoInfoService.recoveryVideoByPrimaryKey(av));
	}
}
