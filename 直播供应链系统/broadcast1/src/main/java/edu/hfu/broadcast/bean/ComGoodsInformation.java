package edu.hfu.broadcast.bean;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*商品表*/
@SuppressWarnings("serial")
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ComGoodsInformation extends SysBaseTable implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
	private Integer goodsId; // 商品编号
	@Column(length = 20)
	private String goodsName; // 产品名称
	@Column(length = 20)
	private String brandName; // 品牌名字
	
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "longtext")
	private String goodsPhoto; // 品牌图片
	@Column(length = 20)
	private String size; // 规格
	@Column(length = 20)
	private String goodsPrice; // 产品单价
	@Column(length = 20)
	private String transportation; // 物流+包材
	@Column(length = 20)
	private String nomalPrice; // 日常零售价
	@Column(length = 20)
	private String pastprice; // 往常主播价
	@Column(length = 20)
	private String roomPrice; // 直播价到手价
	@Column(length = 20)
	private String discountWay; // 优惠方式
	@Column(length = 20)
	private String inventory; // 库存
	@Column(length = 20)
	private String commission; // 佣金
	@Column(length = 20)
	private String comment; // 备注
	@Column(length = 100)
	private String taobaoCommissionLink; // 淘宝佣金链接
	@Column(length = 100)
	private String taobaoGoodsLink; // 天猫/淘宝产品链接
	@Column(length = 100)
	private String douyinGoodsLink; // 抖音链接
	@Column(length = 100)
	private String kuaishougoodslink; // 快手链接
	@Column(length = 100)
	private String otherlink; // 其他品牌链接
	@Column(length = 20)
	private String productlinkman; // 产品联系人
	@Column(length = 20)
	private String phonenumber; // 联系电话
	@Column(length = 20)
	private String saleTrait; // 卖点
	@Column(length = 20)
	private String fristCategory; // 一级类别
	@Column(length = 20)
	private String secondCatogory; // 二级类别
	@Column(length = 20)
	private String thirdCategory; // 三级类别
	@Column(length = 20)
	private String sellerName; // 卖家名称
	

}
