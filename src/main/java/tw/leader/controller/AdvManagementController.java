package tw.leader.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import tw.leader.dao.AdvDao;
import tw.leader.po.AdvMain;

@Controller
public class AdvManagementController {
	
	@Autowired
	private AdvDao aDao;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostMapping("/advForm1")
	public String advUpload1(
			@RequestParam("title1")String title,
			@RequestParam("seller1") String seller,
			@RequestParam("content1") String content,
			@RequestParam("activity1") String discount,
			@RequestParam("fileImage1") MultipartFile multipartFile
			) throws IOException{
		String currentUser = GetCurrentUserAccount();
				
		AdvMain adv = aDao.findBySeat(1);
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		fileName = adv.getMainId() + fileName;
		
		adv.setEmail(seller);		
		adv.setTitle(title);
		adv.setContent(content);
		adv.setActivity(discount);
		adv.setaPhoto(fileName);
		adv.setSeat(1);
		
		
		
		
		aDao.save(adv);
		// C:\SpringBoot\eclipse\Workspace\Vintageyo.zip_expanded\Vintage\src\main\resources\static\img
		String uploadDir = "D:\\springboot\\Git3\\Vintage\\src\\main\\resources\\static\\\\img\\advManagement";

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
		
		return "AdvertisementManagement";
	}
	
	@PostMapping("/advForm2")
	public String advUpload2(
			@RequestParam("title2")String title,
			@RequestParam("seller2") String seller,
			@RequestParam("content2") String content,
			@RequestParam("activity2") String discount,
			@RequestParam("fileImage2") MultipartFile multipartFile
			) throws IOException{
		String currentUser = GetCurrentUserAccount();
				
		AdvMain adv = aDao.findBySeat(2);
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		fileName = adv.getMainId() + fileName;
		
		adv.setEmail(seller);
		adv.setTitle(title);
		adv.setContent(content);
		adv.setActivity(discount);
		adv.setaPhoto(fileName);
		adv.setSeat(2);
		
		
		aDao.save(adv);
		
		String uploadDir = "D:\\springboot\\Git3\\Vintage\\src\\main\\resources\\static\\img\\advManagement";

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
		
		return "AdvertisementManagement";
	}
	
	@PostMapping("/advForm3")
	public String advUpload3(
			@RequestParam("title3")String title,
			@RequestParam("seller3") String seller,
			@RequestParam("content3") String content,
			@RequestParam("activity3") String discount,
			@RequestParam("fileImage3") MultipartFile multipartFile
			) throws IOException{
		String currentUser = GetCurrentUserAccount();
				
		AdvMain adv = aDao.findBySeat(3);
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		fileName = adv.getMainId() + fileName;
		
		adv.setEmail(seller);
		adv.setTitle(title);
		adv.setContent(content);
		adv.setActivity(discount);
		adv.setaPhoto(fileName);
		adv.setSeat(3);
		
		aDao.save(adv);
		
		String uploadDir = "D:\\springboot\\Git3\\Vintage\\src\\main\\resources\\static\\img\\advManagement";

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
		
		return "AdvertisementManagement";
	}
	
	@PostMapping(value="/panSelectAdvMain1")
	@ResponseBody
	public String selectAdvMain1() throws Exception {
		int seat = 1;
		List<AdvMain> aList = aDao.findAdvMain(seat);
		String aJson = objectMapper.writeValueAsString(aList);
		System.out.println(aJson);
		return aJson;
	}
	
	@PostMapping(value="/panSelectAdvMain2")
	@ResponseBody
	public String selectAdvMain2() throws Exception {
		int seat = 2;
		List<AdvMain> aList = aDao.findAdvMain(seat);
		String aJson = objectMapper.writeValueAsString(aList);
		System.out.println(aJson);
		return aJson;
	}
	
	@PostMapping(value="/panSelectAdvMain3")
	@ResponseBody
	public String selectAdvMain3() throws Exception {
		int seat = 3;
		List<AdvMain> aList = aDao.findAdvMain(seat);
		String aJson = objectMapper.writeValueAsString(aList);
		System.out.println(aJson);
		return aJson;
	}
	
		
	public String GetCurrentUserAccount() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return userName;
		// this method is for getting current user account which has login.
	}
	
	
}

