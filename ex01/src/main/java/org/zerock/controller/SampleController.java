package org.zerock.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	//@RequestMapping(value = "basic", method = {RequestMethod.GET,RequestMethod.POST})
	@GetMapping("/basic")
	public void basic() {
		log.info("basic..........");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto.toString());
		return "ex01";
	}
	

	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name =" +  name);
		log.info("age = "+ age);
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids =" + ids);
	
		return "ex02List";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos =" + list);
	
		return "ex02Bean";
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(df,false));
//	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo dtos =" + todo);
	
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto,int page, Model model) {
		
		log.info("dto : " + dto);
		log.info("page : " + page);
		
		model.addAttribute("page",page);
		return "/sample/ex04";
		//@ModelAttribute("page") 
	}
	
	@GetMapping("/exRedirect")
	public String exRedirect(String data,RedirectAttributes rttr) {
//		model.addAttribute(data);
		rttr.addFlashAttribute("data",data);
		return "redirect:/sample/exRedirect2";
	}
	
	@GetMapping("/exRedirect2")
	public String exRedirect2() {
		//model.addAttribute("data",data);
		
		return "sample/exRedirect";
	}
	
	@GetMapping("/ex06") 
	public @ResponseBody SampleDTO ex06() {
		log.info("ex06...........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
		
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		String msg = "<html><body><h1>홍길동</h1><hr></body></html>";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=utf-8");
		header.add("testheader", "testcontent");
		return new ResponseEntity<>(msg,header,HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload....");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});
	}	
}