package com.shop.service;

import java.util.List;

import com.shop.model.BookVO;
import com.shop.model.Criteria;

public interface BookService {
	
	/* »óÇ° °Ë»ö */
	public List<BookVO> getGoodsList(Criteria cri);
	
	/* »óÇ° ÃÑ °¹¼ö */
	public int goodsGetTotal(Criteria cri);

}
