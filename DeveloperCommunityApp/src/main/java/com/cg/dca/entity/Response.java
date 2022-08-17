package com.cg.dca.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.transaction.Transactional;

import lombok.Data;

@Entity
@Transactional
@Data
@Table(name = "responses")
public class Response {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long respId;
	private String answer;
	private LocalDate respDate = LocalDate.now();
	private LocalTime respTime = LocalTime.now();
	private int accuracy;

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
}
