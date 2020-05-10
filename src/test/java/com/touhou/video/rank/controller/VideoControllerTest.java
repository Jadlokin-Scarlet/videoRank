package com.touhou.video.rank.controller;

import com.touhou.video.rank.StartApplication;
import com.touhou.video.rank.entity.Video;
import com.touhou.video.rank.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
@WebAppConfiguration
public class VideoControllerTest {

	@Autowired
	private VideoController videoController;

	@Test
	public void list() {
		ResponseEntity<List<Video>> videoListEntity = videoController.listTop30((short)1);
		List<Video> videoList = videoListEntity.getBody();
		System.out.println(videoList);
	}
}