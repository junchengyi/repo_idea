package com.lagou.domain;

public class PromotionAdVo {
    /*http://localhost:8080/ssm-web/PromotionAd/findAllPromotionAdByPage?currentPage=1&pageSize=5*/
    private Integer currentPage;

    private Integer pageSize;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
