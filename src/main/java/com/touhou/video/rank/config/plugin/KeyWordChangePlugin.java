package com.touhou.video.rank.config.plugin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class KeyWordChangePlugin extends PluginAdapter {

	private final List<String> keyWords = Arrays.asList("view");

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
		String newName = "["+ tableName +"]";
		if(keyWords.contains(tableName)) {
			introspectedTable.setSqlMapAliasedFullyQualifiedRuntimeTableName(newName);
			introspectedTable.setSqlMapFullyQualifiedRuntimeTableName(newName);
		}
		super.initialized(introspectedTable);
	}

//	@Override
//	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
//		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
//		log.warn("table : " + tableName);
//		if(keyWords.contains(tableName)){
//			log.warn("find keyWord : " + tableName);
//			List<Element> texts = element.getElements();
//			for(int i=1;i<texts.size();i++){
//				Element text = texts.get(i);
//				if(text instanceof TextElement){
//					String newText = text.getFormattedContent(0)
//							.replace(" " + tableName," \""+ tableName +"\"");
//					texts.set(i,new TextElement(newText));
//				}
//			}
//		}
//		return super.sqlMapInsertElementGenerated(element, introspectedTable);
//	}
}
