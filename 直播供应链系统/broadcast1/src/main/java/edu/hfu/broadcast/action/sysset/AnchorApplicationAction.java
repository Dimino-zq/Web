package edu.hfu.broadcast.action.sysset;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.hfu.broadcast.bean.AnchorApplication;
import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.sysset.AnchorApplicationService;
import edu.hfu.broadcast.service.sysset.AnchorManageService;

@RestController
@RequestMapping(value = "/application")
public class AnchorApplicationAction {
	private final Logger LOG = LoggerFactory.getLogger(AnchorApplicationAction.class);
	
	@Resource
	AnchorApplicationService applicationService;
	
	@Resource
	private AnchorManageService anchorManageservice;
	
	@RequestMapping(value = "/gotoAnchorApplication",method = {RequestMethod.GET,RequestMethod.POST})
	@PreAuthorize("hasRole('ROLE_1')")
	public ModelAndView gotoAnchorApplication()
	{
		ModelAndView mod= new ModelAndView("sysset/anchorApplication.btl");
		return mod;
	}
	
	@RequestMapping(value = "/getAllApplication", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/getAllApplication','03-05-001')")
	public List<AnchorApplication> getAllApplication(HttpSession session)
	{
		Object user = session.getAttribute("user");
		try {
			if(null == user)
				throw new RuntimeException("查询失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("查询失败：用户类型不匹配！");
			return applicationService.getAllApplication();
		} catch (RuntimeException e) {
			LOG.debug(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/savaAnchorGoodsForApplication", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/savaAnchorGoodsForApplication','03-05-002')")
	public Map<String, String> savaAnchorGoodsForApplication(
			@RequestBody List<GoodsDistribution> goodsDistributions,
			@RequestParam(value = "anchorId") String anchorId,
			@RequestParam(value = "oldGoodsIds") String oldGoodsIds,
			@RequestParam(value = "applicationId") String applicationId,
			HttpSession session)
	{
    	Map<String,String> mess = new HashMap<String, String>();
    	//从session中获取当前登录用户的信息
    	SysUser user = (SysUser) session.getAttribute("user");
		try {
			if(null == session.getAttribute("user"))
				throw new RuntimeException("保存失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("保存失败：用户类型不匹配！");
			else if(null == anchorId || "".equals(anchorId))
				throw new RuntimeException("保存失败：获取主播信息失败！");
			else
			{
				anchorManageservice.saveAnchorGoods(Integer.parseInt(anchorId), goodsDistributions, oldGoodsIds, user.getPhoneNum());
				mess.put("tip", applicationService.approveApplication(Integer.parseInt(applicationId), user.getPhoneNum()));		//目前用户名即为电话号码
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			LOG.debug(e.getMessage());
			mess.put("tip", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mess;
	}
	
	@RequestMapping(value = "/refuseApplication", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, String> refuseApplication(AnchorApplication application, HttpSession session)
	{
    	Map<String,String> mess = new HashMap<String, String>();
    	//从session中获取当前登录用户的信息
    	SysUser user = (SysUser) session.getAttribute("user");
		try {
			if(null == session.getAttribute("user"))
				throw new RuntimeException("保存失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("保存失败：用户类型不匹配！");
			else if(null == application || null == application.getApplicationId())
				throw new RuntimeException("保存失败：获取申请表信息失败！");
			else
				mess.put("tip", applicationService.refuseApplication(application, user.getPhoneNum()));		//目前用户名即为电话号码
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			LOG.debug(e.getMessage());
			mess.put("tip", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mess;
	}
	
	//查询
	@RequestMapping(value = "/getAnchorApplicationByCon1", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getAnchorApplicationByCon1(HttpSession session, AnchorApplication anchorApplication, AnchorInformation anchor, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
    	//从session中获取当前登录用户的信息
    	SysUser user = (SysUser) session.getAttribute("user");
		try {
			if(null == user)
				throw new RuntimeException("查询失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("查询失败：用户类型不匹配！");
			else if(null == anchor)
				throw new RuntimeException("查询失败：数据丢失！");
			anchorApplication.setAnchorInformation(anchor);
			List<AnchorApplication> ls = applicationService.getAnchorApplicationByCon1(anchorApplication, page, rows);
			Integer count = applicationService.getAnchorApplicationCountByCon1(anchorApplication);
			map.put("rows", ls);
			map.put("total", count);
		} catch (RuntimeException e) {
			LOG.debug(e.getMessage());
			map.put("error", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e.getMessage());
		}
		return map;
	}
		
}
