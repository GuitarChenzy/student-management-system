package com.cogu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cogu.model.User;
import com.cogu.service.UserService;

import cn.dsna.util.images.ValidateCode;

@Controller
@RequestMapping("user")
public class UserController {

	private String check; // 验证码载体
	@Autowired
	private UserService userService;

	/**
	 * 登录控制器
	 * 
	 * @param user
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String userLogin(@ModelAttribute("user") User user, HttpServletRequest req) {
		if (!this.check.equals(req.getParameter("checkcode").toUpperCase())) {
			req.setAttribute("message", "验证码错误！");
			return "../../index";
		} else {
			int status = userService.userLogin(user);
			if (status == 200) {
				if (user.getStatus() == 1) {
					req.setAttribute("username", user.getUsername());
					req.getSession().setAttribute("messages", user.getUsername());
					return "/system";
				} else if (user.getStatus() == 2) {
					return "/teacher";
				} else {
					return "/student";
				}
			} else {
				req.setAttribute("message", "登录失败，账号或密码错误！");
				return "../../index";
			}
		}
	}

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public void genarateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ValidateCode validateCode = new ValidateCode(180, 40, 4, 80);
		String code = validateCode.getCode();
		System.out.println("验证码：" + code);
		request.getSession().setAttribute("code", code);
		this.check = code;
		response.setContentType("image/jpeg");
		validateCode.write(response.getOutputStream());
	}
	
	@RequestMapping(value = "/quit",method = RequestMethod.GET)
	public String quit(HttpServletRequest request){
		request.getSession().removeAttribute("code");
		request.getSession().removeAttribute("messages");
		return "../../index";
	}

	@RequestMapping(value = "/openall", method = RequestMethod.GET)
	public String openCourse(HttpServletRequest request) {
		if (request.getSession().getAttribute("code") != null) {
			return "/all";
		} else {
			return "../../index";
		}
	}
}
