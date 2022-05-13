package edu.hfu.broadcast.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
public class SysGrant implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
	private Integer grantId;
	@Column(length = 20)
	private String grantCode;
	@Column(length = 20)
	private String grantName;
	@Column(length = 100)
	private String grantPath;

}
