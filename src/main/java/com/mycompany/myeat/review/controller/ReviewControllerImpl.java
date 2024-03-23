package com.mycompany.myeat.review.controller;

import com.mycompany.myeat.product.vo.PageMaker;
import com.mycompany.myeat.review.service.ReviewService;
import com.mycompany.myeat.review.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller("reviewController")
public class ReviewControllerImpl implements ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Override
    @RequestMapping(value="/review/viewReview.do", method = RequestMethod.GET)
    public ModelAndView reviewListSelect(@RequestParam("prodno") int prodno, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        ModelAndView mav = new ModelAndView(viewName);
        List reviewList = reviewService.viewReviews(prodno);
        ReviewVO reviewProduct = reviewService.viewProductReview(prodno);
        mav.setViewName(viewName);
        mav.addObject("reviewList", reviewList);
        mav.addObject("product", reviewProduct);
        return mav;
    }
    @Override
    @RequestMapping(value = "/review/viewReview.do/remove", method = RequestMethod.POST)
    public ModelAndView removeReview(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // ajax瑜� �넻�빐 �꽆�뼱�삩 諛곗뿴 �뜲�씠�꽣 �꽑�뼵
        String[] revIds = request.getParameterValues("id");
        String viewName = getViewName(request);
        reviewService.removeReview(Arrays.asList(revIds));

        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }

    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
        }

        int begin = 0;
        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
        }

        int end;
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        String viewName = uri.substring(begin, end);
        if (viewName.indexOf(".") != -1) {
            viewName = viewName.substring(0, viewName.lastIndexOf("."));
        }
        if (viewName.lastIndexOf("/") != -1) {
            viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
        }
        return viewName;
    }



}
