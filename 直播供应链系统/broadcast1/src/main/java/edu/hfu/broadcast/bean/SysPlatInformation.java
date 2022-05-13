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

/*电商平台信息*/
@SuppressWarnings("serial")
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class SysPlatInformation extends SysBaseTable implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
	private Integer platformId; // 平台编号
	@Column(length = 20)
	private String platformName; // 平台名称
	@Column(length = 20)
	private String platformDirector; // 平台负责人
	@Column(length = 20)
	private String directorPhone; // 负责人电话
	@Column(length = 20)
	private String sysAdministrators; // 系统管理员
	@Column(length = 500)
	private String briefIntroduction; // 平台简介
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	private SysUser user;
}
