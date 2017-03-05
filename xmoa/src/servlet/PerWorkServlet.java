package servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import po.OaUser;
import service.PerwService;
import util.BaseServlet;
import util.PageUtil;

public class PerWorkServlet extends BaseServlet{
	
	private PerwService service = new PerwService();
	
	public String queryAllForDo(HttpServletRequest request,HttpServletResponse response){
		String curPage = request.getParameter("curPage");
		System.out.println("curPage======>" + curPage);
		if (curPage == null || curPage == "") {
			curPage = "1";
		}
		OaUser user = (OaUser)request.getSession().getAttribute("currentuser");
		service.pd.person = user.getUname();
		PageUtil util = service.queryByPage(Integer.parseInt(curPage));
		request.setAttribute("util", util);
		request.setAttribute("curPage", curPage);
		return "/files/listgerenrenwu.jsp";
	}
	public String doHomework(HttpServletRequest request,HttpServletResponse response){
		String plid = request.getParameter("plid");
		String result = service.doWork(Integer.parseInt(plid));
		request.setAttribute("result", result);
		return queryAllForDo(request, response);
	}
}
