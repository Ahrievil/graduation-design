package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import po.OaFunctions;
import po.OaRole;
import service.FunService;
import service.RoleService;
import util.BaseServlet;

public class FuncServlet extends BaseServlet{

	private FunService service = new FunService();
	public String queryAllFunName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<OaFunctions> list = service.queryAllFunName();
		for(OaFunctions fun: list){
			System.out.println(fun);
		}
		request.setAttribute("list", list);
		//��������
		Long l = System.currentTimeMillis();
		request.getSession().setAttribute("check", l+"");
		return "/files/addquanxian.jsp";
	}
	public void queryAllFunByRoname(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String roname = request.getParameter("roname");
		List<OaFunctions> list = service.queryFunByRoname(roname);
		JSONArray array1 = JSONArray.fromObject(list);
		System.out.println(array1.toString());
		out.println(array1.toString());
		out.flush();
		out.close();
	}
	public String updateFun(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String fid[] = request.getParameterValues("fids");
		String roname = request.getParameter("mccc");
		System.out.println(roname);
		int k = 0;
		service.deleteFunByRoleName(roname);
		for(int i = 0;i < fid.length;i++){
			System.out.println(fid[i]);
			k+=service.insertFunByRoleName(roname, Integer.parseInt(fid[i]));
		}
		request.setAttribute("result", "���������"+k+"������");
		return queryAllFunName(request, response);
	}
	public String queryAllFunNameForSee(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<OaFunctions> list = service.queryAllFunName();
		for(OaFunctions fun: list){
			System.out.println(fun);
		}
		request.setAttribute("list", list);
		return "/files/tjjs.jsp";
	}
	public String insertFun(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String check = (String)request.getSession().getAttribute("check");
		String refresh = request.getParameter("refresh");
		System.out.println(check+"----"+refresh);
		int k = 0;
		if(check.equals(refresh)){
			String roname = request.getParameter("juename");
			String other = request.getParameter("juebeizhu");
			String fid[] = request.getParameterValues("fids");
			System.out.println(roname+"---"+other);
			OaRole role = new OaRole();
			role.setRoname(roname);
			role.setOther(other);
			RoleService rs = new RoleService();
			rs.insertRole(role);
			if(fid!=null){
				for(int i = 0;i < fid.length;i++){
					System.out.println("------"+fid[i]);
					k+=service.insertFunByRoleName(roname, Integer.parseInt(fid[i]));
				}
			}
		}
		request.setAttribute("result", "�ý�ɫ�ɹ�������"+k+"�����ܣ�");
		return queryAllFunName(request, response);
	}
}
