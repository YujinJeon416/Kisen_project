package com.simpson.kisen.product.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.simpson.kisen.product.model.vo.ProductImgExt;

import lombok.extern.slf4j.Slf4j;


@Repository
@Slf4j
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<ProductImgExt> selectProductList() {
		return session.selectList("product.selectProductList");
	}

	@Override
	public ProductImgExt selectOneProduct(int no) {
		return session.selectOne("product.selectOneProduct",no);
	}
	
	
}
