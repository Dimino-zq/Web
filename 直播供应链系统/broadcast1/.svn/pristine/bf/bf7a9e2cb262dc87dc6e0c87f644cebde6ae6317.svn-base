package edu.hfu.broadcast.action.sysset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.company.GoodsInformationService;
@RestController
@RequestMapping(value = "/sysgoods")
public class SysGoodsAction {
	private static final Logger LOG = LoggerFactory.getLogger(SysGoodsAction.class);
	
	@Resource
	GoodsInformationService goodsinformationservice;

	@RequestMapping(value = "/gotosysgoods", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasRole('ROLE_1')")
	public ModelAndView gotocomgoodsinformation() {
		ModelAndView mod = new ModelAndView("sysset/SysGoods.btl");
		return mod;
    }
	
	//查询
	@RequestMapping(value = "/getAnchorComm", method = { RequestMethod.GET, RequestMethod.POST })
	
	public Map<String, Object> getAnchorComm(HttpSession session, ComGoodsInformation comgoods, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Object user = session.getAttribute("user");
			SysUser nowUser = (SysUser) user;
			/*
			 * SysCompany company =new SysCompany();
			 * company.setComName(nowUser.getCreateUser());
			 */
			System.out.println("+++++++++");
			System.out.println(comgoods);
			
			List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
			ls =goodsinformationservice.getAnchorComm(nowUser,comgoods,page,rows);
			Integer count = goodsinformationservice.getAnchorCommCountByCon(nowUser,comgoods);
			System.out.println(ls.toString());
			StringBuilder sb = new StringBuilder();
			
			System.out.println("+++++++++");
			//System.out.println((Map<String, Object>)ls.get(0).get(sb));
			Map<String, Object> lsall = (Map<String, Object>)ls.get(0);
			// System.out.println(lsall.toString());
			for (Map.Entry<String, Object> entry : lsall.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			
		
			System.out.println("+++++++++");
			//Integer count = goodsinformationservice.getComGoodsInformationCountByCon(comgoods);
			map.put("rows", ls);
			map.put("total", count);

		} catch (Exception e) {
			e.printStackTrace();
			map.put("total", 0);
			map.put("rows", null);

		}
		return map;
	}
	
	//查询
	@RequestMapping(value = "/getComgoodsByCon", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/getComgoodsByCon','03-03-001')")
	public Map<String, Object> getComgoodsByCon(HttpSession session, ComGoodsInformation comgoods, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Object user = session.getAttribute("user");
			SysUser nowUser = (SysUser) user;
			SysCompany company =new SysCompany();
			company.setComName(nowUser.getCreateUser());
			
			
			List<ComGoodsInformation> ls = goodsinformationservice.getComGoodsInformationByCon(company,nowUser,comgoods, page, rows);
			Integer count = goodsinformationservice.getComGoodsInformationCountByCon(company,nowUser,comgoods);
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
	@RequestMapping(value = "/saveimga", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/saveimga','03-03-002')")
	public Map<String, String>  saveimga(ComGoodsInformation comgoods, String imgString, HttpSession session) throws Exception {
		Map<String, String> mess = new HashMap<String, String>() ;
		
		String mes = "";

		try {
			SysUser createUser = (SysUser) session.getAttribute("user");
			Object user = session.getAttribute("user");
			SysUser nowUser = (SysUser) user;
			SysCompany company =new SysCompany();
			company.setComName(nowUser.getCreateUser());
			comgoods.setGoodsPhoto(imgString);
			mes = goodsinformationservice.saveSysCompanyByHand(company,nowUser,comgoods,createUser.getPhoneNum());
		} catch (Exception e) {
			e.printStackTrace();
			mes = e.toString();
		}
		mess.put("backmess", mes);
		
	    return mess;
	}
	
	//修改
	@RequestMapping(value = "/updcomgoods", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/updcomgoods','03-03-003')")
	public Map<String, String> updcomgoods(ComGoodsInformation comgoods, String imgString, HttpSession session) throws IOException {
		Map<String, String> mess = new HashMap<String, String>() ;
		
		String mes = "";

		try {
			SysUser updUser = (SysUser) session.getAttribute("user");
			
			comgoods.setUpdDate(new Date());
			comgoods.setGoodsPhoto(imgString);
			
			mes = goodsinformationservice.updComGoodsInformation(comgoods,updUser.getPhoneNum());
		} catch (Exception e) {
			e.printStackTrace();
			mes = e.toString();
		}
		mess.put("backmess", mes);
		return mess;
	}
}
