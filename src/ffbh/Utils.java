package ffbh;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    private static String sepa = java.io.File.separator;

    public static void move(String src, String des,String file) throws Exception {
        File des_dir = new File(des);
        Files.move(Paths.get(src+sepa+file), Paths.get(des+sepa+file));
    }

    public static void moveAll(String src,String des) throws Exception{
        File file = new File(src);
        for(File f : file.listFiles()){
            move(src,des,f.getName());
        }

    }

    public static void copy(String src, String des,String file, String des_name) throws Exception {
        File des_dir = new File(des);
        Files.copy(Paths.get(src+sepa+file), Paths.get(des+sepa+des_name));
    }


    public static void cmakeLists(String path,String p,String f) throws Exception{
        BufferedWriter out = new BufferedWriter(new FileWriter(path, false));
        out.write("cmake_minimum_required(VERSION 3.9)\n");
        out.write(String.format("project(%s)\n",p));
        out.write("set(CMAKE_CXX_STANDARD 14)\n");
        out.write("\n");
        String special_blank = "\\\\" + " ";
        out.write(String.format("add_executable(%s %s)\n",p,f.trim().replaceAll(" ",special_blank)));
        out.close();
    }
//
//    public static void main(String[] args) {
//        String f1 = "/Users/jiazhi.zhou/task/plugin/test1";
//        String f2 = "/Users/jiazhi.zhou/task/plugin/test2";
//
//        try {
//            copy(f2,f1,"2.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
