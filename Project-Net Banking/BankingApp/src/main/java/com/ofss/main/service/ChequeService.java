package com.ofss.main.service;

import com.ofss.main.domain.Cheques;

public interface ChequeService {
	
	public boolean updateCheque(Cheques cheque);
	
	public boolean deleteChequesbyAccountNo(int accountNo);
	
}
