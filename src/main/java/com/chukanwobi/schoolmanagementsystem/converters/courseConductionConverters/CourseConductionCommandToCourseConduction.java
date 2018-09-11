package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseCommandToCourse;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerCommandToLecturer;
import com.chukanwobi.schoolmanagementsystem.converters.studentConverter.StudentCommandToStudent;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseConductionCommandToCourseConduction implements Converter<CourseConductionCommand,CourseConduction> {

    private LecturerCommandToLecturer commandToLecturerConverter;
    private CourseCommandToCourse commandToCourseConverter;
    private StudentCommandToStudent commandToStudentConverter;

    public CourseConductionCommandToCourseConduction(LecturerCommandToLecturer commandToLecturerConverter, CourseCommandToCourse commandToCourseConverter, StudentCommandToStudent commandToStudentConverter) {
        this.commandToLecturerConverter = commandToLecturerConverter;
        this.commandToCourseConverter = commandToCourseConverter;
        this.commandToStudentConverter = commandToStudentConverter;
    }

    @Override
    public CourseConduction convert(CourseConductionCommand conductionCommand) {
        if(conductionCommand==null){
            return null;
        }

        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setId(conductionCommand.getId());
        courseConduction.setCourse(commandToCourseConverter.convert(conductionCommand.getCourse()));
        courseConduction.setYear(conductionCommand.getYear());
        courseConduction.setLecturer(commandToLecturerConverter.convert(conductionCommand.getLecturer()));
       courseConduction.setSemester(conductionCommand.getSemester());
       courseConduction.setCapacity(conductionCommand.getCapacity());
       if(conductionCommand.getStudentCommands()!=null&& conductionCommand.getStudentCommands().size()>0){
           conductionCommand.getStudentCommands().forEach(studentCommand -> courseConduction.getStudents().add(commandToStudentConverter.convert(studentCommand)));
       }

       if(conductionCommand.getCourseConductionAssessments()!=null && conductionCommand.getCourseConductionAssessments().size()>0){
           conductionCommand.getCourseConductionAssessments().forEach(courseConductionAssessment -> courseConduction.getCourseConductionAssessments().add(courseConductionAssessment));
       }

        return courseConduction;
    }
}
