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

	@GetMapping("/issue/{issue}/av/{av}")
	public ResponseEntity<Video> getByAv(@PathVariable long av, @PathVariable short issue) {
		Video video = videoService.getVideoByAv(av, issue);
		return ResponseEntity.ok(video);
	}

	@GetMapping(value = "/issue/{issue}")
	public ResponseEntity<List<Video>> search(
			@PathVariable short issue,
			@RequestParam(required = false, defaultValue = "全部") String type,
			@RequestParam(required = false, defaultValue = "30") int top,
			@RequestParam(required = false, defaultValue = "") String searchKey,
			@RequestParam(required = false, defaultValue = "point") String sortKey,
			@RequestParam(required = false, defaultValue = "false") boolean isDelete) {
		List<Video> videoList = videoService.search(issue, top, type, searchKey, sortKey);
		return ResponseEntity.ok(videoList);
	}

	@GetMapping(value = "/issue")
	public ResponseEntity<Short> getNewIssue() {
		return ResponseEntity.ok(videoService.getNewIssue());
	}

	@DeleteMapping(value = "/{av}/isDelete/true")
	public ResponseEntity<Boolean> falseDelete(@PathVariable long av) {
		return ResponseEntity.ok(videoService.falseDeleteVideo(av));
	}

	@DeleteMapping(value = "/{av}")
	public ResponseEntity<Boolean> delete(@PathVariable long av) {
		return ResponseEntity.ok(videoService.deleteVideo(av));
	}
}
