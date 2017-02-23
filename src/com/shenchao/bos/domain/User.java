package com.shenchao.bos.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String id;
	private String username;
	private String password;
	private Date birthday;
	private String gender;
	private String station;
	private String telephone;
	private String remark;
	private Set<Role> roles = new HashSet(0);
	private Set<Noticebill> noticebills = new HashSet(0);

	public String getFormateBirthday(){
		if (birthday == null) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(birthday);
	}

	// Constructors

	/** default constructor */
	public User() {
	}

	public String getRoleNames(){
		String names = "";
		for (Role role : roles) {
			names += role.getName() + " ";
		}
		return names;
	}

	/** minimal constructor */
	public User(String id) {
		this.id = id;
	}

	/** full constructor */
	public User(String id, String username, String password, Date birthday,
			String gender, String station, String telephone, String remark,
			Set roles, Set noticebills) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.station = station;
		this.telephone = telephone;
		this.remark = remark;
		this.roles = roles;
		this.noticebills = noticebills;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Noticebill> getNoticebills() {
		return noticebills;
	}

	public void setNoticebills(Set<Noticebill> noticebills) {
		this.noticebills = noticebills;
	}
}