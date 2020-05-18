package com.touhou.video.rank.service;

import com.touhou.video.rank.StartApplication;
import com.touhou.video.rank.entity.VideoInfo;
import com.touhou.video.rank.mapper.VideoInfoMapper;
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
import java.util.stream.Stream;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
@WebAppConfiguration
public class VideoInfoServiceTest {

	@Autowired
	private VideoInfoService videoInfoService;
	@Autowired
	private VideoInfoMapper videoInfoMapper;


	@Test
	public void selectByPrimaryKey() {
		VideoInfo videoInfo2 = videoInfoService.selectByPrimaryKey(967999675L);
		assertNotNull(videoInfo2);
		System.out.println(videoInfo2);
	}

	@Test
	public void listVideoInfoRandom() {
		List<VideoInfo> infos = videoInfoService.listVideoInfoRandom("游戏", 10)
				.collect(Collectors.toList());
		Assert.assertTrue(!infos.isEmpty());
	}
}