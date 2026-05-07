package com.shop.mapper;

import java.util.List;

import com.shop.model.BookVO;
import com.shop.model.Criteria;

public interface BookMapper {

	/* »óÇ° °Ë»ö */
	public List<BookVO> getGoodsList(Criteria cri);
	
	/* »óÇ° ÃÑ °¹¼ö */
	public int goodsGetTotal(Criteria cri);
}
