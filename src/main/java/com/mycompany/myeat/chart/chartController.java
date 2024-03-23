package com.mycompany.myeat.chart;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public interface chartController {
	public ModelAndView simplechart(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView tab01(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView tab02(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView tab03(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView tab04(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView tab01lookup(@ModelAttribute("chart")  ChartVO chart,
			RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView tab02lookup(@ModelAttribute("chart")  ChartVO chart,
			RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView tab03lookup(@ModelAttribute("input_date") Date input_date,
			RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws Exception;

}
