package net.koreate.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.koreate.util.UploadUtils;

@RestController
public class UploadController {

	@Inject
	ServletContext context;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<List<String>> uploadFile(MultipartFile[] file) throws IOException{
		/*List<String> list = UploadUtils.getInstance(context).uploadFile(file);
		return new ResponseEntity<List<String>>(list, HttpStatus.CREATED);*/
		return new ResponseEntity<List<String>>(UploadUtils.getInstance(context).uploadFile(file), HttpStatus.CREATED);
	}
	
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws IOException{
		UploadUtils utils = UploadUtils.getInstance(context);
		return new ResponseEntity<byte[]>(utils.getBytes(fileName),utils.getHeader(fileName),HttpStatus.OK);
	}
	
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName) throws IOException{
		UploadUtils utils = UploadUtils.getInstance(context);
		String result = utils.deleteFile(fileName);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PostMapping("/deleteAllFiles")
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files){
		ResponseEntity<String> entity = null;
		System.out.println("deleteAllFiles");
		/*for(String file : files) {
			System.out.println(file);
		}*/
		return new ResponseEntity<>(UploadUtils.getInstance(context).deleteAllFiles(files),HttpStatus.OK);
				
	}
}
