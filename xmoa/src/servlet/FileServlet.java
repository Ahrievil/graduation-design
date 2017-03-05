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
		System.out.println("-------------进来了吗");
		SmartUpload su=new SmartUpload();
		  //上传初使化
		ServletConfig config = super.getServletConfig();

//		 ServletConfig config = (ServletConfig)request.getSession().getServletContext().getAttribute("servletConfig");
		PrintWriter out=response.getWriter();
		     su.initialize(config,request,response);
		   //准备上传
		    try {
				su.upload();
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		   //获取页面的所属项目
		    String prName=su.getRequest().getParameter("pro");
		   //保存上传的文件
		    try {
				su.save("/upload");
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		   // String ext=su.getFiles().getFile(0).getFieldName();
		    String filename=su.getFiles().getFile(0).getFileName();
		    //取得不代后缀的文件
		    String suffix=filename.substring(0,filename.lastIndexOf('.'));
		    //取得后缀名
		    String ext=su.getFiles().getFile(0).getFileExt();
		  //取得文件大小
		   int  fileSize=su.getFiles().getFile(0).getSize();
		   if(fileSize>1024){
			   fileSize=fileSize/1024;
		   }else{
			   
		   }
		   //获得上传人
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
