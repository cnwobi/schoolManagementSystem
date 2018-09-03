package util;

import com.chukanwobi.schoolmanagementsystem.models.Semester;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


import java.time.Year;
import java.util.Calendar;

public class CurrentSemesterUtil {

     private static  CurrentSemesterUtil instance;
    private CurrentSemesterUtil() {
    }

    public static CurrentSemesterUtil getInstance() {
        if(instance == null)
        {
            instance =  new CurrentSemesterUtil();
        }
        return instance;
    }

    @Nullable
    public   Semester calculateCurrentSemester(){
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.MONTH)>=1 && calendar.get(Calendar.MONTH)<=5){
            return   Semester.FIRST;

        }
        else if(calendar.get(Calendar.MONTH)>=6 && calendar.get(Calendar.MONTH)<=10) {
            return Semester.SECOND;

        }

        return Semester.BREAK;


    }

    public Year getCurrentYear(){
return Year.now();
    }
}
