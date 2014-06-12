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
import com.desmond.servicebuilder.util.enums.TypeTransformEnum;

public class SQLGeneratorHelper {
	 private static Logger log = Logger.getLogger(SQLGeneratorHelper.class.getName());
	
	public static void creteTable(Builder builder) throws NoSuchDBTypeException{
    	List<Entity> entityList = builder.getEntities();

		StringBuilder sqlSb = new StringBuilder("drop database if exists ecommerce;\rcreate database"
				+ " ecommerce character set utf8;\ruse ecommerce;");
		
		String packageFileName = builder.getPackateName().replace(".",
				"/");
		StringBuilder sqlFileDirctory = new StringBuilder(
				DMConstants.sourceDirectory);
		sqlFileDirctory.append(packageFileName).append("/").append("sqls/");
		
    	if(entityList != null && entityList.size() > 0) {
    		for(Entity entity : entityList) {
    			List<Column> columnList = entity.getColumns();
    			StringBuilder tableSb = new StringBuilder("\r\n\n");
    			StringBuilder rowsSb = new StringBuilder();
    			String tableName = StringUtils.isNotBlank(entity.getTableName()) ?
						entity.getTableName() 
						: builder.getNameSpace() + "_" + entity.getName();
    			tableSb.append(SQLStatementConstant.CREATE_TABLE_PREFIX.replace("${table-name}", tableName));
    			
    			if(columnList != null && columnList.size() > 0) {
    				for(Column c : columnList) {
    					createIndividualRow(c, rowsSb);
    				} // end: for:column
    				String rows =  rowsSb.toString();
    				rows = rows.lastIndexOf(",") != -1 
    						? rows.substring(0, rows.lastIndexOf(","))
    								: rows;
    				tableSb.append(rows);
    			} // end: if:columnList
    			
    			tableSb.append(SQLStatementConstant.CREATE_TABLE_SUFFIX);
    			
    			StringBuilder fileNameSb = new StringBuilder(sqlFileDirctory); 
    			
    			fileNameSb.append(entity.getName()).append(".sql");

				// write to source
				GeneratorHelper
						.writeToDestFile(tableSb.toString(), fileNameSb.toString());
    			//log.debug("sql: " + tableSb);
				sqlSb.append(tableSb);
        	} // end: for:entity
    	} // end: if:entityList
    	
    	sqlSb.append("\r\n\ncreate table ec_counter (\r\tname varchar(75) not null primary key,"
    			+ "\r\tcurrentId bigint(20)\r) engine InnoDB;");
    	GeneratorHelper
		.writeToDestFile(sqlSb.toString(), sqlFileDirctory.append("init.sql").toString());
    }
    
    private static void createIndividualRow(Column column, StringBuilder rowsSb) throws NoSuchDBTypeException{
    	if(column != null) {
    		rowsSb.append("\r\t");
    		String columnName = StringUtils.uncapitalize(column.getName());
    		rowsSb.append(columnName).append(" ");
    		TypeTransformEnum type
    			= TypeTransformEnum.getTypeByTypeInXml(DMConstants.DB_TYPE_MYSQL, column.getType());
    		
    		if(type.getDbType() == DMConstants.DB_TYPE_INVAILD) {
    			throw new NoSuchDBTypeException("No Such field type: " + type.getTypeInDB()
    						+ " for type in Java: " + type.getTypeInJava()
    						+ " in DB: " + type.getDbType()
    						+ " in column: " + column.getType());
    		}
    		
    		rowsSb.append(type.getTypeInDB()).append(" ");
    		
    		if(column.isNotNull()) {
    			rowsSb.append("NOT NULL").append(" ");
    		}
    		
    		if(column.isPrimary()) {
    			rowsSb.append("PRIMARY KEY").append(" ");
    		}
    		
    		if(column.isUnique()) {
    			rowsSb.append("UNIQUE").append(" ");
    		}
    		
    		if(column.isAutoIncrement()) {
    			rowsSb.append("AUTO_INCREMENT");
    		}
    		
    		rowsSb.append(",");
    	}
    }
}
