package com.desmond.servicebuilder.model.xml;

public class FinderColumn {
	private String name;

	public FinderColumn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FinderColumn(String name) {
		super();
		this.name = name;
	}

	/**
	 * Returns the name of this FinderColumn.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this FinderColumn.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FinderColumn [name=" + name + "]";
	}
}
