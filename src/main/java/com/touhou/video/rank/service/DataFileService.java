package com.touhou.video.rank.service;

import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataFileService {

	private final VideoService videoService;

//	private ClassPathResource resource;
//	private Path dataFilePath = Paths.get("D:/data.txt");

	private List<String> fields = Arrays.asList("av", "name", "img", "type", "owner",
					"copyright", "pubTime", "startTime", "view", "reply",
					"favorite", "coin", "point", "rank", "hisRank", "isLen");

	@Autowired
	public DataFileService(VideoService videoService) {
		this.videoService = videoService;
//		resource = new ClassPathResource("static/data.txt");
	}

	public String getDataFile(short issue) {
		String head = String.join("\t", fields) + "\n";
		String data = videoService.listVideoTop100(issue)
				.map(videoService::getAndSetHisRank)
				.map(videoService::getAndSetIsLen)
				.map(videoService::changeToShortPubTime)
				.map(BeanMap::new)
				.map(beanMap -> fields.stream()
						.map(beanMap::get)
						.map(Object::toString)
						.collect(Collectors.joining("\t")))
				.collect(Collectors.joining("\n"));
//		flush();
//		write(head + data);
		return head + data;
	}

//	public String read() {
//		try(BufferedReader reader = Files.newBufferedReader(dataFilePath)) {
//			return reader.lines().collect(Collectors.joining("\n"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public void write(String value) {
//		try(BufferedWriter writer = Files.newBufferedWriter(dataFilePath)) {
//			writer.write(value);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void flush() {
//		try(BufferedWriter writer = Files.newBufferedWriter(dataFilePath)) {
//			writer.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
