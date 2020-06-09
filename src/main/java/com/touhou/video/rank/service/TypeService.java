package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.Type;
import com.touhou.video.rank.mapper.TypeMapper;
import com.touhou.video.rank.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Cacheable(cacheNames = "TypeService")
public class TypeService {
	public final Type ALL;
	private TypeMapper typeMapper;

	@Autowired
	public TypeService(TypeMapper typeMapper) {
		this.typeMapper = typeMapper;
		ALL = this.ALL();
	}

	private List<Type> list() {
		return typeMapper.selectAll();
	}

	private List<List<Type>> getSonMap() {
		List<Type> typeList = list();
		List<List<Type>> sonListList = typeList.stream()
				.map(Lists::of)
				.collect(Collectors.toList());
		for (int i = typeList.size() - 1; i >= 0; i--) {
			Type type = typeList.get(i);
			if (type.getFatherId() == 0) continue;
			List<Type> sonList = sonListList.get(type.getId() - 1);
			sonListList.get(type.getFatherId() - 1).addAll(sonList);
		}
		return sonListList;
	}

//	private List<Type> listByFatherType(Type father) {
//		List<Type> sonList = new ArrayList<>();
//		sonList.add(father);
//		if (father.getSonList().isEmpty()) {
//			return sonList;
//		}
//		for (Type son : father.getSonList()) {
//			sonList.addAll(listByFatherType(son));
//		}
//		return sonList;
//	}

	public Type getTypeByName(String name) {
		return list().stream()
				.filter(type -> name.equals(type.getName()))
				.findFirst().orElse(null);
	}

	public List<Type> listByFatherType(String fatherName) {
		Type father = getTypeByName(fatherName);
		return listByFatherType(father);
	}

	public List<Type> listByFatherType(Type father) {
		return getSonMap().get(father.getId() - 1);
	}

	public Type ALL() {
		return list().get(1 - 1);
	}
}
