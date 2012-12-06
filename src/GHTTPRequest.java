import java.net.*;
import java.beans.Encoder;
import java.io.*;

import sun.io.Converters;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

public class GHTTPRequest {
	public static String request(String url) throws IOException, SocketTimeoutException {
		String str = null;
		URL u = new URL(url);
		HttpURLConnection urlcon = (HttpURLConnection) u.openConnection();
		urlcon.setRequestMethod("GET");
		
		int code = urlcon.getResponseCode();
		System.out.println("getResponseCode() : " + code);
		if(code == 200) { //���������� �Ǿ����� html ���� ���
			
			System.out.println("---------------RESPONSE STRING------------------");
			
			InputStream input = urlcon.getInputStream();
			int c, cnt=0;
			while((c=input.read()) != -1) { //TODO ���ڵ� ��� UTF-8�� �ؾ� ��
				System.out.print((char)c);
				cnt++;
			}
			input.close();
			System.out.println(str);
			
			System.out.println("------------RESPONSE STRING END---------------");
			System.out.println("Length : " + cnt);
		}
		else {
			System.out.println(url + "���� ����");
		}
		
		urlcon.disconnect();
		return str;
	}
	//public static String parseText
}
