package com.touhou.video.rank.service;

import com.touhou.video.rank.StartApplication;
import com.touhou.video.rank.entity.Video;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
@WebAppConfiguration
public class VideoServiceTest {

	@Autowired
	public VideoService videoService;
	@Autowired
	TypeService typeService;

	@Test
	public void search() {
		List<Video> videoList = videoService.search(
				videoService.getNewIssue(),
				30,
				typeService.getTypeByName("动画"),
				"",
				"point");
		assertTrue(!videoList.isEmpty());
		log.info(videoList.toString());
	}

}