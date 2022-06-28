package assignment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author pvv
 */
public class Terminal extends Parser {

    Parser parser;
    static File _file_;

    public Terminal() {
        Path pwd = Paths.get("");
        String s = pwd.toAbsolutePath().toString();
        _file_ = new File(s);
    }

//Implement each command in a method, for example:
    public void pwd() {
        System.out.println(_file_);

    }

    public void cd() {
        _file_ = new File(System.getProperty("user.dir"));
        System.out.println(_file_);
    }

    public void cd(String word) {
        try {

            if (args[0].equals("..")) {
                _file_ = new File(_file_.getParent());
                System.out.println(_file_);
            } else {
                String path = Paths.get(word).toString();
                File file = new File(path);

                String st1[] = path.split("\\\\");

                if (file.exists()) {
                    _file_ = new File(path);
                    System.out.println(_file_);
                } else {
                    String path1 = _file_.getAbsoluteFile().toString() + "\\" + file;
                    _file_ = new File(path1);
                    System.out.println(_file_);
                }

            }
        } catch (Exception e) {
            System.out.println("not valid path");
            _file_ = new File(System.getProperty("use.dir"));
        }
    }

    protected static void copybyte(String file1, String file2) throws Exception {
        try {

            String st1[] = file1.split("\\\\");
            String st2[] = file2.split("\\\\");
            if (st1.length == 1) {
                String path1 = _file_.toString() + "\\" + file1;
                file1 = path1;
            }
            if (st2.length == 1) {
                String path2 = _file_.toString() + "\\" + file2;
                file2 = path2;
            }

            File FilePath1 = new File(file1);
            File Filepath2 = new File(file2);
            if (!Filepath2.exists()) {
                FileWriter FilePath1_2 = new FileWriter(Filepath2);
                FilePath1_2.write("");
                FilePath1_2.close();
            }

            FileInputStream fis = new FileInputStream(FilePath1);
            FileOutputStream fos = new FileOutputStream(Filepath2);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int i = 0;
            while ((i = bis.read()) != -1) {
                bos.write(i);
            }
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
            fis.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }

    }

    public static void cat(String file) throws Exception {
        try {
            String st1[] = file.split("\\\\");
            if (st1.length == 1) {
                String path1 = _file_.toString() + "\\" + file;
                file = path1;
            }
            File Filepath = new File(file);
            FileInputStream fileread = new FileInputStream(Filepath);
            int i = 0;
            while ((i = fileread.read()) != -1) {
                System.out.print((char) i);
            }
            fileread.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e);
        }
    }

    public static void cat(String file1, String file2) throws Exception {
        try {
            String st1[] = file1.split("\\\\");
            String st2[] = file2.split("\\\\");
            if (st1.length == 1) {
                String path1 = _file_.toString() + "\\" + file1;
                file1 = path1;
            }
            if (st2.length == 1) {
                String path2 = _file_.toString() + "\\" + file2;
                file2 = path2;
            }
            File Filepath1 = new File(file1);
            File Filepath2 = new File(file2);
            FileInputStream fileread1 = new FileInputStream(Filepath1);
            FileInputStream fileread2 = new FileInputStream(Filepath2);
            int i = 0;
            while ((i = fileread1.read()) != -1) {
                System.out.print((char) i);
            }
            while ((i = fileread2.read()) != -1) {
                System.out.print((char) i);
            }
            fileread1.close();
            fileread2.close();
            //or we can use this is -->  cat(file1);cat(file2);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e);
        }
    }

    private static void copyfolder(String s, String d) throws IOException, Exception {
        String st1[] = s.split("\\\\");
        String st2[] = d.split("\\\\");
        if (st1.length == 1) {
            String path1 = _file_.toString() + "\\" + s;
            s = path1;
        }
        if (st2.length == 1) {
            String path2 = _file_.toString() + "\\" + d;
            d = path2;
        }
        File file_1 = new File(s);
        File file_2 = new File(d);
        if (file_1.isDirectory()) {
            if (!file_2.exists()) {
                file_2.mkdirs();
            }
            String files[] = file_1.list();
            for (String file : files) {
                //this for creat file from parent it has son folder in it
                File srcFile = new File(file_1, file);
                File destFile = new File(file_2, file);

                String sr = srcFile.toString();
                String de = destFile.toString();
                copyfolder(sr, de);
            }
        } else {
            //for copy the text
            String sf = file_1.toString();
            String df = file_2.toString();
            copybyte(sf, df);
        }
    }

    public void rmdir(String fi) {
        if (args[0].equals("*")) {

            File[] files = _file_.listFiles();

            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    if (files[i].length() == 0) {
                        files[i].delete();
                    }
                }
            }
        } else {

            File file = new File(fi);
            String f = file.toString();
            String st2[] = f.split("\\\\");
            if (st2.length == 1) {
                String path2 = _file_.toString() + "\\" + f;
                f = path2;
                file = new File(f);
            }

            if (file.isDirectory()) {

                File[] files = file.listFiles();
                System.out.println(files.length);
                if (files.length == 0) {
                    file.delete();
                }
            }
        }
    }

    public void remove(File file) {

        String f = file.toString();
        String st2[] = f.split("\\\\");
        if (st2.length == 1) {
            String path2 = _file_.toString() + "\\" + f;
            f = path2;
            file = new File(f);
        }

        if (file.isDirectory()) {

            File[] files = file.listFiles();

            for (File numOfFiles : files) {
                remove(numOfFiles);
            }
        }
        file.delete();
    }

    public void touch(String path) throws IOException {

        String st2[] = path.split("\\\\");
        File f = new File(path);
        System.out.println(path);
        if (!f.exists()) {
            path = _file_.toString() + "\\" + path;

        }

        System.out.println(path);
        File file = new File(path);
        file.createNewFile();
    }

    public void echo(String[] ags) {

        String arg = " ";
        for (int i = 0; i < numoflength - 1; i++) {
            System.out.print(ags[i] + " ");

        }
        System.out.println(" ");
    }

    public void ls() {
        String[] list_content = _file_.list();
        if (list_content != null) {
            String[] current = list_content;

            for (int i = 0; i < list_content.length; i++) {
                String sorting_alphaptically = current[i];
                System.out.println(sorting_alphaptically);
            }
        }
    }

    public void ls_r() {
        String[] list_content = _file_.list();
        if (list_content != null) {
            String[] current = list_content;

            LinkedList<String> q = new LinkedList();

            for (int i = 0; i < list_content.length; i++) {
                String sorting_alphaptically = current[i];
                q.addFirst(sorting_alphaptically);
            }

            for (int i = 0; i < list_content.length; i++) {
                System.out.println(q.getFirst());
                q.removeFirst();
            }
        }
    }

    public void mkdir(String files[]) {

        for (int i = 0; i < files.length; i++) {
            if (files[i] == null) {
                break;
            }
            String st2[] = files[i].split("\\\\");
            File f = new File(files[i]);
            System.out.println(files[i]);
            if (!f.exists()) {
                files[i] = _file_.toString() + "\\" + files[i];
                f = new File(files[i]);
                f.mkdir();
            }

        }

    }

    public String chooseCommandAction(String Word) throws Exception {
        if (Word.equals("rmdir")) {
            rmdir(args[0]);
        } else if (Word.equals("mkdir") && numoflength > 0) {
            mkdir(args);
        } else if (Word.equals("ls") && numoflength == 2 && args[0].equals("-r")) {
            ls_r();
        } else if (Word.equals("ls") && numoflength == 1) {
            ls();
        } else if (Word.equals("echo")) {
            echo(args);
        } else if (Word.equals("touch") && numoflength == 2) {
            touch(args[0]);

        } else if (Word.equals("cd") && args[0] == null) {
            cd();

        } else if (Word.equals("cd") && numoflength == 2) {
            cd(args[0]);

        } else if (Word.equals("pwd")) {

            pwd();
        } else if (Word.equals("cp") && !args[0].equals("-r")) {
            //if the input is not tow argment then will not working
            if (numoflength == 3) //this for check if the input is 2 word or no 
            {
                copybyte(args[0], args[1]);
            } else {
                System.out.println("Error: you sould enter tow argment");
            }
        } else if (Word.equals("cp") && args[0].equals("-r")) {

            if (numoflength == 4) //this for check if the input is 2 word or no 
            {
                copyfolder(args[1], args[2]);
            } else {
                System.out.println("Error: you sould enter tow argment");
            }
        } else if (Word.equals("cat")) {
            if (numoflength == 2) {
                cat(args[0]);
            } else if (numoflength == 3) {
                cat(args[0], args[1]);
            } else {
                System.out.println("Error: you sould enter tow argment");
            }
        } else if (Word.equals("rm")) {
            if (numoflength == 2) {
                File file = new File(args[0]);
                remove(file);
            } else {
                System.out.println("Error: you sould enter tow argment");
            }
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        String command = "";
        Terminal t1 = new Terminal();
        Parser p1 = new Parser();
        do {
            command = "";
            Scanner input = new Scanner(System.in);
            command = input.nextLine();
            p1.parse(command);
            t1.chooseCommandAction(commandName);
            if (commandName.equals("exit")) {
                break;
            }
        } while (true);
    }
}
