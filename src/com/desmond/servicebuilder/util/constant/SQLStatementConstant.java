package com.desmond.servicebuilder.util.constant;

public interface SQLStatementConstant {
	String DROP_TABLE_IF_EXIST = "drop table if exists ";
	String CREATE_TABLE_PREFIX = "create table ${table-name} (\r";
	String CREATE_TABLE_SUFFIX = "\r) engine InnoDB;";
}
