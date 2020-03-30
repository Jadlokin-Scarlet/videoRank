package com.touhou.video.rank.service;

import com.touhou.video.rank.StartApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
@WebAppConfiguration
public class VideoTagServiceTest {

	@Autowired
	public VideoTagService videoTagService;

	@Test
	public void getTags() {
		List<String> tags = videoTagService.getTags(53625263);
		System.out.println(tags);
		Assert.assertTrue(!tags.isEmpty());
	}
}