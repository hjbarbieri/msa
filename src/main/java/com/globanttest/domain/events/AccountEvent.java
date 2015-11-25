package com.globanttest.domain.events;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountEvent implements Serializable{

	private static final long serialVersionUID = 1L;
	private BigDecimal amount;
	private Long accountId;
	private AccountEventType accountType;

	public AccountEvent(BigDecimal amount, Long accountId,
			AccountEventType accountType) {
		this.accountId = accountId;
		this.amount = amount;
		this.accountType = accountType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Long getAccountId() {
		return accountId;
	}

	public AccountEventType getAccountType() {
		return accountType;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result
				+ ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountEvent other = (AccountEvent) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (accountType != other.accountType)
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountEvent [amount=" + amount + ", accountId="
				+ accountId + ", accountType=" + accountType + "]";
	}
}
