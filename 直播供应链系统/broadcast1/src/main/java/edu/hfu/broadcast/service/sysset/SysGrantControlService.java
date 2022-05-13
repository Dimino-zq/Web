package edu.hfu.broadcast.service.sysset;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.action.sysset.SysGrantCode;
import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysGrant;
import edu.hfu.broadcast.bean.SysUser;

import edu.hfu.broadcast.dao.sysset.SysGrantControlDao;
import edu.hfu.broadcast.util.MD5Util;

@Service
@Transactional
public class SysGrantControlService {
	@Resource
	SysGrantControlDao sysGrantControlDao;
	public List<SysUser> getAllSysGrantControl() throws Exception {
		return sysGrantControlDao.getAllSysGrantCode();
	}
	
	

	public List<SysCompany> getSysCompanyByConForImport(SysCompany sysCompa) throws Exception {

		return sysGrantControlDao.getSysCompanyByConForImport(sysCompa);
	}

	public List<SysCompany> getSysGrantControlByCon(SysGrant sysGrant, SysUser sysUser,int pageNo, int pageSize) throws Exception {
		return sysGrantControlDao.getSysGrantControlByCon(sysGrant,sysUser, pageNo, pageSize);
	}

	public Integer getSysGrantControlCountByCon(SysGrant sysGrant ,SysUser sysUser) throws Exception {
		return sysGrantControlDao.getSysGrantControlCountByCon(sysGrant,sysUser);
	}
	
	//增加
	  public String saveSysGrantControl(SysGrant sysGrant) throws Exception {
	    // TODO Auto-generated method stub
	    
	    sysGrantControlDao.saveSysGrantControl(sysGrant);
	   
	    return "succ";
	  }
	
	
	  public List<SysGrant> getGrantId(String grantCode) throws Exception{
			return sysGrantControlDao.getGrantById(grantCode);
		}
	  
	//修改
	public String updSysGrantControl(Integer userId, String grandCode) throws Exception {
		
		SysUser sysUser=sysGrantControlDao.getUserCon(userId).get(0);
		if(null == grandCode || "".equals(grandCode)) {
			sysUser.setGrants(null);
		}else {
			List<SysGrant> grantIds=getGrantId(grandCode);
			System.out.println("+++++++++");
			System.out.println(grantIds);
			sysUser.setGrants(grantIds);
		}
		System.out.println("+++++++++");
		System.out.println(sysUser);
		sysGrantControlDao.updSysGrantControl(sysUser);
		return "succ";
	}
	//删除
	public void deleteSysGrantControl(SysGrant sysGrant) throws Exception {
		sysGrantControlDao.deleteSysGrantControl(sysGrant);
	}


	/**
	 * 保存管理员信息
	 * @author tomset
	 * @param user
	 * @param phoneNum
	 * @return
	 * @throws Exception 
	 */
	public String saveAdmin(SysUser user, String createUser) throws Exception
	{
		if(null == user)
			throw new RuntimeException("保存失败：管理员信息不存在！");
		else if(null == user.getPhoneNum() || "".equals(user.getPhoneNum()))
			throw new RuntimeException("保存失败：用户名不存在！");
		else if(null == user.getPassword() || "".equals(user.getPassword()))
			throw new RuntimeException("保存失败：密码不存在！");
		List<SysUser> lsUser = sysGrantControlDao.getUserByPhoneNum(user.getPhoneNum());
		if(lsUser.size()>0)
			throw new RuntimeException("保存失败：该用户已存在！");
		user.setPassword(MD5Util.string2MD5(user.getPassword().toString()));
		user.setUserRole("1");
		user.setCreateDate(new Date());
		user.setCreateUser(createUser);
		sysGrantControlDao.saveAdmin(user);
		return "succ";
	}

	/**
	 * 修改管理员信息
	 * @author tomset
	 * @param user
	 * @param phoneNum
	 * @return
	 * @throws Exception 
	 */
	public String updateAdmin(SysUser user, String updUser) throws Exception
	{
		if(null == user)
			throw new RuntimeException("修改失败：管理员信息不存在！");
		else if(null == user.getPhoneNum() || "".equals(user.getPhoneNum()))
			throw new RuntimeException("修改失败：用户名不存在！");
		else if(null == user.getPassword() || "".equals(user.getPassword()))
			throw new RuntimeException("修改失败：密码不存在！");
		SysUser oldUser = sysGrantControlDao.getUserCon(user.getUserId()).get(0);
		if(!"1".equals(oldUser.getUserRole()))		//
			throw new RuntimeException("修改失败：该用户不是管理员！");		//若需要修改其他用户信息则去掉这段代码
		oldUser.setPhoneNum(user.getPhoneNum());
		oldUser.setPassword(MD5Util.string2MD5(user.getPassword().toString()));
		oldUser.setUpdDate(new Date());
		oldUser.setUpdUser(updUser);
		sysGrantControlDao.updateAdmin(oldUser);
		return "succ";
	}

	/**
	 * 删除一个管理员
	 * @author tomset
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public String delAdmin(Integer userId) throws Exception
	{
		String mess = "";
		//判断userId是否为空
		if(null == userId)
			throw new RuntimeException("无法删除,用户id不存在！");
		//根据id查询管理员数据，判断该管理员是否存在
		SysUser oldUser = sysGrantControlDao.getUserCon(userId).get(0);
		if(null == oldUser)
			mess = "删除失败：不存在该管理员数据，请刷新页面后重试！";
		else
		{
			sysGrantControlDao.delAdmin(oldUser);
			mess = "succ";
		}
		return mess;
	}



	
}
