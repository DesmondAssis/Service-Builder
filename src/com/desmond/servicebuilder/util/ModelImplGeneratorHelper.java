package com.desmond.servicebuilder.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.desmond.servicebuilder.exception.NoSuchDBTypeException;
import com.desmond.servicebuilder.model.xml.Builder;
import com.desmond.servicebuilder.model.xml.Column;
import com.desmond.servicebuilder.model.xml.Entity;
import com.desmond.servicebuilder.util.constant.DMConstants;
import com.desmond.servicebuilder.util.constant.SQLStatementConstant;
import com.desmond.servicebuilder.util.enums.TemplateEnum;
import com.desmond.servicebuilder.util.enums.TypeTransformEnum;

public class ModelImplGeneratorHelper {
	private static Logger log = Logger.getLogger(ModelImplGeneratorHelper.class.getName());
	
	public static void generate(Builder builder) {
		// model
		generateModel(builder);
		
		// impl
		generateModelImpl(builder);
	}
	
	private static void generateModel(Builder builder) {
		String baseTemplate = GeneratorHelper.templateFileMap.get(TemplateEnum.MODEL_BASE.getType());
		String template = GeneratorHelper.templateFileMap
				.get(TemplateEnum.MODEL.getType());

		List<Entity> entityList = builder.getEntities();
		if (entityList != null && entityList.size() > 0) {
			for (Entity entity : entityList) {
				List<Column> columnList = entity.getColumns();
				StringBuilder setterAndGetterSb = new StringBuilder();
				StringBuilder importsSb = new StringBuilder();
				Map<String, String> flagMap = new HashMap<String, String>();

				if (columnList != null && columnList.size() > 0) {
					for (Column c : columnList) {
						if(!GeneratorHelper.filedsExcludeMap.containsKey(c.getName())) {

							String nameCapitalition = StringUtils.capitalize(c
									.getName());
							String nameUnCapitalition = StringUtils.uncapitalize(c
									.getName());

							if (c.getType().equals("Date")
									&& !flagMap.containsKey("Date")) {
								importsSb.append("import java.util.Date;\n");
								flagMap.put("Date", "");
							} else if (c.getType().equals("Timestamp")
									&& !flagMap.containsKey("Timestamp")) {
								importsSb.append("import java.sql.Timestamp;\n");
								flagMap.put("Timestamp", "");
							}

							// getter
							setterAndGetterSb.append("\r\tpublic ")
									.append(TypeTransformEnum.getJavaTypeByTypeInXml(c.getType())).append(" get")
									.append(nameCapitalition).append("(")
									.append(");").append("\r\n");

							// setter
							setterAndGetterSb.append("\r\tpublic ").append("void")
									.append(" set").append(nameCapitalition)
									.append("(").append(TypeTransformEnum.getJavaTypeByTypeInXml(c.getType()))
									.append(" ")
									.append(nameUnCapitalition).append(");")
									.append("\r\n");
						} 
					}
				}

				String outputTemplate = template
						.replace("${packageName}",
								entity.getPackageName() + ".intf")
						.replace("${imports}", importsSb.toString())
						.replace("${model}",
								StringUtils.capitalize(entity.getName()))
						.replace("${setterAndGetter}",
								setterAndGetterSb.toString());
				
				String outputBaseTemplate = baseTemplate
						.replace("${packageName}",
						entity.getPackageName() + ".intf.base");

				String packageFileName = entity.getPackageName().replace(".",
						"/");
				StringBuilder fileNameSb = new StringBuilder(
						DMConstants.sourceDirectory);
				String modelBaseFile = fileNameSb.toString() + packageFileName + "/intf/base/BaseModel.java";
				fileNameSb.append(packageFileName).append("/").append("intf/")
						.append(entity.getName()).append(".java");

				// write to source
				// model base;
				GeneratorHelper
				.writeToDestFile(outputBaseTemplate, modelBaseFile);
				
				// models
				GeneratorHelper
						.writeToDestFile(outputTemplate, fileNameSb.toString());
				
			} // end:entity
		}
	}

	private static void generateModelImpl(Builder builder) {
		String baseTemplate = GeneratorHelper.templateFileMap.get(TemplateEnum.MODEL_BASE_IMPL.getType());
		String template = GeneratorHelper.templateFileMap
				.get(TemplateEnum.MODEL_IMPL.getType());

		List<Entity> entityList = builder.getEntities();
		if (entityList != null && entityList.size() > 0) {
			for (Entity entity : entityList) {
				List<Column> columnList = entity.getColumns();

				StringBuilder privateFieldsSb = new StringBuilder();
				StringBuilder setterAndGetterSb = new StringBuilder();
				StringBuilder mockSb = new StringBuilder();
				StringBuilder importsSb = new StringBuilder();
				Map<String, String> flagMap = new HashMap<String, String>();

				if (columnList != null && columnList.size() > 0) {
					for (Column c : columnList) {
						if(!GeneratorHelper.filedsExcludeMap.containsKey(c.getName())) {
							String nameCapitalition = StringUtils.capitalize(c
									.getName());
							String nameUnCapitalition = StringUtils.uncapitalize(c
									.getName());
							if (c.getType().equals("Date")
									&& !flagMap.containsKey("Date")) {
								importsSb.append("import java.util.Date;\n");
								flagMap.put("Date", "");
							} else if (c.getType().equals("Timestamp")
									&& !flagMap.containsKey("Timestamp")) {
								importsSb.append("import java.sql.Timestamp;\n");
								flagMap.put("Timestamp", "");
							}

							// private fields
							privateFieldsSb.append("\r\tprivate ")
									.append(TypeTransformEnum.getJavaTypeByTypeInXml(c.getType())).append(" ")
									.append(c.getName()).append(";").append("\r\n");

							// getter
							setterAndGetterSb.append("\r\tpublic ")
									.append(TypeTransformEnum.getJavaTypeByTypeInXml(c.getType())).append(" get")
									.append(nameCapitalition).append("(")
									.append(") {").append("\r\t\treturn ")
									.append(c.getName()).append(";")
									.append("\r\t}").append("\r\n");

							// setter
							setterAndGetterSb.append("\r\tpublic ").append("void")
									.append(" set").append(nameCapitalition)
									.append("(").append(TypeTransformEnum.getJavaTypeByTypeInXml(c.getType()))
									.append(" ")
									.append(nameUnCapitalition).append(") {")
									.append("\r\t\tthis.")
									.append(nameUnCapitalition).append(" = ")
									.append(nameUnCapitalition).append(";")
									.append("\r\t}").append("\r\n");
							
							// mock
							String valueObj = TypeTransformEnum.getMockValueByTypeInXml(c.getType(), nameCapitalition);
							mockSb.append("\r\t\t").append("${modelVariable}.set")
									.append(nameCapitalition)
									.append("(")
									.append(valueObj)
									.append(");");
						}

					} // end: column
				} // end: if:column

				String modelName = entity.getPackageName() + ".intf."
						+ entity.getName();
				importsSb.append("import " + modelName + ";\n");
				String outputTemplate = template
						.replace("${packageName}",
								entity.getPackageName() + ".impl.model")
						.replace("${imports}", importsSb.toString())
						.replace(
								"${modelImpl}",
								StringUtils.capitalize(entity.getName())
										+ "Impl")
						.replace("${model}",
								StringUtils.capitalize(entity.getName()))
						.replace("${privateFields}", privateFieldsSb.toString())
						.replace("${setterAndGetter}",
								setterAndGetterSb.toString())
						.replace("${mockSetValue}", mockSb.toString())
						.replace("${modelVariable}",
								entity.getName().toLowerCase())
								;

				String packageFileName = entity.getPackageName().replace(".",
						"/");
				StringBuilder fileNameSb = new StringBuilder(
						DMConstants.sourceDirectory);
				fileNameSb.append(packageFileName).append("/").append("impl/model/")
						.append(entity.getName()).append("Impl")
						.append(".java");
				
				String outputBaseTemplate = baseTemplate
						.replace("${package}",
						entity.getPackageName());
				String modelBaseFile = DMConstants.sourceDirectory + packageFileName + "/impl/model/base/BaseModelImpl.java";

				// write to source file.
				// model base
				GeneratorHelper
				.writeToDestFile(outputBaseTemplate, modelBaseFile);
				
				// models
				GeneratorHelper
						.writeToDestFile(outputTemplate, fileNameSb.toString());
			} // end: entity
		} // end: if:entity

	}

}