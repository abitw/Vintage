
package tw.leader.controller;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

//	@GetMapping("/index")
//	public String hello() {
//		return "uploader";
//	}
//
//	@PostMapping("/upload")
//	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
//		String filemName = file.getOriginalFilename();
//		filemName = "pan"+filemName;
//		try {
//			file.transferTo(new File("C:\\SpringBoot\\eclipse\\Workspace\\Git\\VintageTest\\src\\main\\resources\\static\\img\\products\\" + filemName));
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//		return ResponseEntity.ok("File uploaded successfully!!");
//	}

}
