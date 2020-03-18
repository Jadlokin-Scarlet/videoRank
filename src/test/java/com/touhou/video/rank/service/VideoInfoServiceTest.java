package com.touhou.video.rank.service;

import com.touhou.video.rank.StartApplication;
import com.touhou.video.rank.entity.VideoInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
@WebAppConfiguration
public class VideoInfoServiceTest {

	@Autowired
	private VideoInfoService videoInfoService;


	@Test
	public void selectByPrimaryKey() {
		VideoInfo videoInfo = videoInfoService.selectByPrimaryKey(89314138L);
		System.out.println(videoInfo);
	}
}