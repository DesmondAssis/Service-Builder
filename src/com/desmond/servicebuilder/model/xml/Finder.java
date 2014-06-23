package com.desmond.servicebuilder.model.xml;

import java.util.List;

public class Finder {
	private String name;
	private String returnType;
	private List<FinderColumn> finderColumns;
	
	public Finder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Finder(String name, String returnType,
			List<FinderColumn> finderColumns) {
		super();
		this.name = name;
		this.returnType = returnType;
		this.finderColumns = finderColumns;
	}

	/**
	 * Returns the name of this Finder.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of this Finder.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the returnType of this Finder.
	 * @return the returnType
	 */
	public String getReturnType() {
		return returnType;
	}
	/**
	 * Sets the returnType of this Finder.
	 * @param returnType the returnType to set
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	/**
	 * Returns the finderColumn of this Finder.
	 * @return the finderColumn
	 */
	public List<FinderColumn> getFinderColumns() {
		return finderColumns;
	}

	/**
	 * Sets the finderColumn of this Finder.
	 * @param finderColumn the finderColumn to set
	 */
	public void setFinderColumns(List<FinderColumn> finderColumns) {
		this.finderColumns = finderColumns;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toString(true);
	}
	
	private String toString(boolean isTrue) {
		StringBuilder resultSb  = new StringBuilder();
		String toString = "Finder [name=" + name + ", returnType=" + returnType
				+ ", finderColumns=" + finderColumns + "]\n";
		resultSb.append(toString);
		
		if(this.getFinderColumns() != null) {
			for(FinderColumn column : this.getFinderColumns()){
				resultSb.append("\t-------")
						.append(column.toString())
						.append("\n");
			}
		}
		
		return resultSb.toString();
	}
}
