package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam int courseId){
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "查询课时成功", sectionList);
        return result;
    }

    /*http://localhost:8080/ssm-web/courseContent/findCourseByCourseId?courseId=15*/
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam int courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",course.getId());
        map.put("courseName",course.getCourseName());
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
    /*http://localhost:8080/ssm-web/courseContent/saveOrUpdateSection*/
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {
        if (section.getId()==null){
            courseContentService.saveSection(section);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
            return result;
        }else {
            courseContentService.updateSection(section);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
            return result;
        }

    }
    /*http://localhost:8080/ssm-web/courseContent/updateSectionStatus*/
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status) {
        courseContentService.updateSectionStatus(id, status);
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }
    /*http://localhost:8080/ssm-web/courseContent/saveLesson */
    @RequestMapping("/saveLesson")
    public ResponseResult saveLesson(@RequestBody CourseLesson courseLesson) {
        if (courseLesson.getId()== null){
            courseContentService.saveLesson(courseLesson);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
            return result;
        }else {
            courseContentService.updateLesson(courseLesson);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
            return result;
        }
        }
}

