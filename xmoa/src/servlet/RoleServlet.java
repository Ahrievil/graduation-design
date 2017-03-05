package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import po.OaRole;
import service.RoleService;
import util.BaseServlet;

public class RoleServlet extends BaseServlet{

	private RoleService service = new RoleService();
	public void queryAllRoleName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<String> list = service.queryAllName();
		JSONArray array1 = JSONArray.fromObject(list);
		System.out.println(array1.toString());
		out.println(array1.toString());
		out.flush();
		out.close();
	}
	public String deleteRole(HttpServletRequest request,HttpServletResponse response){
		List<OaRole> list = service.loadAll();
		request.setAttribute("list", list);
		return "/files/scjs.jsp";
	}
	public String deleteSecRole(HttpServletRequest request,HttpServletResponse response) throws IOException{
		FuncServlet ser = new FuncServlet();
		String[] roid = request.getParameterValues("roid");
		for(int i = 0;i < roid.length;i++){
			if(service.countUser(Integer.parseInt(roid[i])) == 0){//�ж��Ƿ����û���ʹ�øý�ɫ
				service.deleteFunByRoid(Integer.parseInt(roid[i]));//�˴��м��role_fun�е�����
			}
			
		}
		String unable = " ";
		int k = 0;
		for(int i = 0;i < roid.length;i++){
			int flag = service.deleteById(Integer.parseInt(roid[i]));
			if(flag!=0){
				k++;
			}else{
				String name = service.queryNameById(Integer.parseInt(roid[i]));
				unable = unable+" "+name;
			}
		}
		request.setAttribute("result", "�ɹ�����"+k+"����ɫ��Ϣ������"+unable+"���ɾ��ʧ�ܣ��������û�����ʹ�øý�ɫ�����ʵû���û�ʹ�øý�ɫ��ɾ����");
		return ser.queryAllFunName(request, response);
	}
}
