package edu.hfu.broadcast.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import edu.hfu.broadcast.bean.AnchorInformation;
import edu.hfu.broadcast.bean.SysCompany;
import edu.hfu.broadcast.bean.SysUser;
import edu.hfu.broadcast.service.security.CustomUserDetailsAuthenticationProvider;
import edu.hfu.broadcast.service.security.CustomUserDetailsService;
import edu.hfu.broadcast.service.security.MyUserDetails;
import edu.hfu.broadcast.service.security.UserPassEncoder;
import edu.hfu.broadcast.service.sysset.SystemService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private final Logger LOG = LoggerFactory.getLogger(WebSecurityConfig.class);
	@Resource
	SystemService systemService;
	@Autowired
	CustomUserDetailsService userDetailsService;
	@Autowired
	AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
	
	@Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
	
	@Bean
	public PasswordEncoder myPasswordEncoder() {
		 return new UserPassEncoder(); 
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/druid/**","/login", "/static/**","/",
				"/goBlank","/invalidSession","/vercode");
	} 
	
	public AuthenticationProvider authProvider() {
        CustomUserDetailsAuthenticationProvider provider 
            = new CustomUserDetailsAuthenticationProvider(myPasswordEncoder(), userDetailsService);
        return provider;
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().and()
		.formLogin()
		.loginPage("/")
		.loginProcessingUrl("/userLogin")
		.usernameParameter("userCode")
		.passwordParameter("userPass")
		.authenticationDetailsSource(authenticationDetailsSource)
		.permitAll()
		.failureHandler(new AuthenticationFailureHandler() {
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
				String msg=e.toString().replace("org.springframework.security.authentication.InternalAuthenticationServiceException:", "");
				if(msg.indexOf("Bad credentials")>-1) {
					msg="账号或密码错误";
				}
				String path="/?mess="+msg;
				request.getRequestDispatcher(path).forward(request,response);
			}
		}).successHandler(new AuthenticationSuccessHandler() {
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
				try {
					LOG.debug("登录成功");
					//登录成功，将必要信息保存到session
					HttpSession session=request.getSession();
					Object principal=auth.getPrincipal();
					if (principal != null && principal instanceof MyUserDetails) {
						MyUserDetails usd = (MyUserDetails) principal;
						SysUser user=(SysUser) usd.getCustomObj();
						String userType=user.getUserRole();
						session.setAttribute("user", user);
						session.setAttribute("userType", userType);
						session.setAttribute("userCode", usd.getUsername());
						if ("2".equals(userType)) {//主播
							AnchorInformation anch=systemService.getAnchorByUser(user.getPhoneNum());
							session.setAttribute("anchor", anch);
						}else if("3".equals(userType)){//企业
							SysCompany company=systemService.getCompanyByUser(user.getPhoneNum());
							session.setAttribute("company", company);
						}
					}
					request.getRequestDispatcher("/goIndex").forward(request,response);
				} catch (Exception e) {
					e.printStackTrace();
					String path="/?mess="+e.toString().replace("org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.springframework.security.access.AccessDeniedException: ", "");
					request.getRequestDispatcher(path).forward(request,response);
				}
				
			}
		}).and().logout().permitAll()
		//and().headers().frameOptions().sameOrigin() 可以解决页面不允许在iframe打开的问题
		.and().headers().frameOptions().sameOrigin()
		.and().csrf().disable().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler)
		.and().sessionManagement()
		.invalidSessionUrl("/invalidSession")
		.maximumSessions(1)
		// 当达到最大值时，是否保留已经登录的用户 为true，新用户无法登录；为 false，旧用户被踢出
		.maxSessionsPreventsLogin(false)
		.expiredSessionStrategy(new SessionInformationExpiredStrategy() {
			@Override
			public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
				System.out.println("expiredSessionStrategy");
			}
		});;
	}
}
