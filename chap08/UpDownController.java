package com.tjoeun.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/file")
public class UpDownController
{
	@Autowired
	ResourceLoader resourceLoader;
	
	@GetMapping("/upload")
	public String uploadForm()
	{
		return "upload/upload_form";
	}
	
	@GetMapping("/upload/image")
	public String uploadImage()
	{
		return "upload/upload_image";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("files") MultipartFile[] mfiles,
			HttpServletRequest request,
			@RequestParam("author") String author) {
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/WEB-INF/upload");
		
		try {// 여러개의 파일이 있을 시 반복문을 사용하여 지정파일 경로에 저장.
			for(int i=0;i<mfiles.length;i++) {
				mfiles[i].transferTo(
				  new File(savePath+"/"+mfiles[i].getOriginalFilename()));
				/* MultipartFile 주요 메소드
				String cType = mfiles[i].getContentType();
				String pName = mfiles[i].getName();
				Resource res = mfiles[i].getResource();
				long fSize = mfiles[i].getSize();
				boolean empty = mfiles[i].isEmpty();
				*/
			}
			String msg = String.format("파일(%d)개 저장성공(작성자:%s)", mfiles.length,author);
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "파일 저장 실패:";
		}
	}
	
	@PostMapping("/upload/image")
	@ResponseBody
	public String uploadImage(@RequestParam("files") MultipartFile[] mfiles,
			HttpServletRequest request,
			@RequestParam("author") String author) {
		ServletContext context = request.getServletContext();
		String savePath = context.getRealPath("/images");
		String filename = mfiles[0].getOriginalFilename();
		try {// 여러개의 파일이 있을 시 반복문을 사용하여 지정파일 경로에 저장.
			for(int i=0;i<mfiles.length;i++) {
				mfiles[i].transferTo(
					new File(savePath+"/"+mfiles[i].getOriginalFilename()));
					
				/* MultipartFile 주요 메소드
				String cType = mfiles[i].getContentType();
				String pName = mfiles[i].getName();
				Resource res = mfiles[i].getResource();
				long fSize = mfiles[i].getSize();
				boolean empty = mfiles[i].isEmpty();
				 */
			}
			//String msg = String.format("파일(%d)개 저장성공(작성자:%s)", mfiles.length,author);
			String msg = String.format("<img src = '/images/%s' style = width : 100px; height : 100px;>", mfiles[0].getOriginalFilename());
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "파일 저장 실패:";
		}
	}
						// url에 들어온 파일명을 파일경로를 지정하고 컨텐트타입을 구한다.
	@GetMapping("download/{filename}")
	public ResponseEntity<Resource> download(
			HttpServletRequest request,
			@PathVariable String filename){
		Resource resource = (Resource)resourceLoader.getResource("WEB-INF/upload/"+filename);
		System.out.println("파일명:"+resource.getFilename());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))  // 파일명주고
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
}
