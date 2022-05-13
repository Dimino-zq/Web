package edu.hfu.broadcast.action.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.CommCategories;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.company.GoodsInformationService;

@RestController
@RequestMapping(value = "/comgoods")
public class ComGoodsInformationAction {
	@Resource
	GoodsInformationService goodsinformationservice;
	@RequestMapping(value = "/gotocomgoodsinformation", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize("hasRole('ROLE_3')")
	public ModelAndView gotocomgoodsinformation() {
		ModelAndView mod = new ModelAndView("company/company.btl");
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
				ls =goodsinformationservice.getAnchorCommToCompany(nowUser,comgoods,page,rows);
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
		
	
	//查询所有
	@RequestMapping(value = "/getAllComgoods", method = { RequestMethod.GET, RequestMethod.POST })
	public List<ComGoodsInformation> getAllComgoods() {

		try {
			return goodsinformationservice.getAllGoodsInformation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询
		@RequestMapping(value = "/getComgoodsByCon", method = { RequestMethod.GET, RequestMethod.POST })
		@PreAuthorize("hasPermission('/getComgoodsByCon','01-01-001')")
		public Map<String, Object> getComgoodsByCon(HttpSession session, ComGoodsInformation comgoods, int page, int rows) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				Object user = session.getAttribute("user");
				SysCompany company =(SysCompany)session.getAttribute("company");

				SysUser nowUser = (SysUser) user;
				
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
		
				
		
		  //查询1级
		  
		  @RequestMapping(value = "/getComgoodsLevelFri", method = { RequestMethod.GET,
					RequestMethod.POST })
			public List<CommCategories> getComgoodsLevelFri(ComGoodsInformation comgoods,String level) {

				try {
					return goodsinformationservice.getComgoodsLevelFri(level);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		 
         //查询父级
		  
		  @RequestMapping(value = "/getComgoodsParents", method = { RequestMethod.GET,
					RequestMethod.POST })
			public List<CommCategories> getComgoodsParents(CommCategories commCategories,String level) {

				try {
					System.out.println("++++++++++++++++++");
					System.out.println(commCategories);
					return goodsinformationservice.getComgoodsParents(commCategories);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		  
//查询父级
		  
		  @RequestMapping(value = "/getComgoodsParentsThi", method = { RequestMethod.GET,
					RequestMethod.POST })
			public List<CommCategories> getComgoodsParentsThi(CommCategories commCategories,String level) {

				try {
					System.out.println("++++++++++++++++++");
					System.out.println(commCategories);
					return goodsinformationservice.getComgoodsParents(commCategories);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		  
		
		//增加
		@RequestMapping(value = "/savecomgoods", method = { RequestMethod.GET, RequestMethod.POST })
		@PreAuthorize("hasPermission('/savecomgoods','01-01-002')")
		public String savecomgoods(ComGoodsInformation comgoods, HttpSession session) {
			String mess = "";

			try {
				SysUser createUser = (SysUser) session.getAttribute("user");
				Object user = session.getAttribute("user");
				SysCompany company =(SysCompany)session.getAttribute("company");
				SysUser nowUser = (SysUser) user;
				
				
				mess = goodsinformationservice.saveSysCompanyByHand(company,nowUser,comgoods,createUser.getPhoneNum());
			} catch (Exception e) {
				e.printStackTrace();
				mess = e.toString();
			}
			return mess;
		}
		
		//查看图片
				@RequestMapping(value = "/saveimg", method = { RequestMethod.GET, RequestMethod.POST })
				public Map<String, String> saveimg(@RequestParam("fileimg") MultipartFile file, ComGoodsInformation comgoods, HttpSession session) throws Exception {
					Map<String, String> mess = new HashMap<String, String>() ;
					Base64 base64 = new Base64();
					String encodedText = base64.encodeToString(file.getBytes());
					System.out.println(encodedText);
					mess.put("code", encodedText);

					try {
						
						Object user = session.getAttribute("user");
						session.getAttribute("");
					} catch (Exception e) {
						e.printStackTrace();
						
					}
					return mess;
				}

		//增加	
				@RequestMapping(value = "/saveimga", method = { RequestMethod.GET, RequestMethod.POST })
				public Map<String, String>  saveimga(@RequestParam("fileimg") MultipartFile file,  ComGoodsInformation comgoods,HttpSession session,
						HttpServletRequest request) throws Exception {
					Map<String, String> mess = new HashMap<String, String>() ;
					//Map<String, String> mess = sysCompanyService.importSysCompany(file, thisStaff);

					System.out.println(comgoods);
			
					System.out.println("++++++++++++++++");
					System.out.println(file.getName());
					System.out.println(file.getClass());
					System.out.println(file.getContentType());
					System.out.println(file.getBytes());
					System.out.println();
					System.out.println(mess);
					//进行Base64编码
					Base64 base64 = new Base64();
					String encodedText = base64.encodeToString(file.getBytes());
					System.out.println(encodedText);
					mess.put("code", encodedText);
					System.out.println(mess);
					
					String mes = "";

					try {
						SysUser createUser = (SysUser) session.getAttribute("user");
						Object user = session.getAttribute("user");
						SysCompany company =(SysCompany)session.getAttribute("company");
						SysUser nowUser = (SysUser) user;
						comgoods.setGoodsPhoto(encodedText);
						mes = goodsinformationservice.saveSysCompanyByHand(company,nowUser,comgoods,createUser.getPhoneNum());
					} catch (Exception e) {
						e.printStackTrace();
						mes = e.toString();
					}
					mess.put("backmess", mes);
					
					
				    return mess;
					//return mess;
				}
		
		//修改
		@RequestMapping(value = "/updcomgoods", method = { RequestMethod.GET, RequestMethod.POST })
		@PreAuthorize("hasPermission('/updcomgoods','01-01-003')")
		public Map<String, String> updcomgoods(HttpSession session,@RequestParam("fileimg") MultipartFile file, ComGoodsInformation comgoods, String companyTimeVal) throws IOException {
			Map<String, String> mess = new HashMap<String, String>() ;
			String mes = "";
			//进行Base64编码
			Base64 base64 = new Base64();
			String encodedText = base64.encodeToString(file.getBytes());
			try {
				SysUser updUser = (SysUser) session.getAttribute("user");
				comgoods.setUpdDate(new Date());
				comgoods.setGoodsPhoto(encodedText);
				System.out.println("-------------------");
				System.out.println(comgoods);
				System.out.println(comgoods.getGoodsPhoto());
				System.out.println(encodedText);
				mes = goodsinformationservice.updComGoodsInformation(comgoods,updUser.getPhoneNum());

			} catch (Exception e) {
				e.printStackTrace();
				mes = e.toString();
			}
			mess.put("backmess", mes);
			return mess;
		}
		
		//删除
		@RequestMapping(value = "/deletecomgoods", method = { RequestMethod.GET, RequestMethod.POST })
		@PreAuthorize("hasPermission('/deletecomgoods','01-01-004')")
		public String deletecomgoods(ComGoodsInformation comgoods) {
			String mess = "";
			try {
				System.out.println(comgoods.getGoodsId());
				goodsinformationservice.deleteLession(comgoods);
				mess = "deletesuccess";
			} catch (Exception e) {
				e.printStackTrace();
				mess = e.toString();
			}
			return mess;
		}
}
