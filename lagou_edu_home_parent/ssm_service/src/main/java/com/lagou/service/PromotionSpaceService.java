package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    /*获取所有广告位*/
    public List<PromotionSpace> findAllPromotionSpace();
    /*添加广告位*/
    public void  savePromotionSpace(PromotionSpace promotionSpace);
    /*更新广告位*/
    public void  updatePromotionSpace(PromotionSpace promotionSpace);
    /*广告位回显，根据ID查询*/
    public PromotionSpace findPromotionSpaceById(int id);
}
