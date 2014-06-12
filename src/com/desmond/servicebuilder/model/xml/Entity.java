package com.desmond.servicebuilder.model.xml;

import java.util.List;

public class Entity {
	private String name;
	private String tableName;
	private String subPackageName;
	private String packageName;
	List<Column> columns;

	public Entity() {
		super();
	}

	public Entity(String name, String tableName, List<Column> columns) {
		super();
		this.name = name;
		this.tableName = tableName;
		this.columns = columns;
	}

	/**
	 * Returns the name of this Entity.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this Entity.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the tableName of this Entity.
	 * 
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Sets the tableName of this Entity.
	 * 
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * Returns the subPackageName of this Entity.
	 * @return the subPackageName
	 */
	public String getSubPackageName() {
		return subPackageName;
	}

	/**
	 * Sets the subPackageName of this Entity.
	 * @param subPackageName the subPackageName to set
	 */
	public void setSubPackageName(String subPackageName) {
		this.subPackageName = subPackageName;
	}

	/**
	 * Returns the packageName of this Entity.
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets the packageName of this Entity.
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = (packageName + "." + this.subPackageName).toLowerCase();
	}

	/**
	 * Returns the columns of this Entity.
	 * @return the columns
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * Sets the columns of this Entity.
	 * @param columns the columns to set
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder resultSb 
		= new StringBuilder("Entity [name=" + name + ", tableName=" + tableName
				+ ", subPackageName=" + subPackageName + ", packageName="
				+ packageName + ", columns=" + columns + "]:\n");
		
		if(this.getColumns() != null) {
			for(Column column : this.getColumns()){
				resultSb.append("\t-------")
						.append(column.toString())
						.append("\n");
			}
		}
		
		return resultSb.toString();
	}
	
	
}
