package edu.hfu.broadcast.action.sysset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.sysset.AnchorManageService;

@RestController
@RequestMapping(value = "/anchor")
public class AnchorManageAction {
	private final Logger LOG = LoggerFactory.getLogger(AnchorManageAction.class);

	@Resource
	AnchorManageService anchorManageservice;
	
	@RequestMapping(value = "/gotoAnchor",method = {RequestMethod.GET,RequestMethod.POST})
	@PreAuthorize("hasRole('ROLE_1')")
	public ModelAndView gotoAnchor()
	{
		ModelAndView mod= new ModelAndView("sysset/anchorManagement.btl");
		return mod;
	}
	
	@RequestMapping(value = "/getAllanchor", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AnchorInformation> getAllAnchor(HttpSession session)
	{
		try {
			if(null == session.getAttribute("user"))
				throw new RuntimeException("查询失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("查询失败：用户类型不匹配！");
			return anchorManageservice.getAllAnchor();
		} catch (RuntimeException e) {
			LOG.debug("{}",e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/getAnchorByCon",method = {RequestMethod.GET,RequestMethod.POST})
	@PreAuthorize("hasPermission('/getAnchorByCon','03-02-001')")
	public Map<String,Object> getAnchorByCon(AnchorInformation anch,int page, int rows, HttpSession session)
	{
		Map<String,Object> map=new HashMap<String, Object>();
		//从session中获取当前用户
		SysUser user = (SysUser) session.getAttribute("user");
		if (null == user)
			throw new RuntimeException("查询失败：未登录状态！");
		else if(!"1".equals(session.getAttribute("userType")))
			throw new RuntimeException("查询失败：用户类型不匹配！");
		try {
			List<AnchorInformation> ls=anchorManageservice.getAnchorByCon(anch,page,rows);
			int count=anchorManageservice.getAnchorCountByCon(anch);
			map.put("rows", ls);
			map.put("total",count);
		} catch (RuntimeException e) {
			LOG.debug("{}",e.getMessage());
			map.put("error", e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//创建主播信息
	@RequestMapping(value = "/saveAnchor",method = {RequestMethod.GET,RequestMethod.POST})
	@PreAuthorize("hasPermission('/saveAnchor','03-02-002')")
	public Map<String, String> saveAnchor(AnchorInformation anchor, HttpSession session)
	{
    	Map<String,String> mess = new HashMap<String, String>();
    	//从session中获取当前登录用户的信息
    	SysUser createUser = (SysUser) session.getAttribute("user");
		try {
			if(null == session.getAttribute("user"))
				throw new RuntimeException("增加失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("增加失败：用户类型不匹配！");
			else if(null == anchor)
				throw new RuntimeException("增加失败：获取数据失败！");
			else if(anchorManageservice.checkForm(anchor))
				mess.put("tip", anchorManageservice.saveAnchor(anchor, createUser.getPhoneNum()));		//目前用户名即为电话号码
		} catch (RuntimeException e) {
			LOG.debug("{}",e.getMessage());
			mess.put("tip", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mess;
	}
	
	//更新主播信息
	@RequestMapping(value = "/updateAnchor",method = {RequestMethod.GET,RequestMethod.POST})
	@PreAuthorize("hasPermission('/updateAnchor','03-02-003')")
	public Map<String,String> updateStudent(AnchorInformation anchor, HttpSession session)
	{
    	Map<String,String> mess = new HashMap<String, String>();
    	//从session中获取当前登录用户的信息
    	SysUser updUser = (SysUser) session.getAttribute("user");
		try {
			if(null == session.getAttribute("user"))
				throw new RuntimeException("修改失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("修改失败：用户类型不匹配！");
			else if(null == anchor || null == anchor.getAnchorId())
				throw new RuntimeException("修改失败：请选择一条数据！");
			else if(anchorManageservice.checkForm(anchor))
				mess.put("tip", anchorManageservice.updateAnchor(anchor, updUser.getPhoneNum()));		//目前用户名即为电话号码
		} catch (RuntimeException e) {
			LOG.debug("{}",e.getMessage());
			mess.put("tip", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mess;
	}
	
	//删除主播信息
    @RequestMapping(value = "/delAnchor",method = {RequestMethod.GET,RequestMethod.POST})
    @PreAuthorize("hasPermission('/delAnchor','03-02-004')")
    public Map<String,String> delAnchor(Integer anchorId, HttpSession session)
    {
    	Map<String,String> mess = new HashMap<String, String>();
		try {
			if(null == session.getAttribute("user"))
				throw new RuntimeException("删除失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("删除失败：用户类型不匹配！");
			else if(null == anchorId)
				throw new RuntimeException("删除失败：请选择一条数据！");
			else
				mess.put("tip", anchorManageservice.delAnchor(anchorId));
		} catch (RuntimeException e) {
			LOG.debug("{}",e.getMessage());
			mess.put("error", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mess;
    }
    
    /**
     * 根据条件查询商品，查询的条件有商品类别（三级类别都可以查询）、商品名称、所属企业
     * @param session
     * @param comgoods
     * @param page
     * @param rows
     * @return
     */
	@RequestMapping(value = "/getComgoodsByCon", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getComgoodsByCon(HttpSession session,AnchorInformation anchor, ComGoodsInformation comgoods) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(anchor);
		Object user = session.getAttribute("user");
		try {
			if(null == user)
				throw new RuntimeException("查询失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("查询失败：用户类型不匹配！");
			if(null != anchor && null != anchor.getAnchorId())
			{
				List<GoodsDistribution> anchorGoods = anchorManageservice.getAnchorGoods(anchor, comgoods);
				map.put("anchorGoods", anchorGoods);
			}
			List<ComGoodsInformation> allGoods = anchorManageservice.getComGoodsByCon(comgoods);
			map.put("rows", allGoods);
		} catch (RuntimeException e) {
			LOG.debug("{}",e.getMessage());
			map.put("error", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e.toString());
		}
		return map;
	}
	
	@RequestMapping(value = "/savaAnchorGoods", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, String> savaAnchorGoods(@RequestBody List<GoodsDistribution> goodsDistributions, @RequestParam(value = "anchorId") String anchorId, @RequestParam(value = "oldGoodsIds") String oldGoodsIds, HttpSession session)
	{
    	Map<String,String> mess = new HashMap<String, String>();
    	//从session中获取当前登录用户的信息
    	SysUser createUser = (SysUser) session.getAttribute("user");
		try {
			if(null == session.getAttribute("user"))
				throw new RuntimeException("保存失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("保存失败：用户类型不匹配！");
			else if(null == anchorId || "".equals(anchorId))
				throw new RuntimeException("保存失败：获取主播信息失败！");
			else
				mess.put("tip", anchorManageservice.saveAnchorGoods(Integer.parseInt(anchorId), goodsDistributions, oldGoodsIds, createUser.getPhoneNum()));		//目前用户名即为电话号码
		} catch (RuntimeException e) {
			LOG.debug("{}",e.getMessage());
			mess.put("tip", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mess;
	}
	
}
