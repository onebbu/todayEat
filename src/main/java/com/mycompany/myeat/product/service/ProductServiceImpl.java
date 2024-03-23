package com.mycompany.myeat.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.mySpring.myapp.board.vo.ArticleVO;
import com.mycompany.myeat.product.dao.ProductDAO;
import com.mycompany.myeat.product.vo.Criteria;
import com.mycompany.myeat.product.vo.ProductVO;

@Service("productService")
@Transactional(propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List listProducts() throws DataAccessException {
		List productsList = null;
		productsList = productDAO.selectAllProductList();
		return productsList;
	}
	/*
	 @Override
	    public List sortProducts(String sort) throws DataAccessException {
	        List productsList = null;
	        // log.info("sort={}",sort);
	        if (sort.equals("korean")) {
	            productsList = productDAO.productSortByKorean();
	        } else if (sort.equals("priceAsc")) {
	            productsList = productDAO.productSortByPriceAsc();
	        } else if (sort.equals("priceDesc")) {
	            productsList = productDAO.productSortByPriceDesc();
	        } else if (sort.equals("ratingAsc")) {
	            productsList = productDAO.productSortByRatingAsc();
	        } else if (sort.equals("ratingDesc")) {
	            productsList = productDAO.productSortByRatingDesc();
	        }
	        return productsList;
	    }

	    @Override
	    public List searchProducts(String keyword) throws DataAccessException {
	        List productsList = null;
	        productsList = productDAO.productSearch(keyword);
	        return productsList;
	    }
*/
		@Override
		public int addNewProduct(Map articleMap) throws Exception{
			return productDAO.insertNewProduct(articleMap);
		}
		

		@Override
		public ProductVO viewProduct(int productNO) throws Exception {
			ProductVO productVO = productDAO.selectProduct(productNO);
			return productVO;
		}
	
		@Override
		public void modProduct(Map articleMap) throws Exception {
			productDAO.updateProduct(articleMap);
		}
	@Override
	public int addProduct(ProductVO productVO) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public int countProducts(Criteria cri) throws DataAccessException {
        return productDAO.countProducts(cri);
    }
	
	@Override
    public List searchSortProducts(Criteria cri) throws DataAccessException {
        List productsList = null;
        productsList = productDAO.searchSortProducts(cri);

        return productsList;
    }

    @Override
    public void removeProduct(List<String> productIds) throws DataAccessException {
        productDAO.deleteProduct(productIds);
    }
	
}
