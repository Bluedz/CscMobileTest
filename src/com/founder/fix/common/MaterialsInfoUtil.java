package com.founder.fix.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.founder.fix.dbcore.CommandType;
import com.founder.fix.dbcore.DBGetResult;
import com.founder.fix.dbcore.DBGetResultHandle;
import com.founder.fix.dbcore.DataTable;
import com.founder.fix.dbcore.Parm_Struct;

/**
 * @author winsanity
 *
 */
public class MaterialsInfoUtil {

	public static DataTable selectMaterInfo(String searchText, int start, int end) throws Exception{
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		DataTable dt = null;
		try{
			String sql ="";
			sql = "SELECT * FROM (select A.*, ROWNUM RN FROM (" +
					"select a.req_applycode,a.req_category,a.req_materialno,a.req_description,a.req_materialmaker,a.req_status,a.req_amount," +
					"a.req_approvaldate,b.con_arrivedate,c.total," +
					"case when c.total is null then '未到货' when a.REQ_AMOUNT>c.total then '部分到货' else '全部到货' end as arrstatus" +
					" from CSC_PURCHASE_REQUISITION a" +
					" left join " +
					" (select t.mat_applycode,tt.con_arrivedate" +
					" from CSC_CONTRACT_MATERIALINFO t,CSC_CONTRACT_CONTRACTINFO tt" +
					" where t.mat_contractcode=tt.con_code)b" +
					" on a.req_applycode=b.mat_applycode" +
					" left join" +
					" (select REQ_APPLYCODE,sum(POI_RECEIPTS)as total from CSC_PO_ARRIVALINFO" +
					" group by REQ_APPLYCODE)c" +
					" on a.req_applycode=c.req_applycode";
			
			Parm_Struct p1 = new Parm_Struct(start);
			Parm_Struct p2 = new Parm_Struct(end);
			Parm_Struct p3 = new Parm_Struct("%" + searchText + "%");
			List<Parm_Struct> list = new ArrayList<Parm_Struct>();
			
			if (searchText != null && !searchText.equals("")) {
				sql += " where a.req_applycode like ?";
				list.add(p3);
			}
			
			list.add(p2);
			list.add(p1);
			sql += " ) A WHERE ROWNUM <= ?)" +
					" WHERE RN >= ?";
			
			dt = dbgr.GetDataTable(sql, CommandType.SQL, list);
		
		} catch (Exception e) { 
			e.printStackTrace();
			throw e;
		} finally{
			dbgr.closeConn();
		}
		return dt;
	}
	
	
	public static int countMaterInfo(String searchText) throws Exception{
		int counts = 0 ;
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		try{
			String sql ="";
			sql = "select count(1) from CSC_PURCHASE_REQUISITION a where 1 = 1 ";
			if (searchText != null && !searchText.equals(""))
				sql += " and req_applycode like '%" + searchText + "%'";
			Object obj = dbgr.GetFirstValue(sql);
			if (obj != null)
				counts = ((BigDecimal) obj).intValue();
		}finally{
			dbgr.closeConn();
		}
		return counts;
	}
}
