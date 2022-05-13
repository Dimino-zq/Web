package edu.hfu.broadcast.dao.anchor;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.base.BaseDaoImpl;

@Repository
public class anchorInformDao {
	@Resource
	BaseDaoImpl baseDaoImpl;
	
	
	//获取全部
		public List<AnchorInformation> getAllAnchorInformation(AnchorInformation anchorinform) throws Exception {
			String hql = "from AnchorInformation";
			return baseDaoImpl.find(hql);
		}
	//根据Id查询
		public AnchorInformation GetAnchorInformationById(Integer anchorId) throws Exception {
			return baseDaoImpl.get(AnchorInformation.class, anchorId);
		}
		
	//修改信息
	public void updAnchorInformation(AnchorInformation anchorinform) throws Exception {
		baseDaoImpl.update(anchorinform);
	}
	
	//更改密码
	public void chgPassword(SysUser user) throws Exception {

		baseDaoImpl.update(user);

		// TODO Auto-generated method stub

	}
	
	//获取用户
	public SysUser getUserById(Integer userId) throws Exception {
		return baseDaoImpl.get(SysUser.class, userId);
	}
			
		
			
			//增加
			public void saveAnchorInformation(AnchorInformation anchorinform) throws Exception {
				baseDaoImpl.save(anchorinform);
			}
}
