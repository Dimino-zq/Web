package edu.hfu.broadcast.service.sysset;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.sysset.SysUserDao;
import edu.hfu.broadcast.util.MD5Util;

@Component
public class InitApplicationRunner implements ApplicationRunner {

	@Resource
	SysUserDao userDao;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<SysUser> ls=userDao.findByPhoneNum("admin");
		if (null==ls||ls.size()==0) {
			//增加默认管理员
			SysUser user=new SysUser();
			user.setCreateDate(new Date());
			user.setPhoneNum("admin");
			user.setPassword(MD5Util.string2MD5("admin"));
			user.setCreateUser("系统");
			user.setUserRole("1");
			userDao.save(user);
		}
		
	}

}
