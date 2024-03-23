package com.mycompany.myeat.review.service;

import com.mycompany.myeat.review.dao.ReviewDAO;
import com.mycompany.myeat.review.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("reviewService")
@Transactional(propagation = Propagation.REQUIRED)
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List viewReviews(int prodno) throws Exception {
        List<ReviewVO> reviewList = null;
        reviewList = reviewDAO.selectReviewList(prodno);
        return reviewList;
    }

    @Override
    public ReviewVO viewProductReview(int prodno) throws Exception {
        return reviewDAO.selectReviewProduct(prodno);
    }

    @Override
    public void removeReview(List<String> revIds) throws DataAccessException {
        reviewDAO.deleteReview(revIds);
    }
}
