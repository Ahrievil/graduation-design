package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import po.OaFunctions;
import po.OaProject;
import service.ProService;
import util.BaseServlet;
import util.PageUtil;

public class ProServlet extends BaseServlet{

	private ProService service = new ProService();
	public String ProjectPanel(HttpServletRequest request,HttpServletResponse response){
		String curPage = request.getParameter("curPage");
		System.out.println("curPage======>" + curPage);
		if (curPage == null || curPage == "") {
			curPage = "1";
		}
		PageUtil util = service.queryByPage(Integer.parseInt(curPage));
		request.setAttribute("util", util);
		request.setAttribute("curPage", curPage);
		Long l = System.currentTimeMillis();
		request.getSession().setAttribute("check", l+"");
		return "/files/listxiangmuxinxi.jsp";
	}
	public String addPro(HttpServletRequest request,HttpServletResponse response){
		String   prName=request.getParameter("proname");
		String   prClient=request.getParameter("clientno");
		String   prMoney=request.getParameter("promoney");
		String   prNum=request.getParameter("pronum");
		String   prMgr=request.getParameter("proManager");
		String   prStatus=request.getParameter("proidcomplete");
		String   prCost=request.getParameter("prozmoney");
		String   prStartTime=request.getParameter("probeginDate");
		String   prEndTime=request.getParameter("proendDate");
		String   prLevel=request.getParameter("promission");
		String   prOther=request.getParameter("probeizhu");
		OaProject pro = new OaProject();
		pro.setPrName(prName);
		pro.setPrClient(prClient);
		pro.setPrMoney(Float.parseFloat(prMoney));
		pro.setPrNum(Integer.parseInt(prNum));
		pro.setPrMgr(prMgr);
		pro.setPrStatus(prStatus);
		pro.setPrCost(Float.parseFloat(prCost));
		pro.setPrStartTime(prStartTime);
		pro.setPrEndTime(prEndTime);
		pro.setPrLevel(prLevel);
		pro.setPrOther(prOther);
		System.out.println(pro);
		int k = service.insertPro(pro);
		request.setAttribute("result", "�ɹ����"+k+"����¼��");
		return ProjectPanel(request, response);
	}
	public String deletePro(HttpServletRequest request,HttpServletResponse response){
		String [] array=request.getParameterValues("hello");
		String [] array1=request.getParameterValues("world");
		int k = 0;
		for(int i=0;i<array.length;i++){
			if(array1[i].equals("yes")){
				k+=service.deleteProById(Integer.parseInt(array[i]));
			}
		}
		request.setAttribute("result", "�ɹ�ɾ��"+k+"����¼��");
		return ProjectPanel(request, response);
	}
	public String editProForSee(HttpServletRequest request,HttpServletResponse response){
		String prid = request.getParameter("prid");
		OaProject pro = service.load(Integer.parseInt(prid));
		request.setAttribute("pro", pro);
		return "/files/editxiangmu.jsp";
	}
	public String editPro(HttpServletRequest request,HttpServletResponse response){
		String   prid=request.getParameter("prono");
		String   prName=request.getParameter("proname");
		String   prClient=request.getParameter("clientno");
		String   prMoney=request.getParameter("promoney");
		String   prNum=request.getParameter("pronum");
		String   prMgr=request.getParameter("proManager");
		String   prStatus=request.getParameter("proidcomplete");
		String   prCost=request.getParameter("prozmoney");
		String   prStart=request.getParameter("probeginDate");
		String   prEnd=request.getParameter("procompeteDate");
		String   prLevel=request.getParameter("promission");
		String   prOther=request.getParameter("probeizhu");
		OaProject pro = new OaProject();
		pro.setPrid(Integer.parseInt(prid));
		pro.setPrName(prName);
		pro.setPrClient(prClient);
		pro.setPrMoney(Float.parseFloat(prMoney));
		pro.setPrNum(Integer.parseInt(prNum));
		pro.setPrMgr(prMgr);
		pro.setPrStatus(prStatus);
		pro.setPrCost(Float.parseFloat(prCost));
		pro.setPrStart(prStart);
		pro.setPrEnd(prEnd);
		pro.setPrLevel(prLevel);
		pro.setPrOther(prOther);
		System.out.println(pro);
		int k = service.updateProById(pro);
		request.setAttribute("result", "���������"+k+"������");
		return ProjectPanel(request, response);
	}
	public String loadOnePro(HttpServletRequest request,HttpServletResponse response){
		String prid = request.getParameter("prid");
		OaProject pro = service.load(Integer.parseInt(prid));
		request.setAttribute("pro", pro);
		return "/files/listxiangmumingxi.jsp";
	}
	public void loadAllName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<String> list = service.loadAllName();
		JSONArray array1 = JSONArray.fromObject(list);
		System.out.println(array1.toString());
		out.println(array1.toString());
		out.flush();
		out.close();
	}
}
