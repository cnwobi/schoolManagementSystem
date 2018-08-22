package com.chukanwobi.schoolmanagementsystem.converters.courseConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class CourseToCourseCommand implements Converter<Course, CourseCommand> {
    @Override
    public CourseCommand convert(Course course) {
        if(course == null){
            return null;
        }
        CourseCommand command = new CourseCommand();

        command.setId(course.getId());
        command.setTitle(course.getTitle());
        command.setDepartmentalCodes(course.getDepartmentalCodes());

        if(course.getPrerequisitesCollection() !=null&& course.getPrerequisitesCollection().size()>0)
        {
            course.getPrerequisitesCollection().forEach(course1 -> command.getPrerequisitesCollection().add(course1));
        }

        if(course.getCourseConductionList()!=null&&course.getCourseConductionList().size()>0){
            course.getCourseConductionList().forEach(courseConduction -> command.getCourseConductionList().add(courseConduction));
        }
        if(course.getEnrollmentList()!=null && course.getEnrollmentList().size()>0){
            course.getEnrollmentList().forEach(enrollment -> command.getEnrollmentList().add(enrollment));
        }

        if(course.getCourseAssesments()!=null&& course.getCourseAssesments().size()>0){
            course.getCourseAssesments().forEach(courseAssessment -> command.getCourseAssessmentList().add(courseAssessment));
        }

            return command;
    }
}
