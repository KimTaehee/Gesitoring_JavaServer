import java.io.*;
import java.net.*;
import java.util.*;

/*
 * class : PizzaHandler 
 * public class PizzaHandler는 서버로 들어온 클라이언트와의 연결을 
 * 개별적으로 처리하는 일을 맡은 Thread 클래스.  
 * 클라이언트로부터의 메시지를 받는 역할
 */
public class GSocketHandler implements Runnable {
	protected Socket socket;
	protected DataInputStream dataIn;
	protected DataOutputStream dataOut;
	protected Thread listener;

	public GSocketHandler(Socket socket) {
		this.socket = socket;
	}
	public synchronized void init() {
		if (listener == null) {
			try {
				dataIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));  // 소켓 입력스트림
				dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())); // 소켓 출력 스트림

				listener = new Thread(this); 	// listener thread를 생성하고 시작.
				listener.start();
			} catch (IOException ignored) {
			}
		}
	}

	public synchronized void stop() {
		if (listener != null) {
			try {
				if (listener != Thread.currentThread())
					listener.interrupt(); //Thread에 인터럽트
				listener = null;
				dataOut.close(); //dataOut 닫음
			}
			 catch (IOException ignored) {
			}
		}
	}

	public void run() {
		
		try {
			while (!Thread.interrupted()) { //thread가 작동중이면
				String msg = dataIn.readUTF();
				try {
					System.out.println(msg);
					
//			StringTokenizer stk = new StringTokenizer(message, "|");
//			String name = stk.nextToken();
		} catch (NoSuchElementException e) {
					stop();
			}
		}
	} 
	catch (EOFException ignored) {
			System.out.println( "접속을 종료하셨습니다.");
	}
	catch (IOException ie) {
			if (listener == Thread.currentThread())
				ie.printStackTrace();
	} finally {
			stop();
		}
	}
}
