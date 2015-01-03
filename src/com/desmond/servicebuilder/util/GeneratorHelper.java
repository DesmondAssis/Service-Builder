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
import com.desmond.servicebuilder.model.xml.Database;
import com.desmond.servicebuilder.model.xml.Entity;
import com.desmond.servicebuilder.model.xml.Finder;
import com.desmond.servicebuilder.model.xml.FinderColumn;
import com.desmond.servicebuilder.run.Run;
import com.desmond.servicebuilder.util.enums.TemplateEnum;

public class GeneratorHelper {
	private static Logger log = Logger.getLogger(GeneratorHelper.class.getName());
	
	public static Map<Integer, String> templateFileMap = new HashMap<Integer, String>();
	public static Map<String, String> filedsExcludeMap = new HashMap<String, String>();
	
	/*
	 * initial
	 */
	static {
		for(TemplateEnum en : TemplateEnum.values()) {
    		String source = GeneratorHelper.getServiceFileAsString(en.getRelativeURL());
    		templateFileMap.put(en.getType(), source);
    	}
		
		filedsExcludeMap.put("id", "id");
		filedsExcludeMap.put("createdDate", "createdDate");
		filedsExcludeMap.put("modifiedDate", "modifiedDate");
	}
	
	private static void mkDir(String preDir, String directoryPath) {
		String[] dirctories = directoryPath.split("/");
		if(dirctories != null && dirctories.length == 1) {
			// all dirs were made done. just leave the file.
			return;
		}
		
		preDir = StringUtils.isBlank(preDir) ? "" : preDir.endsWith("\\") ? preDir.replace("\\", "/") : preDir + "/";
		File directory = new File(preDir + dirctories[0]);
//		log.info(directory);
		if(!directory.exists()) {
			directory.mkdir();
		} else if(!directory.isDirectory()) {
			directory.delete();
			directory.mkdir();
			
		}
		preDir = directory.getPath();
		int index = directoryPath.indexOf("/");
		String dirctoryStr = index != -1 ? directoryPath.substring(index+1, directoryPath.length()) : "";
		
		mkDir(preDir, dirctoryStr);
	}
	
	public static void main(String[] args) {
		String test = "L:/Users/Presley/Desktop/test/test01/test02/test03/test01/java/a.java";
		String destPath = "L:/Users/Presley/Desktop/test/test01/test02/test03/test01/java/a.java";
		mkDir("", destPath);
		File f = new File(test);
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
			bw.write("Hello, world");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//log.info(sourceFileStr);
		

		log.info(f.exists());
	}
	
	public static void writeToDestFile(String sourceFileStr, String destdile) {
    	try {
    		
    		mkDir("", destdile);
    		
    		BufferedWriter bw = new BufferedWriter(new FileWriter(destdile));
    		//log.info(sourceFileStr);
			bw.write(sourceFileStr);
			bw.flush();
			bw.close();
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
			InputStream in = Run.class.getClassLoader().getResourceAsStream(fileName);
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
			
			// database
			Element databaseEle = root.getChild(DesmondXMLConstant.DATABASE);
			Database db = new Database(
					databaseEle.getChildTextTrim(DesmondXMLConstant.JDBC_URL),
					databaseEle.getChildTextTrim(DesmondXMLConstant.USERNAME),
					databaseEle.getChildTextTrim(DesmondXMLConstant.PASSWORD),
					databaseEle.getChildTextTrim(DesmondXMLConstant.DRIVER_CLASS)
					);
			builder.setDatabase(db);

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
				
				// columns
				List<Element> columnElementList = eleEntity
						.getChildren(DesmondXMLConstant.COLUMN);
				List<Column> columnList = new ArrayList<Column>(
						columnElementList.size());
				entity.setColumns(columnList);
				
				// finders
				List<Element> finderElementList = eleEntity
						.getChildren(DesmondXMLConstant.FINDER);
				List<Finder> finderList = new ArrayList<Finder>(
						finderElementList.size());
				entity.setFinders(finderList);
				
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
				
				if(finderElementList != null && finderElementList.size() > 0) {
					for(Element ele : finderElementList) {
						Finder finder = new Finder(
								StringUtils.trim(ele.getAttributeValue(DesmondXMLConstant.NAME)),
								StringUtils.trim(ele.getAttributeValue(DesmondXMLConstant.RETURN_TYPE)),
								null
								);
						List<Element> finderColumnElementList = ele
								.getChildren(DesmondXMLConstant.FINDER_COLUMN);
						List<FinderColumn> finderColumnList = new ArrayList<FinderColumn>(
								finderColumnElementList.size());
						for(Element coEle : finderColumnElementList) {
							FinderColumn col 
								= new FinderColumn(StringUtils.trim(coEle.getAttributeValue(DesmondXMLConstant.NAME)));
							finderColumnList.add(col);
						}
						
						finder.setFinderColumns(finderColumnList);
						finderList.add(finder);
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
