package edu.hfu.broadcast.service.sysset;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.action.sysset.SysGrantCode;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysGrant;
import edu.hfu.broadcast.bean.SysUser;

import edu.hfu.broadcast.dao.sysset.SysGrantCodeDao;
import edu.hfu.broadcast.util.MD5Util;

@Service
@Transactional
public class SysGrantCodeService {
	@Resource
	SysGrantCodeDao sysGrantCodeDao;
	public List<SysGrant> getAllSysGrantCode() throws Exception {
		return sysGrantCodeDao.getAllSysGrantCode();
	}
	
	

	public List<SysCompany> getSysCompanyByConForImport(SysCompany sysCompa) throws Exception {

		return sysGrantCodeDao.getSysCompanyByConForImport(sysCompa);
	}

	public List<SysCompany> getSysGrantCodeByCon(SysGrant sysGrant, int pageNo, int pageSize) throws Exception {
		return sysGrantCodeDao.getSysGrantCodeByCon(sysGrant, pageNo, pageSize);
	}

	public Integer getSysGrantCodeCountByCon(SysGrant sysGrant) throws Exception {
		return sysGrantCodeDao.getSysGrantCodeCountByCon(sysGrant);
	}
	
	//增加
	  public String saveSysCompanyByHand(SysGrant sysGrant) throws Exception {
	    // TODO Auto-generated method stub
	    
	    sysGrantCodeDao.saveSysGrant(sysGrant);
	   
	    return "succ";
	  }
	
	
	
	//修改
	public String updSysGrantCode(SysGrant sysGrant) throws Exception {
		
		
		sysGrantCodeDao.updSysGrantCode(sysGrant);
		return "succ";
	}
	//删除
	public void deleteSysGrantCode(SysGrant sysGrant) throws Exception {
		sysGrantCodeDao.deleteSysGrantCode(sysGrant);
	}
}
