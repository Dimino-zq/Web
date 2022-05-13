package edu.hfu.broadcast.dao.sysset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.dao.base.BaseDaoImpl;


@Repository
public class AnchorManageDao {
	
	@Resource
	private BaseDaoImpl baseDao;
	
	/**
	 * 根据id查找主播,查询不到时返回null
	 * @param anchor
	 * @return AnchorInformation
	 * @throws Exception
	 */
	public AnchorInformation getAnchorById(Integer anchorId) throws Exception {
		return baseDao.get(AnchorInformation.class, anchorId);
	}

	/**
	 * 查找所有主播，返回主播的List集合
	 * @return List
	 * @throws Exception
	 */
	public List<AnchorInformation> getAllAnchor() throws Exception {
		String hql = "from AnchorInformation";
		return baseDao.find(hql);
	}

	/**
	 * 根据条件查询主播，返回主播的List集合
	 * @param anch
	 * @return List
	 * @throws Exception
	 */
	public List<AnchorInformation> getAnchorByCon(AnchorInformation anch) throws Exception {
		String hql = "from AnchorInformation ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != anch) {
			//根据主播姓名查询
			if (null != anch.getAnchorName() && !"".equals(anch.getAnchorName())) {
				hql += (index == 0 ? "where" : "and") + " anchorName like ?" + index++;
				params.add('%' + anch.getAnchorName() + '%');
			}
			//根据主播手机号查询
			if (null != anch.getPhone() && !"".equals(anch.getPhone())) {
				hql += (index == 0 ? "where" : "and") + " phone like ?" + index++;
				params.add('%' + anch.getPhone() + '%');
			}
		}
		hql += " order by anchorId";
		return baseDao.find(hql, params.toArray());
	}

	/**
	 * 根据条件分页查询查询主播，返回主播的List集合
	 * @param anch
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 * @throws Exception
	 */
	public List<AnchorInformation> getAnchorByCon(AnchorInformation anch, int pageNo, int pageSize) throws Exception {
		String hql = "from AnchorInformation ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != anch) {
			//根据主播姓名查询
			if (null != anch.getAnchorName() && !"".equals(anch.getAnchorName())) {
				hql += (index == 0 ? "where" : "and") + " anchorName like ?" + index++;
				params.add('%' + anch.getAnchorName() + '%');
			}
			//根据主播手机号查询
			if (null != anch.getPhone() && !"".equals(anch.getPhone())) {
				hql += (index == 0 ? "where" : "and") + " phone like ?" + index++;
				params.add('%' + anch.getPhone() + '%');
			}
		}
		hql += " order by anchorId";
		return baseDao.findByPage(hql, params.toArray(), pageNo, pageSize);
	}

	/**
	 * 根据条件查询主播，返回查询到记录的数量
	 * @param anch
	 * @return int
	 * @throws Exception
	 */
	public Integer getAnchorCountByCon(AnchorInformation anch) throws Exception {
		String hql = "from AnchorInformation ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != anch) {
			//根据主播姓名查询
			if (null != anch.getAnchorName() && !"".equals(anch.getAnchorName())) {
				hql += (index == 0 ? "where" : "and") + " anchorName like ? " + index++;
				params.add('%' + anch.getAnchorName() + '%');
			}
			//根据主播手机号查询
			if (null != anch.getPhone() && !"".equals(anch.getPhone())) {
				hql += (index == 0 ? "where" : "and") + " phone like ?" + index++;
				params.add('%' + anch.getPhone() + '%');
			}
		}
		hql += " order by anchorId";
		return baseDao.queryBeanCountByHql(hql, params.toArray());
	}

	/**
	 * 保存一条主播信息
	 * @param anchor
	 * @throws Exception
	 */
	public Serializable saveAnchor(AnchorInformation anchor) throws Exception {
		return baseDao.save(anchor);
	}

	/**
	 * 更新一条主播数据
	 * @param anchor
	 * @throws Exception
	 */
	public void updateAnchor(AnchorInformation anchor) throws Exception {
		//baseDao.clear();
		baseDao.update(anchor);
	}

	/**
	 * 删除一条主播数据<br/>
	 * 注意传入的主播对象应是已持久化的（即对该数据先查询一遍）
	 * @param anchor
	 * @throws Exception
	 */
	public void delAnchor(AnchorInformation anchor) throws Exception {
//		String hql = "delete AnchorInformation where anchorId=?0";
//		baseDao.bulkUpdate(hql, anchor);
		baseDao.delete(anchor);
	}

	public List<ComGoodsInformation> getComGoodsByCon(ComGoodsInformation comgoods) throws Exception
	{
		String hql = "from ComGoodsInformation ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != comgoods) {
			//根据商品名称查询
			if (null != comgoods.getGoodsName() && !"".equals(comgoods.getGoodsName())) {
				hql += (index == 0 ? "where" : "and") + " goodsName like ? " + index++;
				params.add('%' + comgoods.getGoodsName() + '%');
			}
			//根据所属企业查询
			if (null != comgoods.getSellerName() && !"".equals(comgoods.getSellerName())) {
				hql += (index == 0 ? "where" : "and") + " sellerName like ?" + index++;
				params.add('%' + comgoods.getSellerName() + '%');
			}
			//根据一级类别查询
			if (null != comgoods.getFristCategory() && !"".equals(comgoods.getFristCategory())) {
				hql += (index == 0 ? "where" : "and") + " fristCategory like ?" + index++;
				params.add('%' + comgoods.getFristCategory() + '%');
			}
			//根据二级类别查询
			if (null != comgoods.getSecondCatogory() && !"".equals(comgoods.getSecondCatogory())) {
				hql += (index == 0 ? "where" : "and") + " secondCatogory like ?" + index++;
				params.add('%' + comgoods.getSecondCatogory() + '%');
			}
			//根据三级类别查询
			if (null != comgoods.getThirdCategory() && !"".equals(comgoods.getThirdCategory())) {
				hql += (index == 0 ? "where" : "and") + " thirdCategory like ?" + index++;
				params.add('%' + comgoods.getThirdCategory() + '%');
			}
		}
		hql += " order by goodsId";
		return baseDao.find(hql, params.toArray());
	}

	public List<GoodsDistribution> getAnchorGoods(AnchorInformation anchor, ComGoodsInformation comgoods) throws Exception {
		String hql = "from GoodsDistribution ";
		List<Object> params = new ArrayList<Object>();
		int index = 0;
		if(null != anchor) {
			//根据主播Id查询
			if (null != anchor.getAnchorId()) {
				hql += (index == 0 ? "where" : "and") + " anchorInformation.anchorId = ?" + index++;
				params.add(anchor.getAnchorId());
			}
		} else {
			throw new RuntimeException("主播Id不存在！");
		}
			
		if (null != comgoods) {
			//根据商品名称查询
			if (null != comgoods.getGoodsName() && !"".equals(comgoods.getGoodsName())) {
				hql += (index == 0 ? "where" : "and") + " comgoodsinformation.goodsName like ?" + index++;
				params.add('%' + comgoods.getGoodsName() + '%');
			}
			//根据所属企业查询
			if (null != comgoods.getSellerName() && !"".equals(comgoods.getSellerName())) {
				hql += (index == 0 ? "where" : "and") + " comgoodsinformation.sellerName like ?" + index++;
				params.add('%' + comgoods.getSellerName() + '%');
			}
			//根据一级类别查询
			if (null != comgoods.getFristCategory() && !"".equals(comgoods.getFristCategory())) {
				hql += (index == 0 ? "where" : "and") + " comgoodsinformation.fristCategory like ?" + index++;
				params.add('%' + comgoods.getFristCategory() + '%');
			}
			//根据二级类别查询
			if (null != comgoods.getSecondCatogory() && !"".equals(comgoods.getSecondCatogory())) {
				hql += (index == 0 ? "where" : "and") + " comgoodsinformation.secondCatogory like ?" + index++;
				params.add('%' + comgoods.getSecondCatogory() + '%');
			}
			//根据三级类别查询
			if (null != comgoods.getThirdCategory() && !"".equals(comgoods.getThirdCategory())) {
				hql += (index == 0 ? "where" : "and") + " comgoodsinformation.thirdCategory like ?" + index++;
				params.add('%' + comgoods.getThirdCategory() + '%');
			}
		}
		hql += " order by distributeId";
		return baseDao.find(hql, params.toArray());
	}

	public void delOldGoods(int anchorId, String oldGoodsIds) throws Exception {
		String hql = "delete GoodsDistribution where comgoodsinformation.goodsId in ("+oldGoodsIds+") and anchorInformation.anchorId =?0";
		baseDao.bulkUpdate(hql, anchorId);
	}

	public void saveAnchorGoods(GoodsDistribution distribution) throws Exception {
		baseDao.save(distribution);
	}
	
}
