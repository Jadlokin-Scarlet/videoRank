package com.touhou.video.rank.controller;

import com.touhou.video.rank.entity.VideoData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/video/data",produces = "application/json")
@Validated
@Slf4j
public class VideoDataController {

//    @GetMapping("/av/{av}")
//    public ResponseEntity<VideoData> getVideoData(@PathVariable String av) {
//
//        return ResponseEntity.of();
//    }

}
