package edu.hfu.broadcast.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.hfu.broadcast.util.VerifyCode;

@RestController
public class LoginIndexAction {
	private final Logger LOG = LoggerFactory.getLogger(LoginIndexAction.class);

	@RequestMapping(value = { "/", "/login","/error" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView gotoLoginPage(String mess) {
		LOG.debug("进入登录页...");
		ModelAndView mod = new ModelAndView("/login.btl");
		if (null == mess) {
			mess = "";
		}
		mod.addObject("mess", mess);
		return mod;
	}

	@GetMapping("/vercode")
	public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		String text = vc.getText();
		HttpSession session = req.getSession();
		session.setAttribute("index_code", text);
		LOG.debug("vercode:"+text);
		VerifyCode.output(image, resp.getOutputStream());
	}

	@RequestMapping(value = { "/goIndex" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView goIndex() {
		ModelAndView mod = new ModelAndView("/index.btl");
		return mod;
	}

	@RequestMapping(value = { "/invalidSession" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView invalidSession() {
		ModelAndView mod = new ModelAndView("/invalidSession.btl");
		return mod;
	}

	@RequestMapping(value = { "/goBlank" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView goBlank() {
		ModelAndView mod = new ModelAndView("/blank.btl");
		return mod;
	}

	@RequestMapping(value = { "/loginOut" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String loginOut(HttpSession session) {
		String person_name = String.valueOf(session.getAttribute("userName"));
		session.invalidate();
		LOG.debug("用户 " + person_name + " 退出登录");
		session.invalidate();
		return "success";
	}
}
