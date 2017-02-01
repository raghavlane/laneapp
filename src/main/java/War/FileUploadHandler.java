package War;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadHandler extends HttpServlet {
	private static final long serialVersionUID = -4051934827750146254L;
	private static String UPLOAD_DIRECTORY;
	public static String message;
	public static String username;
	public static String password;
	public static String senderId;
	public static final String messagePath = "C:/Users/amraghax/Desktop/uploads/app.properties";
	
	public FileUploadHandler() {
		super();
	}
	
	public void init() {
		loadProperties();
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("existingMessage", message);
		request.getRequestDispatcher("/updateMessage.jsp").forward(request,
				response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String requestSource = request.getParameter("flow");
		if (null== requestSource)
			requestSource="";
		if (requestSource.contains("updateMessage")) {
			String message = request.getParameter("message");
			if(null== message|| message.equalsIgnoreCase("")){
			request.setAttribute("error", "enter valid message");
			request.getRequestDispatcher("/updateMessage.jsp").forward(request,
					response);
			}
			updateMessage(message);
			request.setAttribute("error", "Message Updated successfully");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} else {
			uploadexcelmessage(request, response);
		}
	}

	private void uploadexcelmessage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String out1 = "";
		String out2 = "";
		String requestSource = request.getHeader("referer");
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);
				List<String> namelist = new ArrayList<String>();
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						String fileName = UPLOAD_DIRECTORY + File.separator
								+ name;
						checkfileexists(fileName);
						item.write(new File(fileName));
						namelist.add(name);
					}
				}
				out1 = UPLOAD_DIRECTORY + "/" + namelist.get(0);
				out2 = UPLOAD_DIRECTORY + "/" + namelist.get(1);
			} catch (Exception ex) {
				request.setAttribute("error",
						"File Upload Failed due to " + ex);
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			}
		} else {
			request.setAttribute("error",
					"Sorry this Servlet only handles file upload request");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
		if (out1.equals("") || out2.equals("")) {
			request.setAttribute("error",
					"error: Browse and add both the files");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} else if (out1.equalsIgnoreCase(out2)) {
			request.setAttribute("error", "error: Both Files are same");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} else if ((!out1.contains(".xl")) || (!out2.contains(".xl"))) {
			request.setAttribute("error", "error: upload excel files only");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} else {
			request.setAttribute("message", "File Uploaded Successfully");
			CallObject c2 = MainClass.read(out2);
			CallObject c1 = MainClass.read(out1);
			Set<String> missedset = MainClass.comparecalls(c1, c2);
			List<String> missedList = new ArrayList<String>();
			for (String a : missedset) {
				missedList.add(a);
			}
			Set<String> dialledset = MainClass.dialledAndRecieved(c1, c2);
			List<String> dialledList = new ArrayList<String>();
			for (String a : dialledset) {
				dialledList.add(a);
			}
			
			if (requestSource.contains("sendMessage")) {
				sendMessage(message, missedList);
			}
			request.setAttribute("missedlist", missedList);
			request.setAttribute("dialledlist", dialledList);
			request.setAttribute("missedlist1", c1.getMlist());
			request.setAttribute("missedlist2", c2.getMlist());
			request.getRequestDispatcher("/result.jsp").forward(request,
					response);
		}
	}

	private void checkfileexists(String fileName) {
		File f = new File(fileName);
		if (f.exists()) {
			File f1 = new File(fileName + getCurrentTimeStampString());
			f.renameTo(f1);
		}

	}

	public static String getCurrentTimeStampString() {
		SimpleDateFormat timeStampFormatter = new SimpleDateFormat(
				"ddMMyyyy_HHmmss");
		Calendar cal = Calendar.getInstance();
		Date currentTime = new Date(cal.getTime().getTime());
		return timeStampFormatter.format(currentTime);
	}

	private static void sendMessage(String finalmessage, List<String> callList) {
		String url = "http://smshorizon.co.in/api/sendsms.php?";
		String user = "user=raghavlane&apikey=2222222&senderid=SMSIND&type=txt";
		String message ="&message=";
		String mobile = "&mobile=";
		finalmessage = finalmessage.replaceAll(" ", "%20");
		finalmessage = finalmessage.replaceAll("\n", "%0A");
		for (String number : callList) {
			hitURL(url+user+message+finalmessage+mobile+number);
		}

	}
	private static void hitURL(String Url) {
		System.out.println(Url);
		try {
			URL obj = new URL("http://www.google.co.in");
			HttpURLConnection httpURLConnection = (HttpURLConnection) obj
					.openConnection();
			//int responseCode = httpURLConnection.getResponseCode();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = bufferedReader.readLine()) != null) {
				response.append(inputLine);
			}
			bufferedReader.close();
			System.out.println(response.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void updateMessage(String message){
		try {
			FileInputStream in = new FileInputStream(messagePath);
			Properties props = new Properties();
			props.load(in);
			in.close();
			FileOutputStream out = new FileOutputStream(messagePath);
			props.setProperty("MESSAGE", message);
			props.store(out, null);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadProperties();
	}

	private static void loadProperties() {
		try {
			String isipHome = System.getProperty("ISIP_HOME");
			FileInputStream appConfigFileInputStream = new FileInputStream(
					isipHome+"app.properties");
			Properties props = new Properties();
			props.load(appConfigFileInputStream);
			message = props.getProperty("MESSAGE");
			username = props.getProperty("USERNAME");
			password = props.getProperty("PASSWORD");
			senderId = props.getProperty("SENDERID");
			UPLOAD_DIRECTORY = props.getProperty("UPLOAD_DIRECTORY");
			UPLOAD_DIRECTORY="/tmp";
		} catch (IOException e) {
			e.printStackTrace();
			UPLOAD_DIRECTORY="/tmp";
		}
	}
}
