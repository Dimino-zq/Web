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

import edu.hfu.broadcast.bean.AnchorApplication;
import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.anchor.AnchorListService;

@RestController
@RequestMapping(value = "/anchorList")
public class anchorListAction {
	@Resource
	AnchorListService anchorlistservice;
	private final Logger LOG = LoggerFactory.getLogger(AnchorDistributeAction.class);
	
	@RequestMapping(value = "/gotoanchorList", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasRole('ROLE_2')")
	public ModelAndView gotocomgoodsinformation() {
		ModelAndView mod = new ModelAndView("anchor/anchorlist.btl");
		return mod;
	}
	
	
	
	@RequestMapping(value = "/getAllApplication", method = { RequestMethod.GET, RequestMethod.POST })
	public List<AnchorApplication> getAllApplication(HttpSession session) throws Exception
	{
		Object anchor=session.getAttribute("anchor");
		List<AnchorApplication> anchorInfo=anchorlistservice.getanchor((AnchorInformation)anchor);
		try {
			
			return (List<AnchorApplication>) anchorInfo;
		} catch (RuntimeException e) {
			e.printStackTrace();
			LOG.debug(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询
	@RequestMapping(value = "/getAnchorApplicationByCon", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasPermission('/getAnchorApplicationByCon','02-03-001')")	//判断是否有查询权限
	public Map<String, Object> getAnchorApplicationByCon(HttpSession session, AnchorApplication anchorApplication, AnchorInformation anchor, int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<AnchorApplication> ls = anchorlistservice.getAnchorApplicationByCon(anchorApplication, page, rows);
			Integer count = anchorlistservice.getAnchorApplicationCountByCon(anchorApplication);
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
