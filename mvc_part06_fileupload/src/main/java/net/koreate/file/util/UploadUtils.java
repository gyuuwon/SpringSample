package net.koreate.file.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

// 경로 계산 생성
// media type 에 따라 썸네일 제작
// 파일 업로드
public class UploadUtils {
	
	public static String uploadFile(
			String originalName, 
			String uploadPath, 
			byte[] fileData) throws IOException{
		
		String uploadFileName ="";
		
		UUID uuid = UUID.randomUUID();
		String savedName = uuid.toString().replace("-","")+"_"+originalName;
		String path = calcPath(uploadPath);
		File file = new File(uploadPath+path,savedName);
		
		FileCopyUtils.copy(fileData, file);
		// asjkdhaskjdas.jpg
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadFileName = makeThumnail(uploadPath,path,savedName);
		}else {
			uploadFileName = makeFile(uploadPath,path,savedName);
		}
		return uploadFileName;
	}
	
	
	
	public static String makeFile(String uploadPath,String path,String savedName) {
		String file = "";
		String name = uploadPath+path+File.separator+savedName;
		file = name.substring(uploadPath.length()).replace(File.separatorChar,'/');
		return file;
	}
	
	public static String makeThumnail(
					String uploadPath, 
					String path, 
					String savedName) throws IOException{
		
		File file = new File(uploadPath+path,savedName);
		BufferedImage fileImage = ImageIO.read(file);
		
		BufferedImage sourceImage = 
				Scalr.resize(fileImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumnail = uploadPath+path+File.separator+"s_"+savedName;
		System.out.println("thumnail"+thumnail);
		File file1 = new File(thumnail);
		// 확장자
		String formatName = savedName.substring(savedName.lastIndexOf(".")+1);
		System.out.println("forMatName : "+formatName);
		ImageIO.write(sourceImage, formatName, file1);
		String name 
		= thumnail.substring(uploadPath.length()).replace(File.separatorChar,'/');
		
		return name;
	}
	
	
	public static String calcPath(String uploadPath) {
		String datePath = "";
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		String monthPath = yearPath +
						   File.separator +
						   new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		datePath = monthPath + File.separator
				   + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		System.out.println(datePath);
		mkDir(uploadPath,yearPath,monthPath,datePath);
		return datePath;						
	}
	
	public static void mkDir(String uploadPath, String... path) {
		if(new File(path[path.length-1]).exists()) {
			return;
		}
		
		for(String p : path) {
			File file = new File(uploadPath+p);
			if(!file.exists()) {
				file.mkdir();
			}
		}
	}



	public static ResponseEntity<byte[]> displayFile(String uploadPath, String fileName) {
		ResponseEntity<byte[]> entity = null;
		
		InputStream in = null;
		System.out.println("fileName" + fileName);
		
		
			try {
				String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
				
				MediaType mType = MediaUtils.getMediaType(formatName);
				
				in = new FileInputStream(uploadPath+fileName);
				
				HttpHeaders headers = new HttpHeaders();
				
				if(mType != null) {
					headers.setContentType(mType);
				}else {
					fileName  = fileName.substring(fileName.indexOf("_")+1);
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					headers.add("content-disposition", "attachment;filename=\""
							+ new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				}
				
				entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.OK);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {}
			}
			return entity;
	}



	public static ResponseEntity<String> deleteFile(String uploadPath, String fileName) {
		ResponseEntity<String> entity = null;
		
		System.out.println("삭제요청 : "+fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		MediaType media = MediaUtils.getMediaType(formatName);
		
		if(media != null) {
			System.out.println("이미지 파일");
			String name = fileName.replace("s_", "");
			String filePath = (uploadPath+name).replace('/',File.separatorChar);
			File file = new File(filePath);
			file.delete();
		}
		
		new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
		
		entity = new ResponseEntity<>("DELETED",HttpStatus.OK);
		System.out.println("삭제 완료");
		return entity;
	}
	
}















