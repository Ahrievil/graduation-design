package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import po.OaFunctions;
import po.OaUser;
import service.FunService;
import service.UserService;
import util.BaseServlet;
import dao.UserDao;

public class UserServlet extends BaseServlet {

	UserService service = new UserService();

	public String login(HttpServletRequest request, HttpServletResponse reponse) {
		String username = request.getParameter("textfield");
		String pwd = request.getParameter("textfield2");
		String check = request.getParameter("textfield3");
		String check_code = (String) request.getSession().getAttribute("check_code");
		String result = "登录失败";
		OaUser user = new UserDao().load(username);
		if (user != null) {
			if (user.getUpwd().equals(pwd)) {
				result = "登录成功";
			}
		}
		request.setAttribute("result", result);
		if ("登录成功".equals(result)) {
			FunService fs = new FunService();
			List<OaFunctions> list = fs.queryFunByRoname(user.getUrole());
			System.out.println(list.size());
			request.getSession().setAttribute("funlist", list);
			request.getSession().setAttribute("currentuser", user);
			return "/index.jsp";
		} else {
			return "login.jsp";
		}
	}

	public void exit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		OaUser user = (OaUser) request.getSession().getAttribute("currentuser");
		if (user != null) {
			request.getSession().removeAttribute("currentuser");
		}
		response.sendRedirect("/xmoa/login.jsp");

	}
	
	public void loadAllMgr(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<String> list = service.loadAllMgr();
		JSONArray array = JSONArray.fromObject(list);
		System.out.println(array.toString());
		out.println(array.toString());
		out.flush();
		out.close();
	}

	public String savePersonInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String usid = request.getParameter("pno"); // 人员编号，也作为登入的用户名
		String uname = request.getParameter("pname"); // 人员真实姓名
		String upost = request.getParameter("zwno");// 职位
		String ustatus = request.getParameter("plei");// 员工类型 0实习 1在职
		String uidNum = request.getParameter("pid");// 身份证号；
		String usex = request.getParameter("psex");// 性别；
		String ubirthdate = request.getParameter("pbirthday");// 出生时间
		String uadd = request.getParameter("paddr");// 居住地址
		String utell = request.getParameter("ptelephone");// 联系电话
		String uemail = request.getParameter("pemail");// email
		String upwd = request.getParameter("ppassword");// 密码,用于用户登入
		String ubankName = request.getParameter("pbankName"); // 银行名称
		String ubankAcc = request.getParameter("pbankuser");// 银行账号
		String upay = request.getParameter("pfsalary");// 基本工资
		String ureward = request.getParameter("pgsalary");// 奖金
		String upaje = request.getParameter("posalary");// 其他补助
		String uhiredate = request.getParameter("pbydate");// 入职时间
		String uother = request.getParameter("pdescription");// 描述
		String urole = request.getParameter("jueno");// 权限角色
		OaUser user = new OaUser();
		user.setUsid(Integer.parseInt(usid));
		user.setUname(uname);
		user.setUpost(upost);
		user.setUstatus(ustatus);
		user.setUidNum(uidNum);
		user.setUsex(usex);
		user.setUbirthdate(ubirthdate);
		user.setUadd(uadd);
		user.setUtell(utell);
		user.setUemail(uemail);
		user.setUpwd(upwd);
		user.setUbankName(ubankName);
		user.setUbankAcc(ubankAcc);
		user.setUpay(Float.parseFloat(upay));
		user.setUreward(Float.parseFloat(ureward));
		user.setUpaje(Float.parseFloat(upaje));
		user.setUhiredate(uhiredate);
		user.setUother(uother);
		user.setUrole(urole);
		service.upload(user);
		request.getSession().removeAttribute("currentuser");
		request.getSession().setAttribute("currentuser", user);
		return "/files/succ.jsp";
	}
	public String queryUserInfo(HttpServletRequest request,HttpServletResponse response){
		List<OaUser> list = service.loadAll();
		System.out.println(list.size());
		request.setAttribute("allUserInfo", list);
		return "/files/listyuangong.jsp";
	}
	public String showUserInfo(HttpServletRequest request,HttpServletResponse response){
		String usid = request.getParameter("usid");
		OaUser user = service.loadWithId(Integer.parseInt(usid));
		request.setAttribute("userInfo", user);
		return "/files/updateyuangong.jsp";
	}
	public String updateAllInfock(HttpServletRequest request,HttpServletResponse response){
		String usid = request.getParameter("pno");
		String upwd = request.getParameter("ppassword");
		String uname = request.getParameter("pname");
		String usex = request.getParameter("psex");
		String uage = request.getParameter("page");
		String upost = request.getParameter("upost");
		String ubirthdate = request.getParameter("pbirthday");
		String uidNum = request.getParameter("pid");
		String uemail = request.getParameter("pemail");
		String utell = request.getParameter("ptelephone");
		String uadd = request.getParameter("paddr");
		String ustatus = request.getParameter("plei");
		String ubankName = request.getParameter("pbankName");
		String urole = request.getParameter("urole");
		System.out.println(urole+"--"+upost);
		String ubankAcc = request.getParameter("pbankuser");
		String upay = request.getParameter("pfsalary");
		String ureward = request.getParameter("pgsalary");
		String upaje = request.getParameter("posalary");
		String uhiredate = request.getParameter("pbydate");
		String uother = request.getParameter("pdescription");
		OaUser user = new OaUser();
		user.setUsid(Integer.parseInt(usid));
		user.setUpwd(upwd);
		user.setUname(uname);
		user.setUsex(usex);
		user.setUage(Integer.parseInt(uage));
		user.setUpost(upost);
		user.setUbirthdate(ubirthdate);
		user.setUidNum(uidNum);
		user.setUemail(uemail);
		user.setUtell(utell);
		user.setUadd(uadd);
		user.setUstatus(ustatus);
		user.setUbankName(ubankName);
		user.setUbankAcc(ubankAcc);
		user.setUrole(urole);
		user.setUpay(Float.parseFloat(upay));
		user.setUreward(Float.parseFloat(ureward));
		user.setUpaje(Float.parseFloat(upaje));
		user.setUhiredate(uhiredate);
		user.setUother(uother);
		service.upload(user);
		return queryUserInfo(request,response);
	}
	public String deleteUserInfo(HttpServletRequest request,HttpServletResponse response){
		String [] array=request.getParameterValues("hello");
		String [] array1=request.getParameterValues("world");
		int lizhirenshu = 0;
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]+"====="+array1[i]);
			if(array1[i].equals("yes")){
				System.out.println("id========"+array[i]);
				int usid = Integer.parseInt(array[i]);
				int k = service.deleteOneUser(usid);
				if(k==1){
					lizhirenshu++;
				}
			}
		}
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(2);
		session.setAttribute("lizhirenshu", lizhirenshu);
		return queryUserInfo(request, response);
	}
	//json使用的queryall
	public void queryUserInfoauto(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		List<OaUser> list = service.loadAll();
		JSONArray array1 = JSONArray.fromObject(list);
		System.out.println(array1.toString());
		out.println(array1.toString());
		out.flush();
		out.close();
	}
	public void loadAllWorker(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		List<String> list = service.loadAllWorker();
		JSONArray array1 = JSONArray.fromObject(list);
		System.out.println(array1.toString());
		out.println(array1.toString());
		out.flush();
		out.close();
	}
}
