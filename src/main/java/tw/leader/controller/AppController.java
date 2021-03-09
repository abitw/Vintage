package tw.leader.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.leader.config.Utility;
import tw.leader.dao.FindUserInfoDao;

import tw.leader.dao.UserRepository;
import tw.leader.po.Product;
import tw.leader.po.User;
import tw.leader.service.CustomUserDetailsService;
import tw.leader.vo.UserResp;

import net.bytebuddy.utility.RandomString;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private FindUserInfoDao fDao;
	
	@Autowired
	private CustomUserDetailsService service;

	
	@PostMapping("/process_register")
	public String processRegister(User user, Model m, HttpServletRequest request) 
	throws UnsupportedEncodingException, MessagingException{
		String roles = "ROLES_USER";
		user.setRoles(roles);
				
		service.register(user);
		String siteURL = Utility.getSiteURL(request);
		service.sendVerificationEmail(user, siteURL);
		
		return "register_success";
	}
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code, Model m) {
		boolean verified = service.verify(code);
		
		String pageTitle = verified ? "Verification Succeeded" : "Verification Failed";
		m.addAttribute("pageTitle", pageTitle);
		return (verified ? "verify_success" : "verify_failed");
	}
	
	@GetMapping("/list_users")
	public String viewUsersList(Model m) {
		List<User> listUsers = uRepo.findAll();
		m.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/testpage1")
	public String testP1(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "testPage1";
	}
	
	@GetMapping("/testpage2")
	public String testP2(Model m) {
		String user = GetCurrentUserAccount();
		m.addAttribute("user", user);
		return "testPage2";
	}
		
	
	@PostMapping("/resetPassword")
	public String getPassword(
			@RequestParam("email") String email,
			@RequestParam("password") String secondPwd,
			Model m) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(!uRepo.findByEmail(email).getEmail().isEmpty()) {
			User user = uRepo.findByEmail(email);
			user.setPassword(encoder.encode(secondPwd));
			uRepo.save(user);
			return "index";
		}else {
			m.addAttribute("errors", "NO EMAIL EXIST!! PLEASE CHECK AGAIN.");
			return "resetPassword";
		}
	}
		
	
	
	
	@GetMapping("/registerProcess")
	public String registerProcess() {
		return "register_success";
	}
	
	@PostMapping(value="/panselectUserInfo.user")
	@ResponseBody
	public String selectUserInfoUser(@RequestBody UserResp userData) throws Exception {
		String email = userData.getEmail();
		List<User> uList = fDao.findAllDataByEmail(email);
				
		String uJson = objectMapper.writeValueAsString(uList);
		System.out.println(uJson);
		return uJson;
	}
	
	
	
	@PostMapping("/userInfoUpdate")
	public String userInfoUpdate(
			@RequestParam("userBirthday")String userBirthday,
			@RequestParam("userPhone") String userPhone,
			@RequestParam("gender") String gender,
			@RequestParam("fileImage") MultipartFile multipartFile
			) throws IOException{
		String currentUser = GetCurrentUserAccount();
		String newRole = "ROLES_SELLER";
		System.out.println(currentUser);
		User user = uRepo.findByEmail(currentUser);
		
//		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if(fileName == "") {
		}
		else {
			fileName = user.getUserId() + fileName;
			user.setUserPic(fileName);
		}
		
		user.setEmail(currentUser);
		user.setBirthday(userBirthday);
		user.setPhone(userPhone);
		user.setGender(gender);
		user.setRoles(newRole);
		
		uRepo.save(user);
		

		String uploadDir ="D:\\springboot\\Git3\\Vintage\\src\\main\\resources\\static\\img\\userPic";

		Path uploadPath = Paths.get(uploadDir);

		if ( !Files.exists(uploadPath) ) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Could not save upload file" + fileName); 
		}
		
		return "login3";
	}
	
	/*
	 * -----------------------------------------
	 * 		AdvFindUserDetail
	 * */
	
	@PostMapping(value="/panAdvFindUser")
	@ResponseBody
	public String advFindUser(@RequestBody UserResp userData) throws Exception {
		String email = userData.getEmail();
		List<User> uList = fDao.findAllDataByEmail(email);
		String uJson = objectMapper.writeValueAsString(uList);
		return uJson;
	}
	
	/*
	 * -----------------------------------------------------------------
	 * 		IndexFindUserDetail
	 * */
	
	@PostMapping(value="/panFindIndexUser")
	@ResponseBody
	public String findIndexUser() throws Exception {
			List<User> uList = fDao.findIndexUser();
		String uJson = objectMapper.writeValueAsString(uList);
		return uJson;
	}
	
	
		
	public String GetCurrentUserAccount() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return userName;
		// this method is for getting current user account which has login.
	}
}
