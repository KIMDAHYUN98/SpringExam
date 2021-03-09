package com.company.temp.service.impl;

import java.util.List;

import com.company.temp.vo.ProductVO;
public interface ProductMapper {
	
	public void insertPro(ProductVO vo);
	
	public List<ProductVO> getSearchPro(ProductVO vo);
}
