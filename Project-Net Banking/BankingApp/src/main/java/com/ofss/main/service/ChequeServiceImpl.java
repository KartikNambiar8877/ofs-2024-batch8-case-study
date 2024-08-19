package com.ofss.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.domain.Cheques;
import com.ofss.main.repository.ChequesRepository;


@Service
public class ChequeServiceImpl implements ChequeService{
	
	@Autowired
	ChequesRepository chequesRepository;
	
	@Override
	public boolean updateCheque(Cheques cheque) {
	    Optional<Cheques> chq = chequesRepository.findById(cheque.getChequeId());

	    if (chq.isPresent() && cheque.getStatus().toLowerCase()=="not received") {
	        Cheques existingCheque = chq.get();
	        
	        
	        existingCheque.setAmount(cheque.getAmount());
	        existingCheque.setStatus(cheque.getStatus());
	        existingCheque.setPayeeName(cheque.getPayeeName());
	        existingCheque.setChkAccountId(cheque.getChkAccountId());
	        existingCheque.setChkSlipId(cheque.getChkSlipId());
	        
	        
	        chequesRepository.save(existingCheque);
	        
	        return true;
	    } else {
	        throw new RuntimeException("Cheque not found");
	    }
	}

	@Override
    public boolean deleteChequesbyAccountNo(int accountNo) {
        try {
            chequesRepository.deleteChequesbyAccountNo(accountNo);
            return true;  
        } catch (Exception e) {
            return false;
        }
    }


}
