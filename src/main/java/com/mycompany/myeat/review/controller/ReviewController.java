package com.mycompany.myeat.review.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReviewController {

	public ModelAndView reviewListSelect(int prodno, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView removeReview(HttpServletRequest request, HttpServletResponse response) throws Exception;

}

