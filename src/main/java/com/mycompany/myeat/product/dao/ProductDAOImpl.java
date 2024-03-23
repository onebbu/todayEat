package com.mycompany.myeat.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import com.mycompany.myeat.product.vo.ProductVO;
import com.mycompany.myeat.product.vo.Criteria;
import com.mycompany.myeat.product.vo.ImageVO;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllProductList() throws DataAccessException {
		List<ProductVO> productsList = null;
		productsList = sqlSession.selectList("mapper.product.selectAllProductList");
		return productsList;
	}
	
	@Override
	public void updateProduct(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.product.updateProduct", articleMap);
	}
	/*	
	@Override
	public List productSortByKorean() throws DataAccessException {
		List<ProductVO> productsList = null;
		productsList = sqlSession.selectList("mapper.product.productSortByKorean");
		return productsList;
	}

	@Override
	public List productSortByPriceAsc() throws DataAccessException {
		List<ProductVO> productsList = null;
		productsList = sqlSession.selectList("mapper.product.productSortByPriceAsc");
		return productsList;
	}

	@Override
	public List productSortByPriceDesc() throws DataAccessException {
		List<ProductVO> productsList = null;
		productsList = sqlSession.selectList("mapper.product.productSortByPriceDesc");
		return productsList;
	}

	@Override
	public List productSortByRatingAsc() throws DataAccessException {
		List<ProductVO> productsList = null;
		productsList = sqlSession.selectList("mapper.product.productSortByRatingAsc");
		return productsList;
	}

	@Override
	public List productSortByRatingDesc() throws DataAccessException {
		List<ProductVO> productsList = null;
		productsList = sqlSession.selectList("mapper.product.productSortByRatingDesc");
		return productsList;
	}

	@Override
	public List productSearch(String keyword) throws DataAccessException {
		List<ProductVO> productsList = null;
		productsList = sqlSession.selectList("mapper.product.productSearch", keyword);
		return productsList;
	}
*/
	@Override
	public int countProducts(Criteria cri) throws DataAccessException {
		int result = sqlSession.selectOne("mapper.product.countProducts", cri);
		return result;
	}
	
	@Override
	public List searchSortProducts(Criteria cri) throws DataAccessException{
		List<ProductVO> productsList = null;
		productsList = sqlSession.selectList("mapper.product.searchSortProducts", cri);
		return productsList;
	}

	// id를 건너받아서 id 갯수만큼 쿼리문 수행 후 종료(리턴값 없음)
	@Override
	public void deleteProduct(List<String> productIds) throws DataAccessException {
		// 리스트에서 아이디를 하나씩 꺼내서 쿼리문 수행
		for(int i=0; i<productIds.size(); i++){
			int id = Integer.valueOf(productIds.get(i));
			System.out.println("id="+id);
			sqlSession.update("mapper.product.deleteProduct", id);
		}
	}
	
	
	@Override
	public int insertNewProduct(Map articleMap) throws DataAccessException {
		int productNO = selectNewProductNO();
		articleMap.put("prodno", productNO);
		sqlSession.insert("mapper.product.insertNewProduct",articleMap);
		return productNO;
	}
	
	private int selectNewProductNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.product.selectNewProductNO");
	}
	@Override
	public ProductVO selectProduct(int prodNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.product.selectProduct", prodNO);
	}
	
	@Override
	public List selectImageFileList(int prodNO) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.product.selectImageFileList",prodNO);
		return imageFileList;
	}
	
	

}
