import java.io.*;
import java.net.*;
import java.util.logging.SocketHandler;


// class : PizzaServer 
// public static void main(String args[]) throws IOException ...
// Ŭ���̾�Ʈ�� ������ ����ؼ� ��ٸ��� ���ӽ� �̸� PizzaHandler�� �����ڿ� �Ѱ��ְ�, 
// PizzaHandler�� ��ü�� ����.

public class GServer {
	
//  method : main() 
//  ������ �°� ���Դ��� ��������(������ ����� ��Ʈ ��ȣ�� �˻�), 
//  �� ���� ServerSocket��ü�� �����ϰ� ������ �����鼭, ServerSocket�� accept() 
//  �޼ҵ带 ���� Ŭ���̾�Ʈ�κ����� �������. 
//  �� ���ῡ ���Ͽ� PizzaHandler Ŭ������ �ν��Ͻ��� ���� ����µ�, 
//  ���⿡ accept()�� ��� �� �� ������ �������� �Ű������� ��. 
//  �ڵ鷯 ��ü�� ���� �Ŀ��� start() �޼ҵ�� �̰��� ���۽�Ű��, 
//  �̶� �־��� ������ ó���� �� �ִ� �����尡 ���� ���ư��� ��.  
//  �׸��� �� ����, ���� ������ ����Ͽ� ������ ���鼭 ���ο� ������ ��ٸ��� ��.

	public static void main(String args[]) throws IOException {
		int port =9000;
		// �������� ����.
		ServerSocket server = new ServerSocket(port);
		GDBHandler dbhandler = new GDBHandler();
		
		System.out.println("Gesitoring Server Started..!!!");
		
		/* DB �ڵ鷯 �ʱ�ȭ */
		try {
			dbhandler.init();
		} catch (Exception e) {
			System.out.println(e.toString());
			//e.printStackTrace();
		}
	
		
		
		
		
		// �ټ��� Ŭ���̾�Ʈ�� ������ �޾Ƶ帮�� ���ؼ� ���ѹݺ� ����.
		while (true) {
			// Ŭ���̾�Ʈ�� ������ ���.
			// Ŭ���̾�Ʈ�� �����ϸ� Socket�� ��ü client�� ����.
		Socket client = server.accept();
		// ������ Ŭ���̾�Ʈ�� ������ ���.
		System.out.println("Accepted from: " + client.getInetAddress());
		// Ŭ���̾�Ʈ socket��ü�� ���ڷ� PizzaHandler�� ��ü�� ����.
		GSocketHandler sockethandler = new GSocketHandler(client);
		// handler�� init() �޼ҵ带 ȣ��.
		sockethandler.init();
		
		}
	}
}
