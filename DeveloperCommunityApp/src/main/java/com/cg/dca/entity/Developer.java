package com.cg.dca.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Transactional
@Data
@Table(name = "developer")
public class Developer {

//	private Feed feeds;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long devId;
	private String name;
	private String email;
	private String skillLevel;
	private LocalDate memberSince = LocalDate.now();
	private boolean isVerfied;
	private boolean isBlocked;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_devId",referencedColumnName = "devId")
	private List<Feed> feeds;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_devId",referencedColumnName = "devId")
	private List<Response> responses;

	public long getDevId() {
		return devId;
	}

	public void setDevId(long devId) {
		this.devId = devId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public LocalDate getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(LocalDate memberSince) {
		this.memberSince = memberSince;
	}
	public boolean isVerfied() {
		return isVerfied;
	}
	public void setVerfied(boolean isVerfied) {
		this.isVerfied = isVerfied;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}
	
	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	
}
