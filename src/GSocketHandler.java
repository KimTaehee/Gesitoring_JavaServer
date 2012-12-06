import java.io.*;
import java.net.*;
import java.util.*;

/*
 * class : PizzaHandler 
 * public class PizzaHandler�� ������ ���� Ŭ���̾�Ʈ���� ������ 
 * ���������� ó���ϴ� ���� ���� Thread Ŭ����.  
 * Ŭ���̾�Ʈ�κ����� �޽����� �޴� ����
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
				dataIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));  // ���� �Է½�Ʈ��
				dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())); // ���� ��� ��Ʈ��

				listener = new Thread(this); 	// listener thread�� �����ϰ� ����.
				listener.start();
			} catch (IOException ignored) {
			}
		}
	}

	public synchronized void stop() {
		if (listener != null) {
			try {
				if (listener != Thread.currentThread())
					listener.interrupt(); //Thread�� ���ͷ�Ʈ
				listener = null;
				dataOut.close(); //dataOut ����
			}
			 catch (IOException ignored) {
			}
		}
	}

	public void run() {
		
		try {
			while (!Thread.interrupted()) { //thread�� �۵����̸�
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
			System.out.println( "������ �����ϼ̽��ϴ�.");
	}
	catch (IOException ie) {
			if (listener == Thread.currentThread())
				ie.printStackTrace();
	} finally {
			stop();
		}
	}
}
