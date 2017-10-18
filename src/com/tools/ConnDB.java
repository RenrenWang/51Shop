package com.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDB {
    public Connection conn=null;
    public Statement stmt=null;
    public ResultSet rs=null;
    private static  String dbClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static  String dbUrl="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_shop";
    private static String dbUser="sa";
    private static String dbPwd="123456";
    public static Connection getConnection(){
    	Connection conn=null;
    	try {
			Class.forName(dbClassName).newInstance();
			conn=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(conn!=null){
    		System.out.print("DbConnectionManager.getConnection():"+dbClassName+"\r\n:"+dbUrl+"\r\n"+dbUser+"\r\n"+dbPwd);
    	}
    	
    	return conn;
    }
    /*
     * 数据更新
     * 
     * 
     */
    public int executeUpdate(String sql){
    	int result =0;
    	
    	try {
    		
			stmt=CommonConn();
			result=stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=0;
			e.printStackTrace();
		}
    	try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return result;
    	
    }
    
    public ResultSet executeQuery(String sql){
    
    	try {
			stmt=CommonConn();
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
    }
    public void close(){
    	try {
    	if(rs!=null){
    	
				rs.close();
		
    	}
    	if(stmt!=null){
    		stmt.close();
    	}
    	if(conn!=null){
    		conn.close();
    	}
	    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    }
    public  Statement CommonConn() throws SQLException{
    	conn=getConnection();
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		return stmt;
    }
//    public static void main(String[] args) {
//		if(getConnection()!=null){
//			System.out.print("数据库链接成功");
//		}
//	}
}
