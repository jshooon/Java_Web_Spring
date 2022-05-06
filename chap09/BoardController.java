package com.tjoeun.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.svc.BoardSVC;
import com.tjoeun.vo.BoardVO;


//@RestController
@Controller
@RequestMapping("/bbs")
public class BoardController {

	@Autowired
	ResourceLoader resourceLoader; //파일의 절대경로를 알기위한 클래스 변수.
	
	@Autowired
	private BoardSVC svc;
	
//	@GetMapping("/add")
//	@ResponseBody
//	public String add() {
//		BoardVO board = new BoardVO();
//		board.setTitle("후..후..게시판 테스트");
//		board.setAuthor("harim");
//		board.setContents("자동증가 필드 값은!!!?");
//		boolean added = svc.addBoard(board);
//		int num = board.getNum();
//		return "added = " + added + ", AI = " + num ;
//	}
	
	@GetMapping("/add")
	public String addForm() {
		return "board/addForm";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Boolean> save(@RequestParam("files") MultipartFile[] mfiles,
						HttpServletRequest request,
						BoardVO board) { //attach = mfiles
		boolean saved = svc.addBoard(request, board, mfiles); // board, attach, file
		Map<String, Boolean> map = new HashMap<>();
		map.put("saved", saved);
		return map;
		}
	
	@GetMapping("/list")
	public String boardList(Model m) {	
		m.addAttribute("list", svc.boardList());
		return "board/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam int num, Model m) {
		BoardVO detail = svc.detail(num);
		m.addAttribute("detail", detail);
		return "board/detail";
	}
	
	@GetMapping("/file/download/{num}")
	public ResponseEntity<Resource> fileDownload(@PathVariable int num,
			HttpServletRequest request)
	{
		// attach 테이블에서 att_num 번호를 이용하여 파일명을 구하여 위의 방법을 사용
		String filename = svc.getFilename(num);
		Resource resource = (Resource)resourceLoader.getResource("WEB-INF/upload/"+filename);
		//System.out.println("파일명:"+resource.getFilename());
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
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
//	@GetMapping("/delete/{num}")
//	@ResponseBody
//	public Map<String, Boolean> delete(@PathVariable int num) {
//		boolean deleted = svc.delete(num);
//		Map<String, Boolean> map = new HashMap<>();
//		map.put("deleted", deleted);
//		return map;
//	}
	
	@GetMapping("/delete")
	@ResponseBody
	public Map<String, Boolean> delete(@RequestParam int num) {
		boolean deleted = svc.delete(num);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", deleted);
		return map;
	}
	@PostMapping("/file/delete")
	@ResponseBody
	public Map<String, Boolean> delete_file(@RequestParam int num){
	boolean deleted = svc.delete_file(num, resourceLoader);
	Map<String, Boolean> map =new HashMap<>();
	map.put("deleted", deleted);
	return map;
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int num, Model m) {
		boolean edit = svc.delete_file(num, resourceLoader);
		m.addAttribute("edit", svc.delete_file(num, resourceLoader));
		m.addAttribute("edit", svc.detail(num));
		return "board/edit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Boolean> update(@RequestParam("files") MultipartFile[] mfiles,
			HttpServletRequest request,@RequestParam int num, BoardVO baord){
		Boolean updated = svc.update(baord,resourceLoader, num);
		Map<String, Boolean> map = new HashMap<>();
		map.put("updated", updated);
		return map;
		
	}
	
}

