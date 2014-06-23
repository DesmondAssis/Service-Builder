package com.desmond.servicebuilder.util;

import com.desmond.servicebuilder.model.xml.Column;
import com.desmond.servicebuilder.model.xml.Entity;

public class TypeTransformUtil {
	public static String returnTypeTransform(String returnType, String modelName) {
		if(returnType == null || modelName == null) {
			return "";
		}
		
		if("Collection".equals(returnType)) {
			return "java.util.List<" + modelName + ">";
		}
		
		return "";
	}
	
	public static String returnDeclare(String returnType, String modelName) {
		if(returnType == null || modelName == null) {
			return "";
		}
		
		if("Collection".equals(returnType)) {
			return "java.util.List<" + modelName 
					+ "> ${modelVariable}s = new java.util.ArrayList<" + modelName + ">()";
		}
		
		return "";
	}
	
	public static Column findColumnByNane(Entity entity, String columnName) {
		Column column = null;
		if(columnName != null && entity != null && entity.getColumns() != null) {
			for(Column col : entity.getColumns()) {
				if(columnName.equals(col.getName())) {
					column = col;
					break;
				}
			}
		}
		
		return column;
	}
}
