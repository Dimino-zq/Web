package edu.hfu.broadcast.service.sysset;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.ComGoodsInformation;
import edu.hfu.broadcast.bean.GoodsDistribution;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.sysset.AnchorManageDao;
import edu.hfu.broadcast.util.MD5Util;

@Service
@Transactional
public class AnchorManageService {
	private final Logger LOG = LoggerFactory.getLogger(AnchorManageService.class);
	
	@Resource
	AnchorManageDao anchordao;
	
	/**
	 * 查询所有主播数据，返回主播数据List集合
	 * @return List
	 * @throws Exception
	 */
	public List<AnchorInformation> getAllAnchor() throws Exception {
		return anchordao.getAllAnchor();
	}

	/**
	 * 根据条件查询主播数据，返回主播数据List集合
	 * @param anch
	 * @return List
	 * @throws Exception
	 */
	public List<AnchorInformation> getAnchorByCon(AnchorInformation anch) throws Exception {
		return anchordao.getAnchorByCon(anch);
	}

	/**
	 * 根据条件分页查询主播数据，返回主播数据List集合
	 * @param anch
	 * @param pageNo
	 * @param pageSize
	 * @return List
	 * @throws Exception
	 */
	public List<AnchorInformation> getAnchorByCon(AnchorInformation anch, int pageNo, int pageSize) throws Exception {
		return anchordao.getAnchorByCon(anch, pageNo, pageSize);
	}

	/**
	 * 根据条件查询主播数据，返回主播数据的条数
	 * @param anch
	 * @return int
	 * @throws Exception
	 */
	public int getAnchorCountByCon(AnchorInformation anch) throws Exception {
		return anchordao.getAnchorCountByCon(anch);
	}
	
	/**
	 * 检查接受到的表单字段是否符合合法
	 * @param anchor
	 * @return Boolean 符合要求返回true，否则抛出相应异常信息
	 * @throws Exception
	 */
	public Boolean checkForm(AnchorInformation anchor) throws Exception
	{
		if(null != anchor)
		{
			if(null == anchor.getAnchorName() || "".equals(anchor.getAnchorName()))
				throw new RuntimeException("请输入主播姓名！");
			else if(anchor.getAnchorName().length()>10)
				throw new RuntimeException("主播姓名长度不应该超过10个字！");
			else if(null == anchor.getPhone() || "".equals(anchor.getPhone()))
				throw new RuntimeException("请输入主播联系方式！");
			else if(anchor.getPhone().length()!=11)
				throw new RuntimeException("目前只支持11位的国内手机号，请输入正确的联系方式！");
			if(null == anchor.getAnchorPlatform() || "".equals(anchor.getAnchorPlatform()))
				throw new RuntimeException("请输入直播平台！");
			else if(anchor.getAnchorPlatform().length()>50)
				throw new RuntimeException("直播平台长度不应该超过50个字！");
			else if(null!=anchor.getFans() && anchor.getFans().length()>10)
				throw new RuntimeException("粉丝数长度不应该超过10个字！");
			else if(null!=anchor.getPitFee() && anchor.getPitFee().length()>10)
				throw new RuntimeException("坑位数长度不应该超过10个字！");
			else if(null!=anchor.getCommission() && anchor.getCommission().length()>10)
				throw new RuntimeException("佣金长度不应该超过10个字！");
			else if(null!=anchor.getService() && anchor.getService().length()>10)
				throw new RuntimeException("服务费长度不应该超过10个字！");
			else if(null!=anchor.getNinetySale() && anchor.getNinetySale().length()>10)
				throw new RuntimeException("近90日销售额长度不应该超过10个字！");
			else if(null!=anchor.getRoomId() && anchor.getRoomId().length()>10)
				throw new RuntimeException("直播间Id长度不应该超过10个字！");
			else if(null!=anchor.getWorkTime() && anchor.getWorkTime().length()>10)
				throw new RuntimeException("主播时间段长度不应该超过10个字！");
			else if(null!=anchor.getBusinessAmount() && anchor.getBusinessAmount().length()>10)
				throw new RuntimeException("招商计划数长度不应该超过10个字！");
			else if(null!=anchor.getThirtyWorkTimes() && anchor.getThirtyWorkTimes().length()>10)
				throw new RuntimeException("30日直播次数长度不应该超过10个字！");
			else if(null!=anchor.getInvestedInformation() && anchor.getInvestedInformation().length()>250)
				throw new RuntimeException("招商信息长度不应该超过250个字！");
			else if(null!=anchor.getPerCustomerPrice() && anchor.getPerCustomerPrice().length()>10)
				throw new RuntimeException("客单价长度不应该超过10个字！");
			else if(null!=anchor.getSaleCategory() && anchor.getSaleCategory().length()>10)
				throw new RuntimeException("产品销售类别长度不应该超过10个字！");
		}
		else
		{
			throw new Exception("参数缺失：anchor");
		}
		return true;
	}
	
	/**
	 * 保存一条主播数据,级联保存用户表中的信息（用户名密码等）
	 * @param anchor 主播实体
	 * @param user 用户实体
	 * @param createUser 操作人（用户名）
	 * @return String 提示信息
	 * @throws Exception
	 */
	public String saveAnchor(AnchorInformation anchor, String createUser) throws Exception {
		String mess = "";
		//判断主播信息是否存在
		if(null == anchor)
			throw new RuntimeException("无法增加：主播信息不存在！");
		else if(null == anchor.getPhone() || "".equals(anchor.getPhone()))
			throw new RuntimeException("无法增加：主播手机号不存在！");
		
		//根据手机号查询主播数据，判断该主播是否已存在
		AnchorInformation anchorTemp = new AnchorInformation();
		anchorTemp.setPhone(anchor.getPhone());
		List<AnchorInformation> anchorlist = anchordao.getAnchorByCon(anchorTemp);
		if(anchorlist.size()!=0)
			mess = "增加失败：该手机号已被使用！";
		else
		{
			//新建用户信息
			SysUser user = new SysUser();
			//创建时间和创建人
			anchor.setCreateDate(new Date());
			anchor.setCreateUser(createUser);
			//级联操作用户表信息
			user.setPhoneNum(anchor.getPhone());		//目前用户名即为电话号码
			user.setPassword(MD5Util.string2MD5("123456"));		//增加用户信息时默认设置密码123456
			user.setUserRole("2");
			user.setCreateDate(new Date());
			user.setCreateUser(createUser);
			anchor.setUser(user);
			if(null != anchordao.saveAnchor(anchor))
			{
				LOG.debug("{}","ID为"+anchor.getAnchorId()+"的主播信息已被管理员"+createUser+"创建");
				mess = "succ";
			}
		}
		return mess;
	}

	/**
	 * 更新一条主播数据,级联修改用户表中的信息（用户名密码等）
	 * @param anchor 主播实体
	 * @param user 用户实体
	 * @param updUser 操作人（用户名）
	 * @return String 提示信息
	 * @throws Exception
	 */
	public String updateAnchor(AnchorInformation anchor, String updUser) throws Exception {
		String mess = "";
		//判断主播信息是否存在
		if(null == anchor || null == anchor.getAnchorId())
			throw new RuntimeException("无法修改,主播信息不存在！");
		//根据id查询主播数据，判断该主播是否存在
		AnchorInformation anch = anchordao.getAnchorById(anchor.getAnchorId());
		if(null == anch)
			mess = "修改失败：不存在该主播数据，请刷新页面后重试！";
		else
		{
			SysUser oldUser = anch.getUser();
			anch.setAnchorName(anchor.getAnchorName());
			anch.setAnchorSex(anchor.getAnchorSex());
			anch.setPhone(anchor.getPhone());
			anch.setAnchorPlatform(anchor.getAnchorPlatform());
			anch.setFans(anchor.getFans());
			anch.setPitFee(anchor.getPitFee());
			anch.setNinetySale(anchor.getNinetySale());
			anch.setRoomId(anchor.getRoomId());
			anch.setBirthDay(anchor.getBirthDay());
			anch.setWorkTime(anchor.getWorkTime());
			anch.setBusinessAmount(anchor.getBusinessAmount());
			anch.setOnlinePhoto(anchor.getOnlinePhoto());
			anch.setThirtyWorkTimes(anchor.getThirtyWorkTimes());
			anch.setInvestedInformation(anchor.getInvestedInformation());
			anch.setPerCustomerPrice(anchor.getPerCustomerPrice());
			anch.setSaleCategory(anchor.getSaleCategory());
			//级联修改用户表信息
			oldUser.setUpdDate(new Date());
			oldUser.setUpdUser(updUser);
			oldUser.setPhoneNum(anchor.getPhone());		//目前用户名即为电话号码
//			if(null != user.getPassword() || !"".equals(user.getPassword()))
//				oldUser.setPassword(MD5Util.string2MD5(user.getPassword().toString()));  //加密修改后的密码,若新密码为空则不修改密码
			anch.setUser(oldUser);
			//修改时间和修改人
			anch.setUpdDate(new Date());
			anch.setUpdUser(updUser);
			anchordao.updateAnchor(anch);
			LOG.debug("{}","ID为"+anch.getAnchorId()+"的主播信息已被管理员"+updUser+"修改");
			mess = "succ";
		}
		return mess;
	}

	/**
	 * 删除一条主播数据
	 * @param anchor
	 * @throws Exception
	 */
	public String delAnchor(Integer anchorId) throws Exception {
		String mess = "";
		//判断anchorId是否为空
		if(null == anchorId)
			throw new RuntimeException("无法删除,主播id不存在！");
		//根据id查询主播数据，判断该主播是否存在
		AnchorInformation anchor = new AnchorInformation();
		anchor = anchordao.getAnchorById(anchorId);
		if(null == anchor)
			mess = "删除失败：不存在该主播数据，请刷新页面后重试！";
		else
		{
			anchordao.delAnchor(anchor);
			LOG.debug("{}","主播"+anchor.getAnchorName()+"的信息已被删除");
			mess = "succ";
		}
		return mess;
	}

	public List<ComGoodsInformation> getComGoodsByCon(ComGoodsInformation comgoods) throws Exception {
		return anchordao.getComGoodsByCon(comgoods);
	}

	public List<GoodsDistribution> getAnchorGoods(AnchorInformation anchor, ComGoodsInformation comgoods) throws Exception {
		return anchordao.getAnchorGoods(anchor, comgoods);
	}

	public String saveAnchorGoods(int anchorId, List<GoodsDistribution> goodsDistributions, String oldGoodsIds, String phoneNum) throws Exception {
		if(null != oldGoodsIds && !"".equals(oldGoodsIds))
			anchordao.delOldGoods(anchorId, oldGoodsIds);
		for(GoodsDistribution distribution : goodsDistributions)
		{
			AnchorInformation anchor = new AnchorInformation();
			anchor.setAnchorId(anchorId);
			distribution.setAnchorInformation(anchor);
			anchordao.saveAnchorGoods(distribution);
		}
		return "succ";
	}
	
}
