package com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
public class LecturerToLecturerCommand implements Converter<Lecturer, LecturerCommand> {

    @Override
    @Synchronized
    @Nullable
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
        lecturerCommand.setUserName(lecturer.getUsername());

        if(lecturer.getCoursesConducted()!=null && lecturer.getCoursesConducted().size()>0){
            lecturer.getCoursesConducted().forEach(courseConduction -> lecturerCommand.getCoursesConducted().add(courseConduction));
        }

        return lecturerCommand;
    }
}
