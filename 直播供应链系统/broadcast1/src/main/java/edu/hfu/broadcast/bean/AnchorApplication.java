package edu.hfu.broadcast.bean;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*申请表信息表*/
@SuppressWarnings("serial")
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class AnchorApplication extends SysBaseTable implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
	private Integer applicationId; // 申请表编号
	@Column(length = 100, nullable = false)
	private String businessCategory; // 商品类别
	@Column(length = 10)
	private String commission; // 预期佣金
	@Column(length = 10)
	private String service; // 预期服务费
	@Column(length = 300)
	private String memo; // 备注
	@Column(length = 300)
	private String reason; // 拒绝理由
	@Column(length = 10, nullable = false)
	private String status; // 状态-未处理-已处理-已拒绝
	
	
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.DETACH)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "anchorId")
	private AnchorInformation anchorInformation;
}

