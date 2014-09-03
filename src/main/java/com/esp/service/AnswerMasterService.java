package com.esp.service;

import java.util.List;

import com.esp.entity.Answermaster;

public interface AnswerMasterService {
	
	public void addAnswerMaster(Answermaster answerMaster);
	
	public List<Answermaster> listAnswerMaster(int ansId);

}
