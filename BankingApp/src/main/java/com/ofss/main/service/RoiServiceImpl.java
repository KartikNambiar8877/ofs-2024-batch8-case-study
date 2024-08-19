package com.ofss.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.repository.RoiRepository;

@Service
public class RoiServiceImpl implements RoiService {
    
    @Autowired
    private RoiRepository roiRepository;

    @Override
    public boolean deletebyAccountNo(int accountNo) {
        try {
            roiRepository.deleteByAccountNo(accountNo);
            return true;  
        } catch (Exception e) {
            return false;
        }
    }
}
