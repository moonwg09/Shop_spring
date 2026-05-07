package com.shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.model.BookVO;
import com.shop.model.Criteria;
import com.shop.model.PageDTO;
import com.shop.service.BookService;

@Controller
public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
//	@Autowired
//	private AttachService attachService;
	
	@Autowired
	private BookService bookService;
	
	/* 상품 검색 */
	@GetMapping("search")
	public String searchGoodsGET(Criteria cri, Model model) {
		
		logger.info("cri : " + cri);
		
		List<BookVO> list = bookService.getGoodsList(cri);
		logger.info("pre list : " + list);
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			logger.info("list : " + list);
		} else {
			model.addAttribute("listcheck", "empty");
			
			return "search";
		}
		
		model.addAttribute("pageMaker", new PageDTO(cri, bookService.goodsGetTotal(cri)));
		
		
		return "search";
		
	}

}
