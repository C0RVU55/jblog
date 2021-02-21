package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	//블로그 메인 : id, userName, blogTitle, logoFile 가져오기
	public BlogVo blogInfo(String id) {
		System.out.println("[BlogService.blogInfo()] --> "+id);
		
		return blogDao.selectBlog(id);
	}
	
	//블로그 관리1 베이직 - 설정 변경 
	public void basicModify(String id, String blogTitle, MultipartFile file) {
		System.out.println("[BlogService.basicModify()] --> "+id+" / "+blogTitle+" / "+file);
		
		if(file == null) { //제목만 변경
			BlogVo blogVo = new BlogVo(id, blogTitle);
			blogDao.basicUpdate(blogVo);
			
		} else { //제목+로고 변경
			System.out.println("[file original name] --> "+file.getOriginalFilename()); 
			
			/////DB에 저장할 파일정보 수집/////
			
			//서버 파일패스(경로)
			String saveDir = "C:\\javaStudy\\강의자료\\99.미니프로젝트_jblog\\jblog_디자인\\assets\\images";
			
			//오리지널 파일명
			String orgName = file.getOriginalFilename();
			System.out.println("orgName: "+orgName);
			
			//확장자명
			String exName = orgName.substring(orgName.lastIndexOf("."));
			System.out.println("exName --> "+exName);
			
			//서버 저장파일명 + 확장자명 --> logoFile에 해당
			String logoFile = System.currentTimeMillis()+UUID.randomUUID().toString() + exName;
			System.out.println("logoFile --> "+logoFile);
			
			//서버 저장경로+파일명
			String filePath = saveDir + "\\" + logoFile;
			System.out.println("filePath --> "+filePath);
			
			//파일 사이즈(필요X)
			/*
			long fileSize = file.getSize();
			System.out.println("fileSize --> "+fileSize);
			*/
			
			//서버 하드디스크에 저장
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(out);
				
				bos.write(fileData);
				bos.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			BlogVo blogVo = new BlogVo(id, blogTitle, logoFile);
			blogDao.basicUpdateAll(blogVo);
			
		} //if문 끝	
		
	}
	
	
}
