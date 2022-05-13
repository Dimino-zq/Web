package edu.hfu.broadcast.action.sysset;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.sysset.SysCompanyService;


@RestController
@RequestMapping(value = "/syscompany")
public class SysCompanyAction {
	private final Logger LOG = LoggerFactory.getLogger(SysCompanyAction.class);
	@Resource
	SysCompanyService syscompanyservice;
	
	@RequestMapping(value = "/gotoSysCompany", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasRole('ROLE_1')")
	public ModelAndView gotoCompany() {
		ModelAndView mod = new ModelAndView("sysset/companyInform.btl");
		return mod;
	}
	
	@RequestMapping(value = "/getAllSysCompany", method = { RequestMethod.GET, RequestMethod.POST })
	public List<SysCompany> getAllSysCompany() {

		try {
			return syscompanyservice.getAllSysCompany();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询
	@RequestMapping(value = "/getSysCompanyByCon", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/getSysCompanyByCon','03-01-001')")
	public Map<String, Object> getSysCompanyByCon(HttpSession session, SysCompany sysCompa, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			List<SysCompany> ls = syscompanyservice.getSysCompanyByCon(sysCompa, page, rows);
			Integer count = syscompanyservice.getSysCompanyCountByCon(sysCompa);
			map.put("rows", ls);
			map.put("total", count);

		} catch (Exception e) {
			e.printStackTrace();
			map.put("total", 0);
			map.put("rows", null);

		}
		return map;
	}
	
	//增加
	@RequestMapping(value = "/saveSysCompany", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/saveSysCompany','03-01-002')")
	public String saveSysCompany(SysCompany sysCompa, HttpServletRequest request) {
		String mess = "";
		try {
			
			HttpSession session = request.getSession();
			SysUser createUser = (SysUser) session.getAttribute("user");
			mess = syscompanyservice.saveSysCompanyByHand(sysCompa,createUser.getPhoneNum());
		} catch (Exception e) {
			e.printStackTrace();
			mess = e.toString();
		}
		return mess;
	}
	
	
	
	//修改
	@RequestMapping(value = "/updSysCompany", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/updSysCompany','03-01-003')")
	public String updSysCompany(HttpSession session, SysCompany sysCompa, String companyTimeVal) {
		String mess = "";

		try {
			SysUser updUser = (SysUser) session.getAttribute("user");
			sysCompa.setUpdDate(new Date());
			mess = syscompanyservice.updSysCompany(sysCompa,updUser.getPhoneNum());

		} catch (Exception e) {
			e.printStackTrace();
			mess = e.toString();
		}
		return mess;
	}
	//删除
	@RequestMapping(value = "/deleteSysCompany", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/deleteSysCompany','03-01-004')")
	public String deleteSysCompany(SysCompany sysCompa,Integer userId) {
		String mess = "";
		try {
			syscompanyservice.deleteLession(sysCompa,userId);
			mess = "deletesuccess";
		} catch (Exception e) {
			e.printStackTrace();
			mess = e.toString();
		}
		return mess;
	}
}
