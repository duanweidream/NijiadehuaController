package com.wooboo.dsp.dao.system;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.base.enums.Status.ProcessType;
import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.AuthInfo;
import com.wooboo.dsp.model.MenuInfo;
import com.wooboo.dsp.model.RoleInfo;
import com.wooboo.dsp.model.SysCityInfo;
import com.wooboo.dsp.model.SysDepartMent;
import com.wooboo.dsp.model.SysLogger;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class SystemDao extends BaseDao {

	
	public SysUser findSysUserByName(String userName,String password){
		Statement stms = stmsFactory.createStatement("select u from SysUser u where u.userName=? and u.password=?");
		stms.addParams(userName,password);
		return hibernateDao.find(stms, SysUser.class);
	}
	
	public List<SysUser> getListUser(){
		Statement stms = stmsFactory.createStatement(" select u from SysUser u ");
		return (List<SysUser>)hibernateDao.query(stms);
	}
	
	
	public SysUser findSysUserByName(String userName){
		Statement stms = stmsFactory.createStatement("select u from SysUser u where u.userName=?");
		stms.addParams(userName);
		return hibernateDao.find(stms, SysUser.class);
	}
	
	public List<MenuInfo> queryRootMenu(){
        Statement stms = stmsFactory.createStatement("from MenuInfo m  where m.PId is null");
                  stms.addOrderBy(" m.menuSeq asc");
        return hibernateDao.query(stms);
	}
	public List<AuthInfo> queryRootAuth(){
        Statement stms = stmsFactory.createStatement("from AuthInfo au where au.pid is null");
                  stms.addOrderBy(" au.id desc");
        return hibernateDao.query(stms);
	}
	public List<RoleInfo> queryRoleInfo(){
        Statement stms = stmsFactory.createStatement("from RoleInfo r where 1=1");
                  stms.addOrderBy(" r.id desc");
        return hibernateDao.query(stms);
	}
	
	public void searchRoleInfo(Page page,RoleInfo role){
        Statement stms = stmsFactory.createStatement("from RoleInfo r where 1=1 and (isPlay<>0 or isPlay is null)");
			      if(null!=role){
			         stms.appendLike(null, "and", "r.roleName", role.getRoleName());
			      }
                  stms.addOrderBy(" r.id desc");
        hibernateDao.search(stms, page);
	}
	
	
	public List<MenuInfo> queryMenuInfoByRoleId(String visitility,Integer roleId){
		//Statement stms = stmsFactory.createStatement("select from MenuInfo where id in (select pm.menuId from RoleMenu pm where pm.roleId=?)");
		Statement stms = stmsFactory.createStatement("select m from MenuInfo m ,RoleMenu rm where m.id=rm.menuId and rm.roleId=?");
		stms.addParam(roleId);
		stms.append("and", "m.visitility", "=", visitility);
        stms.addOrderBy(" m.menuSeq desc");
        return hibernateDao.query(stms);
	}
	
	
	public void deleteRoleMenuByRoleId(Integer roleId){
		Statement stms = stmsFactory.createNativeStatement("DELETE rm FROM sys_role_menu rm WHERE rm.ROLE_ID=?");
        stms.addParam(roleId);
        hibernateDao.update(stms);
	}
	public List<AuthInfo> queryAuthInfoByRoleId(Integer roleId){
		Statement stms = stmsFactory.createStatement("from AuthInfo where id in (select pm.authId from RoleAuth pm where pm.roleId=?)");
		stms.addParam(roleId);
		return hibernateDao.query(stms);
	}
	public void deleteRoleAuthByRoleId(Integer roleId){
		Statement stms = stmsFactory.createNativeStatement("DELETE ra FROM sys_role_auth ra WHERE ra.ROLE_ID=?");
        stms.addParam(roleId);
        hibernateDao.update(stms);
	}
	
	public void searchConstants(String name,String pcode,String display,Page page){
		Statement stms = stmsFactory.createStatement("from Constant where 1=1");
		          stms.append("and", "pcode", "=", pcode);
		          stms.append("and", "display", "=", display);
		          stms.appendLike(null,"and", "contsName", name);
		          stms.addOrderBy("sort desc");
		          hibernateDao.search(stms, page);
	}
	
	 public void searchSysuser(Page page,SysUser user, Integer roleId){
		 Statement stms = stmsFactory.createStatement(" from SysUser su where 1=1");
		           if(null!=user){
		               stms.appendLike(null, "and", "su.userName", user.getUserName()); 
		               stms.appendLike(null, "and", "su.nickName", user.getNickName());
		               stms.append("and", "su.roleInfo.roleId", "=", roleId);
		               //stms.append("and", "su.roleInfo=", roleId);
		           }
		           
		       stms.addOrderBy(" su.id desc");
		       hibernateDao.search(stms, page);
	 }
	 public SysUser loadSysuserByUserName(String userName){
		 Statement stms = stmsFactory.createStatement(" from SysUser su where su.userName=?");
		           stms.addParam(userName);
		 return hibernateDao.find(stms,SysUser.class);
	 }
	 
	 
	 public List<SysLogger> querySysLogger(Long businessId,String businessCode,Integer size){
		 Statement stms = stmsFactory.createStatement(" from SysLogger log where log.businessId=? and log.businessCode=?");
		           stms.addParams(businessId,businessCode);
		           stms.addOrderBy("log.id desc");
		 return hibernateDao.query(stms, 10);
	 }
	 
	 public void searchSysLogger(Page page,String operateName,Date beginDate,Date endDate){
		 Statement stms = stmsFactory.createStatement(" from SysLogger log where 1=1");
  	               stms.appendBetween("and", "log.createTime", beginDate, endDate);
  	               stms.appendLike(null, "and", "log.operateName", operateName);
		           stms.addOrderBy("log.id desc");
		           hibernateDao.search(stms, page);
	 }

	 public List<SysDepartMent> queryRootDepartMent(){
		 Statement stms = stmsFactory.createStatement("from SysDepartMent p where p.parentId=0");
	      
           stms.addOrderBy(" p.id desc");
	        return hibernateDao.query(stms);
	}
	
	 
	 public List<SysDepartMent> queryChildDepartMentByParentId(Integer parentId){
		 Statement stms = stmsFactory.createStatement("from SysDepartMent p where p.parentId=? ");
	      stms.addParam(parentId);
       stms.addOrderBy(" p.id desc");
        return hibernateDao.query(stms);
	}
	 
	 
	 public List<SysDepartMent> queryDepartMent(){
		 Statement stms = stmsFactory.createStatement("from SysDepartMent p where p.parentId=0");
         stms.addOrderBy(" p.id desc");
         return hibernateDao.query(stms);
	 }
	 
	 
	 
	 
	 public List<SysCityInfo> queryCityInfoByCode(String code){
		 Statement stms = stmsFactory.createStatement(" from SysCityInfo c where c.parentCode=? ");
		           stms.addParam(code);
		           //stms.addOrderBy(" ");
	     return hibernateDao.query(stms);
	 }
	 
	 public List<SysCityInfo> queryCityInfoByCode2(String code){
		 Statement stms = stmsFactory.createStatement(" from SysCityInfo c where c.parentCode=? or city_code = ? ");
		           stms.addParam(code);
		           stms.addParam(code);
		           //stms.addOrderBy(" ");
	     return hibernateDao.query(stms);
	 }
	 
	 
	 
	/**
	 * 角色广告主审核，待办事项
	 */
	public int getNotHandledItem() {
		Statement stms = stmsFactory.createNativeStatement(" select sum(1) as n from user_recharge_order where order_status in (1,2) and next_audit_role_id = ? ");
		stms.addParam(SessionHelper.getRoleId());
		Object o = hibernateDao.find(stms);
		return o == null ? 0 : Integer.valueOf(o.toString());
	}
	
	public List<SysUser> querySysUserByRoleId(Integer roleId){
	     Statement stms = stmsFactory.createStatement(" from SysUser u where u.roleInfo.roleId=? and u.state=?");	
	               stms.addParam(roleId);
	               stms.addParam(ATTSystem.LOGIC_TRUE);
	     return hibernateDao.query(stms);
	}
	
	/**
	 * 查询会签接口人&地市接口人
	 * @param bid
	 * @param partId
	 * @param process
	 * @param keywords
	 * @return
	 */
	public List<SysUser> queryProcessCitySysUserByPartId(Long bid , Integer partId,ProcessType process,String keywords){
	     Statement stms = stmsFactory.createStatement(" select b from RoleInfo a,SysUser b,ProcessAuditInfo c where a.partId = ? and a.roleId = b.roleInfo.roleId and b.state = ? and a.roleId = c.step_audit_role_id and c.process_type = ? and c.bid = ? ");	
	               stms.addParam(partId);
	               stms.addParam(ATTSystem.LOGIC_TRUE);
	               
	               if(process == ProcessType.company){
	            	   stms.addParam(process.company.toString());
	               }else if(process == ProcessType.advertiser){
	            	   stms.addParam(process.advertiser.toString());
	               }else if(process == ProcessType.traffic){
	            	   stms.addParam(process.traffic.toString());
	               }else if(process == ProcessType.wechat){
	            	   stms.addParam(process.wechat.toString());
	               }
	               stms.addParam(bid);
	               stms.appendLike(null,"and","a.roleName",keywords);
	     return hibernateDao.query(stms);
	}
	
	/**
	 * 查询会签接口人&地市接口人
	 * @param bid
	 * @param partId
	 * @param process
	 * @param keywords
	 * @return
	 */
	public List<SysUser> queryProcessCitySysUserByPartId(Integer partId,String keywords){
	     Statement stms = stmsFactory.createStatement(" select b from RoleInfo a,SysUser b where a.partId = ? and a.roleId = b.roleInfo.roleId and b.state = ? ");	
	               stms.addParam(partId);
	               stms.addParam(ATTSystem.LOGIC_TRUE);
	               stms.appendLike(null,"and","a.roleName",keywords);
	     return hibernateDao.query(stms);
	}
	
	
	/**
	 * 查询第一审核人
	 * @param bid
	 * @param partId
	 * @param process
	 * @return
	 */
	public List<SysUser> queryProcessFirstSysUserByPartId(Long bid , Integer partId,ProcessType process){
	     Statement stms = stmsFactory.createStatement(" select b from RoleInfo a,SysUser b,ProcessAuditInfo c where a.partId = ? and a.roleId = b.roleInfo.roleId and b.state = ? and a.roleId = c.step_audit_role_id and c.process_type = ? and c.bid = ? and c.step = 1 ");	
	               stms.addParam(partId);
	               stms.addParam(ATTSystem.LOGIC_TRUE);
	               
	               if(process == ProcessType.company){
	            	   stms.addParam(process.company.toString());
	               }else if(process == ProcessType.advertiser){
	            	   stms.addParam(process.advertiser.toString());
	               }else if(process == ProcessType.traffic){
	            	   stms.addParam(process.traffic.toString());
	               }
	               stms.addParam(bid);
	              
	     return hibernateDao.query(stms);
	}
	
	public List<SysUser> querySuperSysUser(){
	     Statement stms = stmsFactory.createStatement(" select b from RoleInfo a,SysUser b where a.roleCode = 'super' and a.roleId = b.roleInfo.roleId and b.state = ? ");	
	               stms.addParam(ATTSystem.LOGIC_TRUE);
	               
	     return hibernateDao.query(stms);
	}
	
	public List<SysUser> querySysUserByEmail(String email){
	     Statement stms = stmsFactory.createStatement(" from SysUser where email = ? and b.state = ? ");	
	     stms.addParam(email);          
	     stms.addParam(ATTSystem.LOGIC_TRUE);
	               
	     return hibernateDao.query(stms);
	}

	public void deleteRoleById(Integer id) {
		Statement stms = stmsFactory.createNativeStatement("UPDATE sys_role_info SET is_play=0 WHERE role_id=?");
        stms.addParam(id);
        hibernateDao.update(stms);
	}

	public List<SysUser> getUsersByroleId(Integer id) {
		Statement stms = stmsFactory.createStatement(" from SysUser where roleInfo.roleId=?") ;
		stms.addParam(id) ;
		
		return hibernateDao.query(stms);
	}
	
	
}
