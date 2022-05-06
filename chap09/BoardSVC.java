package com.tjoeun.svc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.dao.BoardDAO;
import com.tjoeun.vo.AttachVO;
import com.tjoeun.vo.BoardVO;

@Service
public class BoardSVC {
	/* 절대경로를 구하기 위해 컨텍스트가 필요하다
	 *  그 중간에 겟서블릿 컨텍스트객체가 필요하다. */
	// 요지는 파일시스템을 저장하고, 관리를 하기위하여
	// 파일의 정보를 DB에 저장한다.
//		어떤 글에 파일이름과 파일사이즈가 무엇인지 알기위해 DB에 저장.
	@Autowired
	private BoardDAO dao;
	
	public boolean addBoard(BoardVO board) {
		
		return dao.addBoard(board);
	}
	   @Transactional(rollbackFor = {Exception.class}) // 어떤 예외가 발생하면 데이터를 원상복구(저장된걸 취소)한다.					                                      //files란 인풋타입 네임 / MultipartFile[] mfiles 파일 정보가 들어온다
	   public boolean addBoard(HttpServletRequest request, BoardVO board, MultipartFile[] mfiles) 
	   {
	      boolean saved = addBoard(board); // 글 저장
	      int board_num = board.getNum();  // 글 저장시 자동증가 필드
	      if (!saved) {
	         System.out.println("글 저장 실패");
	         return false;
	      }
	   // 파일을 서버컴퓨터에 저장할 경로를 지정하는 코드
	      ServletContext context = request.getServletContext();
	   // /WEB-INF/upload 이 경로에 저장하겠다는 것 절대경로가 나온다.
	      String savePath = context.getRealPath("/WEB-INF/upload");
	      int fileCnt = mfiles.length;
	      int saveCnt = 0;
	      
	    	  try {// 여러개의 파일이 있을 시 반복문을 사용하여 지정파일 경로에 저장.
	    		  for(int i=0;i<mfiles.length;i++) {
	    			  //파일 이름을 추출하여 변수에 저장.
	    			  String filename = mfiles[i].getOriginalFilename();
	    			  // 받은 파일을 지정 경로로 보낸뒤 저장한다.
	    			  // 완전한 경로를 가지고 있는 File 객체에  
	    			  mfiles[i].transferTo( new File(savePath+"/"+filename)); //서버측 디스크
	    			  Map<String,Object> map = new HashMap<>();
	    			  map.put("board_num", board_num);
	    			  map.put("filename", filename); // 파일 저장 후 데이터 베이스에도 저장하는 것.
	    			  map.put("filesize", mfiles[i].getSize());
	    			  boolean fSaved = dao.addFileInfo(map); // attach 테이블에 파일정보 저장
	    			  if(fSaved) saveCnt++;
	    		  }
	    		  return fileCnt==saveCnt ? true : false;
	    		  
	    	  }catch(Exception e) {
	    		  e.printStackTrace();
	    	  }
	      
	      return false;
	   }
	   
	   public List<BoardVO> boardList(){
		   // 첨부파일 개수에 따라 나오는 중복을 가진 list
		   List<Map<String,Object>> list = dao.boardList();
		   List<BoardVO> list2 = new ArrayList<>();
		   // 
		   int prev_num=0; // 앞에 글 번호를 확인하기 위한 변수 선언.
		   for(int i = 0; i<list.size(); i++) {
			   int bnum = (int)list.get(i).get("num"); // 게시판 글번호 저장
			   if(bnum==prev_num) // 첨부파일이 다수개라서 중복되는 행이 있다면...
			   {
					BoardVO _board = list2.get(list2.size()-1);
					AttachVO att = new AttachVO();
					att.setFilename((String)list.get(i).get("filename"));
					att.setFilesize ((int)list.get(i).get("filesize"));
					_board.attach.add(att);
					continue;
				}
				
				// 첨부파일이 없거나 한개인 게시글이라면...
				Map<String, Object> m = list.get(i);
				BoardVO board = new BoardVO();
				board.setNum(bnum);
				board.setTitle((String)m.get("title"));
				board.setAuthor((String) m.get("author"));

				if(m.get("filename")!=null) // 첨부파일을 가진 글이라면...
				{
					AttachVO att = new AttachVO();
					att.setFilename((String)list.get(i).get("filename"));
					att.setFilesize ((int)list.get(i).get("filesize"));
					board.attach.add(att);
				}
				list2.add(board);
				prev_num = bnum;  // 중복되는 행인지 확인하기 위함
			} // end of for()
			return list2;   
		}
	public BoardVO detail(int num) {
		List<Map<String,Object>> list = dao.detail(num);
		BoardVO board = new BoardVO();
		int size = list.size();
		for(int i=0;i<size;i++) 
		{
			Map<String,Object> map = list.get(i);
			if(i==0) 
			{
				board.setNum( (int)map.get("num"));
				board.setTitle( (String)map.get("title"));
				board.setAuthor( (String)map.get("author"));
				board.setContents ( (String)map.get("contents"));
			}
			Object obj = map.get("filename");
			if(obj!=null) 
			{
				AttachVO att = new AttachVO();
				att.setNum( (int)map.get("att_num"));
				att.setFilename((String)map.get("filename"));
				att.setFilesize((int)map.get("filesize"));
				board.attach.add(att);
			}
		}
		return board;
	}
	
	public String getFilename(int num) {
		return dao.getFilename(num);
	}
	public boolean delete(int num) {
		return dao.delete(num);
	}
	public boolean delete_file(int num, ResourceLoader resourceLoader) {
		String filename = getFilename(num);
		Resource resource = (Resource)resourceLoader.getResource("WEB-INF/upload/" + filename);
		boolean deleted = false;
		try {
			String abPath = resource.getFile().getAbsolutePath();
			File fileDel = new File(abPath);
			deleted = fileDel.exists() ? fileDel.delete() : false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(deleted) {
			return dao.deleteFileInfo(num);
		}
		return false;
	}
	
	public boolean update(BoardVO board, ResourceLoader resourceLoader, int num) {
		boolean updated = dao.update(board);
		String filename = getFilename(num);
		Resource resource = (Resource)resourceLoader.getResource("WEB-INF/upload/" + filename);
		boolean deleted = false;
		try {
			String abPath = resource.getFile().getAbsolutePath();
			File fileDel = new File(abPath);
			deleted = fileDel.exists() ? fileDel.delete() : false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(deleted) {
			return dao.deleteFileInfo(num);
		}
		return false;
	}
}
