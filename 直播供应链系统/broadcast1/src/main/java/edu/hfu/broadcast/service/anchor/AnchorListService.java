package edu.hfu.broadcast.service.anchor;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.bean.AnchorApplication;
import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.anchor.AnchorListDao;


@Service
@Transactional
public class AnchorListService {
	@Resource
	AnchorListDao anchorlistdao;
	//获取全部
	public List<AnchorApplication> getAllApplication() throws Exception {
		return anchorlistdao.getAllApplication();
	}
	public List<AnchorApplication> getanchor(AnchorInformation anchor) throws Exception {
		// TODO Auto-generated method stub
		
		return anchorlistdao.getanchor(anchor);
	}
	
	//查询
			public List<AnchorApplication> getAnchorApplicationByCon(AnchorApplication anchorApplication) throws Exception {
				return anchorlistdao.getAnchorApplicationByCon(anchorApplication);
			}

			public List<AnchorApplication> getAnchorApplicationByCon(AnchorApplication anchorApplication, int pageNo, int pageSize) throws Exception {
				return anchorlistdao.getAnchorApplicationByCon(anchorApplication, pageNo, pageSize);
			}

			public Integer getAnchorApplicationCountByCon(AnchorApplication anchorApplication) throws Exception {
				return anchorlistdao.getAnchorApplicationCountByCon(anchorApplication);
			}
			

}
