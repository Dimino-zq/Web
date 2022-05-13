package edu.hfu.broadcast.action.sysset;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysPlatInformation;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.sysset.SysCompanyService;
import edu.hfu.broadcast.service.sysset.SystemService;

@RestController
@RequestMapping(value = "/SysPlatInformation")
public class SystemAction {
	@Resource
	SystemService systemservice;
	
	@RequestMapping(value = "/gotoSysPlatInformation", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasRole('ROLE_1')")
	public ModelAndView gotoSysPlatInformation(HttpSession session) throws Exception {
		ModelAndView mod = new ModelAndView("sysset/system.btl");
		Object user = session.getAttribute("user");
		SysUser systema = (SysUser) user;
		systema.getUserId();
		List<SysPlatInformation> paltInformation = systemservice.getPlatInfomation(systema.getUserId());
		if(null!=paltInformation && paltInformation.size()>0) {
			SysPlatInformation platInfo= paltInformation.get(0);
			mod.addObject("platformId",platInfo.getPlatformId()); //Id
			mod.addObject("platformName",platInfo.getPlatformName()); //平台名称
			mod.addObject("platformDirector",platInfo.getPlatformDirector()); //平台负责人
			mod.addObject("directorPhone",platInfo.getDirectorPhone()); //负责人电话
			mod.addObject("sysAdministrators",platInfo.getSysAdministrators()); //系统管理员
			mod.addObject("briefIntroduction",platInfo.getBriefIntroduction()); //平台简介
		} else {
			mod.addObject("platformId", ""); //Id
			mod.addObject("platformName", ""); //平台名称
			mod.addObject("platformDirector", ""); //平台负责人
			mod.addObject("directorPhone", ""); //负责人电话
			mod.addObject("sysAdministrators", ""); //系统管理员
			mod.addObject("briefIntroduction", ""); //平台简介
		}
		return mod;
	}
	
	/*
	 * //查看全部
	 * 
	 * @RequestMapping(value = "/getAllSysPlatInformation", method = {
	 * RequestMethod.GET, RequestMethod.POST }) public List<SysPlatInformation>
	 * getAllSysPlatInformation() {
	 * 
	 * try { return systemservice.getAllSysPlatInformation(); } catch (Exception e)
	 * { e.printStackTrace(); } return null; }
	 */
	
	//修改
	@RequestMapping(value = "/updSysPlatInformation", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/updSysPlatInformation','03-06-001')")
	public String updSysPlatInformation(HttpSession session, SysPlatInformation system) {
		String mess = "";
		//获取session 数据
		Object user = session.getAttribute("user");
		SysUser adminUser = (SysUser) user;
		try {
			if(null != system.getPlatformId()) {
				system.setUpdDate(new Date());
				system.setUpdUser(adminUser.getPhoneNum());
				mess = systemservice.updSysPlatInformation(system,adminUser.getUserId());
			} else {
				system.setCreateDate(new Date());
				system.setCreateUser(adminUser.getPhoneNum());
				system.setUser(adminUser);
				mess = systemservice.addSysPlatInformation(system);
			}
		}catch (Exception e) {
			e.printStackTrace();
			mess = e.toString();
		}
		return mess;
	}
		
	
		//改密码
		@RequestMapping(value = "/updSysPlatInformationPassword", method = { RequestMethod.GET, RequestMethod.POST })
		@PreAuthorize("hasPermission('/updSysPlatInformationPassword','03-06-002')")
		public String updStudentInformationPassword(HttpSession session, String newpassword, String oldpassword) {
			String mess = "";

			try {
				// SysCompany.setSchool(school);
				/*
				 * Date companyTime = DateUtil.strToDate(companyTimeVal,
				 * "yyyy-MM-dd"); sysCompa.setCompanyTime(companyTime);
				 */
				// sysCompa.setUpdDate(new Date());
				
                Object user = session.getAttribute("user");
				
				SysUser plat = (SysUser) user;
				System.out.println("--------------------------------------");
				System.out.println(newpassword);
				System.out.println(oldpassword);
				System.out.println("--------------------------------------");
				
				mess = systemservice.chgPassword(plat, newpassword, oldpassword);

			} catch (Exception e) {
				e.printStackTrace();
				mess = e.toString();
			}
			return mess;
		}
		
		
		
		
		
		
}
