package com.simpson.kisen.idol.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simpson.kisen.idol.model.service.IdolService;
import com.simpson.kisen.idol.model.vo.DipIdol;
import com.simpson.kisen.idol.model.vo.Idol;
import com.simpson.kisen.idol.model.vo.IdolImg;
import com.simpson.kisen.member.controller.MemberController;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/mypage")
@Slf4j
public class IdolController {
	
	/*
	 * @Autowired private ServletContext application;
	 * 
	 * @Autowired private ResourceLoader resourceLoader;
	 * �׸��� controller�ܿ��� idol ��ü�� list�� �߰� �ؼ� view�ܿ� �Ѱ��ֽø� �ɰŰ��ƿ�
	 * 
	 * img������ upload�� ���ڿ��� �ҷ� ���°�
	 */
	@Autowired
	private IdolService idolService;
		
	@GetMapping("/mypageArtist.do")
	public void selectOneBoard(@RequestParam int fanNo, Model model
			) {
		//1. �������� 
		DipIdol dipidol = idolService.selectOneIdolCollection(fanNo);
		
		//2. ��ü�� ����� ���� ���	
		List<DipIdol> idolList = new ArrayList<>();
		idolList.add(dipidol);
		
		model.addAttribute("dipidol",idolList);
		
	}			
}
