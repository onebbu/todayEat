package com.mycompany.myeat.review.dao;

import com.mycompany.myeat.review.vo.ReviewVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("reviewDAO")
public class ReviewDAOImpl implements ReviewDAO {
	@Autowired
	private SqlSession sqlSession;


	@Override
	public List selectReviewList(int prodno) throws DataAccessException {
		List<ReviewVO> reviewList = null;
		reviewList = sqlSession.selectList("mapper.review.selectReviews", prodno);
		return reviewList;
	}

	@Override
	public ReviewVO selectReviewProduct(int prodno) throws DataAccessException {
		return sqlSession.selectOne("mapper.review.selectReviewProduct", prodno);

	}

	@Override
	public void deleteReview(List<String> revIds) throws DataAccessException {
		// 리스트에서 아이디를 하나씩 꺼내서 쿼리문 수행
		for(int i=0; i<revIds.size(); i++){
			int id = Integer.valueOf(revIds.get(i));
			System.out.println("삭제할 리뷰 id="+id);
			sqlSession.selectList("mapper.review.deleteReview", id);
		}

	}


}
