package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {
    /*查询课程信息*/
    public List<Course> findCourseByCondition(CourseVo courseVo);
    /*保存课程信息*/
    public void saveCourseorTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;
    /*根据ID查询*/
    public CourseVo findCourseById(int id);
    /*修改老师课程*/
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;
    /*修改课程状态*/
    public void updateCourseStatus(Course course);


}
