package com.mycompany.myeat.review.service;

import com.mycompany.myeat.review.vo.ReviewVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ReviewService {

	public List viewReviews(int prodno) throws Exception;
	public ReviewVO viewProductReview(int prodno) throws Exception;
	public void removeReview(List<String> revIds) throws DataAccessException;



}
