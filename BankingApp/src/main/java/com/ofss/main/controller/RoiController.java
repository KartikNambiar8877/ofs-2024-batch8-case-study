package com.ofss.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ofss.main.service.RoiServiceImpl;


@RestController
@RequestMapping("/roi")
public class RoiController {

    @Autowired
    private RoiServiceImpl roiServiceImpl;

    @DeleteMapping("/delete/{accountNo}")
    public boolean deleteRoiByAccountNo(@PathVariable int accountNo) {
    	try {
            roiServiceImpl.deletebyAccountNo(accountNo);
            return true;  
        } catch (Exception e) {
        
            return false;
        }
    }
}
