package edu.hfu.broadcast.service.company;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.CommCategories;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.company.GoodsInformationDao;

@Service
@Transactional
public class GoodsInformationService {
	@Resource
	GoodsInformationDao goodsinformationdao;
	
	//查询全部
	public List<ComGoodsInformation> getAllGoodsInformation() throws Exception {
		return goodsinformationdao.getAllGoodsInformation();
	}
	
	//查询
		public List<ComGoodsInformation> getComGoodsInformationByCon(SysCompany company, SysUser nowUser, ComGoodsInformation comgoods) throws Exception {
			return goodsinformationdao.getComGoodsInformationByCon(company,comgoods);
		}

		public List<ComGoodsInformation> getComGoodsInformationByCon(SysCompany company,SysUser nowUser, ComGoodsInformation comgoods, int pageNo, int pageSize) throws Exception {
			return goodsinformationdao.getComGoodsInformationByCon(company,nowUser,comgoods, pageNo, pageSize);
		}

		public Integer getComGoodsInformationCountByCon(SysCompany company,SysUser nowUser,ComGoodsInformation comgoods) throws Exception {
			return goodsinformationdao.getComGoodsInformationCountByCon(company,nowUser,comgoods);
		}
		
		//增加
		public String saveSysCompanyByHand(SysCompany company, SysUser nowUser, ComGoodsInformation comgoods,String createUser) throws Exception {
			comgoods.setSellerName(company.getComName());
			comgoods.setCreateDate(new Date());
			comgoods.setCreateUser(createUser);
			goodsinformationdao.saveComGoodsInformation(comgoods);
			return "succ";
		}
		
		//修改
		public String updComGoodsInformation(ComGoodsInformation comgoods, String updUser) throws Exception {
			ComGoodsInformation com = goodsinformationdao.GetComGoodsInformationById(comgoods);
			com.setBrandName(comgoods.getBrandName());
			com.setGoodsName(comgoods.getGoodsName());
			com.setGoodsPhoto(comgoods.getGoodsPhoto());
			com.setSize(comgoods.getSize());
			com.setGoodsPrice(comgoods.getGoodsPrice());
			com.setTransportation(comgoods.getTransportation());
			com.setNomalPrice(comgoods.getNomalPrice());
			com.setPastprice(comgoods.getPastprice());
			com.setRoomPrice(comgoods.getRoomPrice());
			com.setDiscountWay(comgoods.getDiscountWay());
			com.setInventory(comgoods.getInventory());
			com.setCommission(comgoods.getCommission());
			com.setTaobaoCommissionLink(comgoods.getTaobaoCommissionLink());
			com.setTaobaoGoodsLink(comgoods.getTaobaoGoodsLink());
			com.setDouyinGoodsLink(comgoods.getDouyinGoodsLink());
			com.setKuaishougoodslink(comgoods.getKuaishougoodslink());
			com.setOtherlink(comgoods.getOtherlink());
			com.setProductlinkman(comgoods.getProductlinkman());
			com.setPhonenumber(comgoods.getPhonenumber());
			com.setSaleTrait(comgoods.getSaleTrait());
			com.setFristCategory(comgoods.getFristCategory());
			com.setSecondCatogory(comgoods.getSecondCatogory());
			com.setThirdCategory(comgoods.getThirdCategory());
			com.setUpdDate(new Date());
			com.setUpdUser(updUser);
			goodsinformationdao.updComGoodsInformation(com);
			return "succ";
		}
		//删除
		public void deleteLession(ComGoodsInformation comgoods) throws Exception {
			goodsinformationdao.deleteComGoodsInformation(comgoods);
		}

		//查询一级
		public List<CommCategories> getComgoodsLevelFri(String level) throws Exception {
			// TODO Auto-generated method stub
			return goodsinformationdao.getComgoodsLevelFri(level);
		}
        //查询父级
		public List<CommCategories> getComgoodsParents(CommCategories commCategories) throws Exception {
			// TODO Auto-generated method stub
			return goodsinformationdao.getComgoodsParents(commCategories);
		}

		public List<Map<String,Object>> getAnchorComm(SysUser nowUser, ComGoodsInformation comgoods, int page, int rows) throws Exception {
			// TODO Auto-generated method stub
		
			return goodsinformationdao.getAnchorComm(comgoods,page,rows);
		}
		
		public List<Map<String,Object>> getAnchorCommToCompany(SysUser nowUser, ComGoodsInformation comgoods, int page, int rows) throws Exception {
			// TODO Auto-generated method stub
		
			return goodsinformationdao.getAnchorCommToCompany(comgoods,page,rows);
		}

		public Integer getAnchorCommCountByCon(SysUser nowUser, ComGoodsInformation comgoods) throws Exception {
			// TODO Auto-generated method stub
			return goodsinformationdao.getAnchorCommCountByCon(comgoods);
		}
}
