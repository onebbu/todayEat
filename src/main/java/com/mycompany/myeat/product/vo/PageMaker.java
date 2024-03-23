package com.mycompany.myeat.product.vo;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


public class PageMaker {
    private Criteria cri; // Criteria瑜� 二쇱엯諛쏅뒗�떎.
    private int totalCount; // 寃뚯떆�뙋 �쟾泥� 寃뚯떆湲� 媛쒖닔
    private int startPage; // �쁽�옱 �솕硫댁뿉�꽌 蹂댁씠�뒗 startPage 踰덊샇
    private int endPage; // �쁽�옱 �솕硫댁뿉 蹂댁씠�뒗 endPage 踰덊샇
    private boolean prev; // �럹�씠吏� �씠�쟾 踰꾪듉 �솢�꽦�솕 �뿬遺�
    private boolean next; // �럹�씠吏� �떎�쓬 踰꾪듉 �솢�꽦�솕 �뿬遺�
    private int displayPageNum = 5; // 寃뚯떆�뙋 �솕硫댁뿉�꽌 �븳踰덉뿉 蹂댁뿬吏� �럹�씠吏� 踰덊샇�쓽 媛쒖닔 //

    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData(totalCount);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage + 1;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public Criteria getCri() {
        return cri;
    }

    private void calcData(int totalCount) {
        endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
        startPage = (endPage - displayPageNum);
        int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
        prev = startPage <= 1 ? false : true;
        next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
    }

    public String makeQueryPage(int page) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", cri.getPerPageNum())
                .build();
        return uri.toUriString();
    }

    // �궎�썙�뱶 �솗�씤
    public String isKeyword() {
        if (cri.getKeyword() == null) {
            return null;
        } else {
            return "&keyword=" + cri.getKeyword();
        }

    }

    // �젙�젹 議곌굔 �솗�씤
    public String isSort() {
        if (cri.getSortType() == null) {
            return null;
        } else {
            return "&sortType=" + cri.getSortType();
        }
    }

}
