import java.io.*;
import java.net.*;

class clientConnect {
	Socket socket;

	public clientConnect() {
		try {
			socket = new Socket("127.0.0.1", 8080);
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
			int len = inData.read(message1);
			byte[] tmp = new byte[len];
			System.arraycopy(message1, 0, tmp, 0, len);
			data1 = new String(tmp);
			
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