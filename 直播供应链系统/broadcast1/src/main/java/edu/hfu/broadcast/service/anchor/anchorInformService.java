package edu.hfu.broadcast.service.anchor;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.anchor.anchorInformDao;
import edu.hfu.broadcast.dao.sysset.AnchorManageDao;
import edu.hfu.broadcast.service.security.UserPassEncoder;
import edu.hfu.broadcast.util.MD5Util;

@Service
@Transactional
public class anchorInformService {
	@Resource
	anchorInformDao anchordao;
	
	@Resource
	AnchorManageDao anchorManageDao;
	
	//获取全部
	public List<AnchorInformation> getAllAnchorInformation(AnchorInformation anchormation) throws Exception {
		return anchordao.getAllAnchorInformation(anchormation);
		
	}
	
	/**
	 * 主播身份修改主播信息
	 * @param anchorinform
	 * @param userId
	 * @param updUser
	 * @return String
	 * @throws Exception
	 */
	public String updAnchorInformation(AnchorInformation anchorinform, String updUser) throws Exception {
		if(null == anchorinform || null == anchorinform.getAnchorId())
			throw new RuntimeException("修改失败：数据缺失！");
		//获取数据库中原主播信息
		AnchorInformation anchor = anchorManageDao.getAnchorById(anchorinform.getAnchorId());
		if(null == anchor)
			throw new RuntimeException("修改失败：主播信息已不存在！");
		anchor.setAnchorName(anchorinform.getAnchorName());
		anchor.setAnchorSex(anchorinform.getAnchorSex());
		anchor.setAnchorPlatform(anchorinform.getAnchorPlatform());
		anchor.setFans(anchorinform.getFans());
		anchor.setPitFee(anchorinform.getPitFee());
		anchor.setCommission(anchorinform.getCommission());
		anchor.setService(anchorinform.getService());
		anchor.setNinetySale(anchorinform.getNinetySale());
		anchor.setRoomId(anchorinform.getRoomId());
		anchor.setBirthDay(anchorinform.getBirthDay());
		anchor.setWorkTime(anchorinform.getWorkTime());
		anchor.setBusinessAmount(anchorinform.getBusinessAmount());
		anchor.setThirtyWorkTimes(anchorinform.getThirtyWorkTimes());
		anchor.setInvestedInformation(anchorinform.getInvestedInformation());
		anchor.setPerCustomerPrice(anchorinform.getPerCustomerPrice());
		anchor.setSaleCategory(anchorinform.getSaleCategory());
		anchor.setUpdDate(new Date());
		anchor.setUpdUser(updUser);
		anchordao.updAnchorInformation(anchor);
		return "succ";
	}
	
	//更改密码
	public String chgPassword(SysUser user, String newpassword, String oldpassword) throws Exception {
		String mess = "";
		//数据库查密码
		SysUser oldUser =anchordao.getUserById(user.getUserId());
		if(null == oldUser)
			throw new RuntimeException("修改失败：用户信息已不存在！");
		if (oldUser.getPassword().equals(MD5Util.string2MD5(oldpassword)))
		{
			oldUser.setPassword(MD5Util.string2MD5(newpassword));
			oldUser.setUpdDate(new Date());
			oldUser.setUpdUser(user.getPhoneNum());
			anchordao.chgPassword(oldUser);
			mess = "succ";
		}
		else
		{
			mess = "输入的原密码错误！";
		}
		return mess;
	}

	public AnchorInformation getAnchorById(AnchorInformation anchor) throws Exception {
		return anchorManageDao.getAnchorById(anchor.getAnchorId());
	}

	public String updPhoto(AnchorInformation anchor, String encodedText, String updUser) throws Exception {
		if(null == anchor || null == anchor.getAnchorId())
			throw new RuntimeException("保存失败：数据缺失！");
		//获取数据库中原主播信息
		AnchorInformation oldAnchor = anchorManageDao.getAnchorById(anchor.getAnchorId());
		if(null == oldAnchor)
			throw new RuntimeException("修改失败：主播信息已不存在！");
		oldAnchor.setOnlinePhoto(encodedText);;
		oldAnchor.setUpdDate(new Date());
		oldAnchor.setUpdUser(updUser);
		anchordao.updAnchorInformation(oldAnchor);
		return "succ";
	}

	
}
