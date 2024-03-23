package com.mycompany.myeat.common.file;

import com.mycompany.myeat.product.vo.ProductVO;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


@Controller
public class FileDownloadController {

    @Autowired
    ProductVO productVO;

//    깃허브 업로드
	private static String img = "";
    
    // 경로 수정
    private static final String PRODUCT_IMAGE_REPO = img;
    private static final String ARTICLE_IMAGE_REPO = img;
    
 // 썸네일 이미지 가져오기
    @RequestMapping("/download.do/thumbnail")
    protected void downloadThumbnail(
            @RequestParam("img") String img,
            @RequestParam("prodno") String prodno,
            HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        String filePath = PRODUCT_IMAGE_REPO + "\\" + prodno + "\\" + img;
        
        File image = new File(filePath);
        int lastIndex = img.lastIndexOf(".");
        String fileName = img.substring(0, lastIndex);
        System.out.println("filename="+fileName);
        File thumbnail = new File(PRODUCT_IMAGE_REPO + "\\" + "thumbnail" + "\\" + fileName + ".png");
        // 원본 이미지 파일을 50*50 픽셀로
        if (image.exists()) {
            Thumbnails.of(image).size(500, 500).outputFormat("png").toOutputStream(out);
        }else {
            return;
        }
        byte[] buffer = new byte[1024 * 8];
        out.write(buffer);
        out.close();
    }
    
    @RequestMapping("/downloadImage.do")
	protected void download(@RequestParam("imageFileName") String imageFileName,
							@RequestParam("productNO") String productNO,
			                 HttpServletResponse response)throws Exception {
		OutputStream out = response.getOutputStream();
		String downFile = ARTICLE_IMAGE_REPO + "\\" +productNO+"\\"+ imageFileName;
		File file = new File(downFile);

		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); 
			if (count == -1) 
				break;
			out.write(buffer, 0, count);
		}				
		in.close();
		out.close();
	}
}
