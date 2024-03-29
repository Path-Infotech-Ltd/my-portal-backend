package com.exam.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "EMI_HISTORY")
public class Emi {

	@Id
	@NotNull
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eId;
	
	@Column(name = "LOAN_NO")
	private String loanNo;

	@Column(name = "EMI_AMOUNT")
	private BigDecimal emiAmount;

	@Column(name = "INTEREST_AMOUNT")
	private BigDecimal interestAmount;

	@Column(name = "NO_OF_PAYMENT")
	private long noOfPayment;

	@Column(name = "EMI_DATE")
	private LocalDate emiDate;

	@Column(name = "LATE_FINE_CHARGE")
	private BigDecimal lateFineCharge;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "EMI_STATUS")
	private boolean emiStatus;

	@Column(name = "CREATED_BY")
	private String createdBy;

	/// @CreationTimestamp
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	/// @UpdateTimestamp
	@Column(name = "MODIFIED_DATE")
	private LocalDateTime modifiedDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="LOAN_ID")
	private Loan loan;


	public Emi() {
	}

	
	public Long geteId() {
		return eId;
	}

	public void seteId(Long eId) {
		this.eId = eId;
	}



	public BigDecimal getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}

	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public long getNoOfPayment() {
		return noOfPayment;
	}

	public void setNoOfPayment(long noOfPayment) {
		this.noOfPayment = noOfPayment;
	}

	public LocalDate getEmiDate() {
		return emiDate;
	}

	public void setEmiDate(LocalDate emiDate) {
		this.emiDate = emiDate;
	}

	public BigDecimal getLateFineCharge() {
		return lateFineCharge;
	}

	public void setLateFineCharge(BigDecimal lateFineCharge) {
		this.lateFineCharge = lateFineCharge;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public Loan getLoan() {
		return loan;
	}


	public void setLoan(Loan loan) {
		this.loan = loan;
	}


	public String getLoanNo() {
		return loanNo;
	}


	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}


	public boolean isEmiStatus() {
		return emiStatus;
	}


	public void setEmiStatus(boolean emiStatus) {
		this.emiStatus = emiStatus;
	}

	


}
