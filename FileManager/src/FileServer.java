import java.io.*;
import java.net.*;
import java.sql.*;

public class FileServer {

	ServerSocket s = null;
	Socket socket = null;
	databaseConnect db;
	String str;

	public FileServer(int port) throws Exception {
		s = new ServerSocket(port);

		while (true) {
			try {
				socket = s.accept();
				System.out.println("connection success");
				FtpThread th = new FtpThread(socket);
				th.start();
				db = new databaseConnect();
				new Comm(socket, db).start();
			} catch (IOException e) {
				System.out.println("Error" + e.toString());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new FileServer(8888);
	}
}

class Comm extends Thread {
	private Socket socket;
	private databaseConnect db;

	public Comm(Socket socket, databaseConnect db) {
		this.socket = socket;
		this.db = db;
	}

	public void run() {
		OutputStream out = null;
		String receiveMsg = null;
		
		try {
			while (true) {
				receiveMsg = receive();
				if(receiveMsg.equals(null))
					continue;
				String[] buf = receiveMsg.split(",");
				/* id check */
				
				System.out.println("buf[0] :" + buf[0]);
				
				if (buf[0].equals("ID")) {
					idCheck(buf[1]);
				}
				if (buf[0].equals("Repo")) {
					repoCheck(buf[1]);
				}
				if (buf[0].equals("Join")) {
					join_in(buf[1], buf[2], buf[3], buf[4]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String receive() {
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

	public void send(String str) {
		try {
			OutputStream message = socket.getOutputStream();
			String mes = str;
			message.write(mes.getBytes());
			message.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void idCheck(String id) {
		String str;
		str = db.idCheck(id);
		if (str.equals("true")) // id 사용 가능
			send("ok");
		else // id 사용 불가
			send("no");

	}

	public void repoCheck(String repo) {
		String str;
		str = db.repoCheck(repo);
		if (str.equals("true")) // repository 사용 가능
			send("ok");
		else // repository 중복
			send("no");
	}

	public void join_in(String id, String name, String pwd, String repository) {
		String str;
		str = db.join_in(id, name, pwd, repository);
		if (str.equals("true")) // insert 성공
			send("ok");
		else
			send("no");
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
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void run() {

		String command = "";

		while (true) {
			try {
				command = r.readUTF();
			} catch (IOException e) {
			}

			try {
				if (command.equals("DOWNLOAD")) { // 파일 다운로드
					sendfile(s);
				} else if (command.equals("UPLOAD")) { // 파일 업로드
					receivefile(s);
				} else if (command.equals("DISS")) { // 접속해제
					s.close();
					r.close();
					w.close();
					System.out.println("연결해제");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} // end while

	}

	// 서버 -> 클라이언트 파일 전송 부분
	public void sendfile(Socket s) {
		try {
			String filename = r.readUTF(); // 수정

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			FileReader freader = new FileReader(filename);

			int readChar;

			while ((readChar = freader.read()) != -1) {
				out.write(readChar);
			}
			out.write(-1); // 파일 끝 표시
			freader.close();
			out.close();
			System.out.println("전송끝");
		} catch (Exception e) {
		}
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

		while ((readChar = br.read()) != -1) {
			fwriter.write(readChar);
		}
		fwriter.close();
		System.out.println("다운끝");

		br.close();
		bw.close();
	}

}

class databaseConnect {
	private Connection con = null;
	ResultSet rs = null;
	String filename = null;
	Statement st;

	public databaseConnect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filemanager", "root", "1q2w3e4r");
			st = con.createStatement();
			System.out.println("database Connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String idCheck(String id) {
		String sql = "select * from user where ID = ?";
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("id 존재");
				return "false";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "true";
	}

	public String repoCheck(String repo) {
		String sql = "select * from user where REPOSITORY = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(4, repo);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("repository 존재");
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "true";
	}

	public String join_in(String id, String name, String pwd, String repository) {
		PreparedStatement ps = null;
		String sql = "insert into user values(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, pwd);
			ps.setString(4, repository);
			int n = ps.executeUpdate();
			if (n > 0) {
				System.out.println("Good");
				return "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "false";
	}
}
