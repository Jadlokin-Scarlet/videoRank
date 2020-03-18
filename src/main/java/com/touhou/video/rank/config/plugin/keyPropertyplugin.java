package com.touhou.video.rank.config.plugin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.*;

import java.util.List;

@Slf4j
public class keyPropertyplugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}


	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		element.addAttribute(new Attribute("useGeneratedKeys","true"));
		element.addAttribute(new Attribute("keyProperty","id"));
		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		element.addAttribute(new Attribute("useGeneratedKeys","true"));
		element.addAttribute(new Attribute("keyProperty","id"));
		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}
}
