package com.desmond.servicebuilder.model.xml;

/**
 * @author Presley
 * @date 22 May 2014 20:52:03
 * @version 1.0
 * #Column
 */
public class Column {
	private String name;
	private String type;
	private Boolean isPrimary = Boolean.FALSE;
	private Boolean isAutoIncrement = Boolean.FALSE;
	private Boolean isUnique =  Boolean.FALSE;
	private Boolean isNotNull = Boolean.FALSE;

	public Column() {
		super();
	}

	public Column(String name, String type, Boolean isPrimary,
			Boolean isAutoIncrement, Boolean isUnique, Boolean isNotNull) {
		super();
		this.name = name;
		this.type = type;
		this.isPrimary = isPrimary;
		this.isAutoIncrement = isAutoIncrement;
		this.isUnique = isUnique;
		this.isNotNull = isNotNull;
	}

	/**
	 * Returns the name of this Column.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this Column.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the type of this Column.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of this Column.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the isPrimary of this Column.
	 * 
	 * @return the isPrimary
	 */
	public Boolean isPrimary() {
		return isPrimary;
	}

	/**
	 * Sets the isPrimary of this Column.
	 * 
	 * @param isPrimary
	 *            the isPrimary to set
	 */
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	/**
	 * Returns the isAutoIncrement of this Column.
	 * 
	 * @return the isAutoIncrement
	 */
	public Boolean isAutoIncrement() {
		return isAutoIncrement;
	}

	/**
	 * Sets the isAutoIncrement of this Column.
	 * 
	 * @param isAutoIncrement
	 *            the isAutoIncrement to set
	 */
	public void setIsAutoIncrement(Boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	/**
	 * Returns the isUnique of this Column.
	 * @return the isUnique
	 */
	public Boolean isUnique() {
		return isUnique;
	}

	/**
	 * Sets the isUnique of this Column.
	 * @param isUnique the isUnique to set
	 */
	public void setIsUnique(Boolean isUnique) {
		this.isUnique = isUnique;
	}

	/**
	 * Returns the isNotNull of this Column.
	 * @return the isNotNull
	 */
	public Boolean isNotNull() {
		return isNotNull;
	}

	/**
	 * Sets the isNotNull of this Column.
	 * @param isNotNull the isNull to set
	 */
	public void setIsNotNull(Boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", isPrimary="
				+ isPrimary + ", isAutoIncrement=" + isAutoIncrement
				+ ", isUnique=" + isUnique + ", isNotNull=" + isNotNull + "]";
	}
	
}
