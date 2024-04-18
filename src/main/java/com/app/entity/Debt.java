package com.app.entity;

import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Debt other = (Debt) obj;
		return Objects.equals(creditor, other.creditor) && Objects.equals(debtor, other.debtor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(creditor, debtor);
	}

}

