package com.mycompany.myeat.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myeat.product.vo.Criteria;


public interface ProductController {
//	public ModelAndView listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception;
//	public ModelAndView productSort(@RequestParam("sort") String sort, HttpServletRequest request, HttpServletResponse response) throws Exception;
//	public ModelAndView productSearch(@RequestParam("keyword") String keyword, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView registProducts(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView productSortSearch(Criteria cri, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView removeProduct(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewProduct(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	public ModelAndView viewProduct(@RequestParam("prodno") int prodno, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	public ModelAndView viewArticle(@RequestParam("productNO") int productNO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView deleteProduct(@RequestParam("prodno") int prodno,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}

