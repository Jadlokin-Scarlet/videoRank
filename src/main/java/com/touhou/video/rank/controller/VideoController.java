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
	public ResponseEntity<List<Video>> list(@PathVariable Short issue) {
		return ResponseEntity.ok(videoService.listTop(issue));
	}

	@DeleteMapping(value = "/{av}")
	public ResponseEntity<Boolean> delete(@PathVariable long av) {
		return ResponseEntity.ok(videoService.deleteVideo(av));
	}

	@PatchMapping(value = "/{av}/startTime/{startTime}")
	public ResponseEntity<Boolean> updateStartTime(@PathVariable long av, @PathVariable int startTime){
		return ResponseEntity.ok(videoService.updateStartTime(av, startTime));
	}


}
