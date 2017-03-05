package dao;

import java.util.ArrayList;
import java.util.List;

import po.OaUser;
import util.jdbcTemplete;

public class UserDao{

	private jdbcTemplete jt = new jdbcTemplete();
	public OaUser load(String username){
		String sql = "select * from oa_user where uname = ?";
		List<Object> u = jt.findObject(sql, OaUser.class, username);
		OaUser user = u.size()==1?(OaUser)u.get(0) : null;
		return user;
	}
	public OaUser loadWithId(int usid){
		String sql = "select * from oa_user where usid = ?";
		List<Object> u = jt.findObject(sql, OaUser.class, usid);
		OaUser user = u.size()==1?(OaUser)u.get(0) : null;
		return user;
	}
	public int deleteonerow(int usid){
		return jt.update("delete from oa_user where usid = ?", usid);
	}
	public int upload(OaUser user){
		System.out.println(user);
		deleteonerow(user.getUsid());
		String sql = "insert into oa_user values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int k = jt.update(sql, user.getUsid(),user.getUname(),user.getUpwd(),user.getUsex(),user.getUbirthdate(),user.getUidNum(),user.getUemail(),user.getUtell(),user.getUadd(),user.getUstatus(),user.getUrole(),user.getUbankName(),user.getUbankAcc(),user.getUpay(),user.getUreward(),user.getUpaje(),user.getUhiredate(),user.getUpost(),user.getUother(),user.getUage());
		System.out.println("user���µ���������"+k);
		return k;
	}
	public List<OaUser> queryall(){
		String sql = "select us.* from oa_user us order by us.usid desc";
		List<OaUser> list = new ArrayList(jt.findObject(sql, OaUser.class));
		return list;
	}
	public List<OaUser> queryallMgr(){
		String sql = "select us.* from oa_user us where upost = '��Ŀ����' order by us.usid desc";
		List<OaUser> list = new ArrayList(jt.findObject(sql, OaUser.class));
		return list;
	}
	public List<OaUser> loadAllWorker(){
		String sql = "select us.* from oa_user us where upost != '����Ա' order by us.usid desc";
		List<OaUser> list = new ArrayList(jt.findObject(sql, OaUser.class));
		return list;
	}
}
