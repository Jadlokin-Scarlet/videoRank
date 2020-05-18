package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.Type;
import com.touhou.video.rank.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class TypeService {

	private TypeMapper typeMapper;

	@Autowired
	public TypeService(TypeMapper typeMapper) {
		this.typeMapper = typeMapper;
	}

	@Cacheable(cacheNames = "type")
	public Stream<Type> list() {
		List<Type> typeList = typeMapper.selectAll();
		for (Type type : typeList) {
			if (type.getFatherId() == 0) continue;
			typeList.get(type.getFatherId() - 1).getSonList().add(type);
		}
		return typeList.stream();
	}

	private List<Type> listByFatherType(Type father) {
		List<Type> sonList = new ArrayList<>();
		sonList.add(father);
		if (father.getSonList().isEmpty()) {
			return sonList;
		}
		for (Type son : father.getSonList()) {
			sonList.addAll(listByFatherType(son));
		}
		return sonList;
	}

	public List<Type> listByFatherType(String fatherName) {
		Type father = list()
				.filter(type -> fatherName.equals(type.getName()))
				.findFirst().orElse(null);
		if (father == null) {
			return null;
		}
		return listByFatherType(father);
	}

	public Type getFirstType() {
		return typeMapper.selectByPrimaryKey(1);
	}
}
