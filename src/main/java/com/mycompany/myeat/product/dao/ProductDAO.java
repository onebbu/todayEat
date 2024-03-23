package com.mycompany.myeat.product.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.myeat.product.vo.Criteria;
//import com.mySpring.myapp.board.vo.ArticleVO;
import com.mycompany.myeat.product.vo.ProductVO;

public interface ProductDAO {
	public List selectAllProductList() throws DataAccessException;
//	public List productSortByKorean() throws DataAccessException;
//	public List productSortByPriceAsc() throws DataAccessException;
//	public List productSortByPriceDesc() throws DataAccessException;
//	public List productSortByRatingAsc() throws DataAccessException;
//	public List productSortByRatingDesc() throws DataAccessException;
//	public List productSearch(String keyword) throws DataAccessException;
		
	public int countProducts(Criteria cri) throws DataAccessException;
	public List searchSortProducts(Criteria cri) throws DataAccessException;
	public void deleteProduct(List<String> productIds) throws DataAccessException;

	 public void updateProduct(Map articleMap) throws DataAccessException;
	
	public int insertNewProduct(Map prodMap) throws DataAccessException;
	public ProductVO selectProduct(int productNO) throws DataAccessException;
	public List selectImageFileList(int productNO) throws DataAccessException;
	 

}
