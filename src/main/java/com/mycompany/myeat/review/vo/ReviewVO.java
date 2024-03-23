package com.mycompany.myeat.review.vo;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component("reviewVO")
public class ReviewVO {
    private int prodno;
    private String prodname;
    private String userid;
    private int rating;
    private String content;
    private Date indate;
    private char useYN;
    private int revno;
    private int odno;
    private String img;
    private int rNum;

    public ReviewVO() {
    }

    public int getProdno() {
        return prodno;
    }

    public void setProdno(int prodno) {
        this.prodno = prodno;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public char getUseYN() {
        return useYN;
    }

    public void setUseYN(char useYN) {
        this.useYN = useYN;
    }

    public int getRevno() {
        return revno;
    }

    public void setRevno(int revno) {
        this.revno = revno;
    }

    public int getOdno() {
        return odno;
    }

    public void setOdno(int odno) {
        this.odno = odno;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rNum) {
        this.rNum = rNum;
    }
}
