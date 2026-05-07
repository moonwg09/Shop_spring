package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.BookMapper;
import com.shop.model.BookVO;
import com.shop.model.Criteria;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper bookMapper;
	
	/* »óÇ° °Ë»ö */
	@Override
	public List<BookVO> getGoodsList(Criteria cri) {
		
		log.info("getGoodsList().......");
		
		return bookMapper.getGoodsList(cri);
	}

	/* »çÇ° ÃÑ °¹¼ö */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		log.info("goodsGetTotal().......");
		
		return bookMapper.goodsGetTotal(cri);
		
	}
}
