package com.dao;

import java.util.List;

import com.model.Member;

public interface MemberDao {
     public int insert(Member m);
     public List select();
}
