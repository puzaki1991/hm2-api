package com.hm2.bill.controllers;

import com.hm2.bill.entities.Bill;
import com.hm2.bill.repositories.BillRepo;
import com.hm2.common.controllers.BaseController;
import com.hm2.common.exceptions.InvalidRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController extends BaseController {

    @Autowired
    private BillRepo billRepo;

    @GetMapping("/get-bill-active")
    public ResponseEntity<?> getBillActive() {
        try {
            List<Bill> rs = billRepo.findByIsShowOrderByCreatedDateDesc(true);
            return ResponseEntity.ok(rs);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/get-bill-all")
    public ResponseEntity<?> getBillAll() {
        try {
            List<Bill> rs = billRepo.findAllByOrderByCreatedDateDesc();
            return ResponseEntity.ok(rs);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Bill bill) {
        ResponseEntity<?> res = null;
        try {
            if (StringUtils.isBlank(bill.getTitle())) throw new InvalidRequestException("BILL_TITLE00");
            res = ResponseEntity.ok(billRepo.save(bill));
        } catch (InvalidRequestException e) {
            res = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getMessage(e.getMsg()));
        } catch (Exception e) {
            res = ResponseEntity.status(500).body(e.getMessage());
        }
        return res;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        ResponseEntity<?> res = null;
        try {
            billRepo.deleteById(id);
            res = (ResponseEntity<?>) ResponseEntity.ok();
        } catch (Exception e) {
            res = ResponseEntity.status(500).body(e.getMessage());
        }
        ;
        return res;
    }
}
