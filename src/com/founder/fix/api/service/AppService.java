/**
 * 
 */
package com.founder.fix.api.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.founder.fix.apputil.util.JSONUtil;
import com.founder.fix.dbcore.DBGetResult;
import com.founder.fix.dbcore.DBGetResultHandle;
import com.founder.fix.dbcore.DataTable;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/appService")
public class AppService {
	
	/**
	 * 检查版本是否需要更新
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkUpdate", produces="text/html;charset=UTF-8")  
	@ResponseBody
	public String checkUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		String curVersion = request.getParameter("curVersion");             // 当前版本号
		System.out.println("当前版本号为：" + curVersion);
		
		DBGetResult dbgr = DBGetResultHandle.createDBGetResult();
		String sql = "select * from csc_mobile_version";
		DataTable dt = dbgr.GetDataTable(sql);
		if (dt != null && dt.Rows.length > 0) {                  // 已经关注过，提示前端
			String dbCurVesion = String.valueOf(dt.Rows[0].ItemValue("CUR_VERSION"));             // 当前版本号
			String dbCheckingVesion = String.valueOf(dt.Rows[0].ItemValue("CHECKING_VERSION"));   // 审核中版本号
			if (curVersion.equals(dbCurVesion) || curVersion.equals(dbCheckingVesion)) {
				returnMap.put("needUpdate", "0");                // 不需要更新
			} else {
				returnMap.put("needUpdate", "1");                // 需要更新
			}
		} else {
			returnMap.put("needUpdate", "0");                    // 不需要更新
		}
		dbgr.closeConn();
		return JSONUtil.parseObject2JSON(returnMap);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
