package edu.hfu.broadcast.dao.sysset;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hfu.broadcast.bean.SysUser;

public interface  SysUserDao extends JpaRepository<SysUser, Integer> {
	
	public List<SysUser> findByPhoneNum(String phoneNum);
}
