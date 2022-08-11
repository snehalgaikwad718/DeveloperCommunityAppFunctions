package com.cg.dca.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "responses")
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long respId;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "responseDate")
	private LocalDate respDate = LocalDate.now();
	
	@Column(name = "responseTime")
	private LocalTime respTime = LocalTime.now();
	
	@Column(name = "responseAccuracy")
	private int accuracy;
	
	
//	private Developer dev;
//	private Feed feed;
	
	public Response(String answer, LocalDate respDate, LocalTime respTime, int accuracy) {
		super();
		this.answer = answer;
		this.respDate = respDate;
		this.respTime = respTime;
		this.accuracy = accuracy;
	}

	public Response() {
	}

	public long getRespId() {
		return respId;
	}

	public void setRespId(long respId) {
		this.respId = respId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

	public LocalDate getRespDate() {
		return respDate;
	}

	public void setRespDate(LocalDate respDate) {
		this.respDate = respDate;
	}

	public LocalTime getRespTime() {
		return respTime;
	}

	public void setRespTime(LocalTime respTime) {
		this.respTime = respTime;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	@Override
	public String toString() {
		return "Response [respId=" + respId + ", answer=" + answer + ", respDate=" + respDate + ", respTime=" + respTime
				+ ", accuracy=" + accuracy + "]";
	}

	
	
	
}
