package com.mycompany.myeat.product.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class ImageVO {
    private int imgNo; //imageFileNO -> imgNo(ImgNo)
    private String img; // imageFileName -> img(Img)
    private int prodno;

    public int getImgNo() {
        return imgNo;
    }
    public void setImgNo(int imageFileNO) {
        this.imgNo = imageFileNO;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String imageFileName) {
        try {
            if(imageFileName!= null && imageFileName.length()!=0) {
                this.img = URLEncoder.encode(imageFileName,"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public int getProdno() {
        return prodno;
    }
    public void setProdno(int prodno) {
        this.prodno = prodno;
    }


}
