package controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

@WebServlet(name = "CallBackServlet", urlPatterns = {"/CallBackServlet"})

public class SaveController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = null;
        JSONObject jsonObj=null;
        System.out.println("===saveeditedfile------------") ;
        try {
            writer = response.getWriter();
            Scanner scanner = new Scanner(request.getInputStream()).useDelimiter("\\A");
            String body = scanner.hasNext() ? scanner.next() : "";
            jsonObj = (JSONObject) new JSONParser().parse(body);
            System.out.println("===saveeditedfile:" + jsonObj.get("status")) ;
	            /*
	                0 - no document with the key identifier could be found,
	                1 - document is being edited,
	                2 - document is ready for saving,
	                3 - document saving error has occurred,
	                4 - document is closed with no changes,
	                6 - document is being edited, but the current document state is saved,
	                7 - error has occurred while force saving the document.
	             * */
            if ((long) jsonObj.get("status") == 2) {
                /*
                 * 当我们关闭编辑窗口后，十秒钟左右onlyoffice会将它存储的我们的编辑后的文件，，此时status = 2，通过request发给我们，我们需要做的就是接收到文件然后回写该文件。
                 * */
                /*
                 * 定义要与文档存储服务保存的编辑文档的链接。当状态值仅等于2或3时，存在链路。
                 * */
                String downloadUri = (String) jsonObj.get("url");
                System.out.println("====文档编辑完成，现在开始保存编辑后的文档，其下载地址为:" + downloadUri);
                //解析得出文件名
                //String fileName = downloadUri.substring(downloadUri.lastIndexOf('/')+1);
                String fileName = request.getParameter("fileName");
                System.out.println("====下载的文件名:" + fileName);

                URL url = new URL(downloadUri);
                java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                //更换为实际的路径F:\DataOfHongQuanzheng\java\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Java Example\\app_data\192.168.56.1\
                //File savedFile = new File("F:\\DataOfHongQuanzheng\\onlyoffice_data\\app_data\\"+fileName);
                File savedFile = new File("F:\\tomcat\\apache-tomcat-8.5.51\\webapps\\modify-app-data\\"+fileName);
                try (FileOutputStream out = new FileOutputStream(savedFile)) {
                    int read;
                    final byte[] bytes = new byte[1024];
                    while ((read = stream.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    out.flush();
                }
                connection.disconnect();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
         * status = 1，我们给onlyoffice的服务返回{"error":"0"}的信息，这样onlyoffice会认为回调接口是没问题的，这样就可以在线编辑文档了，否则的话会弹出窗口说明
         * */
        if((long) jsonObj.get("status") == 6) {
            System.out.println("====保存失败:");
            writer.write("{\"error\":1}");
        }else {
            writer.write("{\"error\":0}");
        }


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
