package tw.leader.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tw.leader.po.User;

@Controller
public class SimpleJumpController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@GetMapping("/index")
	public String ViewHomePage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "index";
	}

	@GetMapping("/shopPage")
	public String fashiShopPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "shop";
	}

	@GetMapping("/product")
	public String productPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "product";
	}

	@GetMapping("/blog")
	public String blogPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "blog";
	}

	@GetMapping("/blogDetails")
	public String blogDetailsPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "blog-details";
	}

	@GetMapping("/checkOut")
	public String checkOutPage() {
		return "check-out";
	}

	@GetMapping("/contact")
	public String contactPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "contact";
	}

	@GetMapping("/faq")
	public String faqPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "faq";
	}

	@GetMapping("/shoppingCart")
	public String shoppingCartPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "shopping-cart";
	}

	@GetMapping("/uploader")
	public String uploaderPage() {
		return "uploader";
	}

	@GetMapping("/registerNow")
	public String toRegister(Model m) {
		m.addAttribute("users", new User());
		return "register3";
	}

	@GetMapping("/forgetPassword")
	public String forgetPassword() {
		return "resetPassword";
	}

	@GetMapping("/loginPage")
	public String viewLoginPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "login3";
	}

	@GetMapping("/toHomePage")
	public String viewHomePage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "index";
	}

	@GetMapping("/personalInfo")
	public String viewPersonalInfo() {
		return "personalInfo";
	}

	@GetMapping("/toUserInfoDetail")
	public String toUserInfoDetail(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "userInfoDetail";
	}

	@GetMapping("/Activity")
	public String viewActivityPage(Model m) {
//		String user = GetCurrentUserAccount();
//		m.addAttribute("user", user);
		return "Activity";
	}

	@GetMapping("/test")
	public String testPage() {
		return "adminTest";
	}

	// 賣家管理頁面
	@GetMapping("/admin")
	public String adminPage(Model m) {
		String userName = GetCurrentUserAccount();
		m.addAttribute("user", userName);
		return "admin";
	}

	public String GetCurrentUserAccount() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return userName;
		// this method is for getting current user account which has login.
	}
}
