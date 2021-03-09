package com.company.temp.service;

import java.util.List;

import com.company.temp.vo.ProductVO;

public interface ProductService {
	
	public void insertPro(ProductVO vo);
	
	public List<ProductVO> getSearchPro(ProductVO vo);
}
