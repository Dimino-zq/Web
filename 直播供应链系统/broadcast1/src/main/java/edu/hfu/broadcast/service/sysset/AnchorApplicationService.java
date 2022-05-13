package edu.hfu.broadcast.service.sysset;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.bean.AnchorApplication;
import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.dao.anchor.anchorInformDao;
import edu.hfu.broadcast.dao.sysset.AnchorApplicationDao;
import edu.hfu.broadcast.dao.sysset.AnchorManageDao;

@Service
@Transactional
public class AnchorApplicationService {
	@Resource
	AnchorApplicationDao applicationDao;

	public List<AnchorApplication> getAllApplication() throws Exception {
		return applicationDao.getAllApplication();
	}

	public String approveApplication(int applicationId, String updUser) throws Exception
	{
		AnchorApplication application = applicationDao.getAnchorApplicationById(applicationId);
		if(null == application)
			throw new RuntimeException("保存失败，申请表不存在！");
		if("已拒绝".equals(application.getStatus()))
			throw new RuntimeException("保存失败：该申请已被拒绝！");
		application.setStatus("已同意");
		application.setUpdDate(new Date());
		application.setUpdUser(updUser);
		applicationDao.approveApplication(application);
		return "succ";
	}
	
	public String refuseApplication(AnchorApplication application, String updUser) throws Exception
	{
		if(null == application || null == application.getApplicationId())
			throw new RuntimeException("保存失败，请选中一行数据！");
		AnchorApplication ls = applicationDao.getAnchorApplicationById(application.getApplicationId());
		if(null == ls)
			throw new RuntimeException("保存失败，未查询到该申请表！");
		if(!"未处理".equals(ls.getStatus()))
			throw new RuntimeException("保存失败：该申请已被处理，请勿重复操作！");
		ls.setStatus("已拒绝");
		ls.setReason(application.getReason());
		ls.setUpdDate(new Date());
		ls.setUpdUser(updUser);
		applicationDao.approveApplication(ls);
		return "succ";
	}
	//条件查询
	public List<AnchorApplication> getAnchorApplicationByCon1(AnchorApplication anchorApplication) throws Exception {
		return applicationDao.getAnchorApplicationByCon1(anchorApplication);
	}

	public List<AnchorApplication> getAnchorApplicationByCon1(AnchorApplication anchorApplication, int pageNo, int pageSize) throws Exception {
		return applicationDao.getAnchorApplicationByCon1(anchorApplication, pageNo, pageSize);
	}

	public Integer getAnchorApplicationCountByCon1(AnchorApplication anchorApplication) throws Exception {
		return applicationDao.getAnchorApplicationCountByCon1(anchorApplication);
	}
}
