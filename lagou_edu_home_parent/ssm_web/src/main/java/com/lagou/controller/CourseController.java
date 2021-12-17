package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    /*多条件课程查询*/
    @RequestMapping("/findAllCourse")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){
        System.out.println(courseVo.getCourseName()+"-----------"+courseVo.getStatus());
        List<Course> courseList = courseService.findCourseByCondition(courseVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);
        return result;
    }


    /*课程图片上传*/
    /* http://localhost:8080/ssm-web/course/courseUpload*/
    @RequestMapping("/courseUpload")
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

    /*新建课程信息*/
    /* http://localhost:8080/ssm-web/course/saveOrUpdateCourse */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        System.out.println(courseVo);
        if (courseVo.getId()!=null){
            courseService.updateCourseOrTeacher(courseVo);
            ResponseResult result = new ResponseResult(true, 200, "更新成功", null);
            return result;
        }else {
            courseService.saveCourseorTeacher(courseVo);
            ResponseResult result = new ResponseResult(true, 200, "保存成功", null);
            return result;
        }
    }
    /*根据ID查询*/
    /*http://localhost:8080/ssm-web/course/findCourseById?id=16*/
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int id){
        CourseVo courseVo = courseService.findCourseById(id);
        System.out.println(courseVo);
        ResponseResult result = new ResponseResult(true, 200, "根据ID查询成功", courseVo);
        return result;
    }
    /*http://localhost:8080/ssm-web/course/updateCourseStatus*/
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        Course course = new Course();
        course.setId(id);
        course.setUpdateTime(new Date());
        course.setStatus(status);
        courseService.updateCourseStatus(course);
        //回显信息
        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
}
