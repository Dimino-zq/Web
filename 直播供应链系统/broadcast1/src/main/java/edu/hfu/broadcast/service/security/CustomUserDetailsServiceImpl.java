package edu.hfu.broadcast.service.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import edu.hfu.broadcast.bean.SysGrant;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.dao.sysset.SysUserDao;

@Component
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
	private final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);
	@Resource
	SysUserDao userDao;
	@Override
	public UserDetails loadUserByUsernameAndDomain(String userCode, String errorMsg) throws UsernameNotFoundException,Exception {
		if (null==errorMsg) {
			UserDetails usd=null;
			LOG.debug("userCode：+"+userCode);
			
			List<SysUser> ls=userDao.findByPhoneNum(userCode);
			if (null==ls||ls.size()==0) {
				throw new RuntimeException("username: " + userCode + " 不存在..");
			}else {
				SysUser user=ls.get(0);
				List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
				//设置当前用户角色
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+user.getUserRole());
				
				List<SysGrant> grants=user.getGrants();
				for (SysGrant grant:grants) {
					LOG.debug("grant:"+grant.getGrantCode());
					GrantedAuthority grantedAuth= new SimpleGrantedAuthority("ROLE_"+grant.getGrantCode());
					grantedAuthorities.add(grantedAuth);
				}
				//增加默认权限（以后添加）
		        grantedAuthorities.add(grantedAuthority);
		        //admin/admin
		        usd=new MyUserDetails(userCode, user.getPassword(), grantedAuthorities,user) ;
				return usd;
			}
			
		}else {
			throw new java.lang.RuntimeException(errorMsg);
		}
	}

}
