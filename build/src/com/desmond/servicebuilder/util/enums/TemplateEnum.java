package com.desmond.servicebuilder.util.enums;

public enum TemplateEnum {
	MODEL(0, "com/desmond/servicebuilder/template/Model.dt"),
	MODEL_IMPL(1, "com/desmond/servicebuilder/template/ModelImpl.dt"),
	DAO(2, ""),
	DAO_IMPL(3, "com/desmond/servicebuilder/template/DaoImpl.dt"),
	SERVICE(4, ""),
	SERVICE_IMPL(5, "com/desmond/servicebuilder/template/LocalServiceImpl.dt"),
	SERVICE_BASE_IMPL(6, "com/desmond/servicebuilder/template/ServiceBaseImpl.dt");

	private int type;
	private String relativeURL;

	private TemplateEnum(int type, String relativeURL) {
		this.type = type;
		this.relativeURL = relativeURL;
	}

	/**
	 * Returns the type of this TemplateEnum.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type of this TemplateEnum.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Returns the relativeURL of this TemplateEnum.
	 * 
	 * @return the relativeURL
	 */
	public String getRelativeURL() {
		return relativeURL;
	}

	/**
	 * Sets the relativeURL of this TemplateEnum.
	 * 
	 * @param relativeURL
	 *            the relativeURL to set
	 */
	public void setRelativeURL(String relativeURL) {
		this.relativeURL = relativeURL;
	}

}
