//package com.desmond.servicebuilder.run.digester;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.digester.Digester;
//import org.apache.log4j.Logger;
//import org.xml.sax.SAXException;
//
//import com.desmond.servicebuilder.util.GeneratorHelper;
//
//public class Test {
//	private static Logger log = Logger.getLogger(Test.class.getName());
//
//	public static void main(String[] args) {
//		Digester digester = new Digester();
//		digester.setValidating(false);
//		digester.addObjectCreate("foo", "com.desmond.generator.run.digester.Foo");
//		digester.addSetProperties("foo");
//		digester.addObjectCreate("foo/bar", Bar.class);
//		digester.addSetProperties("foo/bar");
//		digester.addSetNext("foo/bar", "addBar", "com.desmond.generator.run.digester.Bar");
//		
//		InputStream input = GeneratorHelper.class.getClassLoader().getResourceAsStream(
//				"com/desmond/generator/run/digester/foobar.xml");
//		try {
//			Foo foo = (Foo) digester.parse(input);
//			log.info(foo);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//}
