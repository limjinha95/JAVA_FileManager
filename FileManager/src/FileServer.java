import java.io.*;
import java.net.*;

public class FileServer {

	ServerSocket s = null;
	Socket s1 = null;

	public FileServer(int port) throws Exception {
		s = new ServerSocket(port);

		while(true) {
			try {
				s1 = s.accept();
				FtpThread th = new FtpThread(s1);
				th.start();
			}catch(IOException e) {
				System.out.println("Error" + e.toString());
			}
		}
	}

	public static void main (String[] args) throws Exception {
		new FileServer(7777);
	}
}

class FtpThread extends Thread {

	Socket s;
	DataInputStream r;
	DataOutputStream w;

	public FtpThread(Socket s) throws Exception {
		this.s = s;
		try {
			r = new DataInputStream(s.getInputStream());
			w = new DataOutputStream(s.getOutputStream());
		}catch(IOException e) {
			System.out.println(e);
		}
	}

	public void run() {
		
			String command = "";

			while(true) {
				try {
					command = r.readUTF();
				}catch(IOException e) {}

				try {
					if(command.equals("DOWNLOAD")) {  // 파일 다운로드  
						sendfile(s);
					}
					else if(command.equals("UPLOAD")) {  // 파일 업로드 
						receivefile(s);
					}
					else if(command.equals("DISS")) {  // 접속해제 
						s.close();
						r.close();
						w.close();
						System.out.println("연결해제");
					}
				}catch(Exception e) {
					System.out.println(e);
				}
			}  // end while
		
	}

	// 서버 -> 클라이언트 파일 전송 부분 
	public void sendfile(Socket s) {
		try {
			String filename = r.readUTF();  // 수정  

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			FileReader freader = new FileReader(filename);

			int readChar;

			while((readChar = freader.read()) != -1) {
				out.write(readChar);
			}
			out.write(-1);  // 파일 끝 표시 
			freader.close();
			out.close();
			System.out.println("전송끝");
		}catch(Exception e) {}
	}

	// 클라이언트 -> 서버 파일 전송 부분 
	public void receivefile(Socket s) throws IOException {
		int readChar;
		String filepath = "C:/";

		filepath = filepath + r.readUTF();
		System.out.println("파일명" + filepath);

		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
		FileWriter fwriter = new FileWriter(filepath);
		
		while((readChar = br.read()) != -1) {
			fwriter.write(readChar);
		}
		fwriter.close();
		System.out.println("다운끝");
		
		br.close();
		bw.close();
	}

}







