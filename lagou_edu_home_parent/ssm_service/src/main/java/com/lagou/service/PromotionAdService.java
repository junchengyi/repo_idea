package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

public interface PromotionAdService {
    /* 分页获取所有的广告列表 */
    public PageInfo findAllAdByPage(PromotionAdVo adVo);
    /*保存广告*/
    public void savePromotionAd(PromotionAd promotionAd);
    /*更新广告*/
    public void updatePromotionAd(PromotionAd promotionAd);
    /*回显广告*/
    public PromotionAd findPromotionById(int id);
    /*修改广告上下线*/
    public void updatePromotionAdStatus(int id,int status);

}
