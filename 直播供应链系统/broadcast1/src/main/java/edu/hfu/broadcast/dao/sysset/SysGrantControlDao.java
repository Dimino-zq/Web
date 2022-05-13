package edu.hfu.broadcast.dao.sysset;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.hfu.broadcast.action.sysset.SysGrantCode;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysGrant;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.base.BaseDaoImpl;


@Repository
public class SysGrantControlDao {
	@Resource
	private BaseDaoImpl dao;
	
	public List<SysUser> getAllSysGrantCode() throws Exception {
		String hql = "from SysUser";
		return dao.find(hql);
	}
	
	//查询
	public List<SysCompany> getSysCompanyByCon(SysCompany sysCompa) throws Exception {
		String hql = "from SysCompany ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != sysCompa) {
			if (null != sysCompa.getComName() && !"".equals(sysCompa.getComName())) {
				hql += (index == 0 ? "where" : "and") + "  comName=?" + index++;
				params.add(sysCompa.getComName());
			}
		}
		hql += " order by companyId";
		return dao.find(hql, params.toArray());
	}

	public List<SysCompany> getSysCompanyByConForImport(SysCompany sysCompa) throws Exception {
		String hql = "from SysCompany ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != sysCompa) {
			if (null != sysCompa.getComName() && !"".equals(sysCompa.getComName())) {
				hql += (index == 0 ? "where" : "and") + "  comName=?" + index++;
				params.add(sysCompa.getComName());
			}
		}
		hql += " order by companyId";
		return dao.find(hql, params.toArray());
	}

	public List<SysCompany> getSysGrantControlByCon(SysGrant sysGrant, SysUser sysUser, int pageNo, int pageSize) throws Exception {
		String hql = "from SysUser ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != sysUser) {
			if (null != sysUser.getPhoneNum() && !"".equals(sysUser.getPhoneNum())) {
				hql += (index == 0 ? "where" : "and") + "  phoneNum like ?" + index++;
				params.add('%' + sysUser.getPhoneNum() + '%');
			}	
		}
		hql += " order by userId";
		return dao.findByPage(hql, params.toArray(), pageNo, pageSize);
	}

	public Integer getSysGrantControlCountByCon(SysGrant sysGrant,SysUser sysUser) throws Exception {
		String hql = "from SysUser ";
		int index = 0;
		List<Object> params = new ArrayList<Object>();
		if (null != sysUser) {
			if (null != sysUser.getPhoneNum() && !"".equals(sysUser.getPhoneNum())) {
				hql += (index == 0 ? "where" : "and") + "  phoneNum like ?" + index++;
				params.add('%' + sysUser.getPhoneNum() + '%');
			}	
		}
		hql += " order by userId";
		return dao.queryBeanCountByHql(hql, params.toArray());
	}
	
	//增加
	public void saveSysGrantControl(SysGrant sysGrant) throws Exception {

		dao.save(sysGrant);

	}
	
	//修改
	public void updSysGrantControl(SysUser sysUser) throws Exception {
		dao.update(sysUser);
	}
	//删除
	public void deleteSysGrantControl(SysGrant sysGrant) throws Exception {
		String hql = "delete SysGrant where grantId=?0";
		dao.bulkUpdate(hql, sysGrant.getGrantId());
	}
	//根据Id查询
	public SysCompany GetSysCompanyById(SysCompany sysCompa) throws Exception {
		String hql = "from SysCompany where CompanyId = " + sysCompa.getCompanyId();
		return (SysCompany) dao.find(hql).get(0);
	}

	public List<SysGrant> getGrantById(String grantCode) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from SysGrant where grantCode in (" + grantCode + ") ";
		return dao.find(hql);
	}

	public List<SysUser> getUserCon(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		String hql = "from SysUser where 1=1";
		List<Object> param = new ArrayList<Object>();
		if (null != userId) {
			hql += " and userId = ?0";
			param.add(userId);
		}
		return  dao.find(hql, param.toArray());
	}

	/**
	 * 增加管理员信息
	 * @author tomset
	 * @param user
	 * @throws Exception
	 */
	public void saveAdmin(SysUser user) throws Exception
	{
		dao.save(user);
	}

	/**
	 * 修改管理员信息
	 * @author tomset
	 * @param oldUser
	 * @throws Exception
	 */
	public void updateAdmin(SysUser oldUser) throws Exception
	{
		dao.update(oldUser);
	}

	/**
	 * 删除一个管理员
	 * @author tomset
	 * @param oldUser
	 * @throws Exception
	 */
	public void delAdmin(SysUser user) throws Exception
	{
		dao.delete(user);
	}

	/**
	 * 根据用户名查询管理员
	 * @author tomset
	 * @param phoneNum
	 * @return List<SysUser>
	 * @throws Exception 
	 */
	public List<SysUser> getUserByPhoneNum(String phoneNum) throws Exception
	{
		String hql = "from SysUser where phoneNum=?0";
		return dao.find(hql,phoneNum);
	}
}
