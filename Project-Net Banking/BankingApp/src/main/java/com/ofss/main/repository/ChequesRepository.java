package com.ofss.main.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ofss.main.domain.Cheques;

public interface ChequesRepository extends CrudRepository<Cheques, Integer> {
	
	@Modifying
    @Transactional
    @Query("DELETE FROM Cheques c WHERE c.chkAccountId = ?1")
	void deleteChequesbyAccountNo(int accountNo);

}
