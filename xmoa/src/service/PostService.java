package service;

import java.util.ArrayList;
import java.util.List;

import po.OaPost;
import util.PageUtil;
import dao.PostDao;

public class PostService {

	private PageUtil pu = new PageUtil();
	private PostDao pd = new PostDao();
	public List<String> queryAllName(){
		List<OaPost> post = pd.loadAll();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < post.size();i++){
			list.add(post.get(i).getName());
		}
		return list;
	}
//	public List<OaPost> loadAll(){
//		return pd.loadAll();
//	}
	public PageUtil queryByPage(int page){
		return pu.queryByPage(page, pd);
	}
	public void deleteById(int poid){
		int k = pd.deleteById(poid);
		System.out.println("ɾ��һ��post+"+k);
	}
	public int insertOne(int poid,String name,String other){
		int k = pd.insertOne(poid, name, other);
		System.out.println("����post���ݸ���+"+k);
		return k;
	}
	public OaPost load(int poid){
		return pd.load(poid);
	}
	public String updateById(int poid,String name,String other){
		int k = pd.updateById(poid, name, other);
		String result = "��������ʧ�ܣ������ְ������";
		if(k==1){
			result = "������"+k+"������";
		}
		return result;
	}
}
