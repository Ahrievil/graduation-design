package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.OaFile;
import po.OaUser;
import service.FileService;
import util.BaseServlet;
import util.PageUtil;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class FileServlet extends BaseServlet{

	private FileService service = new FileService();
	private ServletConfig config = getServletConfig();
	
	public String queryAll(HttpServletRequest request,HttpServletResponse response){
		String curPage = request.getParameter("curPage");
		System.out.println("curPage======>" + curPage);
		if (curPage == null || curPage == "") {
			curPage = "1";
		}
		PageUtil util = service.queryByPage(Integer.parseInt(curPage));
		request.setAttribute("util", util);
		request.setAttribute("curPage", curPage);
		return "/files/listshangchuan.jsp";
	}
	public String upload(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");  
		System.out.println("-------------��������");
		SmartUpload su=new SmartUpload();
		  //�ϴ���ʹ��
		ServletConfig config = super.getServletConfig();

//		 ServletConfig config = (ServletConfig)request.getSession().getServletContext().getAttribute("servletConfig");
		PrintWriter out=response.getWriter();
		     su.initialize(config,request,response);
		   //׼���ϴ�
		    try {
				su.upload();
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		   //��ȡҳ���������Ŀ
		    String prName=su.getRequest().getParameter("pro");
		   //�����ϴ����ļ�
		    try {
				su.save("/upload");
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		   // String ext=su.getFiles().getFile(0).getFieldName();
		    String filename=su.getFiles().getFile(0).getFileName();
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
		   return "/files/simple1.jsp?flag=1";
	}
}
