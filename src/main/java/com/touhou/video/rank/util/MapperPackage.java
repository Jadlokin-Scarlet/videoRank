package com.touhou.video.rank.util;

public class MapperPackage extends Package {

	public MapperPackage(String pack) {
		super(pack);
	}

	@Override
	public String getName() {
		String className = super.getName();
		return className.replace("Mapper","");
	}

	@Override
	public Package setName(String mapperName) {
		String className = mapperName.concat("Mapper");
		return super.setName(className);
	}

}
