package edu.hfu.broadcast.dao.anchor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.hfu.broadcast.bean.AnchorApplication;
import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.base.BaseDaoImpl;

@Repository
public class AnchorListDao {
	@Resource
	private BaseDaoImpl baseDaoImpl;
	
	public List<AnchorApplication> getAllApplication() throws Exception {
		String hql = "from AnchorApplication";
		return baseDaoImpl.find(hql);
	}
	//根据Id查询
			public AnchorApplication GetAnchorApplicationById(Integer applicationId) throws Exception {
				return baseDaoImpl.get(AnchorApplication.class, applicationId);
			}
	//获取主播id
	public List<AnchorApplication> getanchor(AnchorInformation anchor) throws Exception {
	// TODO Auto-generated method stub
	String hql="from AnchorApplication where anchorId =?0";
	return  baseDaoImpl.find(hql,anchor.getAnchorId());
	}
	
	
	//查询
		public List<AnchorApplication> getAnchorApplicationByCon(AnchorApplication anchorApplication) throws Exception {
			String hql = "from AnchorApplication ";
			int index = 0;
			List<Object> params = new ArrayList<Object>();
			if (null != anchorApplication) {
				if (null != anchorApplication.getBusinessCategory() && !"".equals(anchorApplication.getBusinessCategory())) {
					hql += (index == 0 ? "where" : "and") + "  BusinessCategory like?" + index++;
					params.add('%' + anchorApplication.getBusinessCategory() + '%');
				}
			}
			hql += " order by applicationId";
			return baseDaoImpl.find(hql, params.toArray());
		}
		public List<AnchorApplication> getAnchorApplicationByCon(AnchorApplication anchorApplication, int pageNo, int pageSize) throws Exception {
			String hql = "from AnchorApplication ";
			int index = 0;
			List<Object> params = new ArrayList<Object>();
			if (null != anchorApplication) {
				if (null != anchorApplication.getBusinessCategory() && !"".equals(anchorApplication.getBusinessCategory())) {
					hql += (index == 0 ? "where" : "and") + "  BusinessCategory like?" + index++;
					params.add('%' + anchorApplication.getBusinessCategory() + '%');
				}
			}
			hql += " order by applicationId";
			System.out.println(hql);
			return baseDaoImpl.findByPage(hql, params.toArray(), pageNo, pageSize);
		}

		public Integer getAnchorApplicationCountByCon(AnchorApplication anchorApplication) throws Exception {
			String hql = "from AnchorApplication ";
			int index = 0;
			List<Object> params = new ArrayList<Object>();
			if (null != anchorApplication) {
				if (null != anchorApplication.getBusinessCategory() && !"".equals(anchorApplication.getBusinessCategory())) {
					hql += (index == 0 ? "where" : "and") + "  BusinessCategory like?" + index++;
					params.add('%' + anchorApplication.getBusinessCategory() + '%');
				}
			}
			hql += " order by applicationId";
			return baseDaoImpl.queryBeanCountByHql(hql, params.toArray());
		}

}
