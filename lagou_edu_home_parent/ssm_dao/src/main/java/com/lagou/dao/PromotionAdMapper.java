package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /*分页查询广告*/
    public List<PromotionAd> findAllByPage();
    /*保存广告*/
    public void savePromotionAd(PromotionAd promotionAd);
    /*更新广告*/
    public void updatePromotionAd(PromotionAd promotionAd);
    /*回显广告*/
    public PromotionAd findPromotionById(int id);
    /*修改广告上下线*/
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
