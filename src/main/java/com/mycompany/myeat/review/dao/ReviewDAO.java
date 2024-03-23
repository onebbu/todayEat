package com.mycompany.myeat.review.dao;

import com.mycompany.myeat.product.vo.ProductVO;
import com.mycompany.myeat.review.vo.ReviewVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ReviewDAO {

    public List selectReviewList(int prodno) throws DataAccessException;

    public ReviewVO selectReviewProduct(int prodno) throws DataAccessException;

    public void deleteReview(List<String> revIds) throws DataAccessException;



}
