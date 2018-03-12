package org.hit.simulate;
import java.io.InputStream;  
import java.io.OutputStreamWriter;  
import java.io.PrintWriter;  
import java.net.Socket;  
  
public class SendPackage {  
    public static void main(String[] args)  throws Exception{  
  
        //需要链接的ip地址  
        Socket s=new Socket("www.baidu.com",80);  
  
  
        //模拟浏览器给网站发送数据  
        PrintWriter out=new PrintWriter(s.getOutputStream(),true);  
        out.println("GET /500.html HTTP/1.1");  
        out.println("Accept: */*");  
        out.println("Host: www.csdn.com");  
        out.println("Connection: close");  
        out.println();  
        out.println();  
  
        //接受返回的数据  
        InputStream in = s.getInputStream();  
        byte[] buf = new byte[1024];  
        int len = in.read(buf);  
  
        String str =new String(buf,0,len);  
        System.out.println(str);  
  
        s.close();  
    }  
}  