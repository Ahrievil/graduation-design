package dao;

import java.util.List;

import po.OaPlan;
import util.BaseDao;

public class PerwDao extends BaseDao{

	public String person = null;
	@Override
	public List<Object> queryAllByPage(int beginIndex, int endIndex) {
		String sql = "select * from (select rownum as num,z.* from (select level,t.* from oa_plan t connect by t.pplid = prior t.plid start with pplid=0 order by level asc ) z) where plworker = ? and flag = 0 and plstatus = 'ÒÑÏÂ·¢' and num between ? and ?";
		List<Object> list = jt.findObject(sql, OaPlan.class,person,beginIndex,endIndex);
		return list;
	}

	@Override
	public int queryCount() {
		String sql = "select nvl(count(*),0) as cou from oa_plan";
		return jt.queryCount(sql);
	}
	public int doWork(OaPlan plan){
		String sql = "update oa_plan set flag = ? ,PlStartTime = to_date(?,'yyyy-MM-dd') where plid = ?";
		return jt.update(sql,plan.getFlag(),plan.getPlStartTime(),plan.getPlid());
	}
	
}
