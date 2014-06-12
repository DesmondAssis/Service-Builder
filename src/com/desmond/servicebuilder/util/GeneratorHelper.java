package com.desmond.servicebuilder.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.desmond.servicebuilder.DesmondXMLConstant;
import com.desmond.servicebuilder.model.xml.Builder;
import com.desmond.servicebuilder.model.xml.Column;
import com.desmond.servicebuilder.model.xml.Entity;
import com.desmond.servicebuilder.run.TestRun;
import com.desmond.servicebuilder.util.enums.TemplateEnum;

public class GeneratorHelper {
	private static Logger log = Logger.getLogger(GeneratorHelper.class.getName());
	
	public static Map<Integer, String> templateFileMap = new HashMap<Integer, String>();
	public static Map<String, String> filedsExcludeMap = new HashMap<String, String>();
	
	static {
		for(TemplateEnum en : TemplateEnum.values()) {
    		String source = GeneratorHelper.getServiceFileAsString(en.getRelativeURL());
    		templateFileMap.put(en.getType(), source);
    	}
		
		filedsExcludeMap.put("id", "id");
		filedsExcludeMap.put("createdDate", "createdDate");
		filedsExcludeMap.put("modifiedDate", "modifiedDate");
	}
	
	private static void mkDir(String fullPath, String directoryPath) {
		String[] dirctories = directoryPath.split("/");
		if(dirctories == null || dirctories.length <= 1) {
			return;
		}
		File directory = new File(fullPath.substring(0, fullPath.indexOf("/" + dirctories[0] + "/") + dirctories[0].length() + 1));
//		log.info(directory);
		if(!directory.exists()) {
			directory.mkdir();
		} else if(!directory.isDirectory()) {
			directory.delete();
			directory.mkdir();
		}
		int index = directoryPath.indexOf("/");
		String dirctoryStr = index != -1 ? directoryPath.substring(index+1, directoryPath.length()) : "";
		mkDir(fullPath, dirctoryStr);
	}
	
//	public static void main(String[] args) {
//		String destPath = "C:/Users/Presley/Desktop/test/test01/test02/test03/java/a.java";
//		mkDir(destPath, destPath);
//	}
	
	public static void writeToDestFile(String sourceFileStr, String destdile) {
    	try {
    		mkDir(destdile, destdile);
    		
    		BufferedWriter bw = new BufferedWriter(new FileWriter(destdile));
    		//log.info(sourceFileStr);
			bw.write(sourceFileStr);
			bw.flush();
			log.info("generate: " + destdile);
		} catch (FileNotFoundException e) {
			log.error("error", e);
		} catch (IOException e) {
			log.error("error", e);
		}
    }
    
    public static String getServiceFileAsString(String fileName) {
//    	log.info("fileName: " + fileName);
    	String templateFileAsString = null;
    	
		try {
			InputStream in = TestRun.class.getClassLoader().getResourceAsStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(in);
			int size = in.available();
			byte[] b = new byte[size];
			bis.read(b, 0, size);
			templateFileAsString = new String(b);
		} catch (IOException e) {
			log.error("Cannot get data from source file: " + fileName, e);
		} catch (Exception e) {
			log.error("Cannot get data from source file: " + fileName, e);
		}
    	
    	return templateFileAsString;
    }
    
    public static Builder transformElementToBean(Element root) {
		Builder builder = null;
		if (root != null) {
			builder = new Builder();
			Element author = null, namespace = null;

			String packageName = root
					.getAttributeValue(DesmondXMLConstant.PACKAGE_NAME);
			String authorValue = (author = root
					.getChild(DesmondXMLConstant.AUTHOR)) != null ? author
					.getTextTrim() : "Presley";
			String nspValue = (namespace = root
					.getChild(DesmondXMLConstant.NAMESPACE)) != null ? namespace
					.getTextTrim() : "ec";

			builder.setPackateName(packageName);
			builder.setAuthor(authorValue);
			builder.setNameSpace(nspValue);

			List<Element> elementEntityList = root
					.getChildren(DesmondXMLConstant.ENTITY);
			List<Entity> entities = new ArrayList<Entity>(
					elementEntityList.size());
			builder.setEntities(entities);
			for (Element eleEntity : elementEntityList) {
				Entity entity = new Entity();
				entity.setName(eleEntity
						.getAttributeValue(DesmondXMLConstant.NAME));
				entity.setTableName(eleEntity
						.getAttributeValue(DesmondXMLConstant.TABLE_NAME));
				entity.setSubPackageName(eleEntity
						.getAttributeValue(DesmondXMLConstant.SUB_PACKAGE_NAME));
				entity.setPackageName(builder.getPackateName());
				List<Element> columnElementList = eleEntity
						.getChildren(DesmondXMLConstant.COLUMN);
				List<Column> columnList = new ArrayList<Column>(
						columnElementList.size());
				entity.setColumns(columnList);
				if (columnElementList != null && !columnElementList.isEmpty()) {
					for (Element ele : columnElementList) {
						if (ele == null)
							break;
						Column column = new Column(
								StringUtils
										.trim(ele
												.getAttributeValue(DesmondXMLConstant.NAME)),
								StringUtils.trim(ele
										.getAttributeValue(DesmondXMLConstant.TYPE)),
								BooleanUtils.toBoolean(ele
										.getAttributeValue(DesmondXMLConstant.PRIMARY)),
								BooleanUtils.toBoolean(ele
										.getAttributeValue(DesmondXMLConstant.AUTO_INCREMENT)),
								BooleanUtils.toBoolean(ele
										.getAttributeValue(DesmondXMLConstant.UNIQUE)),
								BooleanUtils.toBoolean(ele
										.getAttributeValue(DesmondXMLConstant.NOT_NULL)));
						columnList.add(column);
					}
				}

				entities.add(entity);
			}

		}

		return builder;
	}
    
    public static Element getService() throws JDOMException, IOException {
    	InputStream in = GeneratorHelper.class.getClassLoader().getResourceAsStream(
				"com/desmond/servicebuilder/service-ec.xml");
		// Use a SAX builder
		SAXBuilder saxBuilder = new SAXBuilder();
		// build a JDOM2 Document using the SAXBuilder.
		Document jdomDoc = saxBuilder.build(in);

		// get the document type
		//log.info(jdomDoc.getDocType());

		// get the root element
		Element root = jdomDoc.getRootElement();
		
		return root;
    }

}
