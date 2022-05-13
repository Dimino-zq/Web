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
import lombok.ToString;

/*商品类别*/
@SuppressWarnings("serial")
@Data
@ToString(exclude = {"parent"})
@Entity
@EqualsAndHashCode(callSuper = false)
public class CommCategories extends SysBaseTable implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略
	private Integer categoryId; // 类别编号
	@Column(length = 20)
	private String categoryName; // 类别名称
	@Column(length = 20)
	private String categorylevel; // 类别等级
	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "parentCategoryId")
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
	private  CommCategories parent;

}
