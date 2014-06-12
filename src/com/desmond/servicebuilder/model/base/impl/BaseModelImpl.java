package com.desmond.servicebuilder.model.base.impl;

import java.sql.Timestamp;

import com.desmond.servicebuilder.model.base.intf.BaseModel;

/**
 * 
 * @author Presley
 * @date 11 Jun 2014 06:58:49
 * @version 1.0
 * @see com.desmond.servicebuilder.model.base.intf.BaseModel
 * #BaseModelImpl
 */
public class BaseModelImpl implements BaseModel{
	private long primaryKey;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	
	/**
	 * Returns the primaryKey of this BaseModelImpl.
	 * @return the primaryKey
	 */
	public long getPrimaryKey() {
		return primaryKey;
	}
	
	/**
	 * Sets the primaryKey of this BaseModelImpl.
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(long primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	/**
	 * Returns the createdDate of this BaseModelImpl.
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the createdDate of this BaseModelImpl.
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Returns the modifiedDate of this BaseModelImpl.
	 * @return the modifiedDate
	 */
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	
	/**
	 * Sets the modifiedDate of this BaseModelImpl.
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
