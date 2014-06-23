package com.desmond.servicebuilder.util.constant;

public interface SQLStatementConstant {
	String CREATE_TABLE_PREFIX = "create table ${table-name} (\r";
	String CREATE_TABLE_SUFFIX = "\r) engine InnoDB;";
}
