package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    /*查询课程*/
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        List<Course> courseList = courseMapper.findCourseByCondition(courseVo);
        return courseList;
    }


    /*保存课程*/
    @Override
    public void saveCourseorTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        System.out.println(courseVo);
        Course course = new Course();
        BeanUtils.copyProperties(course, courseVo);//封装属性

        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        System.out.println(course);
        //保存课程
        courseMapper.saveCourse(course);
        System.out.println(course);
        int id = course.getId();

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVo);
        System.out.println(teacher);

        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);

        courseMapper.saveTeacher(teacher);
        System.out.println(teacher);
    }

    @Override
    public CourseVo findCourseById(int id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course=new Course();
        BeanUtils.copyProperties(course,courseVo);
        //更新时间
        course.setUpdateTime(new Date());
        //更新课程
        courseMapper.updateCourse(course);

        //封装教师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);
        //补全教师
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(new Date());
        //更新教师
        courseMapper.updateTeacher(teacher);

    }

    @Override
    public void updateCourseStatus(Course course) {
        courseMapper.updateCourseStatus(course);
    }


}
