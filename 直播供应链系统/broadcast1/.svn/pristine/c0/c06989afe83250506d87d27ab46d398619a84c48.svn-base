package edu.hfu.broadcast.dao.sysset;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.hfu.broadcast.bean.AnchorApplication;
import edu.hfu.broadcast.dao.base.BaseDaoImpl;

@Repository
public class AnchorApplicationDao {
	
	@Resource
	private BaseDaoImpl baseDao;
	
	//主播申请查询byid
	public AnchorApplication getAnchorApplicationById(Integer applicationId) throws Exception
	{
		return baseDao.get(AnchorApplication.class, applicationId);
	}
	
	//查询所有
	public List<AnchorApplication> getAllApplication() throws Exception {
		String hql = "from AnchorApplication";
		return baseDao.find(hql);
	}
	//申请
	public void approveApplication(AnchorApplication applicaiton) throws Exception {
		baseDao.update(applicaiton);
	}
	//条件查询
	//查询
			public List<AnchorApplication> getAnchorApplicationByCon1(AnchorApplication anchorApplication) throws Exception {
				String hql = "from AnchorApplication ";
				int index = 0;
				List<Object> params = new ArrayList<Object>();
				if (null != anchorApplication) {
					if (null != anchorApplication.getStatus()&& !"".equals(anchorApplication.getStatus())) {
						hql += (index == 0 ? "where" : "and") + "  status like?" + index++;
						params.add('%' + anchorApplication.getStatus() + '%');
					}
			    if (null != anchorApplication.getAnchorInformation().getAnchorName()&& !"".equals(anchorApplication.getAnchorInformation().getAnchorName())) {
						hql += (index == 0 ? "where" : "and") + "  anchorInformation.anchorName like?" + index++;
						params.add('%' + anchorApplication.getAnchorInformation().getAnchorName() + '%');
					}
					if (null != anchorApplication.getBusinessCategory() && !"".equals(anchorApplication.getBusinessCategory())) {
						hql += (index == 0 ? "where" : "and") + "  businessCategory like?" + index++;
						params.add('%' + anchorApplication.getBusinessCategory() + '%');
					}
				}
				hql += " order by applicationId";
				return baseDao.find(hql, params.toArray());
			}
			public List<AnchorApplication> getAnchorApplicationByCon1(AnchorApplication anchorApplication, int pageNo, int pageSize) throws Exception {
				String hql = "from AnchorApplication ";
				int index = 0;
				List<Object> params = new ArrayList<Object>();
				if (null != anchorApplication) {
					if (null != anchorApplication.getStatus()&& !"".equals(anchorApplication.getStatus())) {
						hql += (index == 0 ? "where" : "and") + "  status like?" + index++;
						params.add('%' + anchorApplication.getStatus() + '%');
					}
					if (null != anchorApplication.getAnchorInformation().getAnchorName()&& !"".equals(anchorApplication.getAnchorInformation().getAnchorName())) {
						hql += (index == 0 ? "where" : "and") + "  anchorInformation.anchorName like?" + index++;
						params.add('%' + anchorApplication.getAnchorInformation().getAnchorName() + '%');
					}
					if (null != anchorApplication.getBusinessCategory() && !"".equals(anchorApplication.getBusinessCategory())) {
						hql += (index == 0 ? "where" : "and") + "  businessCategory like?" + index++;
						params.add('%' + anchorApplication.getBusinessCategory() + '%');
					}
				}
				hql += " order by applicationId";
				System.out.println(hql);
				return baseDao.findByPage(hql, params.toArray(), pageNo, pageSize);
			}

			public Integer getAnchorApplicationCountByCon1(AnchorApplication anchorApplication) throws Exception {
				String hql = "from AnchorApplication ";
				int index = 0;
				List<Object> params = new ArrayList<Object>();
				if (null != anchorApplication) {
					if (null != anchorApplication.getStatus()&& !"".equals(anchorApplication.getStatus())) {
						hql += (index == 0 ? "where" : "and") + "  status like?" + index++;
						params.add('%' + anchorApplication.getStatus() + '%');
					}
					if (null != anchorApplication.getAnchorInformation().getAnchorName()&& !"".equals(anchorApplication.getAnchorInformation().getAnchorName())) {
						hql += (index == 0 ? "where" : "and") + "  anchorInformation.anchorName like?" + index++;
						params.add('%' + anchorApplication.getAnchorInformation().getAnchorName() + '%');
					}
					if (null != anchorApplication.getBusinessCategory() && !"".equals(anchorApplication.getBusinessCategory())) {
						hql += (index == 0 ? "where" : "and") + "  businessCategory like?" + index++;
						params.add('%' + anchorApplication.getBusinessCategory() + '%');
					}
				}
				hql += " order by applicationId";
				return baseDao.queryBeanCountByHql(hql, params.toArray());
			}
	
}
