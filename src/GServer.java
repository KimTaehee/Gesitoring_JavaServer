import java.io.*;
import java.net.*;
import java.util.logging.SocketHandler;


// class : PizzaServer 
// public static void main(String args[]) throws IOException ...
// 클라이언트의 접속을 계속해서 기다리며 접속시 이를 PizzaHandler의 생성자에 넘겨주고, 
// PizzaHandler의 객체를 생성.

public class GServer {
	
//  method : main() 
//  인자의 맞게 들어왔느지 조사한후(서버가 실행될 포트 번호를 검사), 
//  그 다음 ServerSocket객체를 생성하고 루프에 빠지면서, ServerSocket의 accept() 
//  메소드를 통해 클라이언트로부터의 연결받음. 
//  각 연결에 대하여 PizzaHandler 클래스의 인스턴스를 새로 만드는데, 
//  여기에 accept()로 얻어 낸 새 소켓을 생성자의 매개변수로 함. 
//  핸들러 객체를 만든 후에는 start() 메소드로 이것을 동작시키며, 
//  이때 주어진 연결을 처리할 수 있는 쓰레드가 새로 돌아가게 됨.  
//  그리고 그 동안, 메인 서버는 계속하여 루프를 돌면서 새로운 연결을 기다리게 됨.

	public static void main(String args[]) throws IOException {
		int port =9000;
		// 서버소켓 생성.
		ServerSocket server = new ServerSocket(port);
		GDBHandler dbhandler = new GDBHandler();
		
		System.out.println("Gesitoring Server Started..!!!");
		
		/* DB 핸들러 초기화 */
		try {
			dbhandler.init();
		} catch (Exception e) {
			System.out.println(e.toString());
			//e.printStackTrace();
		}
	
		
		
		
		
		// 다수의 클라이언트의 접속을 받아드리기 위해서 무한반복 수행.
		while (true) {
			// 클라이언트의 접속을 대기.
			// 클라이언트가 접속하면 Socket의 객체 client를 생성.
		Socket client = server.accept();
		// 접속한 클라이언트의 정보를 출력.
		System.out.println("Accepted from: " + client.getInetAddress());
		// 클라이언트 socket객체를 인자로 PizzaHandler형 객체를 생성.
		GSocketHandler sockethandler = new GSocketHandler(client);
		// handler의 init() 메소드를 호출.
		sockethandler.init();
		
		}
	}
}
