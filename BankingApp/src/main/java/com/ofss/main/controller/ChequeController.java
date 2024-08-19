package com.ofss.main.controller;

import com.ofss.main.domain.Cheques;
import com.ofss.main.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cheques")
public class ChequeController {

    @Autowired
    ChequeService chequeService;

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCheque(@PathVariable Integer id, @RequestBody Cheques cheque) {
        cheque.setChequeId(id); 
        boolean updated = chequeService.updateCheque(cheque);
        if (updated) {
            return ResponseEntity.ok("Cheque updated successfully");
        } else {
            return ResponseEntity.status(404).body("Cheque not found or update failed");
        }
    }


    @DeleteMapping("/delete/{accountNo}")
    public ResponseEntity<String> deleteChequesByAccountNo(@PathVariable int accountNo) {
        boolean deleted = chequeService.deleteChequesbyAccountNo(accountNo);
        if (deleted) {
            return ResponseEntity.ok("Cheques deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Cheques not found or delete failed");
        }
    }
}
