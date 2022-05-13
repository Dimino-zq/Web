package edu.hfu.broadcast.dao.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.CommCategories;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.base.BaseDaoImpl;

@Repository
public class GoodsInformationDao {
	@Resource
	private BaseDaoImpl dao;
	
	public List<ComGoodsInformation> getAllGoodsInformation() throws Exception {
		String hql = "from ComGoodsInformation";
		return dao.find(hql);
	}
	
	//查询
	public List<ComGoodsInformation> getComGoodsInformationByCon(SysCompany company, ComGoodsInformation comgoods) throws Exception {
		String hql = "from ComGoodsInformation ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != comgoods) {
			if (null != comgoods.getGoodsName() && !"".equals(comgoods.getGoodsName())) {
				hql += (index == 0 ? "where" : "and") + "  goodsName like?" + index++;
				params.add('%' + comgoods.getGoodsName() + '%');
			}
		}
		if (null != comgoods) {
			if (null != comgoods.getBrandName() && !"".equals(comgoods.getBrandName())) {
				hql += (index == 0 ? "where" : "and") + "  brandName like?" + index++;
				params.add('%' + comgoods.getBrandName() + '%');
			}
		}
		hql += " order by goodsId";
		return dao.find(hql, params.toArray());
	}
	public List<ComGoodsInformation> getComGoodsInformationByCon(SysCompany company, SysUser nowUser, ComGoodsInformation comgoods, int pageNo, int pageSize) throws Exception {
		String hql = "from ComGoodsInformation goods ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != comgoods) {
			if (null != comgoods.getGoodsName() && !"".equals(comgoods.getGoodsName())) {
				hql += (index == 0 ? "where" : " and") + "  goodsName like?" + index++;
				params.add('%' + comgoods.getGoodsName() + '%');
			}
			if (null != comgoods.getFristCategory() && !"".equals(comgoods.getFristCategory())) {
				hql += (index == 0 ? "where" : " and") + "  fristCategory =? " + index++;
				params.add(  comgoods.getFristCategory());
			}
			if (null != comgoods.getSecondCatogory() && !"".equals(comgoods.getSecondCatogory())) {
				hql += (index == 0 ? "where" : " and") + "  secondCatogory =? " + index++;
				params.add(comgoods.getSecondCatogory() );
			}
			if (null != comgoods.getThirdCategory() && !"".equals(comgoods.getThirdCategory())) {
				hql += (index == 0 ? "where" : " and") + "  thirdCategory =? " + index++;
				params.add(comgoods.getThirdCategory() );
			}
		}
		if (null != comgoods) {
			if (null != comgoods.getBrandName() && !"".equals(comgoods.getBrandName())) {
				hql += (index == 0 ? "where" : " and") + "  brandName like?" + index++;
				params.add('%' + comgoods.getBrandName() + '%');
			}
		}
		if (null != company) {
			if (null != company.getComName() && !"".equals(company.getComName())) {
			//	hql += (index == 0 ? "where" : "and") + "  goods.sysCompany.companyId=?" + index++;
			//	params.add(nowUser.getUserId());
				hql += (index == 0 ? "where" : " and") + "  sellerName='" +company.getComName()  +"'";
			}
		}
		hql += " order by goodsId";
		System.out.println(hql);
		return dao.findByPage(hql, params.toArray(), pageNo, pageSize);
		//return dao.findByPage(hql,  pageNo, pageSize);
	}

	public Integer getComGoodsInformationCountByCon(SysCompany company,SysUser nowUser,ComGoodsInformation comgoods) throws Exception {
		String hql = "from ComGoodsInformation goods ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != comgoods) {
			if (null != comgoods.getGoodsName() && !"".equals(comgoods.getGoodsName())) {
				hql += (index == 0 ? "where" : "and") + "  goodsName like?" + index++;
				params.add('%' + comgoods.getGoodsName() + '%');
			}
		}
		if (null != comgoods) {
			if (null != comgoods.getBrandName() && !"".equals(comgoods.getBrandName())) {
				hql += (index == 0 ? "where" : "and") + "  brandName like?" + index++;
				params.add('%' + comgoods.getBrandName() + '%');
			}
		}
		if (null != nowUser) {
			if (null != nowUser && !"".equals(nowUser)) {
			//	hql += (index == 0 ? "where" : "and") + "  goods.sysCompany.companyId=?" + index++;
			//	params.add(nowUser.getUserId());
				hql += (index == 0 ? "where" : "and") + "  sellerName='" +company.getComName()  +"'";
			}
		}
		hql += " order by goodsId";
		System.out.println(hql);
		return dao.queryBeanCountByHql(hql, params.toArray());
	}
	
	//增加
		public void saveComGoodsInformation(ComGoodsInformation comgoods) throws Exception {
			dao.save(comgoods);
		}
		
		//修改
		public void updComGoodsInformation(ComGoodsInformation comgoods) throws Exception {
			dao.update(comgoods);
		}
		
		//删除
		public void deleteComGoodsInformation(ComGoodsInformation comgoods) throws Exception {
			String hql = "delete ComGoodsInformation where goodsId=?0";
			System.out.println(hql);
			dao.bulkUpdate(hql, comgoods.getGoodsId());
		}
		
		//根据Id查询
		public ComGoodsInformation GetComGoodsInformationById(ComGoodsInformation comgoods) throws Exception {
			String hql = "from ComGoodsInformation where goodsId = " + comgoods.getGoodsId();
			return (ComGoodsInformation) dao.find(hql).get(0);
		}

		public List<CommCategories> getComgoodsLevelFri(String level) throws Exception {
			// TODO Auto-generated method stub
			String hql = "from CommCategories where categorylevel='1'";
			return dao.find(hql);
		}

		public List<CommCategories> getComgoodsParents(CommCategories commCategories) throws Exception {
			// TODO Auto-generated method stub
			String hql = "from CommCategories where parent='"+commCategories.getCategoryId()+"'";
			return dao.find(hql);
		}

		public List<Map<String,Object>> getAnchorComm(ComGoodsInformation comgoods, int page, int rows) throws Exception {
			// TODO Auto-generated method stub
			//String hql = " from GoodsDistribution goodsDis where goodsDis.comgoodsinformation.goodsId='"+comgoods.getGoodsId()+"'";
			String hql = "select  money as money,goodsDis.anchorInformation.anchorName as anchorName"
					+ " from GoodsDistribution goodsDis where goodsDis.comgoodsinformation.goodsId=?0";
			
			
			System.out.println(hql);
			//return dao.findMap(hql, comgoods.getGoodsId());
			return dao.findByPageMap(hql, comgoods.getGoodsId(), page, rows);
			
		}
		
		public List<Map<String,Object>> getAnchorCommToCompany(ComGoodsInformation comgoods, int page, int rows) throws Exception {
			// TODO Auto-generated method stub
			//String hql = " from GoodsDistribution goodsDis where goodsDis.comgoodsinformation.goodsId='"+comgoods.getGoodsId()+"'";
			String hql = "select  "
					+ "goodsDis.anchorInformation.anchorName as anchorName"
					+ ",goodsDis.anchorInformation.fans as fans"
					+ ",goodsDis.anchorInformation.anchorPlatform as anchorPlatform"
					+ " from GoodsDistribution goodsDis where goodsDis.comgoodsinformation.goodsId=?0";
			
			
			System.out.println(hql);
			//return dao.findMap(hql, comgoods.getGoodsId());
			return dao.findByPageMap(hql, comgoods.getGoodsId(), page, rows);
			
		}

		public Integer getAnchorCommCountByCon(ComGoodsInformation comgoods) throws Exception {
			// TODO Auto-generated method stub
			String hql1 = "from GoodsDistribution goodsDis where goodsDis.comgoodsinformation.goodsId='"+comgoods.getGoodsId()+"'";
			//return dao.queryBeanCount(hql);
			String hql = "from GoodsDistribution goodsDis ";
			int index = 0;
			List<Object> params = new ArrayList<Object>();
			if (null != comgoods) {
				if (null != comgoods.getGoodsId() && !"".equals(comgoods.getGoodsId())) {
					hql += (index == 0 ? "where" : "and") + "  goodsDis.comgoodsinformation.goodsId =?" + index++;
					params.add(comgoods.getGoodsId());
				}
			}
			
			
			hql += " order by goodsId";
			System.out.println(hql);
			return dao.queryBeanCountByHql(hql, params.toArray());
		}

		

		
}
