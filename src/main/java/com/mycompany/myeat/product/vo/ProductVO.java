package com.mycompany.myeat.product.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Component;

@Component("productVO")
public class ProductVO {
	private int prodno;
	private String prodname;
	private int price;
	private String descr;
	private String img;
	private String cat;
	public char useyn;
	public Long avgRating;
	public int rNum;
	
	public ProductVO() {

	}
	public String getImg() {
		return img;
	}

	public int getProdno() {
		return prodno;
	}

	public void setProdno(int prodono) {
		this.prodno = prodono;
	}

	public String getProdname() {
		return prodname;
	}

	public void setProdname(String prodname) {
		this.prodname = prodname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}
	public String getImageFileName() {
		return img;
	}

	public void setImageFileName(String imageFileName) {
		try {
			if(imageFileName!= null && imageFileName.length()!=0) {
				this.img = URLEncoder.encode(imageFileName,"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public char getUseYN() {
		return useyn;
	}

	public void setUseYN(char useyn) {
		this.useyn = useyn;
	}

	public Long getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Long avgRating) {
		this.avgRating = avgRating;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	
}
