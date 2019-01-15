package com.founder.fix.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.founder.fix.dbcore.CommandType;
import com.founder.fix.dbcore.DBGetResult;
import com.founder.fix.dbcore.DBGetResultHandle;
import com.founder.fix.dbcore.DataTable;
import com.founder.fix.dbcore.Parm_Struct;

public class PurchaseTrackUtil {
	 
	public static int countPurchaseTrack(Map<String, String> filters) throws Exception{
		int counts = 0 ;
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		try{
			List<Parm_Struct> params = new ArrayList<Parm_Struct>();
			String sql = "select count(1) from CSC_PURCHASETRACK a where 1 = 1";
			
			if (filters.containsKey("applyCode") && filters.get("applyCode") != null) {
				sql += " and APPLY_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("applyCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("sdsCode") && filters.get("sdsCode") != null) {
				sql += " and SDC_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("sdsCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("svwCode") && filters.get("svwCode") != null) {
				sql += " and SVW_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("svwCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("maDesc") && filters.get("maDesc") != null) {
				String descStr = filters.get("maDesc");
				if (descStr.indexOf("@") > -1) {
					String[] arrs = descStr.split("@");
					sql += " and MA_DESC like ?";
					String str = "%";
					for (int i = 0; i < arrs.length; i++) {
						str += arrs[i] + "%";
					}
					Parm_Struct p = new Parm_Struct(str);
					params.add(p);
				} else {
					sql += " and MA_DESC like ?";
					Parm_Struct p = new Parm_Struct("%" + descStr + "%");
					params.add(p);
				}
			}
			
			//:-
			extraApis exx = new extraApis();
			exx.getIt(filters, params, sql);
			sql = exx.sql;
			params = exx.params;	
			//-:
			
			if (filters.containsKey("proj") && filters.get("proj") != null) {
				sql += " and PROJ = ?";
				Parm_Struct p = new Parm_Struct(filters.get("proj"));
				params.add(p);
			}
			System.out.println("selectCount================" + sql);
			
			Object obj = dbgr.GetFirstValue(sql, CommandType.SQL, params);
			if (obj != null)
				counts = ((BigDecimal) obj).intValue();
		}finally{
			dbgr.closeConn();
		}
		return counts;
	}
	
	public static int countPurchaseTrackSq(Map<String, String> filters) throws Exception{
		int counts = 0 ;
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		try{
			List<Parm_Struct> params = new ArrayList<Parm_Struct>();
			String sql = "select count(1) from CSC_PURCHASETRACK_SQ a where 1 = 1";
			
			if (filters.containsKey("applyCode") && filters.get("applyCode") != null) {
				sql += " and APPLY_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("applyCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("sdsCode") && filters.get("sdsCode") != null) {
				sql += " and SDC_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("sdsCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("svwCode") && filters.get("svwCode") != null) {
				sql += " and SVW_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("svwCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("maDesc") && filters.get("maDesc") != null) {
				String descStr = filters.get("maDesc");
				if (descStr.indexOf("@") > -1) {
					String[] arrs = descStr.split("@");
					sql += " and MA_DESC like ?";
					String str = "%";
					for (int i = 0; i < arrs.length; i++) {
						str += arrs[i] + "%";
					}
					Parm_Struct p = new Parm_Struct(str);
					params.add(p);
				} else {
					sql += " and MA_DESC like ?";
					Parm_Struct p = new Parm_Struct("%" + descStr + "%");
					params.add(p);
				}
			}
			
			//:-
			extraApis exx = new extraApis();
			exx.getIt(filters, params, sql);
			sql = exx.sql;
			params = exx.params;	
			//-:
			
			if (filters.containsKey("proj") && filters.get("proj") != null) {
				sql += " and PROJ = ?";
				Parm_Struct p = new Parm_Struct(filters.get("proj"));
				params.add(p);
			}
			System.out.println("selectCount================" + sql);
			
			Object obj = dbgr.GetFirstValue(sql, CommandType.SQL, params);
			if (obj != null)
				counts = ((BigDecimal) obj).intValue();
		}finally{
			dbgr.closeConn();
		}
		return counts;
	}
	
	public static int countPurchaseTrackGm(Map<String, String> filters) throws Exception{
		int counts = 0 ;
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		try{
			List<Parm_Struct> params = new ArrayList<Parm_Struct>();
			String sql = "select count(1) from CSC_PURCHASETRACK_GM a where 1 = 1";
			
			if (filters.containsKey("applyCode") && filters.get("applyCode") != null) {
				sql += " and APPLY_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("applyCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("sdsCode") && filters.get("sdsCode") != null) {
				sql += " and SDC_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("sdsCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("svwCode") && filters.get("svwCode") != null) {
				sql += " and SVW_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("svwCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("maDesc") && filters.get("maDesc") != null) {
				String descStr = filters.get("maDesc");
				if (descStr.indexOf("@") > -1) {
					String[] arrs = descStr.split("@");
					sql += " and MA_DESC like ?";
					String str = "%";
					for (int i = 0; i < arrs.length; i++) {
						str += arrs[i] + "%";
					}
					Parm_Struct p = new Parm_Struct(str);
					params.add(p);
				} else {
					sql += " and MA_DESC like ?";
					Parm_Struct p = new Parm_Struct("%" + descStr + "%");
					params.add(p);
				}
			}
			
			//:-			
			extraApis exx = new extraApis();
			exx.getIt(filters, params, sql);
			sql = exx.sql;
			params = exx.params;	
			/*			
			if (filters.containsKey("approveDateStart") && filters.get("approveDateStart") != null
					&& 
					filters.containsKey("approveDateEnd") && filters.get("approveDateEnd") != null){
				sql += " and a.APPROVE_DATE BETWEEN ? AND ?";
				Parm_Struct p1 = new Parm_Struct(filters.get("approveDateStart"));
				params.add(p1);
				Parm_Struct p2 = new Parm_Struct(filters.get("approveDateEnd"));
				params.add(p2);
				
			}else if (filters.containsKey("approveDateStart") && filters.get("approveDateStart") != null){
				sql += " and a.APPROVE_DATE like ?";
				Parm_Struct p = new Parm_Struct("%" +filters.get("approveDateStart") + "%");
				params.add(p);
			}else if (filters.containsKey("approveDateEnd") && filters.get("approveDateEnd") != null){
				sql += " and a.APPROVE_DATE like ?";
				Parm_Struct p = new Parm_Struct("%" +filters.get("approveEnd") + "%");
				params.add(p);
			}*/				
			//-:
			
			if (filters.containsKey("proj") && filters.get("proj") != null) {
				sql += " and PROJ = ?";
				Parm_Struct p = new Parm_Struct(filters.get("proj"));
				params.add(p);
			}
			
			System.out.println("selectCount================" + sql);
			
			Object obj = dbgr.GetFirstValue(sql, CommandType.SQL, params);
			if (obj != null)
				counts = ((BigDecimal) obj).intValue();
		}finally{
			dbgr.closeConn();
		}
		return counts;
	}
	
	public static DataTable selectPurchaseTrack(Map<String, String> filters) throws Exception{
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		DataTable dt = null;
		try{
			List<Parm_Struct> params = new ArrayList<Parm_Struct>();
			String sql = "select A.* from (select M.*, ROWNUM RN from" +
				"(select a.*, b.attention_id FROM CSC_PURCHASETRACK a " +
				" left join csc_attention b on a.apply_code = b.apply_code and b.login_id = ? where 1 = 1 ";
			
			Parm_Struct p10 = new Parm_Struct(filters.get("user"));
			params.add(p10);
			
			if (filters.containsKey("applyCode") && filters.get("applyCode") != null) {
				sql += " and a.APPLY_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("applyCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("sdsCode") && filters.get("sdsCode") != null) {
				sql += " and a.SDC_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("sdsCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("svwCode") && filters.get("svwCode") != null) {
				sql += " and a.SVW_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("svwCode") + "%");
				params.add(p);
			}
			
			//:-
			extraApis exx = new extraApis();
			exx.getIt(filters, params, sql);
			sql = exx.sql;
			params = exx.params;	
			//-:
			
			if (filters.containsKey("proj") && filters.get("proj") != null) {
				sql += " and a.PROJ = ?";
				Parm_Struct p = new Parm_Struct(filters.get("proj"));
				params.add(p);
			}
			
			if (filters.containsKey("maDesc") && filters.get("maDesc") != null) {
				String descStr = filters.get("maDesc");
				if (descStr.indexOf("@") > -1) {
					String[] arrs = descStr.split("@");
					sql += " and a.MA_DESC like ?";
					String str = "%";
					for (int i = 0; i < arrs.length; i++) {
						str += arrs[i] + "%";
					}
					Parm_Struct p = new Parm_Struct(str);
					params.add(p);
				} else {
					sql += " and a.MA_DESC like ?";
					Parm_Struct p = new Parm_Struct("%" + descStr + "%");
					params.add(p);
				}
			}
			
			sql += " order by b.attention_id desc nulls last, a.approve_date desc nulls last) M" +
			       "  where ROWNUM <= ?";
			
			int start = 1;
			int end = 10;
			if (filters != null) {
				if (filters.containsKey("start"))
					start = Integer.parseInt(filters.get("start"));
				if (filters.containsKey("end"))
					end = Integer.parseInt(filters.get("end"));
			}
			
			Parm_Struct p1 = new Parm_Struct(start);
			Parm_Struct p2 = new Parm_Struct(end);
			
			params.add(p2);
			params.add(p1);
			sql += ") A where A.RN >= ?";
			
			System.out.println("selectMore================" + sql);
			dt = dbgr.GetDataTable(sql, CommandType.SQL, params);
		} catch (Exception e) { 
			e.printStackTrace();
			throw e;
		} finally{
			dbgr.closeConn();
		}
		return dt;
	}
	
	public static DataTable selectPurchaseTrackSq(Map<String, String> filters) throws Exception{
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		DataTable dt = null;
		try{
			List<Parm_Struct> params = new ArrayList<Parm_Struct>();
			String sql = "select A.* from (select M.*, ROWNUM RN from" +
				"(select a.*, b.attention_id FROM CSC_PURCHASETRACK_SQ a " +
				" left join csc_attention_sq b on a.apply_code = b.apply_code and b.login_id = ? where 1 = 1 ";
			
			Parm_Struct p10 = new Parm_Struct(filters.get("user"));
			params.add(p10);
			
			if (filters.containsKey("applyCode") && filters.get("applyCode") != null) {
				sql += " and a.APPLY_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("applyCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("sdsCode") && filters.get("sdsCode") != null) {
				sql += " and a.SDC_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("sdsCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("svwCode") && filters.get("svwCode") != null) {
				sql += " and a.SVW_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("svwCode") + "%");
				params.add(p);
			}
			//:-
			extraApis exx = new extraApis();
			exx.getIt(filters, params, sql);
			sql = exx.sql;
			params = exx.params;	
			//-:
			if (filters.containsKey("proj") && filters.get("proj") != null) {
				sql += " and a.PROJ = ?";
				Parm_Struct p = new Parm_Struct(filters.get("proj"));
				params.add(p);
			}
			
			if (filters.containsKey("maDesc") && filters.get("maDesc") != null) {
				String descStr = filters.get("maDesc");
				if (descStr.indexOf("@") > -1) {
					String[] arrs = descStr.split("@");
					sql += " and a.MA_DESC like ?";
					String str = "%";
					for (int i = 0; i < arrs.length; i++) {
						str += arrs[i] + "%";
					}
					Parm_Struct p = new Parm_Struct(str);
					params.add(p);
				} else {
					sql += " and a.MA_DESC like ?";
					Parm_Struct p = new Parm_Struct("%" + descStr + "%");
					params.add(p);
				}
			}
			
			sql += " order by b.attention_id desc nulls last, a.approve_date desc nulls last) M" +
			       "  where ROWNUM <= ?";
			
			int start = 1;
			int end = 10;
			if (filters != null) {
				if (filters.containsKey("start"))
					start = Integer.parseInt(filters.get("start"));
				if (filters.containsKey("end"))
					end = Integer.parseInt(filters.get("end"));
			}
			
			Parm_Struct p1 = new Parm_Struct(start);
			Parm_Struct p2 = new Parm_Struct(end);
			
			params.add(p2);
			params.add(p1);
			sql += ") A where A.RN >= ?";
			
			System.out.println("selectMore================" + sql);
			dt = dbgr.GetDataTable(sql, CommandType.SQL, params);
		} catch (Exception e) { 
			e.printStackTrace();
			throw e;
		} finally{
			dbgr.closeConn();
		}
		return dt;
	}
	
	public static DataTable selectPurchaseTrackGm(Map<String, String> filters) throws Exception{
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		DataTable dt = null;
		try{
			List<Parm_Struct> params = new ArrayList<Parm_Struct>();
			String sql = "select A.* from (select M.*, ROWNUM RN from" +
				"(select a.*, b.attention_id FROM CSC_PURCHASETRACK_GM a " +
				" left join csc_attention_gm b on a.apply_code = b.apply_code and b.login_id = ? where 1 = 1 ";
			
			Parm_Struct p10 = new Parm_Struct(filters.get("user"));
			params.add(p10);
			
			if (filters.containsKey("applyCode") && filters.get("applyCode") != null) {
				sql += " and a.APPLY_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("applyCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("sdsCode") && filters.get("sdsCode") != null) {
				sql += " and a.SDC_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("sdsCode") + "%");
				params.add(p);
			}
			
			if (filters.containsKey("svwCode") && filters.get("svwCode") != null) {
				sql += " and a.SVW_CODE like ?";
				Parm_Struct p = new Parm_Struct("%" + filters.get("svwCode") + "%");
				params.add(p);
			}
			
			//:-
			extraApis exx = new extraApis();
			exx.getIt(filters, params, sql);
			sql = exx.sql;
			params = exx.params;	
			//-:
			
			if (filters.containsKey("proj") && filters.get("proj") != null) {
				sql += " and a.PROJ = ?";
				Parm_Struct p = new Parm_Struct(filters.get("proj"));
				params.add(p);
			}

			if (filters.containsKey("maDesc") && filters.get("maDesc") != null) {
				String descStr = filters.get("maDesc");
				if (descStr.indexOf("@") > -1) {
					String[] arrs = descStr.split("@");
					sql += " and a.MA_DESC like ?";
					String str = "%";
					for (int i = 0; i < arrs.length; i++) {
						str += arrs[i] + "%";
					}
					Parm_Struct p = new Parm_Struct(str);
					params.add(p);
				} else {
					sql += " and a.MA_DESC like ?";
					Parm_Struct p = new Parm_Struct("%" + descStr + "%");
					params.add(p);
				}
			}
			
			sql += " order by b.attention_id desc nulls last, a.approve_date desc nulls last) M" +
			       "  where ROWNUM <= ?";
			
			int start = 1;
			int end = 10;
			if (filters != null) {
				if (filters.containsKey("start"))
					start = Integer.parseInt(filters.get("start"));
				if (filters.containsKey("end"))
					end = Integer.parseInt(filters.get("end"));
			}
			
			Parm_Struct p1 = new Parm_Struct(start);
			Parm_Struct p2 = new Parm_Struct(end);
			
			params.add(p2);
			params.add(p1);
			sql += ") A where A.RN >= ?";
			
			System.out.println("selectMore================" + sql);
			dt = dbgr.GetDataTable(sql, CommandType.SQL, params);
		} catch (Exception e) { 
			e.printStackTrace();
			throw e;
		} finally{
			dbgr.closeConn();
		}
		return dt;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}

class extraApis{
	 String sql = " ";
	 List<Parm_Struct> params = new ArrayList<Parm_Struct>();
	//Map<String, String> filters = new HashMap<String, String>();
	
	public void getIt(Map<String, String> filters, List<Parm_Struct> params, String sql) throws Exception{
		if (filters.containsKey("approveDateStart") && filters.get("approveDateStart") != null
				&& 
				filters.containsKey("approveDateEnd") && filters.get("approveDateEnd") != null){
			sql += " and a.APPROVE_DATE BETWEEN ? AND ?";
			Parm_Struct p1 = new Parm_Struct(filters.get("approveDateStart"));
			params.add(p1);
			Parm_Struct p2 = new Parm_Struct(filters.get("approveDateEnd"));
			params.add(p2);
			
		}else if (filters.containsKey("approveDateStart") && filters.get("approveDateStart") != null){
			sql += " and a.APPROVE_DATE like ?";
			Parm_Struct p = new Parm_Struct("%" +filters.get("approveDateStart") + "%");
			params.add(p);
		}else if (filters.containsKey("approveDateEnd") && filters.get("approveDateEnd") != null){
			sql += " and a.APPROVE_DATE like ?";
			Parm_Struct p = new Parm_Struct("%" +filters.get("approveEnd") + "%");
			params.add(p);
		}
		
		this.sql = sql;
		this.params = params;
	}	
} 
