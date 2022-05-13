package edu.hfu.broadcast.action.sysset;

import java.util.ArrayList;
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



import edu.hfu.broadcast.bean.CommCategories;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.sysset.MerchKindService;


@RestController
@RequestMapping(value = "/merchkind")
public class MerchKindAction {
	private final Logger LOG = LoggerFactory.getLogger(MerchKindAction.class);
	@Resource
	MerchKindService kindservice;
	
	@RequestMapping(value = "/gotoMerchKind", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasRole('ROLE_1')")
	public ModelAndView gotoCompany() {
		ModelAndView mod = new ModelAndView("sysset/merchKind.btl");
		return mod;
	}
	
	@RequestMapping(value = "/getAllMerchKind", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CommCategories> getAllCommCategories() {

		try {
			return kindservice.getAllCommCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询
	
	@RequestMapping(value = "/getCommCategoriesByCon", method = {RequestMethod.GET, RequestMethod.POST }) 
	@PreAuthorize("hasPermission('/getCommCategoriesByCon','03-04-001')")
	public Map<String, Object> getCommCategoriesByCon(HttpSession session, CommCategories categories, int page, int rows) { Map<String, Object> map = new HashMap<String, Object>();
		//从session中获取当前登录用户的信息
		SysUser user = (SysUser) session.getAttribute("user");
		try {
			if(null == user)
				throw new RuntimeException("查询失败：未登录状态！");
			else if(!"1".equals(session.getAttribute("userType")))
				throw new RuntimeException("查询失败：用户类型不匹配！");
			else if(null == categories)
				throw new RuntimeException("查询失败：数据丢失！");
			List<CommCategories> ls = kindservice.getCommCategoriesByCon(categories,page, rows);
			Integer count =kindservice.getCommCategoriesCountByCon(categories); 
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
	 
	
	//增加
		@RequestMapping(value = "/saveCommCategories", method = { RequestMethod.GET, RequestMethod.POST })
		@PreAuthorize("hasPermission('/saveCommCategories','03-04-002')")
		public String saveCommCategories(CommCategories categories) {
			String mess = "";
			try {
				if("1".equals(categories.getCategorylevel()))
					categories.setParent(null);
				mess = kindservice.saveCommCategories(categories);
			} catch (Exception e) {
				e.printStackTrace();
				mess = e.toString();
			}
			return mess;
		}
		
		//修改
		@RequestMapping(value = "/updCommCategories", method = { RequestMethod.GET, RequestMethod.POST })
		@PreAuthorize("hasPermission('/updCommCategories','03-04-003')")
		public String updSysCompany(HttpSession session, CommCategories categories) {
			String mess = "";

			try {
				mess = kindservice.updCommCategories(categories);

			} catch (Exception e) {
				e.printStackTrace();
				mess = e.toString();
			}
			return mess;
		}
		//删除
		@RequestMapping(value = "/deleteCommCategories", method = { RequestMethod.GET, RequestMethod.POST })
		@PreAuthorize("hasPermission('/deleteCommCategories','03-04-004')")
		public String deleteCommCategories(CommCategories categories) {
			String mess = "";
			try {
				kindservice.deleteCommCategories(categories);
				mess = "deletesuccess";
			} catch (Exception e) {
				e.printStackTrace();
				mess = e.toString();
			}
			return mess;
		}
		
	  // 父级类别获取
		@RequestMapping(value = "/getparent", method = { RequestMethod.GET, RequestMethod.POST })
		public List<CommCategories> getparent(String categorylevel){
			CommCategories categories=new CommCategories();
			List<CommCategories> ls = new ArrayList<CommCategories>();
			categories.setCategorylevel(categorylevel);
			try {
				ls=kindservice.getCommCategoriesByCon(categories);
				return ls;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			
		}
		
		  // 父级类别获取
			@RequestMapping(value = "/getchildcategories", method = { RequestMethod.GET, RequestMethod.POST })
			public List<CommCategories> getChildCategories(String parentId){
				System.out.println(parentId);
				try {
					return kindservice.getChildCategories(Integer.valueOf(parentId));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
				
			}
			/*
			 * //一级类别获取
			 * 
			 * @RequestMapping(value = "/getMerchKindone", method = { RequestMethod.GET,
			 * RequestMethod.POST }) public List<CommCategories>
			 * getMerchKindone(CommCategories categories,String categorylevel) {
			 * CommCategories categoriess=new CommCategories(); List<CommCategories> ls =
			 * new ArrayList<CommCategories>(); categoriess.setCategorylevel(categorylevel);
			 * 
			 * if("1".equals(categories.getCategorylevel())) {
			 * 
			 * try {
			 * 
			 * ls=kindservice.getAllCommCategories(); System.out.println(ls.toString());
			 * return ls;
			 * 
			 * } catch (Exception e) { e.printStackTrace(); } } return null;
			 * 
			 * }
			 */
			


}
