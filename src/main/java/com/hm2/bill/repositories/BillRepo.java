package com.hm2.bill.repositories;

import com.hm2.bill.entities.Bill;
import com.hm2.common.repositories.CommonJpaCrudRepository;

import java.util.List;

public interface BillRepo extends CommonJpaCrudRepository<Bill, Long> {

    List<Bill> findByIsShowOrderByCreatedDateDesc(boolean isShow);
    List<Bill> findAllByOrderByCreatedDateDesc();
}
