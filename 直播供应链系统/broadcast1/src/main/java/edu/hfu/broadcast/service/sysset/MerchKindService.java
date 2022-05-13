package edu.hfu.broadcast.service.sysset;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.hfu.broadcast.bean.CommCategories;
import edu.hfu.broadcast.dao.sysset.MerchKindDao;

@Service
@Transactional
public class MerchKindService {
	@Resource
	private MerchKindDao kinddao;
	public List<CommCategories> getAllCommCategories() throws Exception {
		return kinddao.getAllCommCategories();
	}
	//一级类别获取
	/*
	 * public List<CommCategories> getCommCategoriesOne(CommCategories categories)
	 * throws Exception { return kinddao.getCommCategoriesOne(categories); }
	 */
	//查询
	
	  public List<CommCategories> getCommCategoriesByCon(CommCategories categories)throws Exception {
		  return kinddao.getCommCategoriesByCon(categories); 
		  }
	  
	  public List<CommCategories> getCommCategoriesByCon(CommCategories categories,
	  int pageNo, int pageSize) throws Exception { return
	  kinddao.getCommCategoriesByCon(categories, pageNo, pageSize); } public
	  Integer getCommCategoriesCountByCon(CommCategories categories) throws
	  Exception { return kinddao.getCommCategoriesCountByCon(categories); }
	 


	  //增加
		public String saveCommCategories(CommCategories categories) throws Exception {
			// TODO Auto-generated method stub
			kinddao.saveCommCategories(categories);
			return "succ";
		}
		//修改
		public String updCommCategories(CommCategories categories) throws Exception {
			CommCategories com = kinddao.GetCommCategoriesById(categories);
			com.setCategorylevel(categories.getCategorylevel());
			com.setCategoryName(categories.getCategoryName());
			com.setParent(categories.getParent());
			kinddao.updCommCategories(com);
			return "succ";
		}
		//删除
		public void deleteCommCategories(CommCategories categories) throws Exception {
			kinddao.deleteCommCategories(categories);
		}

		public List<CommCategories> getChildCategories(Integer parentId) throws Exception {
			return kinddao.getChildCategories(parentId);
		}
	

}
