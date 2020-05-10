package com.touhou.video.rank.service;

import com.touhou.video.rank.StartApplication;
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
public class DataFileServiceTest {

	@Autowired
	private DataFileService dataFileService;

	@Test
	public void getDataFile() {
//		String dataFile = dataFileService.getDataFile((short) 3);
//		System.out.println(dataFile);
	}
}