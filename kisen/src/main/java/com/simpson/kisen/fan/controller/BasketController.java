package com.simpson.kisen.fan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


/**
 * �ѱ� �� ���ñ��?
 * @author moon ju���� �����ϴ�
 *
 */
@Controller
@Slf4j
@RequestMapping("/basket")
public class BasketController {
	
	@GetMapping("/cart.do")
	public void cart() {
		
	}
	@GetMapping("/payment.do")
	public void payment() {
		
	}
	@GetMapping("/payComplet.do")
	public void payComplet() {
		
	}
	
	
}
