package com.desmond.servicebuilder.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.desmond.servicebuilder.exception.NoSuchDBTypeException;
import com.desmond.servicebuilder.model.xml.Builder;
import com.desmond.servicebuilder.model.xml.Column;
import com.desmond.servicebuilder.model.xml.Database;
import com.desmond.servicebuilder.model.xml.Entity;
import com.desmond.servicebuilder.util.constant.DMConstants;
import com.desmond.servicebuilder.util.constant.SQLStatementConstant;
import com.desmond.servicebuilder.util.enums.TemplateEnum;
import com.desmond.servicebuilder.util.enums.TypeTransformEnum;

public class UtilsGeneratorHelper {
	private static Logger log = Logger.getLogger(UtilsGeneratorHelper.class.getName());
	
	public static void generateModelAndImpl(Builder builder) {
		
		// counter
		generateCounter(builder);
		
		// dbutil
		generateDBUtil(builder);
	}

	private static void generateCounter(Builder builder) {
		String template = GeneratorHelper.templateFileMap
				.get(TemplateEnum.COUNTER.getType());

		String packageName = builder.getPackateName();
		String outputTemplate = template
				.replace("${package}",
						packageName);						;

		String packageFileName = packageName.replace(".",
				"/");
		StringBuilder fileNameSb = new StringBuilder(
				DMConstants.sourceDirectory);
		fileNameSb.append(packageFileName).append("/").append("util/db/Counter")
				.append(".java");

		// write to source file.
		
		// models
		GeneratorHelper
				.writeToDestFile(outputTemplate, fileNameSb.toString());
	}
	
	private static void generateDBUtil(Builder builder) {
		String template = GeneratorHelper.templateFileMap
				.get(TemplateEnum.DB_UTIL.getType());
		
		Database db = builder.getDatabase();
		String packageName = builder.getPackateName();
		String outputTemplate = template
				.replace("${package}",
						packageName)
				.replace("${jdbcurl}", db.getJdbcUrl())
				.replace("${user}", db.getUsername())
				.replace("${password}", db.getPassword())
				.replace("${driverClass}", db.getDriverClass());

		String packageFileName = packageName.replace(".",
				"/");
		StringBuilder fileNameSb = new StringBuilder(
				DMConstants.sourceDirectory);
		fileNameSb.append(packageFileName).append("/").append("util/db/DBUtil")
				.append(".java");

		// write to source file.
		
		// models
		GeneratorHelper
				.writeToDestFile(outputTemplate, fileNameSb.toString());
	}

}