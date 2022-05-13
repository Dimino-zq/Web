package edu.hfu.broadcast.dao.sysset;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.hfu.broadcast.bean.CommCategories;
import edu.hfu.broadcast.dao.base.BaseDaoImpl;

@Repository
public class MerchKindDao {
	@Resource
	private BaseDaoImpl merchdao;

	public List<CommCategories> getAllCommCategories() throws Exception {
		String hql = "from CommCategories";
		return merchdao.find(hql);
	}
	/*
	 * //查询一级类别 public List<CommCategories> getCommCategoriesOne(CommCategories
	 * categories)throws Exception { String hql = "from CommCategories "; int index
	 * = 0; List<Object> params = new ArrayList<Object>(); if (null != categories) {
	 * if(null != categories.getCategorylevel()
	 * &&!"".equals(categories.getCategorylevel())) { hql += (index == 0 ? "where"
	 * :"and") + "  categorylevel ='1' " + index++;
	 * params.add('%'+categories.getCategoryName()+'%'); }
	 * 
	 * }
	 * 
	 * hql += " order by categoryId"; return merchdao.find(hql, params.toArray()); }
	 */

	// 查询
	// --商品名称--
	
	  public List<CommCategories> getCommCategoriesByCon(CommCategories categories)throws Exception { 
		  String hql = "from CommCategories "; 
		  int index = 0;
	  List<Object> params = new ArrayList<Object>(); 
	  if (null != categories) {
		  if(null != categories.getCategoryName() &&!"".equals(categories.getCategoryName())) {
			  hql += (index == 0 ? "where" :"and") + "  categoryName like ?" + index++;
			  params.add('%'+categories.getCategoryName()+'%'); 
			  }
		  if(null!=categories.getCategorylevel()&&!"".equals(categories.getCategorylevel())) {
			  hql+=(index== 0? "where" :"and")+ "  categorylevel like ?"+index++;
	          params.add('%'+categories.getCategorylevel()+'%'); 
	  }
		/*
		 * if (null!=categories.getParent().getCategoryName()&&!"".equals(categories.
		 * getParent().getCategoryName())) { hql+=(index== 0?
		 * " where ":" and ")+"  parent.categoryName  like ?"+index++;
		 * params.add('%'+categories.getParent().getCategoryName()+'%'); }
		 */
	  
	  } 
	  
				  hql += " order by categoryId";
				  return merchdao.find(hql, params.toArray());
	  }
	  
	  public List<CommCategories> getCommCategoriesByCon(CommCategories categories,int pageNo, int pageSize) throws Exception { 
		  String hql ="from CommCategories "; 
		  int index = 0; List<Object> params = new  ArrayList<Object>();
		  if (null != categories) { 
			  if (null != categories.getCategoryName() && !"".equals(categories.getCategoryName())) {
				  hql += (index == 0 ? "where" : "and") + "  categoryName like ?" + index++;
				  params.add('%'+categories.getCategoryName()+'%'); } 
			  if (null != categories.getCategorylevel() && !"".equals(categories.getCategorylevel())) {
				  hql += (index == 0 ? "where" : "and") + "  categorylevel like ?" + index++;
				  params.add(categories.getCategorylevel()); }
	  
				/*
				 * if (null != categories.getParent().getCategoryName()
				 * &&!"".equals(categories.getParent().getCategoryName())) { hql += (index == 0
				 * ? "where" : "and") + "  parent.categoryName  like ?" + index++;
				 * params.add('%'+categories.getParent().getCategoryName()+'%'); }
				 */
	  
	  } 
		  
		  hql += " order by categorylevel";
		  return merchdao.findByPage(hql,params.toArray(), pageNo, pageSize); }
	  
	  public Integer getCommCategoriesCountByCon(CommCategories categories) throws
	  Exception { String hql = "from CommCategories "; int index = 0; List<Object>
	  params = new ArrayList<Object>(); if (null != categories) { if (null !=
	  categories.getCategoryName() && !"".equals(categories.getCategoryName())) {
	  hql += (index == 0 ? "where" : "and") + "  categoryName like ?" + index++;
	  params.add('%'+categories.getCategoryName()+'%'); } if (null !=
	  categories.getCategorylevel() && !"".equals(categories.getCategorylevel())) {
	  hql += (index == 0 ? "where" : "and") + "  categorylevel  like?" + index++;
	  params.add('%'+categories.getCategorylevel()+'%'); }
	  
	/*
	 * if (null != categories.getParent().getCategoryName()&&
	 * !"".equals(categories.getParent().getCategoryName())) { hql += (index == 0 ?
	 * "where" : "and") + "  parent.categoryName  like ?" + index++;
	 * params.add('%'+categories.getParent().getCategoryName()+'%'); }
	 */
	  
	  } hql += " order by categorylevel"; return merchdao.queryBeanCountByHql(hql,
	  params.toArray());
	  
	  }
	 
	// 增加
	public void saveCommCategories(CommCategories categories) throws Exception {

		merchdao.save(categories);

	}

	// 修改
	public void updCommCategories(CommCategories categories) throws Exception {
		merchdao.update(categories);
	}

	// 删除
	public void deleteCommCategories(CommCategories categories) throws Exception {
		String hql = "delete CommCategories where categoryId=?0";
		merchdao.bulkUpdate(hql, categories.getCategoryId());
	}

	// 根据Id查询
	public CommCategories GetCommCategoriesById(CommCategories categories) throws Exception {
		String hql = "from CommCategories where categoryId = " + categories.getCategoryId();
		return (CommCategories) merchdao.find(hql).get(0);
	}

	/**
	 * 获取父类别id查询子类
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<CommCategories> getChildCategories(Integer parentId) throws Exception {
		String hql = "from CommCategories where parent.categoryId = ?0";
		return merchdao.find(hql, parentId);
	}

}