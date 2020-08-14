package com.touhou.video.rank.service;

import com.touhou.video.rank.StartApplication;
import com.touhou.video.rank.entity.VideoData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
@WebAppConfiguration
public class VideoDataServiceTest {

	@Autowired
	private VideoDataService videoDataService;
	@Autowired
	private TypeService typeService;

	@Test
	public void selectAll() {
		List<VideoData> list =
				videoDataService.selectAll(videoDataService.getNewIssue(), 10, "单机游戏")
				.collect(Collectors.toList());
		Assert.assertTrue(!list.isEmpty());
		log.info(list.toString());
	}
}