package edu.hfu.broadcast.dao.anchor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.hfu.broadcast.bean.AnchorApplication;
import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.dao.base.BaseDaoImpl;

@Repository
public class AnchorDistributeDao {
	
	@Resource
	private BaseDaoImpl baseDaoImpl;

	public void saveApplication(AnchorApplication application) throws Exception {
		baseDaoImpl.save(application);
	}
	
	public List<GoodsDistribution> getAllanchorgoods() throws Exception {
		String hql = "from GoodsDistribution ";
		return baseDaoImpl.find(hql);
	}
	
	//查询所有主播商品
		public List<GoodsDistribution> getAnchor(AnchorInformation anchor) throws Exception {
			String hql = "from GoodsDistribution where anchorId = ?0";
			return baseDaoImpl.find(hql,anchor.getAnchorId());
		}
	
	
	//查询
		public List<GoodsDistribution> getGoodsDistributionByCon(GoodsDistribution goodsdistribution) throws Exception {
			String hql = "from GoodsDistribution ";
			int index = 0;
			List<Object> params = new ArrayList<Object>();
			if (null != goodsdistribution) {
				if (null != goodsdistribution.getAnchorInformation().getAnchorId()) {
					hql += (index == 0 ? "where" : "and") + "  anchorId = ?" + index++;
					params.add('%' + goodsdistribution.getAnchorInformation().getAnchorId() + '%');
				}
				
	     		if (null != goodsdistribution.getComgoodsinformation().getGoodsName() && !"".equals(goodsdistribution.getComgoodsinformation().getGoodsName())) {
					hql += (index == 0 ? "where" : "and") + "comgoodsinformation.goodsName like ?" + index++;
             		params.add('%' + goodsdistribution.getComgoodsinformation().getGoodsName() + '%');
			}
			
				if (null != goodsdistribution.getMoney() && !"".equals(goodsdistribution.getMoney())) {
					hql += (index == 0 ? "where" : "and") + "  money like ?" + index++;
					params.add('%' + goodsdistribution.getMoney() + '%');
				}
			
				if (null != goodsdistribution.getComgoodsinformation().getGoodsPrice()&& !"".equals(goodsdistribution.getComgoodsinformation().getGoodsPrice())) {
					hql += (index == 0 ? "where" : "and") + "   comgoodsinformation.goodsPrice like ?" + index++;
					params.add('%' + goodsdistribution.getComgoodsinformation().getGoodsPrice() + '%');
				}
			}
			hql += " order by distributeId";
			return baseDaoImpl.find(hql, params.toArray());
		}
		
	public List<GoodsDistribution> getGoodsDistributionByCon( GoodsDistribution goodsdistribution, int pageNo, int pageSize) throws Exception {
		String hql = "from GoodsDistribution ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != goodsdistribution) {
			if (null != goodsdistribution.getAnchorInformation().getAnchorId()) {
				hql += (index == 0 ? "where" : "and") + "  anchorInformation.anchorId = ?" + index++;
				params.add(goodsdistribution.getAnchorInformation().getAnchorId());
			}
			if (null != goodsdistribution.getComgoodsinformation().getGoodsName() && !"".equals(goodsdistribution.getComgoodsinformation().getGoodsName())) {
				hql += (index == 0 ? "where" : "and") + "  comgoodsinformation.goodsName like?" + index++;
				params.add('%' + goodsdistribution.getComgoodsinformation().getGoodsName() + '%');
			}
		
//			if (null != goodsdistribution.getMoney() && !"".equals(goodsdistribution.getMoney())) {
//				hql += (index == 0 ? "where" : "and") + "  money like?" + index++;
//				params.add('%' + goodsdistribution.getMoney() + '%');
//			}
//	
//			if (null != goodsdistribution.getComgoodsinformation().getGoodsPrice()&& !"".equals(goodsdistribution.getComgoodsinformation().getGoodsPrice())) {
//				hql += (index == 0 ? "where" : "and") + " comgoodsinformation.goodsPrice like ?" + index++;
//				params.add('%' + goodsdistribution.getComgoodsinformation().getGoodsPrice() + '%');
//			}
		}
		hql += " order by distributeId";
		return baseDaoImpl.findByPage(hql, params.toArray(), pageNo, pageSize);
	}
	
	public Integer getGoodsDistributionCountByCon(GoodsDistribution goodsdistribution) throws Exception {
		String hql = "from GoodsDistribution ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
        if (null != goodsdistribution) {
        	if (null != goodsdistribution.getAnchorInformation().getAnchorId()) {
				hql += (index == 0 ? "where" : "and") + "  anchorInformation.anchorId = ?" + index++;
				params.add(goodsdistribution.getAnchorInformation().getAnchorId());
			}
			if (null != goodsdistribution.getComgoodsinformation().getGoodsName() && !"".equals(goodsdistribution.getComgoodsinformation().getGoodsName())) {
				hql += (index == 0 ? "where" : "and") + "  comgoodsinformation.goodsName like?" + index++;
				params.add('%' + goodsdistribution.getComgoodsinformation().getGoodsName() + '%');
			}
//			if (null != goodsdistribution.getMoney() && !"".equals(goodsdistribution.getMoney())) {
//				hql += (index == 0 ? "where" : "and") + "  money like ?" + index++;
//				params.add('%' + goodsdistribution.getMoney() + '%');
//			
//			}
//			if (null != goodsdistribution.getComgoodsinformation().getGoodsPrice()&& !"".equals(goodsdistribution.getComgoodsinformation().getGoodsPrice())) {
//				hql += (index == 0 ? "where" : "and") + "  comgoodsinformation.goodsPrice like ?" + index++;
//				params.add('%' + goodsdistribution.getComgoodsinformation().getGoodsPrice() + '%');
//			}
        }
		hql += " order by distributeId";
		return baseDaoImpl.queryBeanCountByHql(hql, params.toArray());
	}

	

}
