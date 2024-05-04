package aithings.camAI.controlpanel.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.FileSystems;

@Component
@Slf4j
public class FileUtil {

    public static void deleteFile(String path,String nameFile){
        File file = new File(path+"/"+nameFile);
        file.delete();
    }
    public static String getRoot(){
        return FileSystems.getDefault().getPath("").toAbsolutePath().toString().replaceAll("\\\\","/");
    }
}
