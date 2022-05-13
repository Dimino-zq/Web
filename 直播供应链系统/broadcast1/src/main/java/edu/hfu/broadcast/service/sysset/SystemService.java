package edu.hfu.broadcast.service.sysset;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysPlatInformation;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.sysset.SystemDao;
import edu.hfu.broadcast.service.security.UserPassEncoder;

@Service
@Transactional
public class SystemService {
	@Resource
	SystemDao systemdao;

	// 获取全部
	public List<SysPlatInformation> getAllSysPlatInformation(SysPlatInformation system) throws Exception {
		return systemdao.getAllSysPlatInformation(system);
	}

	// 新增
	public String addSysPlatInformation(SysPlatInformation system) throws Exception {
		systemdao.addSysPlatInformation(system);
		return "succ";
	}
	
	// 更新
	public String updSysPlatInformation(SysPlatInformation system, Integer userId) throws Exception {
		
		List<SysPlatInformation> plats = systemdao.getPlatInfomationByUserId(userId);
		if(null!=plats && plats.size()>0) {
			SysPlatInformation plat = plats.get(0);
			plat.setPlatformId(system.getPlatformId());
			plat.setPlatformName(system.getPlatformName());
			plat.setPlatformDirector(system.getPlatformDirector());
			plat.setDirectorPhone(system.getDirectorPhone());
			plat.setSysAdministrators(system.getSysAdministrators());
			plat.setBriefIntroduction(system.getBriefIntroduction());
			plat.setUpdDate(system.getUpdDate());
			plat.setUpdUser(system.getUpdUser());
			systemdao.updSysPlatInformation(plat);
		} else {
			throw new Exception("未查询到当前已有的平台信息，无法更新，请刷新后重试！");
		}
		return "succ";
	}

	public String chgPassword(SysUser plat, String newpassword, String oldpassword) throws Exception {
		UserPassEncoder userPassEncoder = new UserPassEncoder();
		// 数据库查密码
		SysUser platForPassword = getUser(plat).get(0);
		if (userPassEncoder.matches(oldpassword, platForPassword.getPassword())) {
			String newMd = userPassEncoder.encode(newpassword);
			platForPassword.setPassword(newMd);
			systemdao.chgPassword(platForPassword);
			return "succ";

		} else {
			return "passworderror";
		}

	}

	private List<SysUser> getUser(SysUser plat) throws Exception {
		return systemdao.getUser(plat);
	}

	public List<SysPlatInformation> getPlatInfomation(Integer userId) throws Exception {
		return systemdao.getPlatInfomationByUserId(userId);
	}
	public SysCompany getCompanyByUser(String phoneNum)throws Exception {
		return systemdao.getCompanyByUser(phoneNum);
	}
	public AnchorInformation getAnchorByUser(String phoneNum)throws Exception{
		return systemdao.getAnchorByUser(phoneNum);
	}
}
