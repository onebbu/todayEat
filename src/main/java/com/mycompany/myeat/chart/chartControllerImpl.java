package com.mycompany.myeat.chart;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import java.util.Map;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("chartController")
public class chartControllerImpl implements chartController {
   @Autowired
   ChartVO chartvo;
   @Autowired
   private SqlSession sqlSession;
   
   @Override
   @RequestMapping(value = "/chart/simplechart.do", method = RequestMethod.GET)
   public ModelAndView simplechart(HttpServletRequest request, HttpServletResponse response) throws Exception{
      String viewName = "/chart/simplechart";
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      return mav;
   }
   //tab-1 ~ tab-6 단순 페이지 이동 기능
   @Override
   @RequestMapping(value = "/chart/tab-1.do", method = RequestMethod.GET)
   public ModelAndView tab01(HttpServletRequest request, HttpServletResponse response) throws Exception{
      String viewName = getViewName(request);
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      return mav;
   }
   
   @Override
   @RequestMapping(value = "/chart/tab-2.do", method = RequestMethod.GET)
   public ModelAndView tab02(HttpServletRequest request, HttpServletResponse response) throws Exception{
      String viewName = "/chart/week_chart";
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      return mav;
   }
   
   @Override
   @RequestMapping(value = "/chart/tab-3.do", method = RequestMethod.GET)
   public ModelAndView tab03(HttpServletRequest request, HttpServletResponse response) throws Exception{
      String viewName = "/chart/month_chart";
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      return mav;
   }
   
   @Override
   @RequestMapping(value = "/chart/tab-4.do", method = RequestMethod.GET)
   public ModelAndView tab04(HttpServletRequest request, HttpServletResponse response) throws Exception{
      String viewName = "/chart/quater_chart";
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      return mav;
   }
   
   //lookup 조회하기. 원래 Service, DAO 분리해야하지만 잘 모르겠어서 걍 컨트롤러에 분리해넣음... 
   @Override
   @RequestMapping(value = "/chart/tab-1/lookup.do", method = RequestMethod.GET)
   public ModelAndView tab01lookup(@ModelAttribute("chart") ChartVO chart,
                        RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView mav = new ModelAndView();
      Date date_from = Date.valueOf(request.getParameter("date_from"));
      Date date_to = Date.valueOf(request.getParameter("date_to"));
      int box01 = sqlSession.selectOne("mapper.chart.selectbox1",chart);
      int box02 = sqlSession.selectOne("mapper.chart.selectbox2", chart);
      int box03 = sqlSession.selectOne("mapper.chart.selectbox3", chart);
      String box04 = sqlSession.selectOne("mapper.chart.selectbox4", chart);
      int box04_1 = sqlSession.selectOne("mapper.chart.selectbox4-1", chart);
      String box05 = sqlSession.selectOne("mapper.chart.selectbox5", chart);
      int box05_1 = sqlSession.selectOne("mapper.chart.selectbox5-1", chart);
      String box06 = sqlSession.selectOne("mapper.chart.selectbox6", chart);
      int box06_1 = sqlSession.selectOne("mapper.chart.selectbox6-1", chart);
      request.setAttribute("date_from", date_from);
      request.setAttribute("date_to", date_to);

      mav.addObject("box01", box01);
      mav.addObject("box02", box02);
      mav.addObject("box03", box03);
      mav.addObject("box04", box04);
      mav.addObject("box04_1", box04_1);
      double box04Per = (double)box04_1 / (double)box01;
      mav.addObject("box04Per",box04Per);
      mav.addObject("box05", box05);
      mav.addObject("box05_1", box05_1);
      double box05Per = (double)box05_1 / (double)box03;
      mav.addObject("box05Per",box05Per);
      mav.addObject("box06", box06);
      mav.addObject("box06_1", box06_1);

      mav.setViewName("forward:/chart/tab-1.do");
   
      return mav;
   }
   
   @Override
   @RequestMapping(value = "/chart/tab-2/lookup.do", method = RequestMethod.GET)
   public ModelAndView tab02lookup(@ModelAttribute("chart") ChartVO chart,
                        RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView mav = new ModelAndView();
      Date date_from = Date.valueOf(request.getParameter("date_from"));
      Date date_to = Date.valueOf(request.getParameter("date_to"));
      request.setAttribute("date_from", date_from);
      request.setAttribute("date_to", date_to);
      Map<String,Object>map = new HashMap<String,Object>();
      
      map.put("date_from", date_from);
      map.put("date_to", date_to);

      int[] weeklist = new int[8]; // 1:일 2:월 3:화 4:수 5:목 6:금 7:토
      for(int i = 1; i < 8; i ++ ) {
         map.put("i", i);
         weeklist[i] = sqlSession.selectOne("mapper.chart.weekSumPrice",map); //파라미터로 chart(date_from,date_to)와 i(weekday) 전달
      }
      mav.addObject("weeklist",weeklist);
      mav.setViewName("forward:/chart/tab-2.do");
      
      return mav;
   }
   
   @Override
   @RequestMapping(value = "/chart/tab-3/lookup.do", method = RequestMethod.GET)
   public ModelAndView tab03lookup(@ModelAttribute("input_date") Date input_date,
                        RedirectAttributes rAttr,HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView mav = new ModelAndView();
      SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat yyMM = new SimpleDateFormat("yy-MM");
      Calendar cal = Calendar.getInstance();
      Date date_from = Date.valueOf(input_date.toString());
      Date date_to = Date.valueOf(input_date.toString());
      cal.setTime(date_from);
      cal.add(Calendar.MONTH, -5);
      date_from = Date.valueOf(sdfYMD.format(cal.getTime()));
      request.setAttribute("date_from", date_from);
      request.setAttribute("date_to", date_to);
      Map<String,Object>map = new HashMap<String,Object>();
      
      String[] mList = new String[7];
      
      int[] monthlist_han = new int[7]; // 한식
      int[] monthlist_yang = new int[7]; //양식
      int[] monthlist_joong = new int[7]; //중식
      int[] monthlist_boon = new int[7]; //분식
      int[] cntH = new int[7];
      int[] cntY = new int[7];
      Date start; //월초
      Date end; //월말
      
      cal.setTime(date_from);
      cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH)); // 월 초
      
      for(int i = 1; i < 7; i ++ ) {
         
         start = Date.valueOf(sdfYMD.format(cal.getTime()));
         cal.set(Calendar.DATE,  cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // 마지막 일.
         end = Date.valueOf(sdfYMD.format(cal.getTime()));
         mList[i] = yyMM.format(start); 
         map.put("start", start);
         map.put("end", end);
         map.put("cat", "한식");
         monthlist_han[i] = sqlSession.selectOne("mapper.chart.monthSumPrice",map);
         cntH[i] = sqlSession.selectOne("mapper.chart.monthCntCat",map);
         map.put("cat", "양식");
         monthlist_yang[i] = sqlSession.selectOne("mapper.chart.monthSumPrice",map);
         cntY[i] = sqlSession.selectOne("mapper.chart.monthCntCat",map);
         map.put("cat", "중식");
         monthlist_joong[i] = sqlSession.selectOne("mapper.chart.monthSumPrice",map);
         map.put("cat", "분식");
         monthlist_boon[i] = sqlSession.selectOne("mapper.chart.monthSumPrice",map); 
         
         
         //파라미터로 chart(date_from,date_to)와 i(month), cat(한식) 전달
         cal.add(Calendar.DATE, 1);   
      }
      
      mav.addObject("mList", mList);
      mav.addObject("monthlist_han",monthlist_han);
      mav.addObject("mL_yang",monthlist_yang);
      mav.addObject("monthlist_joong",monthlist_joong);
      mav.addObject("mL_B",monthlist_boon);
      mav.addObject("cntH", cntH);
      mav.addObject("cntY", cntY);
      
      
      
      mav.setViewName("forward:/chart/tab-3.do");
      
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
        if(viewName.indexOf(".") != -1) {
           viewName = viewName.substring(0, viewName.lastIndexOf("."));
        }
      if(viewName.lastIndexOf("/") != -1) {
         viewName = viewName.substring(viewName.lastIndexOf("/",1),viewName.length());
          //   member/listMembers.do
      }
      return viewName;
   }
   
}