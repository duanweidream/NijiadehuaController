package com.wooboo.dsp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wooboo.dsp.util.NumberUtil;


/**
 * MenuInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_menu_info")
public class MenuInfo   implements Comparable, java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer PId;
	private String owner;
	private String menuHref;
	private String menuName;
	private Integer menuSeq;
	private Integer menuLev;
	private String visitility;
	private String menuDesc;
	private String icoClass;
	private String authCode;
    private List<MenuInfo> list=new ArrayList<MenuInfo>();
    //是否是当前菜单
    private String current;
	// Constructors

    
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="P_ID")    
    @OrderBy(" menuSeq asc")
	public List<MenuInfo> getList() {
    	//Collections.sort(list);
		return list;
	}

	public void setList(List<MenuInfo> list) {
		this.list = list;
	}

    
	/** default constructor */
	public MenuInfo() {
	}

	/** full constructor */
	public MenuInfo(Integer PId, String owner, String menuHref,
			String menuName, Integer menuSeq, Integer menuLev,
			String visitility, String menuDesc) {
		this.PId = PId;
		this.owner = owner;
		this.menuHref = menuHref;
		this.menuName = menuName;
		this.menuSeq = menuSeq;
		this.menuLev = menuLev;
		this.visitility = visitility;
		this.menuDesc = menuDesc;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "P_ID")
	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	@Column(name = "OWNER", length = 15)
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "MENU_HREF", length = 128)
	public String getMenuHref() {
		return this.menuHref;
	}

	public void setMenuHref(String menuHref) {
		this.menuHref = menuHref;
	}

	@Column(name = "MENU_NAME", length = 32)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_SEQ")
	public Integer getMenuSeq() {
		return this.menuSeq;
	}

	public void setMenuSeq(Integer menuSeq) {
		this.menuSeq = menuSeq;
	}

	@Column(name = "MENU_LEV")
	public Integer getMenuLev() {
		return this.menuLev;
	}

	public void setMenuLev(Integer menuLev) {
		this.menuLev = menuLev;
	}

	@Column(name = "VISITILITY", length = 1)
	public String getVisitility() {
		return this.visitility;
	}

	public void setVisitility(String visitility) {
		this.visitility = visitility;
	}

	@Column(name = "MENU_DESC", length = 128)
	public String getMenuDesc() {
		return this.menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	@Column(name = "ico_class", length = 20)
	public String getIcoClass() {
		return icoClass;
	}

	public void setIcoClass(String icoClass) {
		this.icoClass = icoClass;
	}

	@Column(name = "auth_code", length = 128)
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	
	
	@Transient
	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	@Override
	public int compareTo(Object o) {
		MenuInfo m1 = (MenuInfo)o;
		if(NumberUtil.getInt(this.getMenuSeq(), 0)<NumberUtil.getInt(m1.getMenuSeq(), 0)){
			return 1;
		}else{
			return 0;
		}
	}

}