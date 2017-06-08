package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.OaFile;
import po.OaUser;
import service.FileService;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class UploadServlet extends HttpServlet {
	FileService service = new FileService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");  
		System.out.println("-------------��������");
		SmartUpload su=new SmartUpload();
		  //�ϴ���ʹ��
		ServletConfig config = super.getServletConfig();

//		 ServletConfig config = (ServletConfig)request.getSession().getServletContext().getAttribute("servletConfig");
		     su.initialize(config,request,response);
		   //׼���ϴ�
		    try {
				su.upload();
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		   //��ȡҳ���������Ŀ
		    String prName=su.getRequest().getParameter("pro");
		    byte[] v = prName.getBytes("gbk");
		    prName = new String(v,"utf-8");
		    ServletContext application = config.getServletContext();
		    String realpath = application.getRealPath("/upload");
		    File useLessFile = new File(realpath);
		    if(!useLessFile.exists()){
		    	useLessFile.createNewFile();
		    }
		    System.out.println(realpath);
		   //�����ϴ����ļ�
		    try {
				su.save(realpath);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		   // String ext=su.getFiles().getFile(0).getFieldName();
		    String filename=su.getFiles().getFile(0).getFileName();
		    byte[] b = filename.getBytes("gbk");
		    filename = new String(b,"utf-8");
		    //ȡ�ò�����׺���ļ�
		    String suffix=filename.substring(0,filename.lastIndexOf('.'));
		    //ȡ�ú�׺��
		    String ext=su.getFiles().getFile(0).getFileExt();
		  //ȡ���ļ���С
		   int  fileSize=su.getFiles().getFile(0).getSize();
		   if(fileSize>1024){
			   fileSize=fileSize/1024;
		   }else{
			   
		   }
		   //����ϴ���
		   OaUser f=(OaUser) request.getSession().getAttribute("currentuser");
		   String uper=f.getUname();
		   byte[] c = uper.getBytes("gbk");
		   uper = new String(c,"utf-8");
		   //if(suffix.length()>20){}
		   if(suffix.length()>20){
			   suffix=suffix.substring(0,19);
		   }
		   OaFile file = new OaFile();
		   file.setFlName(suffix);
		   file.setFlType(ext);
		   file.setFluper(uper);
		   file.setPrName(prName);
		   file.setFlSize(fileSize);
		   service.insert(file);
		   System.out.println(file);
		   request.getRequestDispatcher("/files/simple1.jsp?flag=1").forward(request, response);
	}

}
