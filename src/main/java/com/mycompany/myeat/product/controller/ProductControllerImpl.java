package com.mycompany.myeat.product.controller;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.myeat.product.service.ProductService;
import com.mycompany.myeat.product.vo.ProductVO;
import com.mycompany.myeat.product.vo.Criteria;
import com.mycompany.myeat.product.vo.PageMaker;

@Controller("productController")
public class ProductControllerImpl implements ProductController{
	private static final String ARTICLE_IMAGE_REPO = "C:\\Users\\soupy\\MULTICAMPUS_19\\eclipse-spring3\\todayeat\\src\\main\\webapp\\resources\\images";
	private static final String PRODUCT_IMAGE_REPO = "C:\\Users\\soupy\\MULTICAMPUS_19\\eclipse-spring3\\todayeat\\src\\main\\webapp\\resources\\images";
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private ProductService productService;
	@Autowired
	ProductVO productVO;
		
	
//	@Override
//	@RequestMapping(value="/product/listProducts.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = getViewName(request);
//		System.out.println("viewname =  " +viewName);
//		List productsList = productService.listProducts();
//		ModelAndView mav = new ModelAndView(viewName);
//		mav.addObject("productsList", productsList);
//		return mav;
//	}
	
//	@Override
//	@RequestMapping(value= "/product/listProducts.do/sort", method = RequestMethod.GET)
//	public ModelAndView productSort(@RequestParam("type") String sortType,HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = getViewName(request);
//		System.out.println("viewName = " + viewName);
//		List productsList = productService.sortProducts(sortType);
//		ModelAndView mav = new ModelAndView(viewName);
//		mav.addObject("productsList", productsList);
//		return mav;
//	}
//	
//	// http://localhost:8080/product/listProducts.do/search?keyword=양념반
//	@Override
//	@RequestMapping(value= "/product/listProducts.do/search", method = {RequestMethod.GET})
//		public ModelAndView productSearch(@RequestParam("keyword") String keyword, HttpServletRequest request, HttpServletResponse response) throws Exception {
//			String viewName = getViewName(request);
//			System.out.println("keyword = " + keyword);
//			System.out.println("viewName = " + viewName);
//			List productsList = productService.searchProducts(keyword);
//			ModelAndView mav = new ModelAndView(viewName);
//			mav.addObject("productsList", productsList);
//			return mav;
//		}
	
	@Override
	@RequestMapping(value="/product/registProduct.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView registProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		System.out.println("viewname =  " + viewName);
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	//상품등록
	// 한 개 이미지 글쓰기
		@Override
		@RequestMapping(value="/product/addNewProduct.do" ,method = {RequestMethod.GET, RequestMethod.POST})
		@ResponseBody
		public ResponseEntity addNewProduct(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
			multipartRequest.setCharacterEncoding("utf-8");
			Map<String,Object> articleMap = new HashMap<String, Object>();
			Enumeration enu=multipartRequest.getParameterNames();
			while(enu.hasMoreElements()){
				String name=(String)enu.nextElement();
				String value=multipartRequest.getParameter(name);
				articleMap.put(name,value);
			}
			

			String imageFileName= upload(multipartRequest);

			articleMap.put("imageFileName", imageFileName);
			
			String message;
			ResponseEntity resEnt=null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			try {
				int articleNO = productService.addNewProduct(articleMap);
				if(imageFileName!=null && imageFileName.length()!=0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+ "\\"+imageFileName); //임시 temp 파일에서
					File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"); // prodno 파일로 이미지를 이동시켜.
					FileUtils.moveFileToDirectory(srcFile, destDir,true);
				}
		
				message = "<script>";
				message += " alert('상품 등록이 완료되었습니다..');";
				message += " location.href='"+multipartRequest.getContextPath()+"/product/listProducts.do'; ";
				message +=" </script>";
			    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			}catch(Exception e) {
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+ "\\"+imageFileName);
				srcFile.delete();
				
				message = " <script>";
				message +=" alert('상품 등록이 실패하였습니다.');";
				message +=" location.href='"+multipartRequest.getContextPath()+"/product/registProduct.do'; ";
				message +=" </script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				e.printStackTrace();
			}
			return resEnt;
		}
	
		//한 개 이미지 업로드 하기
		private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
			String imageFileName= null;
			Iterator<String> fileNames = multipartRequest.getFileNames();
			
			while(fileNames.hasNext()){
				String fileName = fileNames.next();
				MultipartFile mFile = multipartRequest.getFile(fileName);
				imageFileName=mFile.getOriginalFilename();
				File file = new File(ARTICLE_IMAGE_REPO +"\\"+fileName); //기본 루트에서 이미지 파일을 가져와.
				if(mFile.getSize()!=0){ //File Null Check
					if(!file.exists()){ 
						if(file.getParentFile().mkdirs()){ 
								file.createNewFile(); 
						}
						mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); //그리고 임시 temp 파일에 저장해.
					}
				}
			}
			return imageFileName;
		}	
	
	

		//상품수정화면
		//한개의 이미지 보여주기
		@Override
		@RequestMapping(value="/product/modifyProduct.do" ,method = RequestMethod.GET)
		public ModelAndView viewArticle(@RequestParam("prodno") int prodno, HttpServletRequest request, HttpServletResponse response) throws Exception{
			String viewName = getViewName(request);
			System.out.println("viewname =  " +viewName +",prodno : "+prodno);
			productVO = productService.viewProduct(prodno);
			System.out.println(productVO.getImg());
			ModelAndView mav = new ModelAndView();
			mav.setViewName(viewName);
			mav.addObject("product", productVO);
			return mav;
		}
			
			
			
			//한 개 이미지 수정 기능
			@Override
			  @RequestMapping(value="/product/modProduct.do" ,method = RequestMethod.POST)
			  @ResponseBody
			  public ResponseEntity modArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception{
			    multipartRequest.setCharacterEncoding("utf-8");
				Map<String,Object> articleMap = new HashMap<String, Object>();
				Enumeration enu=multipartRequest.getParameterNames();
				while(enu.hasMoreElements()){
					String name=(String)enu.nextElement();
					String value=multipartRequest.getParameter(name);
					
//					수정사항 로그 출력.
					System.out.println("name : "+name);
					System.out.println("value : "+value);
					articleMap.put(name,value);
				}
				
				String imageFileName= upload(multipartRequest); //기본 루트에 있는 이미지를 temp로 옮겨. 
				HttpSession session = multipartRequest.getSession();
				imageFileName = URLEncoder.encode(imageFileName, "UTF-8");
						
				
				articleMap.put("imageFileName", imageFileName);
				
				String productNO=(String)articleMap.get("prodno");
				System.out.println(productNO);
				
				String message;
				ResponseEntity resEnt=null;
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			    try {
			       productService.modProduct(articleMap);
			       if(imageFileName!=null && imageFileName.length()!=0) {
			         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName); 
			         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+productNO+"\\");
			         FileUtils.moveFileToDirectory(srcFile, destDir, true);
			         
			         String originalFileName = (String)articleMap.get("originalFileName");
			         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+productNO+"\\"+originalFileName);
			         oldFile.delete();
			       }	
			       message = "<script>";
				   message += " alert('상품을 수정했습니다.');";
				   message += " location.href='"+multipartRequest.getContextPath()+"/product/listProducts.do'; ";
				   
				   message +=" </script>"; 
			       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			    }catch(Exception e) {
			      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			      srcFile.delete();
			      message = "<script>";
				  message += " alert('오류가 발생했습니다.다시 수정해주세요');";
				  message += " location.href='"+multipartRequest.getContextPath()+"/product/modifyProduct.do?prodno="+productNO+"';";
				  message +=" </script>";
			      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			    }
			    return resEnt;
			  }	
		
	
		@Override
	    @RequestMapping(value = "/product/listProducts.do", method ={RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView productSortSearch(Criteria cri, HttpServletRequest request, HttpServletResponse response) throws Exception {
	        String viewName = getViewName(request);
	        ModelAndView mav = new ModelAndView(viewName);
	        PageMaker pageMaker = new PageMaker();
	        pageMaker.setCri(cri);
	        List productsList = productService.searchSortProducts(cri);
	        pageMaker.setTotalCount(productService.countProducts(cri));
	        mav.addObject("productsList", productsList);
	        mav.addObject("pageMaker", pageMaker);
	        mav.addObject("cri", cri);
	        System.out.println();
	        return mav;
	    }
		//개별 삭제.
		@Override
	    @RequestMapping(value = "/product/listProducts/delete.do", method = RequestMethod.POST)
	    public ModelAndView deleteProduct(@RequestParam("prodno") int prodno,HttpServletRequest request, HttpServletResponse response) throws Exception {
	        
	        String viewName = ("/product/listProducts.do");
	        ModelAndView mav = new ModelAndView(viewName);

	        try {sqlSession.update("mapper.product.deleteProduct", prodno);
	        	response.setContentType("text/html; charset=UTF-8");
	        	PrintWriter writer = response.getWriter();
	        	writer.println("<script>alert('삭제 성공'); </script>"); 
	        	writer.close();
	        	sqlSession.commit();
	        }
	        catch(Exception e) {
	        	
	        }
	        finally{
	        	mav.setViewName("redirect:${contextPath}/product/listProducts.do");
	        	return mav;
	        }
	    }
		
	    @Override
	    @RequestMapping(value = "/product/listProducts/remove.do", method = RequestMethod.POST)
	    public ModelAndView removeProduct(HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	        // ajax를 통해 넘어온 배열 데이터 선언
	        String[] productIds = request.getParameterValues("id");
	        String viewName = getViewName(request);

	        ModelAndView mav = new ModelAndView(viewName);

	        try {productService.removeProduct(Arrays.asList(productIds));
	        	response.setContentType("text/html; charset=UTF-8");
	        	PrintWriter writer = response.getWriter();
	        	writer.println("<script>alert('알럿메세지'); location.href=\"${contextPath}/product/listProducts/remove.do\";</script>"); 
	        	writer.close();
	        }
	        catch(Exception e) {
	        	
	        }

	        return mav;
	    }
	
	    
	    @Override
		@RequestMapping(value="/product/viewProduct.do", method = RequestMethod.GET)
		public ModelAndView viewProduct(@RequestParam("prodno") int prodno, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String viewName = getViewName(request);
			productVO = productService.viewProduct(prodno);
			ModelAndView mav = new ModelAndView();
			mav.setViewName(viewName);
			mav.addObject("product", productVO);
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
