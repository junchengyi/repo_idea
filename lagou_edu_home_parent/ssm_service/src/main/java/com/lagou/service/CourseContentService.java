package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    /*查询章节课时信息*/
    public List<CourseSection> findSectionAndLessonByCourseId(int courseid);
    /*回显课程标题*/
    public Course findCourseByCourseId(int courseId);
    /*保存章节*/
    public void saveSection(CourseSection courseSection);
    /*更新章节*/
    public void updateSection(CourseSection courseSection);
    /*修改章节状态*/
    public void updateSectionStatus(int id,int status);
    /*保存课时*/
    public void saveLesson(CourseLesson courseLesson);
    /*更新课时*/
    public void updateLesson(CourseLesson courseLesson);
}
