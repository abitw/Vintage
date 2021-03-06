package tw.leader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.leader.dao.UserRepository;
import tw.leader.po.User;

@Controller
public class SimpleJumpController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Autowired
	private UserRepository uRepo;


	@GetMapping(value="/index")
	public String ViewHomePage(Model m) {
		String user = GetCurrentUserAccount();
		User u = uRepo.findByEmail(user);
		String roles = u.getRoles();
		m.addAttribute("user", user);
		m.addAttribute("roles", roles);
		return "index";
	}

	@GetMapping(value="/shopPage")
	public String fashiShopPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "shop";
	}

	@GetMapping(value="/product")
	public String productPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "product";
	}

	@GetMapping(value="/blog")
	public String blogPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "blog";
	}

	@GetMapping(value="/blogDetails")
	public String blogDetailsPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "blog-details";
	}

	@GetMapping(value="/checkOut")
	public String checkOutPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "check-out";
	}

	@GetMapping(value="/contact")
	public String contactPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "contact";
	}

	@GetMapping(value="/faq")
	public String faqPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "faq";
	}

	@GetMapping(value="/shoppingCart")
	public String shoppingCartPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "shopping-cart";
	}

	@GetMapping(value="/uploader")
	public String uploaderPage() {
		return "uploader";
	}

	@GetMapping(value="/registerNow")
	public String toRegister(Model m) {
		m.addAttribute("users", new User());
		return "register3";
	}

	@GetMapping(value="/forgetPassword")
	public String forgetPassword() {
		return "resetPassword";
	}

	@GetMapping(value="/loginPage")
	public String viewLoginPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "login3";
	}

	@GetMapping(value="/toHomePage")
	public String viewHomePage(Model m) {
		String user = GetCurrentUserAccount();
		User u = uRepo.findByEmail(user);
		String roles = u.getRoles();
		
		if (roles.equals("ROLES_DISABLED")) {
			m.addAttribute("msgA","您已遭到停權，請洽詢Vintage以獲得更多資訊");
			return "login3";
		}
		m.addAttribute("user", user);
		m.addAttribute("roles", roles);
		
		return "index";		
	}
	
	@GetMapping(value="/logoutToHomePage")
	public String logoutPage() {
		return "index";
	}

	@GetMapping(value="/personalInfo")
	public String viewPersonalInfo() {
		return "personalInfo";
	}

	@GetMapping(value="/toUserInfoDetail")
	public String toUserInfoDetail(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "userInfoDetail";
	}

	@GetMapping(value="/Activity")
	public String viewActivityPage(Model m) {
//		String user = GetCurrentUserAccount();
//		m.addAttribute("user", user);
		return "Activity";
	}

//	@GetMapping("/test")
//	public String testPage() {
//		return "adminTest";
//	}
	
	@GetMapping(value="/toUserManagement")
	public String goToUserManagementPage(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "UserManagement";
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
	
	@GetMapping("/test")
	public String testPage() {
		return "test";
	}

}
