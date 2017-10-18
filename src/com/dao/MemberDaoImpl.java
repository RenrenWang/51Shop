package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Member;
import com.tools.ChStr;
import com.tools.ConnDB;

public class MemberDaoImpl implements MemberDao {
    private ConnDB conn=new ConnDB();
    private ChStr chStr=new ChStr();
	@Override
	public int insert(Member m) {
	    int ret=-1;
	    if(m.getUsername()!=null){
	    	String  sql="Insert into  tb_Member  (UserName,TrueName,Password,City,address,"
	    			+ "postcode,CardNO,CardType,Tel,Email) values('"
	    			+chStr.chStr(m.getUsername())+"','"
	    			+chStr.chStr(m.getTruename())+"','"
	    			+chStr.chStr(m.getPwd())+"','"
	    			+chStr.chStr(m.getCity())+"','"
	    			+chStr.chStr(m.getAddress())+"','"
	    			+chStr.chStr(m.getPostcode())+"','"
	    			+chStr.chStr(m.getCardno())+"','"
	    			+chStr.chStr(m.getCardtype())+"','"
	    			+chStr.chStr(m.getTel())+"','"
	    			+chStr.chStr(m.getEmail())+"')";
	    		
	    	System.out.print(sql);
	    	ret=conn.executeUpdate(sql);
	   }else{
		   ret=0;
	   }
	    conn.close();
		return ret;
	}

	@Override
	public List<Member> select() {
		Member form=null;
		List<Member> list =new ArrayList<Member>();
		String sql="select * from tb_member";
		ResultSet rs = conn.executeQuery(sql);
		try {
			while(rs.next()){
				form=new Member();
				form.setID(Integer.valueOf(rs.getString(1)));
				list.add(form);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.close();
		return list;
	}

}
