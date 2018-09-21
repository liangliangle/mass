package test.mass.util; /**
 * Created by SJG on 2015/11/18.
 */
import com.mass.dto.create.CreateActivitiesDto;
import com.mass.utils.Check;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetUrl {
    public static void main(String args[]){
        CreateActivitiesDto dto=new CreateActivitiesDto();
        dto.setName("dddd");
        Check.checkEmpty(dto);
    }
}
