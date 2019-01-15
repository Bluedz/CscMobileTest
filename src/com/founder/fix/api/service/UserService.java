package com.founder.fix.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.founder.fix.api.util.AuthUtil;
import com.founder.fix.api.util.Utils;
import com.founder.fix.apputil.AppInfo;
import com.founder.fix.apputil.OnlineUser;
import com.founder.fix.apputil.util.JSONUtil;
import com.founder.fix.apputil.util.StringUtil;
import com.founder.fix.dbcore.DataTable;
import com.founder.fix.dbcore.DataTableUtil;
import com.founder.fix.filter.RestApiFilter;
import com.founder.fix.webcore.DataView;
import com.founder.fix.webcore.interfaceLayer.AdapterProxy;
import com.founder.fix.webcore.permission.PermissionHander;


@Controller
@RequestMapping("/user")
public class UserService {
	
	private Map<String, Object> getUserExtendInfo(String UserId) throws Exception{
		Map<String, Object> userExtendInfo = new HashMap<String, Object>();
		
		String express = "BASEINFO.getUserAllInfo";
		DataView dataView = DataView.buildDataView();
		String formSql = "EMPLOYEE_CODE = "+UserId+"";
		dataView.getQueryData().add(formSql);
		List<String> queryFields = new ArrayList<String>();
		queryFields.add("*");
		
		dataView.setQueryFields(queryFields);
		
//		Map map = new HashMap();
		Object result = AdapterProxy.execute(express, dataView, null);
		
		if(result instanceof DataTable){
			DataTable infoDataTable  = (DataTable)result;
			
			for(DataTable.DataRow row : infoDataTable.Rows){
				for(DataTable.DataItem item : row.Items){
//					log.debug("{sysLoginUser."+item.ParentDataColumn.ColumnName+"}     "+item.ToString());
				}
			}
			
			if(infoDataTable.Rows.length != 1){
				//
			}
			else{
				return DataTableUtil.parseDataTable2List(infoDataTable).get(0);
			}
		}
		
		
		return userExtendInfo;
		
	}
	
	@RequestMapping(value="/login")  
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response, @RequestBody String json) throws Exception {
		Map<String, Object> jsonParam = Utils.parseJsonStrToMap(json);
		String loginId = StringUtil.getString(jsonParam.get("loginId"));
		String password = StringUtil.getString(jsonParam.get("password"));
		boolean result = PermissionHander.login(loginId, password, true ,request,response);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if(result){
			OnlineUser user = AppInfo.CurrentUser(request.getSession());
			
			//Map<String, Object> userExtendInfo = getUserExtendInfo(user.getUserID());
			//user.getItems().putAll(userExtendInfo);
			
			returnMap.put("loginResut", "1");
			returnMap.put("userInfo", user.getItems());
			
			String authKey = AuthUtil.genAuthKey(user.getUserID());
			
			returnMap.put("authKey", authKey);
			
			RestApiFilter.apiUsers.put(authKey, user);
			
		}else{
			returnMap.put("loginResut", "0");
		}
		
		return JSONUtil.parseObject2JSON(returnMap);
	}

}
