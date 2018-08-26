package com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LecturerCommandToLecturer implements Converter<LecturerCommand, Lecturer> {

    @Override
    @Synchronized
    @Nullable
    public Lecturer convert(LecturerCommand lecturerCommand) {
        if (lecturerCommand == null) {
            return null;
        }

        Lecturer lecturer = new Lecturer();
        lecturer.setId(lecturerCommand.getId());
        lecturer.setFirstName(lecturerCommand.getFirstName());
        lecturer.setSurname(lecturerCommand.getSurname());
        lecturer.setCampus(lecturerCommand.getCampus());
        lecturer.setEmail(lecturerCommand.getEmail());
        lecturer.setPassword(lecturerCommand.getPassword());
        lecturer.setUsername(lecturerCommand.getUserName());
        if (lecturerCommand.getCoursesConducted()!=null&&lecturerCommand.getCoursesConducted().size()>0){
            lecturerCommand.getCoursesConducted().forEach(courseConduction -> lecturer.getCoursesConducted().add(courseConduction));
        }

        return lecturer;
    }
}
