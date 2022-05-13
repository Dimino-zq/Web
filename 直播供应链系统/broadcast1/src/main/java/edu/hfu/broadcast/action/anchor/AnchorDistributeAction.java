package edu.hfu.broadcast.action.anchor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.hfu.broadcast.action.sysset.AnchorManageAction;
import edu.hfu.broadcast.bean.AnchorApplication;
import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.anchor.AnchorDistributeService;

@RestController
@RequestMapping(value = "/anchorDistribute")
public class AnchorDistributeAction {
	private final Logger LOG = LoggerFactory.getLogger(AnchorDistributeAction.class);
	
	@Resource
	AnchorDistributeService distributeService;
	
	@RequestMapping(value = "/gotoanchorDistribute", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasRole('ROLE_2')")
	public ModelAndView gotocomgoodsinformation() {
		ModelAndView mod = new ModelAndView("anchor/anchordistribute.btl");
		return mod;
	}
	
	//新增商品申请
	@RequestMapping(value = "/saveapplication",method = {RequestMethod.GET,RequestMethod.POST})
	@PreAuthorize("hasPermission('/saveapplication','02-02-002')")
	public Map<String, String> saveApplication(AnchorApplication application, HttpSession session)
	{
    	Map<String,String> mess = new HashMap<String, String>();
    	//从session中获取当前登录用户的信息
    	SysUser createUser = (SysUser) session.getAttribute("user");
    	AnchorInformation anchor = (AnchorInformation) session.getAttribute("anchor");
		try {
			if(null == session.getAttribute("user"))
				throw new RuntimeException("增加失败：未登录状态！");
			else if(!"2".equals(session.getAttribute("userType")))
				throw new RuntimeException("增加失败：用户类型不匹配！");
			else if(null == application)
				throw new RuntimeException("增加失败：获取数据失败！");
			else
				mess.put("tip", distributeService.saveApplication(application, createUser.getPhoneNum(), anchor));		//目前用户名即为电话号码
		} catch (RuntimeException e) {
			LOG.debug(e.getMessage());
			mess.put("tip", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mess;
	}
	//获取主播所有商品列表
	//查询所有
	@RequestMapping(value = "/getAllanchorgoods", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GoodsDistribution> getAllanchorgoods() {

		try {
			List <GoodsDistribution> ls=distributeService.getAllanchorgoods();
			return ls;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//查询
	@RequestMapping(value = "/getGoodsDistributionByCon", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/getGoodsDistributionByCon','02-02-001')")
	public Map<String, Object> getGoodsDistributionByCon(HttpSession session,GoodsDistribution goodsdistribution,ComGoodsInformation inform, int page, int rows ) {
		Map<String, Object> map = new HashMap<String, Object>();
		Object user = session.getAttribute("user");
		try {
			if(null == user)
				throw new RuntimeException("增加失败：未登录状态！");
			else if(!"2".equals(session.getAttribute("userType")))
				throw new RuntimeException("增加失败：用户类型不匹配！");
			AnchorInformation anchor=(AnchorInformation) session.getAttribute("anchor");
			goodsdistribution.setComgoodsinformation(inform);
			goodsdistribution.setAnchorInformation((AnchorInformation) anchor);
			List<GoodsDistribution> ls = distributeService.getGoodsDistributionByCon(goodsdistribution, page, rows);
			
			Integer count =distributeService.getGoodsDistributionCountByCon(goodsdistribution);
			map.put("rows", ls);
			map.put("total", count);

		} catch (Exception e) {
			e.printStackTrace();
			map.put("total", 0);
			map.put("rows", null);

		}
		return map;
	}

}
