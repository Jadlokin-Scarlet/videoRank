package com.touhou.video.rank.config.plugin;

import com.touhou.video.rank.util.MapperPackage;
import com.touhou.video.rank.util.Package;
import lombok.var;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

public class MapperRenameToBaseMapperPlugin extends PluginAdapter {

	private Package pack = null;

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		super.initialized(introspectedTable);
		String packString = introspectedTable.getMyBatis3JavaMapperType();
		String newPack = addBaseTo(packString);
		introspectedTable.setMyBatis3JavaMapperType(newPack);

		introspectedTable.setMyBatis3XmlMapperFileName(pack.getName()+"Mapper.xml");
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	private String addBaseTo(String packString){
		pack = new MapperPackage(packString);
		String name = pack.getName();
		return pack.setName(name.concat("Base")).toString();
	}

}
