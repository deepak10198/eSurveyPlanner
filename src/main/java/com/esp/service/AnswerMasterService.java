package com.esp.service;

import java.util.List;

import com.esp.entity.AnswerMaster;

public interface AnswerMasterService {
	
	public void addAnswerMaster(AnswerMaster answerMaster);
	
	public List<AnswerMaster> listAnswerMaster(int ansId);

}
