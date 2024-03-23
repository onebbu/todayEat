package com.mycompany.myeat.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.myeat.product.vo.Criteria;
//import com.mycompany.myeat.product.vo.ArticleVO;
import com.mycompany.myeat.product.vo.ProductVO;

public interface ProductService {
	public List listProducts() throws DataAccessException;
//	public List sortProducts(String sort) throws DataAccessException;
//	public List searchProducts(String sort) throws DataAccessException;
	public int addNewProduct(Map ProductMap) throws Exception;
	public int addProduct(ProductVO productVO) throws DataAccessException;
	public ProductVO viewProduct(int prodno) throws Exception;
	public void modProduct(Map articleMap) throws Exception;
	public int countProducts(Criteria cri) throws DataAccessException;
	public List searchSortProducts(Criteria cri) throws DataAccessException;
	public void removeProduct(List<String> productIds) throws DataAccessException;
}
