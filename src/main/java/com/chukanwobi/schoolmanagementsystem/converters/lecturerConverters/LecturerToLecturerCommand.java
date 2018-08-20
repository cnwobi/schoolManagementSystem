package com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import org.springframework.core.convert.converter.Converter;

public class LecturerToLecturerCommand implements Converter<Lecturer, LecturerCommand> {


    @Override
    public LecturerCommand convert(Lecturer lecturer) {
        if(lecturer == null){
            return null;
        }
        LecturerCommand lecturerCommand = new LecturerCommand();

        lecturerCommand.setId(lecturer.getId());
        lecturerCommand.setCampus(lecturer.getCampus());
        lecturerCommand.setEmail(lecturer.getEmail());
        lecturerCommand.setFirstName(lecturer.getFirstName());
        lecturerCommand.setPassword(lecturer.getPassword());
        lecturerCommand.setSurname(lecturer.getSurname());

        if(lecturer.getCoursesConducted()!=null && lecturer.getCoursesConducted().size()>0){
            lecturer.getCoursesConducted().forEach(courseConduction -> lecturerCommand.getCoursesConducted().add(courseConduction));
        }

        return lecturerCommand;
    }
}
