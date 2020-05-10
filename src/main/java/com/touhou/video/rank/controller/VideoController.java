package com.touhou.video.rank.controller;

import com.touhou.video.rank.entity.Video;
import com.touhou.video.rank.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "api/video",produces = "application/json")
@Validated
@Slf4j
public class VideoController {

	private VideoService videoService;

	@Autowired
	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}

	@GetMapping(value = "/issue/{issue}",produces = "application/json")
	public ResponseEntity<List<Video>> listTop30(@PathVariable Short issue) {
		List<Video> videoList = videoService.listVideoTop30(issue)
				.collect(Collectors.toList());
		return ResponseEntity.ok(videoList);
	}

	@GetMapping(value = "/issue/{issue}/top/{top}")
	public ResponseEntity<List<Video>> listTop(
			@PathVariable Short issue,
			@PathVariable int top) {
		List<Video> videoList = videoService.listVideo(issue, top)
				.collect(Collectors.toList());
		return ResponseEntity.ok(videoList);
	}

	@GetMapping(value = "/issue/{issue}/top/{top}/type/{type}")
	public ResponseEntity<List<Video>> listTop(
			@PathVariable Short issue,
			@PathVariable int top,
			@PathVariable String type) {
		List<Video> videoList = videoService.listVideo(issue, top, type)
				.collect(Collectors.toList());
		return ResponseEntity.ok(videoList);
	}

	@GetMapping(value = "/issue")
	public ResponseEntity<Short> getNewIssue() {
		return ResponseEntity.ok(videoService.getNewIssue());
	}

	@DeleteMapping(value = "/{av}")
	public ResponseEntity<Boolean> delete(@PathVariable long av) {
		return ResponseEntity.ok(videoService.deleteVideo(av));
	}

}
