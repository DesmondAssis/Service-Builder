package com.desmond.servicebuilder.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.desmond.servicebuilder.model.xml.Builder;
import com.desmond.servicebuilder.model.xml.Entity;
import com.desmond.servicebuilder.util.constant.DMConstants;
import com.desmond.servicebuilder.util.enums.TemplateEnum;

public class ServiceImplGeneratorHelper {
	private static Logger log = Logger.getLogger(ServiceImplGeneratorHelper.class.getName());
	
	private static void generateServiceBaseImpl(Builder builder) {
		String template = GeneratorHelper.templateFileMap
				.get(TemplateEnum.SERVICE_BASE_IMPL.getType());
		
		List<Entity> entityList = builder.getEntities();
		if (entityList != null && entityList.size() > 0) {
			for (Entity entity : entityList) {
				StringBuilder importsSb = new StringBuilder();
				
				importsSb.append("import " + entity.getPackageName()+ ".intf." + entity.getName());
				
				String outputTemplate = template
						.replace("${packageName}",
								entity.getPackageName() + ".impl")
						.replace("${imports}", importsSb.toString())
						.replace("${model}", entity.getName())
						.replace("${modelVariable}", StringUtils.uncapitalize(entity.getName()))
								;

				String packageFileName = entity.getPackageName().replace(".",
						"/");
				StringBuilder fileNameSb = new StringBuilder(
						DMConstants.sourceDirectory);
				fileNameSb.append(packageFileName).append("/").append("impl/")
						.append(entity.getName()).append("ServiceBaseImpl.java");
				
				//log.info(template);
				// write to source
				GeneratorHelper
						.writeToDestFile(outputTemplate, fileNameSb.toString());
			}
		}
	}
	
	private static void generateLocalServiceImpl(Builder builder) {
		String template = GeneratorHelper.templateFileMap
				.get(TemplateEnum.SERVICE_IMPL.getType());
		
		List<Entity> entityList = builder.getEntities();
		if (entityList != null && entityList.size() > 0) {
			for (Entity entity : entityList) {
				StringBuilder importsSb = new StringBuilder();
				String outputTemplate = template
						.replace("${packageName}",
								entity.getPackageName() + ".impl")
						.replace("${imports}", importsSb.toString())
						.replace("${model}", entity.getName())
							;

				String packageFileName = entity.getPackageName().replace(".",
						"/");
				StringBuilder fileNameSb = new StringBuilder(
						DMConstants.sourceDirectory);
				fileNameSb.append(packageFileName).append("/").append("impl/")
						.append(entity.getName()).append("LocalServiceImpl.java");
				
				//log.info(template);
				// write to source
				GeneratorHelper
						.writeToDestFile(outputTemplate, fileNameSb.toString());
			}
		}
	}
	
	public static void generateService(Builder builder) {
		// ServiceBaseImpl
		generateServiceBaseImpl(builder);
		
		// LocalServiceImpl
		generateLocalServiceImpl(builder);
		
	}
}