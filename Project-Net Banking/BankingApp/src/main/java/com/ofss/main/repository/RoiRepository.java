package com.ofss.main.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ofss.main.domain.Roi;

public interface RoiRepository extends CrudRepository<Roi, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Roi r WHERE r.roiAccountNo = ?1")
    void deleteByAccountNo(int accountNo);
}
