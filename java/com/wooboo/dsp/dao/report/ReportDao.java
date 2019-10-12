package com.wooboo.dsp.dao.report;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.statement.Statement;
import com.wooboo.dsp.util.UtilFunction;

@Repository
public class ReportDao extends BaseDao {
	
	//用户报表总计
	public Object[] queryReportUserDateTotal(Date beginDate,Date endDate,Integer advertiserId){
		Statement stms = stmsFactory.createNativeStatement("select ifnull(sum(rud.`show`),0),ifnull(sum(rud.`click`),0),ifnull(sum(rud.`money_ad`),0.00),ifnull(sum(rud.`traffic`),0) from ");
		          stms.append("rep_user_date rud,user_company u where u.id =rud.user_id");
		          stms.append("and", "u.id", "=", advertiserId);
		          
		          stms.appendBetween("and", "rud.`created_date`", beginDate, endDate);
		return (Object[])hibernateDao.find(stms);
	}
	//计划报表总计
	public Object[] queryReportPlanDateTotal(Date beginDate,Date endDate,Integer type,Integer planId, Integer userCompanyId){
		Statement stms = stmsFactory.createNativeStatement("select ifnull(sum(rpd.`show`),0),ifnull(sum(rpd.`click`),0),ifnull(sum(rpd.`money_ad`),0.00),ifnull(sum(rpd.`traffic`),0) ");
                  stms.append("from rep_plan_date rpd,admanage_plan p,user_company u where rpd.plan_id=p.id and p.owner=u.id");
                  stms.append("and", "rpd.plan_id", "=", planId);
                  
                  stms.append("and", "p.type", "=", type);
                  stms.append("and", "u.id", "=", userCompanyId);
		stms.appendBetween("and", "rpd.`created_date`", beginDate, endDate);
        return (Object[])hibernateDao.find(stms);
	}
	
	//组报表总计
	public Object[] queryReportGroupDateTotal(Date beginDate,Date endDate,Integer channelId,Integer type,Integer groupId,Integer userCompanyId){
			Statement stms = stmsFactory.createNativeStatement("select ifnull(sum(rgd.`show`),0),ifnull(sum(rgd.`click`),0),ifnull(sum(rgd.`money_ad`),0.00),ifnull(sum(rgd.`traffic`),0)  ");
	                  stms.append("from rep_group_date rgd,admanage_group g,admanage_plan p,user_company u where rgd.group_id=g.id and g.plan_id=p.id and g.owner=u.id");
	                  
	                  stms.append("and", "g.id", "=", groupId);
	                  stms.append("and", "g.platform_limit", "=", channelId);
	                  stms.append("and", "u.id", "=", userCompanyId);
	                  stms.append("and", "p.type", "=", type);
			stms.appendBetween("and", "rgd.`created_date`", beginDate, endDate);
	        return (Object[])hibernateDao.find(stms);
	}
	
	public Object[] queryReportIdeaDateTotal(Date beginDate, Date endDate, Integer channelId, Integer type, Integer ideaId, Long groupId, Integer userCompanyId) {
		Statement stms = stmsFactory.createNativeStatement("select ifnull(sum(rid.`show`),0),ifnull(sum(rid.`click`),0),ifnull(sum(rid.`money_ad`),0.00),ifnull(sum(rid.`traffic`),0) ");
		stms.append("from rep_idea_date rid,admanage_group g,admanage_idea i,admanage_plan p,user_company u where rid.idea_id = i.id and i.plan_id = p.id and i.group_id = g.id and i.owner=u.id");
		
		stms.append("and", "i.id", "=", ideaId);
		stms.append("and", "g.platform_limit", "=", channelId);
		stms.append("and", "p.type", "=", type);
		stms.append("and", "g.id", "=", groupId);
		stms.append("and", "u.id", "=", userCompanyId);
		stms.appendBetween("and", "rid.`created_date`", beginDate, endDate);
		return (Object[]) hibernateDao.find(stms);
	}
	
	//用户报表
	public List<Object[]>  queryReportUserDate(Date beginDate,Date endDate){
		Statement stms = stmsFactory.createNativeStatement("select rud.`created_date`,sum(rud.`show`),sum(rud.`click`),sum(rud.`money_ad`),sum(rud.`traffic`) ");
		          stms.append("from order_info rud u where 1 = 1 ");
		          stms.appendBetween("and", "rud.`created_date`", beginDate, endDate);
		          stms.addGroupBy("rud.`created_date`");
		          
		         
		return hibernateDao.query(stms);
	}
	//0 userId 1 loginName 2:companyName 3 date 4 show 5click 6 money 7 traffic
	public void searchUserDateReport(Page page,Date beginDate,Date endDate,Integer advertiserId){
		Statement stms = stmsFactory.createNativeStatement("select u.`id`,u.`login_name`,u.`company_name`,rdu.`created_date`,sum(rdu.`show`),sum(rdu.`click`),sum(rdu.`money_ad`) ,sum(rdu.`traffic`) ");
		          stms.append("from `user_company` u,`rep_user_date` rdu where u.`id`=rdu.`user_id` ");
		          stms.append("and", "u.id", "=", advertiserId);
		         
		          stms.appendBetween("and", "rdu.`created_date`", beginDate, endDate);
		          stms.addGroupBy("rdu.`user_id`,rdu.`created_date`");
		          stms.addOrderBy("rdu.`created_date` desc");
		hibernateDao.search(stms, page);          
	}
	//0 userId 1:loginName 2:companyName 3:planId 4 planName 5:date 6:show 7 click 8 money 9 traffic
	public void searchPlanDateReport(Page page,Date beginDate,Date endDate,Integer type,Integer planId,Integer userCompanyId){
		Statement stms = stmsFactory.createNativeStatement("select u.`id`,u.`login_name`,u.`company_name`,p.`id` as planId,p.`plan_name`, rpd.`created_date`,sum(rpd.`show`),sum(rpd.`click`),sum(rpd.`money_ad`),sum(rpd.`traffic`)  ");
        stms.append(" from `user_company` u,`admanage_plan` p,`rep_plan_date` rpd where u.`id`=p.`owner` and p.`id`=rpd.`plan_id`");
        
        stms.append("and", "rpd.plan_id", "=", planId);
        
        
        stms.append("and", "p.type", "=", type);
        stms.append("and", "u.id", "=", userCompanyId);
        stms.appendBetween("and", "rpd.`created_date`", beginDate, endDate);
        stms.addGroupBy("u.`id`,p.`id`,rpd.`created_date`");
        stms.addOrderBy("rpd.`created_date` desc");
        hibernateDao.search(stms, page); 
	}
	//0:uid 1:loginName 2:companyName 3:groupId 4:channelId 5:channelName 6:groupName  7:createDate 8:show 9:click 10:money 11:traffic
	public void searchGroupDateReport(Page page,Date beginDate,Date endDate,Integer channelId,Integer type,Integer groupId,Integer userCompanyId){
		Statement stms = stmsFactory.createNativeStatement("select u.`id`,u.`login_name`,u.`company_name`,g.`id` as groupId,ci.`id` as cid,ci.`channel_name`,g.`group_name`, rgd.`created_date`,sum(rgd.`show`),sum(rgd.`click`),sum(rgd.`money_ad`),sum(rgd.`traffic`)    ");
        stms.append(" from `user_company` u,`admanage_group` g,admanage_plan p,`rep_group_date` rgd,`channel_info` ci where u.`id`=g.`owner` and g.`id`=rgd.`group_id` and g.plan_id=p.id and g.`platform_limit`=ci.`id`");
        
        stms.append("and", "g.id", "=", groupId);
        stms.append("and", "g.platform_limit", "=", channelId);
        stms.append("and", "p.type", "=", type);
        stms.append("and", "u.id", "=", userCompanyId);
        stms.appendBetween("and", "rgd.`created_date`", beginDate, endDate);
        stms.addGroupBy("u.`id`,g.`id`,ci.`id`,rgd.`created_date`");
        stms.addOrderBy("rgd.`created_date` desc");
        hibernateDao.search(stms, page); 
	}

	//0:uid 1:loginName 2:companyName 3:ideaId 4:channelId 5:channelName 6:ideaName  7:createDate 8:show 9:click 10:money 11:traffic
	public void searchIdeaDateReport(Page page,Date beginDate,Date endDate,Integer channelId,Integer type,Integer ideaId, Long groupId, Integer userCompanyId){
		Statement stms = stmsFactory.createNativeStatement("SELECT u.`id`,u.`login_name`,u.`company_name`,i.`id` AS ideaId,ci.`id` AS cid,ci.`channel_name`,i.idea_name,rid.`created_date`,sum(rid.`show`),sum(rid.`click`),sum(rid.`money_ad`),sum(rid.`traffic`),g.group_name ");
		stms.append(" FROM `user_company` u,`admanage_idea` i,`admanage_group` g,admanage_plan p,`rep_idea_date` rid,`channel_info` ci WHERE u.`id` = i.`owner` AND i.group_id = g.id AND i.id = rid.idea_id  AND i.plan_id = p.id AND g.`platform_limit` = ci.`id` ");
		
		stms.append("and", "i.id", "=", ideaId);
		stms.append("and", "g.platform_limit", "=", channelId);
		stms.append("and", "p.type", "=", type);
		stms.append("and", "g.id", "=", groupId);
		stms.append("and", "u.id", "=", userCompanyId);
		stms.appendBetween("and", "rid.`created_date`", beginDate, endDate);
		stms.addGroupBy("u.`id`,i.`id`,ci.`id`,rid.`created_date`");
		stms.addOrderBy("rid.`created_date` desc");
		hibernateDao.search(stms, page); 
	}

	
	/**
	 * 查询流量领取记录
	 * 0:时间 1：流水 2：电话 3：计划id 4:名称 5:流量  6：金额  7：状态
	 * */
	public void searchPhoneGift(Page page,  String sequence,String phone_number,Integer planId,String status,String plan_name,String idea_name,String beginDate,String endDate){
		Statement stms = stmsFactory.createNativeStatement("select ptg.created_time,ptg.`sequence`,ptg.`phone_number`,ptg.`plan_id`,p.`plan_name`,ptg.`product_value`, ptg.`product_price`,ptg.`state`,idea.idea_name,ptg.id,ptg.city_code ");
		stms.append(" from `phone_traffic_gift` ptg,`admanage_plan` p,`admanage_idea` idea,`user_company` u ");
		stms.append(" where u.`id`=p.`owner` and idea.`id`=ptg.`idea_id`");
		stms.append(" and idea.plan_id=p.`id`");
		
		
		stms.appendLike(null, "and", "p.plan_name", plan_name);
		stms.appendLike(null, "and", "idea_name", idea_name);
		stms.append("and", "p.`id`", "=", planId);
		stms.append("and", "ptg.`phone_number`", "=", phone_number);
		stms.append("and", "ptg.state", "=", status);
		stms.append("and", "ptg.created_time", ">=", beginDate);
		stms.append("and", "ptg.created_time", "<=", endDate);
		stms.addOrderBy(" ptg.id desc");
		hibernateDao.search(stms, page);
	}
	
	
	/**
	 * 查询流量领取记录
	 * 0:时间 1：流水 2：电话 3：计划id 4:名称 5:流量  6：金额  7：状态
	 * */
	public List searchPhoneGift(String sequence,String phone_number,Integer planId,String status,String plan_name,String idea_name,String beginDate,String endDate,String month){
		Statement stms = stmsFactory.createNativeStatement("select ptg.created_time,ptg.`sequence`,ptg.`phone_number`,ptg.`plan_id`,p.`plan_name`,ptg.`product_value`, ptg.`product_price`,ptg.`state`,idea.idea_name,ptg.id,ptg.city_code,ptg.result,ptg.result_desc,ptg.sequence,ptg.sub_sequence ");
		
		if(UtilFunction.isEmpty(month)){
			stms.append(" from `phone_traffic_gift` ptg,`admanage_plan` p,`admanage_idea` idea,`user_company` u ");
		}else{
			String temp_month = "`phone_traffic_gift_"+month+"` ptg";
			stms.append(" from "+temp_month+",`admanage_plan` p,`admanage_idea` idea,`user_company` u ");
		}
		
		stms.append(" where u.`id`=p.`owner` and idea.`id`=ptg.`idea_id`");
		stms.append(" and idea.plan_id=p.`id`");
		
		
		stms.appendLike(null, "and", "p.plan_name", plan_name);
		stms.appendLike(null, "and", "idea_name", idea_name);
		stms.append("and", "p.`id`", "=", planId);
		stms.append("and", "ptg.`phone_number`", "=", phone_number);
		stms.append("and", "ptg.state", "=", status);
		stms.append("and", "ptg.created_time", ">=", beginDate);
		stms.append("and", "ptg.created_time", "<=", endDate);
		stms.append("and"," DATE_FORMAT(ptg.created_time, '%Y%m') ","=",month);
		stms.addOrderBy(" ptg.id desc");
		return hibernateDao.query(stms,50000);
	}
	
	/**
	 * 查询流量领取日记录
	 * 0:id 1:时间 2：归属地市code 3：领取次数 4：成功次数 5：失败次数 6：消耗流量 7：消耗金额 8：合同 9：素材
	 * */
	public void searchPhoneDaily(Page page, String city_code, Integer planId, String plan_name, String idea_name, String beginDate, String endDate) {
		Statement stms = stmsFactory.createNativeStatement(
				"SELECT ptd.id id,ptd.created_date created_date, ptd.city_code city_code,ptd.total_time total_time,ptd.success_time success_time,ptd.fail_time fail_time,ptd.total_m total_m, ptd.total_yuan total_yuan,ap.plan_name plan_name,ai.idea_name idea_name");
		stms.append(" FROM phone_traffic_daily ptd,admanage_plan ap,admanage_idea ai,user_company uc");
		stms.append(" WHERE uc.id = ap.`owner` AND ai.id = ptd.idea_id AND ai.plan_id = ap.id");

		
		stms.appendLike(null, "and", "ap.plan_name", plan_name);
		stms.appendLike(null, "and", "ai.idea_name", idea_name);
		stms.append("and", "ap.`id`", "=", planId);
		stms.append("and", "ptd.city_code", "=", city_code);
		stms.append("and", "ptd.created_date", ">=", beginDate);
		stms.append("and", "ptd.created_date", "<=", endDate);
		stms.addOrderBy(" ptd.id desc");
		hibernateDao.search(stms, page);
	}
	
	public List exportPhoneDaily(String city_code, Integer planId, String plan_name, String idea_name, String beginDate, String endDate) {
		Statement stms = stmsFactory.createNativeStatement(
				"SELECT ptd.id id,ptd.created_date created_date, ptd.city_code city_code,ptd.total_time total_time,ptd.success_time success_time,ptd.fail_time fail_time,ptd.total_m total_m, ptd.total_yuan total_yuan,ap.plan_name plan_name,ai.idea_name idea_name");
		stms.append(" FROM phone_traffic_daily ptd,admanage_plan ap,admanage_idea ai,user_company uc");
		stms.append(" WHERE uc.id = ap.`owner` AND ai.id = ptd.idea_id AND ai.plan_id = ap.id");
		
		
		stms.appendLike(null, "and", "ap.plan_name", plan_name);
		stms.appendLike(null, "and", "ai.idea_name", idea_name);
		stms.append("and", "ap.`id`", "=", planId);
		stms.append("and", "ptd.city_code", "=", city_code);
		stms.append("and", "ptd.created_date", ">=", beginDate);
		stms.append("and", "ptd.created_date", "<=", endDate);
		stms.addOrderBy(" ptd.id desc");
		return hibernateDao.query(stms, 50000);
	}
	
	public List sumPhoneDaily(String city_code, Integer planId, String plan_name, String idea_name, String beginDate, String endDate) {
		Statement stms = stmsFactory.createNativeStatement("SELECT SUM(ptd.total_time) sum_tot,SUM(ptd.success_time) sum_suc,SUM(ptd.fail_time) sum_fail,SUM(ptd.total_m) sum_totm,FORMAT(SUM(ptd.total_yuan), 2) sum_toty");
		stms.append(" FROM phone_traffic_daily ptd,admanage_plan ap,admanage_idea ai,user_company uc");
		stms.append(" WHERE uc.id = ap.`owner` AND ai.id = ptd.idea_id AND ai.plan_id = ap.id");

		
		stms.appendLike(null, "and", "ap.plan_name", plan_name);
		stms.appendLike(null, "and", "ai.idea_name", idea_name);
		stms.append("and", "ap.`id`", "=", planId);
		stms.append("and", "ptd.city_code", "=", city_code);
		stms.append("and", "ptd.created_date", ">=", beginDate);
		stms.append("and", "ptd.created_date", "<=", endDate);
		stms.addOrderBy(" ptd.id desc");
		return hibernateDao.query(stms);
	}

	public List loadCityList() {
		Statement stms = stmsFactory.createNativeStatement("SELECT DISTINCT ptd.city_code city_code FROM phone_traffic_daily ptd");
		stms.append(" WHERE !ISNULL(ptd.city_code) AND ptd.city_code <> ''");
		stms.addOrderBy(" ptd.city_code ASC");
		return hibernateDao.query(stms);
	}
	
	/**
	 * 查询流量领取记录年月
	 * 0:时年月表1：年月名称
	 * */
	public List searchGiftYearAndMonList(){
		Statement stms = stmsFactory.createNativeStatement("select DISTINCT DATE_FORMAT(d.created_date, '%Y%m') dateid, DATE_FORMAT(d.created_date, '%Y-%m') datename from `phone_traffic_daily` d ORDER BY d.created_date DESC limit 24");

		return hibernateDao.query(stms);
	}

	/**
	 * 查询流量领取历史记录
	 * 0:时间 1：流水 2：电话 3：计划id 4:名称 5:流量  6：金额  7：状态
	 * */
	public void searchGiftHistory(Page page,String sequence,String phone_number,Integer planId,String status,String plan_name,String idea_name, String bDate, String eDate,String giftTabName){
		Statement stms = stmsFactory.createNativeStatement("select ptg.created_time,ptg.`sequence`,ptg.`phone_number`,ptg.`plan_id`,p.`plan_name`,ptg.`product_value`, ptg.`product_price`,ptg.`state`,idea.idea_name,ptg.id,ptg.city_code ");
		stms.append(" from `" + giftTabName + "` ptg,");
		stms.append(" `admanage_plan` p,`admanage_idea` idea,`user_company` u ");
		stms.append(" where u.`id`=p.`owner` and idea.`id`=ptg.`idea_id`");
		stms.append(" and idea.plan_id=p.`id`");
	
		stms.appendLike(null, "and", "p.plan_name", plan_name);
		stms.appendLike(null, "and", "idea_name", idea_name);
		stms.append("and", "p.`id`", "=", planId);
		stms.append("and", "ptg.`phone_number`", "=", phone_number);
		stms.append("and", "ptg.state", "=", status);
		stms.append("and", "ptg.created_time", ">=", bDate + ":00:00");
		stms.append("and", "ptg.created_time", "<=", eDate + ":59:59");
		stms.addOrderBy(" ptg.id desc");
		hibernateDao.search(stms, page);
	}
	
	public List exportGiftHistory(String phone_number,Integer planId,String status,String plan_name,String idea_name, String bDate, String eDate,String giftTabName){
		Statement stms = stmsFactory.createNativeStatement("select ptg.created_time,ptg.`sequence`,ptg.`phone_number`,ptg.`plan_id`,p.`plan_name`,ptg.`product_value`, ptg.`product_price`,ptg.`state`,idea.idea_name,ptg.id,ptg.city_code,ptg.result,ptg.result_desc,ptg.sequence,ptg.sub_sequence ");
		stms.append(" from `" + giftTabName + "` ptg,");
		stms.append(" `admanage_plan` p,`admanage_idea` idea,`user_company` u ");
		stms.append(" where u.`id`=p.`owner` and idea.`id`=ptg.`idea_id`");
		stms.append(" and idea.plan_id=p.`id`");
		
		
		stms.appendLike(null, "and", "p.plan_name", plan_name);
		stms.appendLike(null, "and", "idea_name", idea_name);
		stms.append("and", "p.`id`", "=", planId);
		stms.append("and", "ptg.`phone_number`", "=", phone_number);
		stms.append("and", "ptg.state", "=", status);
		stms.append("and", "ptg.created_time", ">=", bDate + ":00:00");
		stms.append("and", "ptg.created_time", "<=", eDate + ":59:59");
		stms.addOrderBy(" ptg.id desc");
		return hibernateDao.query(stms,50000);
	}

	public List searchTableName(String tableName){
		Statement stms = stmsFactory.createNativeStatement("select table_name from INFORMATION_SCHEMA.TABLES");
		stms.append(" where TABLE_NAME='" + tableName + "'");

		return hibernateDao.query(stms);
	}
	
	//0: 点击消耗（元），1: 流量消耗（元），2: 点击总额, 3: 流量总额
	public Object[] searchCalculateTu(String group_name, String plan_name, Long company_id) {
		Statement stms = stmsFactory.createNativeStatement("SELECT IFNULL(SUM(rg.money_ad),0) money_ad,IFNULL(SUM(rg.traffic),0) traffic,IFNULL(SUM(g.group_budget_total),0) group_budget_total,IFNULL(SUM(g.group_budget_total_flow),0) group_budget_total_flow ");
		stms.append("FROM admanage_group g,admanage_plan p,user_company uc,user_finance uf,");
		stms.append(
				"(select r.group_id,IFNULL(sum(r.`show`),0) shows,IFNULL(sum(r.`click`),0) click,IFNULL(sum(r.`traffic`),0) traffic,IFNULL(sum(r.`money_ad`),0) money_ad from rep_group_date r group by r.group_id) rg ");
		stms.append("WHERE g.plan_id = p.id AND g.id = rg.group_id AND g.`owner` = uf.uid AND uf.uid = uc.id ");

		stms.append("and", "uc.id", "=", company_id);
		stms.appendLike(null, "and", "p.plan_name", plan_name);
		stms.appendLike(null, "and", "g.group_name", group_name);
		//stms.addGroupBy(" g.id");
		//stms.addOrderBy(" g.id desc");
		return (Object[])hibernateDao.find(stms);
	}
	
	//0: 工单ID，1: 工单，2: 所属广告主，3: 所属合同，4: 点击消耗（元），5: 流量消耗（元），6: 点击总额, 7: 流量总额
	public void searchCalculateSum(Page page, String group_name, String plan_name, Long company_id) {
		Statement stms = stmsFactory.createNativeStatement("SELECT g.id,g.group_name,uc.company_name,p.plan_name,rg.money_ad,rg.traffic,IFNULL(g.group_budget_total,0) group_budget_total,IFNULL(g.group_budget_total_flow,0) group_budget_total_flow ");
		stms.append("FROM admanage_group g,admanage_plan p,user_company uc,user_finance uf,");
		stms.append(
				"(select r.group_id,IFNULL(sum(r.`show`),0) shows,IFNULL(sum(r.`click`),0) click,IFNULL(sum(r.`traffic`),0) traffic,IFNULL(sum(r.`money_ad`),0) money_ad from rep_group_date r group by r.group_id) rg ");
		stms.append("WHERE g.plan_id = p.id AND g.id = rg.group_id AND g.`owner` = uf.uid AND uf.uid = uc.id ");

		stms.append("and", "uc.id", "=", company_id);
		stms.appendLike(null, "and", "p.plan_name", plan_name);
		stms.appendLike(null, "and", "g.group_name", group_name);
		//stms.addGroupBy(" g.id");
		stms.addOrderBy(" g.id desc");
		hibernateDao.search(stms, page);
	}
	
}
