package edu.hfu.broadcast.service.sysset;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.sysset.SysCompanyDao;
import edu.hfu.broadcast.util.MD5Util;

@Service
@Transactional
public class SysCompanyService {
	@Resource
	SysCompanyDao syscompanydao;
	public List<SysCompany> getAllSysCompany() throws Exception {
		return syscompanydao.getAllSysCompany();
	}
	
	//查询
	public List<SysCompany> getSysCompanyByCon(SysCompany sysCompa) throws Exception {
		return syscompanydao.getSysCompanyByCon(sysCompa);
	}

	public List<SysCompany> getSysCompanyByConForImport(SysCompany sysCompa) throws Exception {

		return syscompanydao.getSysCompanyByConForImport(sysCompa);
	}

	public List<SysCompany> getSysCompanyByCon(SysCompany sysCompa, int pageNo, int pageSize) throws Exception {
		return syscompanydao.getSysCompanyByCon(sysCompa, pageNo, pageSize);
	}

	public Integer getSysCompanyCountByCon(SysCompany sysCompa) throws Exception {
		return syscompanydao.getSysCompanyCountByCon(sysCompa);
	}
	
	//增加
	  public String saveSysCompanyByHand(SysCompany sysCompa,String createUser) throws Exception {
	    //新建用户信息
	    SysUser user = new SysUser();
	    user.setCreateUser(createUser);
	    //创建时间和创建人
	    sysCompa.setCreateDate(new Date());
	    sysCompa.setCreateUser(createUser);
	    user.setPhoneNum(sysCompa.getComPhone());    //目前用户名即为电话号码
	    user.setPassword(MD5Util.string2MD5("123456"));    //增加用户信息时默认设置密码123456
	    user.setUserRole("3");
	    user.setCreateDate(new Date());
	    user.setCreateUser(createUser);
	    sysCompa.setUser(user);
	    syscompanydao.saveSysCompany(sysCompa);
	    syscompanydao.saveSysUser(user);
	    return "succ";
	  }
	
	
	
	//修改
	public String updSysCompany(SysCompany sysCompa,String updUser) throws Exception {
		SysCompany com = syscompanydao.GetSysCompanyById(sysCompa);
		SysUser oldUser = com.getUser();
		com.setComPhone(sysCompa.getComPhone());
		com.setComStartTime(sysCompa.getComStartTime());
		com.setComeail(sysCompa.getComeail());
		com.setComBusiness(sysCompa.getComBusiness());
		com.setComAddress(sysCompa.getComAddress());
		com.setComProfile(sysCompa.getComProfile());
		com.setComName(sysCompa.getComName());
		com.setComContacts(sysCompa.getComContacts());
		com.setBusinessRange(sysCompa.getBusinessRange());
		oldUser.setUpdDate(new Date());
		oldUser.setUpdUser(updUser);
		oldUser.setPhoneNum(sysCompa.getComPhone());		//目前用户名即为电话号码
		com.setUser(oldUser);
		//修改时间和修改人
		com.setUpdDate(new Date());
		com.setUpdUser(updUser);
		syscompanydao.updSysCompany(com);
		return "succ";
	}
	//删除
	public void deleteLession(SysCompany sysCompa, Integer userId) throws Exception {
		syscompanydao.deleteSysCompany(sysCompa,userId);
	}
}
