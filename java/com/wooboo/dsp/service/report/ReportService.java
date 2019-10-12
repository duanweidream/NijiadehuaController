package com.wooboo.dsp.service.report;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wooboo.dsp.dao.report.ReportDao;
import com.wooboo.dsp.service.base.BaseService;
import com.wooboo.dsp.system.util.Page.Page;

@Service
public class ReportService extends BaseService {

	@Autowired
	private ReportDao reportDao;


	public Object[] queryReportUserDateTotal(Date beginDate,Date endDate,Integer advertiserId){
		return reportDao.queryReportUserDateTotal(beginDate, endDate, advertiserId);
	}
	public Object[] queryReportPlanDateTotal(Date beginDate,Date endDate,Integer type,Integer planId, Integer userCompanyId){
		return reportDao.queryReportPlanDateTotal(beginDate, endDate,type,planId, userCompanyId);
	}
	public Object[] queryReportGroupDateTotal(Date beginDate,Date endDate,Integer channelId,Integer type,Integer groupId,Integer userCompanyId){
		return reportDao.queryReportGroupDateTotal(beginDate, endDate,channelId,type,groupId, userCompanyId);
	}
	public List<Object[]>  queryReportUserDate(Date beginDate,Date endDate){
		return reportDao.queryReportUserDate(beginDate, endDate);
	}
	public void searchCalculateSum(Page page,String group_name, String plan_name, Long company_id){
		reportDao.searchCalculateSum(page, group_name, plan_name, company_id);
	}
	public Object[] searchCalculateTu(String group_name, String plan_name, Long company_id){
		return reportDao.searchCalculateTu(group_name, plan_name, company_id);
	}
	
	
	public void searchUserDateReport(Page page,Date beginDate,Date endDate,Integer advertiserId){
		reportDao.searchUserDateReport(page, beginDate, endDate,advertiserId);
		
	}

	public void searchPlanDateReport(Page page,Date beginDate,Date endDate,Integer type,Integer planId,Integer userCompanyId){
		reportDao.searchPlanDateReport(page, beginDate, endDate,type,planId,userCompanyId);
		
	}
	public void searchGroupDateReport(Page page,Date beginDate,Date endDate,Integer channelId,Integer type,Integer groupId,Integer userCompanyId){
		reportDao.searchGroupDateReport(page, beginDate, endDate,channelId,type,groupId, userCompanyId);
	}
	
	public void searchPhoneGift(Page page,String sequence,String phone_number,Integer planId,String status,String plan_name,String idea_name,String beginDate,String endDate){
		reportDao.searchPhoneGift(page,sequence, phone_number, planId, status,plan_name,idea_name,beginDate,endDate);
	}
	
	/**
	 * 查询流量领取记录
	 * 0:时间 1：流水 2：电话 3：计划id 4:名称 5:流量  6：金额  7：状态
	 * */
	public List searchPhoneGift(String sequence,String phone_number,Integer planId,String status,String plan_name,String idea_name,String beginDate,String endDate,String month){
		return reportDao.searchPhoneGift(sequence, phone_number, planId, status, plan_name, idea_name, beginDate, endDate,month);
	}
	
	/**
	 * 0:id 1:时间 2：归属地市code 3：领取次数 4：成功次数 5：失败次数 6：消耗流量 7：消耗金额 8：合同 9：素材
	 */
	public void searchPhoneDaily(Page page, String city_code, Integer planId, String plan_name, String idea_name, String beginDate, String endDate){
		reportDao.searchPhoneDaily(page, city_code, planId, plan_name, idea_name, beginDate, endDate);
	}
	
	public List exportPhoneDaily(String city_code, Integer planId, String plan_name, String idea_name, String beginDate, String endDate){
		return reportDao.exportPhoneDaily(city_code, planId, plan_name, idea_name, beginDate, endDate);
	}

	public List sumPhoneDaily(String city_code, Integer planId, String plan_name, String idea_name, String beginDate, String endDate) {
		return reportDao.sumPhoneDaily(city_code, planId, plan_name, idea_name, beginDate, endDate);
	}

	public List searchPhoneDailyCityList() {
		return reportDao.loadCityList();
	}

	public List searchGiftYearAndMonList(){
		return reportDao.searchGiftYearAndMonList();
	}

	public List searchTableName(String tableName){
		return reportDao.searchTableName(tableName);
	}

	public void searchGiftHistory(Page page,String sequence,String phone_number,Integer planId,String status,String plan_name,String idea_name,String bDate, String eDate,String giftTabName){
		reportDao.searchGiftHistory(page,sequence, phone_number, planId, status,plan_name,idea_name, bDate, eDate,giftTabName);
	}

	public List exportGiftHistory(String phone_number,Integer planId,String status,String plan_name,String idea_name,String bDate, String eDate,String giftTabName){
		return reportDao.exportGiftHistory(phone_number, planId, status,plan_name,idea_name, bDate, eDate,giftTabName);
	}
	
	public Object[] queryReportIdeaDateTotal(Date beginDate,Date endDate,Integer channelId,Integer type,Integer ideaId, Long groupId, Integer userCompanyId){
		return reportDao.queryReportIdeaDateTotal(beginDate, endDate,channelId,type,ideaId, groupId, userCompanyId);
	}
	
	public void searchIdeaDateReport(Page page,Date beginDate,Date endDate,Integer channelId,Integer type,Integer ideaId, Long groupId, Integer userCompanyId){
		reportDao.searchIdeaDateReport(page, beginDate, endDate,channelId,type,ideaId, groupId, userCompanyId);
	}
	
}
