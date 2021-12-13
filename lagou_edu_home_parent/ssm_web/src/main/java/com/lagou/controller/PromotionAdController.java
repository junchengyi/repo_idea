package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;

    /*广告分页查询*/
    /*http://localhost:8080/ssm-web/PromotionAd/findAllPromotionAdByPage*/
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){
        PageInfo allAdByPage = promotionAdService.findAllAdByPage(promotionAdVo);
        return new ResponseResult(true,200,"分页查询广告成功",allAdByPage);
    }
    /*广告图片上传*/
    /* http://localhost:8080/ssm-web/course/courseUpload*/
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult courseUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if (file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps\
        String webappsPath = realPath.substring(0,realPath.indexOf("ssm-web"));
        //源文件名
        String filename = file.getOriginalFilename();
        //新文件名
        String newfilename = System.currentTimeMillis()+filename.substring(filename.lastIndexOf("."));
        //上传文件路径
        String uploadpath = webappsPath + "upload\\";
        File filepath = new File(uploadpath, newfilename);
        //如果目录不存在就创建目录
        if(!filepath.getParentFile().exists()){
            filepath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filepath);
        }
        file.transferTo(filepath);//上传文件
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newfilename);
        map.put("filePath","http://localhost:8080/upload/"+newfilename);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
    /*保存更新广告位*/
    /*http://localhost:8080/ssm-web/PromotionAd/saveOrUpdatePromotionAd*/
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if(promotionAd.getId()==null){
            promotionAdService.savePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionAd);
            return result;
        }else {
            promotionAdService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionAd);
            return result;
        }
    }
    /**
     * 根据id回显 广告数据
     * */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id){
        try {
            PromotionAd promotionAd = promotionAdService.findPromotionById(id);
            ResponseResult result = new ResponseResult(true,200,"响应成功",promotionAd);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*http://localhost:8080/ssm-web/PromotionAd/updatePromotionAdStatus*/
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam int id,@RequestParam int status){
        promotionAdService.updatePromotionAdStatus(id,status);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }

}
