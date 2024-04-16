package com.app.entity;

import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "debts")
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private User debtor; // User who owes the debt

    @ManyToOne
    @JoinColumn(name = "creditor_id")
    private User creditor; // User to whom the debt is owed

    private BigDecimal amount; // Amount of the debt

}

