package edu.hfu.broadcast.bean;

/*用户表*/
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class SysUser extends SysBaseTable implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
	private Integer userId; // 用户编号
	@Column(length = 50)
	private String openId; // 第三方授权
	@Column(length = 20)
	private String loginIp; // 登录ip
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date loginTime; // 登录时间
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date logoutTime; // 退出时间		
	@Column(length = 30, nullable = false)
	private String phoneNum;  //手机号
	@Column(length = 20, nullable = false)
	private String userRole;  //用户角色1：平台人员，2：主播，3：企业管理员
	@Column(length = 50, nullable = false)
	private String password;  //用户密码
	//@ManyToMany(mappedBy = "users")
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.ALL},targetEntity=SysGrant.class)
	
	@JoinTable( name="SysUserGrant", joinColumns={@JoinColumn(name="userId",referencedColumnName="userId")},
	inverseJoinColumns={@JoinColumn(name="grantId",referencedColumnName="grantId")})
	private List<SysGrant> grants;
}
