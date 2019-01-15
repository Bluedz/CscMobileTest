package com.founder.fix.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.founder.fix.apputil.util.JSONUtil;
import com.founder.fix.common.MaterialsInfoUtil;
import com.founder.fix.common.PurchaseTrackUtil;
import com.founder.fix.dbcore.CommandType;
import com.founder.fix.dbcore.DBGetResult;
import com.founder.fix.dbcore.DBGetResultHandle;
import com.founder.fix.dbcore.DataTable;
import com.founder.fix.dbcore.Parm_Struct;
import com.founder.fix.webcore.Current;

@Controller
@RequestMapping("/purchaseServiceSq")
public class PurchaseServiceSq {
	
	@RequestMapping(value="/attention", produces="text/html;charset=UTF-8")  
	@ResponseBody
	public String attention(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String applyCode = request.getParameter("applyCode");             // 申购单号
		String loginId = Current.getUser().getLoginId();                  // 当前登录人
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		String isExist = "select * from CSC_ATTENTION_SQ where APPLY_CODE = ? and LOGIN_ID = ?";
		String insertSql = "insert into CSC_ATTENTION_SQ values (SEQ_ATTENTION_ID.Nextval, ?, ?, ?)";
		List<Parm_Struct> params = new ArrayList<Parm_Struct>();
		Parm_Struct p1 = new Parm_Struct(applyCode);
		Parm_Struct p2 = new Parm_Struct(loginId);
		params.add(p1);
		params.add(p2);
		DataTable dt = dbgr.GetDataTable(isExist, CommandType.SQL, params);
		if (dt != null && dt.Rows.length > 0) {                  // 已经关注过，提示前端
			returnMap.put("isSuc", "0");
			returnMap.put("msg", "已经关注过该申购单");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdf.format(new Date());
			Parm_Struct p3 = new Parm_Struct(now);
			params.add(p3);
			int count = dbgr.execSQLCmd(insertSql, CommandType.SQL, params);
			if (count == 1) {                                   // 关注成功
				returnMap.put("isSuc", "1");
				returnMap.put("msg", "关注成功");
			} else {
				returnMap.put("isSuc", "0");
				returnMap.put("msg", "关注失败");
			}
		}
		dbgr.closeConn();
		return JSONUtil.parseObject2JSON(returnMap);
	}
	
	@RequestMapping(value="/deAttention", produces="text/html;charset=UTF-8")  
	@ResponseBody
	public String deAttention(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String applyCode = request.getParameter("applyCode");             // 申购单号
		String loginId = Current.getUser().getLoginId();                  // 当前登录人
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		String isExist = "select * from CSC_ATTENTION_SQ where APPLY_CODE = ? and LOGIN_ID = ?";
		String insertSql = "delete from CSC_ATTENTION_SQ where attention_id = ?";
		List<Parm_Struct> params = new ArrayList<Parm_Struct>();
		Parm_Struct p1 = new Parm_Struct(applyCode);
		Parm_Struct p2 = new Parm_Struct(loginId);
		params.add(p1);
		params.add(p2);
		DataTable dt = dbgr.GetDataTable(isExist, CommandType.SQL, params);
		if (dt != null && dt.Rows.length > 0) {                  // 已经关注过，提示前端
			String attId = dt.Rows[0].ItemValue("ATTENTION_ID").toString();
			params.clear();
			Parm_Struct p3 = new Parm_Struct(attId);
			params.add(p3);
			int count = dbgr.execSQLCmd(insertSql, CommandType.SQL, params);
			if (count == 1) {                                   // 关注成功
				returnMap.put("isSuc", "1");
				returnMap.put("msg", "取消关注成功");
			} else {
				returnMap.put("isSuc", "0");
				returnMap.put("msg", "取消关注失败");
			}
		} else {
			returnMap.put("isSuc", "0");
			returnMap.put("msg", "已经取消关注该申购单");
		}
		dbgr.closeConn();
		return JSONUtil.parseObject2JSON(returnMap);
	}
	
	@RequestMapping(value="/list", produces="text/html;charset=UTF-8")  
	@ResponseBody
	public String list(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) throws Exception {
		String loginId = Current.getUser().getLoginId();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, String> params = new HashMap<String, String>();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));           // 当前第几页
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));                 // 每页显示数量
		String searchText = request.getParameter("searchText");                            // 前端传回来的查询条件
		System.out.println("searchText=================" + searchText);
		params.put("user", loginId);
		if (searchText != null && !searchText.equals("")) {                                // 基础查询
			params.put("applyCode", searchText);
		} else {                                                                           // 高级查询
			params.put("applyCode", request.getParameter("applyCode"));
			params.put("sdsCode", request.getParameter("sdsCode"));
			params.put("svwCode", request.getParameter("svwCode"));
			//:-
			params.put("approveDate", request.getParameter("approveDate"));
			params.put("approveDateStart", request.getParameter("approveDateStart"));
			params.put("approveDateEnd", request.getParameter("approveDateEnd"));
			//-:
			String proj = request.getParameter("proj");
			if (proj != null && !proj.equals(""))
				params.put("proj", new String(proj.getBytes("iso-8859-1"), "utf-8"));
			
			String maDesc = request.getParameter("maDesc");
			if (maDesc != null && !maDesc.equals(""))
				params.put("maDesc", new String(maDesc.getBytes("iso-8859-1"), "utf-8"));
		}

		int total = PurchaseTrackUtil.countPurchaseTrackSq(params);                                    // 总记录数
		
		int totalPages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		returnMap.put("totalPages", totalPages);                                           // 总页数
		
		int start = (currentPage - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		params.put("start", start + "");
		params.put("end", end + "");
		DataTable dt = PurchaseTrackUtil.selectPurchaseTrackSq(params);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < dt.Rows.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("applyCode", dt.Rows[i].ItemValue("APPLY_CODE"));
			map.put("maDesc", dt.Rows[i].ItemValue("MA_DESC"));
			map.put("applyType", dt.Rows[i].ItemValue("APPLY_TYPE"));
			map.put("sdsCode", dt.Rows[i].ItemValue("SDC_CODE"));
			map.put("svwCode", dt.Rows[i].ItemValue("SVW_CODE"));
			map.put("maker", dt.Rows[i].ItemValue("MAKER"));
			map.put("purchaseStatus", dt.Rows[i].ItemValue("PURCHASE_STATUS"));
			map.put("purchaseNum", dt.Rows[i].ItemValue("PURCHASE_NUM"));
			map.put("approveDate", dt.Rows[i].ItemValue("APPROVE_DATE"));
			map.put("conArrDate", dt.Rows[i].ItemValue("CON_ARR_DATE"));
			map.put("arrStatus", dt.Rows[i].ItemValue("ARR_STATUS"));
			map.put("arrNum", dt.Rows[i].ItemValue("ARR_NUM"));
			map.put("sdsEngine", dt.Rows[i].ItemValue("SDS_ENGINE"));
			map.put("prePurcher", dt.Rows[i].ItemValue("PRE_PURCHER"));
			map.put("purcher", dt.Rows[i].ItemValue("PURCHER"));
			map.put("proj", dt.Rows[i].ItemValue("PROJ"));
			map.put("attId", dt.Rows[i].ItemValue("ATTENTION_ID"));
			
			returnList.add(map);
		}
		returnMap.put("data", returnList);
		String jsonVal = JSONUtil.parseObject2JSON(returnMap);
		return jsonVal;
	}
	
	@RequestMapping(value="/list2")  
	@ResponseBody
	public String list2(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));           // 当前第几页
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));                 // 每页显示数量
		String searchText = request.getParameter("searchText");                            // 前端传回来的查询条件
		System.out.println("searchText====" + searchText);
		int total = MaterialsInfoUtil.countMaterInfo(searchText);                                    // 总记录数
		
		int totalPages = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		returnMap.put("totalPages", totalPages);                                           // 总页数
		
		int start = (currentPage - 1) * pageSize + 1;
		int end = start + pageSize - 1;
		
		DataTable dt = MaterialsInfoUtil.selectMaterInfo(searchText, start, end);
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < dt.Rows.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("reqApplyCode", dt.Rows[i].ItemValue("REQ_APPLYCODE"));
			map.put("reqCategory", dt.Rows[i].ItemValue("REQ_CATEGORY"));
			map.put("reqMaterialNo", dt.Rows[i].ItemValue("REQ_MATERIALNO"));
			map.put("reqDescription", dt.Rows[i].ItemValue("REQ_DESCRIPTION"));
			map.put("reqMaterialMaker", dt.Rows[i].ItemValue("REQ_MATERIALMAKER"));
			map.put("reqStatus", dt.Rows[i].ItemValue("REQ_STATUS"));
			map.put("reqAmount", dt.Rows[i].ItemValue("REQ_AMOUNT"));
			map.put("reqApprovalDate", dt.Rows[i].ItemValue("REQ_APPROVALDATE"));
			map.put("conArriveDate", dt.Rows[i].ItemValue("CON_ARRIVEDATE"));
			map.put("total", dt.Rows[i].ItemValue("TOTAL"));
			map.put("arrStatus", dt.Rows[i].ItemValue("ARRSTATUS"));
			returnList.add(map);
		}
		returnMap.put("data", returnList);
		String jsonVal = JSONUtil.parseObject2JSON(returnMap);
		System.out.println(jsonVal);
		return jsonVal;
	}
}
