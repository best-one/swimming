package com.chen.jin.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//import sun.tools.jar.CommandLine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class CommandUtils {

    public static void main(String[] args) throws IOException {
//        a();
//        execWindowCmd();
        xx();

      //  Runtime.getRuntime().exec("");
//        Process p = Runtime.getRuntime().exec(new String[]{"cmd /c cd /d D:\\SDK\\AAAAA\\jowo\\mqtt","git pull"});
//        Process p = Runtime.getRuntime().exec(new String[]{"cmd /c dir"});
//        try(InputStream inputStream = p.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
//            BufferedReader bb  = new BufferedReader(inputStreamReader);){
//            String line = bb.readLine();
//            while (StringUtils.isNotEmpty( line)){
//                log.info("line::{}",line);
//                line = bb.readLine();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }


      //  processBuilder.command("")

    }

    public static void xx()throws IOException {
        ProcessBuilder pb = new ProcessBuilder();// 命令
        Map<String, String> env = pb.environment();// 独立环境变量
        System.out.println(env);// 打印环境变量
        env.put("MY_NAME", "KING");// 添加环境变量key-value
        pb.redirectErrorStream(true);// 重定向错误输出流到正常输出流

        File file = new File("D:\\SDK\\AAAAA\\jowo");
        String[] dirs  = file.list();

        for(String dir: dirs){
            File now = new File (file,dir);
            //String fileUrl = now.getAbsolutePath();
            try {
                pb.directory(now);// 设置目录test1
                pb.command("cmd", "/c", "git","pull");// 执行命令
                Process process1;
                process1 = pb.start();// 启动进程
                BufferedReader br1;
                br1 = new BufferedReader(new InputStreamReader(process1.getInputStream(), "gbk"));
                String line1 = null;
                while ((line1 = br1.readLine()) != null) {
                    System.out.println(line1);
                }
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
        }

    }

    public static void execWindowCmd() throws IOException {

        ProcessBuilder pb = new ProcessBuilder();// 命令
        Map<String, String> env = pb.environment();// 独立环境变量
        System.out.println(env);// 打印环境变量
        env.put("MY_NAME", "KING");// 添加环境变量key-value
        pb.redirectErrorStream(true);// 重定向错误输出流到正常输出流

        try {
            pb.directory(new File("d://logs"));// 设置目录test1
            pb.command("cmd", "/c", "dir");// 执行命令
            Process process1;
            process1 = pb.start();// 启动进程
            BufferedReader br1;
            br1 = new BufferedReader(new InputStreamReader(process1.getInputStream(), "gbk"));
            String line1 = null;
            while ((line1 = br1.readLine()) != null) {
                System.out.println(line1);
            }

//            pb.directory(new File("d://test2"));// 设置目录test2
//            pb.command("cmd", "/c", "dir", ">>", "test1.log");// 执行命令,把结果输出到test1.log
//            Process process2 = pb.start();// 启动进程
//            BufferedReader br2 = new BufferedReader(new InputStreamReader(process2.getInputStream(), "gbk"));
//            String line2 = null;
//            while ((line2 = br2.readLine()) != null) {//因为结果输出到了文件,所以本处无信息返回
//                System.out.println(line2);
//            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static void a(){
       // String command ="java --version";
        List<String> commands = new ArrayList<>();
        commands.add("java");
        commands.add("--version");
//        commands.add("");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
//            ProcessBuilder svnProcessBuilder = new ProcessBuilder("java","--version");
            ProcessBuilder svnProcessBuilder = new ProcessBuilder(commands);
            String PATH = svnProcessBuilder.environment().get("PATH");
            System.out.println("PATH  - "+ PATH);

            String path = svnProcessBuilder.environment().get("Path");
            System.out.println("Path  - "+ path);

            Process procObject = svnProcessBuilder.start();

            BufferedReader cmdStreamReader = new BufferedReader(new InputStreamReader(procObject.getInputStream()));
            String cmdOutput;
            while((cmdOutput = cmdStreamReader.readLine())!= null){
                outputStream.write((cmdOutput +"\\").getBytes());
            }
            System.out.println("O / p  - "+ outputStream.toString());
        } catch(IOException e){
            e.printStackTrace();
        } catch(Throwable th){
            th.printStackTrace();
        }
    }

    public static void t(){
        File file = new File("D:\\SDK\\AAAAA\\jowo");
        String[] dirs  = file.list();
        for(String dir: dirs){
            ProcessBuilder processBuilder = new ProcessBuilder();
            File now = new File (file,dir);
            String fileUrl = now.getAbsolutePath();
            log.info("dir::{}",fileUrl);
            List<String> commands = new ArrayList<>();
            commands.add("C:\\Program Files\\Git\\git-bash.exe");
            commands.add("cd "+now);
            commands.add("git pull");
            processBuilder.command(commands);
            processBuilder.redirectErrorStream(true);
            try {
                //启动进程
                Process start = processBuilder.start();
                //获取输入流
                InputStream inputStream = start.getInputStream();
                //转成字符输入流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
                int len = -1;
                char[] c = new char[1024];
                StringBuffer outputString = new StringBuffer();
                //读取进程输入流中的内容
                while ((len = inputStreamReader.read(c)) != -1) {
                    String s = new String(c, 0, len);
                    outputString.append(s);
                    log.info(s);
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
