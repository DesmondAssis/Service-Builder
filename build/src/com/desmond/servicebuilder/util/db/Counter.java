package com.desmond.servicebuilder.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Counter{
	
	public static long getPrimaryKey(String className) {
		return Counter.getPrimaryKey(className, null);
	}
	
	public static long getPrimaryKey(String className, Connection connParement) {
		long nextId = 1l;
		int count = 0;
		Connection conn = connParement != null ? connParement : DButil.getConnection();
		String selectSql = "select currentId from ec_counter where name = ?";
		String insertSql = "insert into ec_counter values(?,?)";
		String updateSql = "update ec_counter set currentId = ? where name = ?";
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(selectSql);
			ps.setString(1, className);
			rs = ps.executeQuery();
			if(rs.next()) {
				long currentId = rs.getLong("currentId");
				nextId = currentId + 1;
				
				ps = conn.prepareStatement(updateSql);
				ps.setLong(1, nextId);
				ps.setString(2, className);
				count = ps.executeUpdate();
			} else {
				ps = conn.prepareStatement(insertSql);
				ps.setString(1, className);
				ps.setLong(2, nextId);
				count = ps.executeUpdate();
			}
		} catch(SQLException e) {
			logger.error("error", e);
		} finally {
			if(connParement == null) {				
				DButil.close(conn, ps, rs);
			} else {
				//DButil.close(ps, rs);
			}
		}
		
		return count != 0 ? nextId : 1;
	}
	
	private static Logger logger = Logger.getLogger(Counter.class.getName());
}
