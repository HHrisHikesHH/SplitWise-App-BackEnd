package com.app.DTO;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.entity.Debt;
import com.app.entity.User;

import lombok.Data;

@Data
public class DebtDTO {
	
		private Integer debtorId; // for ease of finding

	    private String debtorName; // User who owes the debt
	    
	    private Integer creditorId; //  // for ease of finding

	    private String creditorName; // User to whom the debt is owed

	    private BigDecimal amount; // Amount of the debt

}
