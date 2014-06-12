package com.desmond.servicebuilder.util.enums;

import com.desmond.servicebuilder.util.constant.DMConstants;

public enum TypeTransformEnum {
	INVALID(DMConstants.DB_TYPE_INVAILD, "", "", ""),
	LONG(DMConstants.DB_TYPE_MYSQL, "long", "Long", "BIGINT(25)"),
	INT(DMConstants.DB_TYPE_MYSQL, "int", "int", "INT"),
	DOUBLE(DMConstants.DB_TYPE_MYSQL, "double", "double", "DOUBLE"),
	BOOLEAN(DMConstants.DB_TYPE_MYSQL, "boolean", "boolean", "TINYINT"),
	STRING(DMConstants.DB_TYPE_MYSQL, "String", "String", "VARCHAR(75)"),
	TIME(DMConstants.DB_TYPE_MYSQL, "Time", "Timestamp", "TIMESTAMP");
	
	private int dbType;
	private String typeInXml;
	private String typeInJava;
	private String TypeInDB;

	private TypeTransformEnum(int dbType, String typeInXml, String typeInJava, String typeInDB) {
		this.dbType = dbType;
		this.typeInXml = typeInXml;
		this.typeInJava = typeInJava;
		TypeInDB = typeInDB;
	}
	
	public static TypeTransformEnum getTypeByTypeInDB(int db, String typeInDB) {
		for(TypeTransformEnum type : TypeTransformEnum.values()) {
			if(db == type.getDbType() && typeInDB.equals(type.getTypeInDB())) {
				return type;
			}
		}
		
		return TypeTransformEnum.INVALID;
	}
	
	public static TypeTransformEnum getTypeByTypeInJava(int db, String typeInJava) {
		for(TypeTransformEnum type : TypeTransformEnum.values()) {
			if(db == type.getDbType() && typeInJava.equals(type.getTypeInJava())) {
				return type;
			}
		}
		
		return TypeTransformEnum.INVALID;
	}
	
	public static TypeTransformEnum getTypeByTypeInXml(int db, String typeInXml) {
		for(TypeTransformEnum type : TypeTransformEnum.values()) {
			if(db == type.getDbType() && typeInXml.equals(type.getTypeInXml())) {
				return type;
			}
		}
		
		return TypeTransformEnum.INVALID;
	}
	
	public static String getMockValueByTypeInXml(int db, String typeInXml, String name) {
		for(TypeTransformEnum type : TypeTransformEnum.values()) {
			if(db == type.getDbType() && typeInXml.equals(type.getTypeInXml())) {
				switch(type) {
				case LONG:return "Math.round(10000)";
				case INT:return "Math.round(100)";
				case DOUBLE:return "Math.rint(100)";
				case BOOLEAN:return "false";
				case STRING: return "\"" + name + "\" + " + "Math.round(100000000)";
				case TIME: return "new java.sql.Timestamp(new java.util.Date().getTime())";
				
				}
			}
		}
		
		return "unkown value of " + name;
	}
	
	public static String getMockValueByTypeInXml(String typeInXml, String name) {
		
		return TypeTransformEnum.getMockValueByTypeInXml(DMConstants.DB_TYPE_MYSQL, typeInXml, name);
	}

	/**
	 * Returns the dbType of this TypeTransformEnum.
	 * @return the dbType
	 */
	public int getDbType() {
		return dbType;
	}

	/**
	 * Sets the dbType of this TypeTransformEnum.
	 * @param dbType the dbType to set
	 */
	public void setDbType(int dbType) {
		this.dbType = dbType;
	}

	/**
	 * Returns the typeInJava of this TypeTransformEnum.
	 * @return the typeInJava
	 */
	public String getTypeInJava() {
		return typeInJava;
	}

	/**
	 * Sets the typeInJava of this TypeTransformEnum.
	 * @param typeInJava the typeInJava to set
	 */
	public void setTypeInJava(String typeInJava) {
		this.typeInJava = typeInJava;
	}

	/**
	 * Returns the typeInDB of this TypeTransformEnum.
	 * @return the typeInDB
	 */
	public String getTypeInDB() {
		return TypeInDB;
	}

	
	/**
	 * Sets the typeInDB of this TypeTransformEnum.
	 * @param typeInDB the typeInDB to set
	 */
	public void setTypeInDB(String typeInDB) {
		TypeInDB = typeInDB;
	}

	/**
	 * Returns the typeInXml of this TypeTransformEnum.
	 * @return the typeInXml
	 */
	public String getTypeInXml() {
		return typeInXml;
	}

	/**
	 * Sets the typeInXml of this TypeTransformEnum.
	 * @param typeInXml the typeInXml to set
	 */
	public void setTypeInXml(String typeInXml) {
		this.typeInXml = typeInXml;
	}
	
}
