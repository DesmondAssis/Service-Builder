package com.desmond.servicebuilder.run.digester;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Foo {
	private String name;
	private List<Bar> barList = new ArrayList<Bar>();

	public void addBar( Bar bar ) {
		barList.add(bar);
	}

	public Bar findBar( int id ) {
		Bar bar = null;
		for(Bar b : this.barList) {
			if(id == b.getId()) {
				bar = b;
				break;
			}
		}
		
		return bar;
	}

	public Iterator<Bar> getBars() {
		return this.barList.iterator();
	}

	public String getName() {
		return this.name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Foo [name=" + name + ", barList=");
		Iterator ite = this.getBars();
		while(ite.hasNext()) {
			sb.append("\r\t\t" + ite.next().toString());
		}
		
		return sb.toString();
	}

}

