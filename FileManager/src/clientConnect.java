import java.io.*;
import java.net.*;

class clientConnect {
	Socket socket;

	public clientConnect() {
		try {
			socket = new Socket("127.0.0.1", 8888);
			System.out.println("Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getData() {
		String data1 = null;
		try {
			InputStream inData = socket.getInputStream();
			byte[] message1 = new byte[8192];
			inData.read(message1);
			data1 = new String(message1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data1;
	}

	public void sendData(String data) {
		try {
			OutputStream message = socket.getOutputStream();
			String mes = data;
			message.write(mes.getBytes());
			message.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}