package com.chukanwobi.schoolmanagementsystem.services.security;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.repositories.LecturerRepository;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LecturerSecurityService implements UserDetailsService {
    private LecturerRepository lecturerRepository;

    public LecturerSecurityService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       Optional<Lecturer> optionalLecturer=lecturerRepository.findLecturerByUsername(userName);
        if(!optionalLecturer.isPresent()){
            log.debug("User with username: "+ userName+" not found!");
            throw new UsernameNotFoundException("User with username: "+ userName+" not found!");
        }
        return optionalLecturer.get();
    }
}
