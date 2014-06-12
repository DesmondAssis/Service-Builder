package com.desmond.servicebuilder.run;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.desmond.servicebuilder.exception.NoSuchDBTypeException;
import com.desmond.servicebuilder.model.xml.Builder;
import com.desmond.servicebuilder.util.DaoImplGeneratorHelper;
import com.desmond.servicebuilder.util.GeneratorHelper;
import com.desmond.servicebuilder.util.ModelImplGeneratorHelper;
import com.desmond.servicebuilder.util.SQLGeneratorHelper;
import com.desmond.servicebuilder.util.ServiceImplGeneratorHelper;
import com.desmond.servicebuilder.util.constant.DMConstants;

public class TestRun {

	private static Logger log = Logger.getLogger(TestRun.class.getName());

	public static void main(String[] args) throws JDOMException, IOException {
		// get configuration files.
		Element root = GeneratorHelper.getService();
		
		// transform.
		Builder builder = GeneratorHelper.transformElementToBean(root);
		log.info(builder);
		
		// delete old files.
		String directory = DMConstants.sourceDirectory + StringUtils.replace(builder.getPackateName(), ".", "/");
		log.info("deleting " + directory + "...");
		File dir = new File(directory);
		FileUtils.deleteDirectory(dir);
		
		// generate model.
		ModelImplGeneratorHelper.generateModelAndImpl(builder);
		
		// generate dao.
		DaoImplGeneratorHelper.generateDaoImpl(builder);
		
		// generate service
		ServiceImplGeneratorHelper.generateService(builder);
		
		// generate initial SQL.
		try {
			SQLGeneratorHelper.creteTable(builder);
		} catch (NoSuchDBTypeException e) {
			log.error("error occus", e);
		}
		
		
		// test CURD of user.
//		testCURD();
	}
	
//	private static void testCURD() {
//		User user = (new UserImpl()).mockUserImpl();
//		
//		UserDaoImpl userDao = new UserDaoImpl();
//		userDao.add(user);
//		
//		// update.
//		user.setPrimaryKey(12);
//		userDao.update(user);
//		
//		// read.
//		User testUser = userDao.fetchByPrimaryKey(9);
//		log.info(testUser.getEmail());
//		
//		// delete.
//		userDao.delete(12);
//	}

}
