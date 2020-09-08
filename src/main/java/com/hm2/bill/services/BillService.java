package com.hm2.bill.services;

import com.hm2.bill.entities.Bill;
import com.hm2.bill.entities.BillHis;
import com.hm2.bill.repositories.BillHisRepo;
import com.hm2.bill.repositories.BillRepo;
import com.hm2.common.persistencecustom.persistence.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class BillService extends BaseService {


    @Autowired
    private BillHisRepo billHisRepo;

    @Autowired
    private BillRepo billRepo;

    @Transactional
    public void pay(long billId) {

        //TODO find bill by id
        Optional<Bill> billOptional = billRepo.findById(billId);
        if (billOptional.isPresent()) {
            Bill bill = billOptional.get();

            //TODO set and save bill history
            BillHis billHis = new BillHis();
            billHis.setAmount(bill.getAmount());
            billHis.setTitle(bill.getTitle());
            billHis.setDescription(bill.getDescription());
            billHis.setPayDate(new Date());
            billHis.setType(bill.getType());
            billHis = billHisRepo.save(billHis);

            //TODO Update isShow bill
            bill.setShow(false);
            billRepo.save(bill);

        }
    }
}
