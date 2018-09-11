package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseToCourseCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.converters.studentConverter.StudentToStudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseConductionToCourseConductionCommand implements Converter<CourseConduction, CourseConductionCommand> {



private StudentToStudentCommand studentConverter;

    private LecturerToLecturerCommand lecturerConverter;
    private CourseToCourseCommand courseToCourseConverter;

    public CourseConductionToCourseConductionCommand(StudentToStudentCommand studentConverter, LecturerToLecturerCommand lecturerConverter, CourseToCourseCommand courseToCourseConverter) {
        this.studentConverter = studentConverter;
        this.lecturerConverter = lecturerConverter;
        this.courseToCourseConverter = courseToCourseConverter;
    }

    @Override
    @Synchronized
    @Nullable
    public CourseConductionCommand convert(CourseConduction courseConduction) {
        if(courseConduction==null){
            return null;
        }
        CourseConductionCommand  command= new CourseConductionCommand();

        command.setId(courseConduction.getId());
        command.setCapacity(courseConduction.getCapacity());
        command.setCourse(courseToCourseConverter.convert(courseConduction.getCourse()));
        command.setLecturer(lecturerConverter.convert(courseConduction.getLecturer()));
        command.setSemester(courseConduction.getSemester());
        command.setYear(courseConduction.getYear());
        if(courseConduction.getStudents()!=null&& courseConduction.getStudents().size()>0){
            courseConduction.getStudents().forEach(student -> command.getStudentCommands().add(studentConverter.convert(student)));
        }

         if(courseConduction.getCourseConductionAssessments()!=null&&courseConduction.getCourseConductionAssessments().size()>0){
            courseConduction.getCourseConductionAssessments().forEach(courseConductionAssessment -> command.getCourseConductionAssessments().add(courseConductionAssessment));
         }
        return command;
    }
}
