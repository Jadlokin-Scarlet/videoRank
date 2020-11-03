package com.touhou.video.rank.service;

import com.touhou.video.rank.entity.Owner;
import com.touhou.video.rank.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

	private OwnerMapper ownerMapper;

	@Autowired
	public OwnerService(OwnerMapper ownerMapper) {
		this.ownerMapper = ownerMapper;
	}

	// @Cacheable(cacheNames = "owner")
	public Owner get(String name) {
		return ownerMapper.selectByOwnerName(name);
	}
}
