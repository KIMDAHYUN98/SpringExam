package com.company.temp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.temp.service.ProductService;
import com.company.temp.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired ProductMapper dao;
	
	@Override
	public void insertPro(ProductVO vo) {
		dao.insertPro(vo);
		
	}

	@Override
	public List<ProductVO> getSearchPro(ProductVO vo) {
		return dao.getSearchPro(vo);
	}

}
