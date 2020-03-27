package com.touhou.video.rank.controller;

import com.touhou.video.rank.service.DataFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/video",produces = "application/json")
@Validated
@Slf4j
public class DataFileController {

	private final DataFileService dataFileService;

	@Autowired
	public DataFileController(DataFileService dataFileService) {
		this.dataFileService = dataFileService;
	}

	@GetMapping("/issue/{issue}/data.txt")
	public ResponseEntity<String> updateDataFile(@PathVariable Short issue) {
		String data = dataFileService.updateDataFile(issue);
		return ResponseEntity.ok()
				.header("Content-Disposition","attachment")
				.header("Content-Type","text/plain")
				.body(data);
	}

}
