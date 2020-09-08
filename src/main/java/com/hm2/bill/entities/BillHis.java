package com.hm2.bill.entities;

import com.hm2.common.entities.BaseEntity;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "BILL_HISTORY")
@Data
@AttributeOverride(name = "id", column = @Column(name = "BILL_HISTORY_ID", nullable = false))
public class BillHis extends BaseEntity {
    @Column(length = 2000)
    private String description;
    @Column(precision = 7, scale = 2)
    private BigDecimal amount;
    @Column(length = 300)
    private String title;
    @Column(length = 5)
    private String type; // INVEST, PAY, INCOME
    private Date payDate;
}
