package com.exam.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exam.constant.ExceptionConstant;
import com.exam.constant.StatusConstant;
import com.exam.dto.LoanRequest;
import com.exam.dto.LoanResponse;
import com.exam.model.Loan;
import com.exam.model.LoanTypes;
import com.exam.repository.LoanRepository;
import com.exam.repository.LoanTypeRepository;
import com.exam.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private	LoanTypeRepository loanTypeRepository;

//	@Autowired
//	private EmiRepository emiRepository;

	@Override
	public LoanResponse addNewLoan(LoanRequest loanRequest)
			throws UnsupportedOperationException, URISyntaxException, IOException {

		LoanResponse loanResponse = new LoanResponse();
		Loan loan = new Loan();
		Long loanId = 0L;
		String bankId = loanRequest.getBank();
		try {

			loan.setLoanNo(loanRequest.getLoanNo());
			loan.setLoanType(loanRequest.getLoanType());
			loan.setDisbursalDate(loanRequest.getDisbursalDate());
			loan.setEmiAmount(loanRequest.getEmiAmount() != null ? loanRequest.getEmiAmount() : new BigDecimal("0.0"));
			loan.setEmiPaid(loanRequest.getEmiPaid());
			loan.setEmiRemaining(loanRequest.getEmiRemaining());
			loan.setFirstEmiDate(loanRequest.getFirstEmiDate());
			loan.setInterestPaid(
					loanRequest.getInterestPaid() != null ? loanRequest.getInterestPaid() : new BigDecimal("0.0"));
			loan.setInterestType(loanRequest.getInterestType());
			loan.setLastEmiDate(loanRequest.getLastEmiDate());
			loan.setLoanAmount(
					loanRequest.getLoanAmount() != null ? loanRequest.getLoanAmount() : new BigDecimal("0.0"));

			switch (bankId) {

			case "1":
				loan.setLogoName("axis_logo.png");
				break;

			case "8":
				loan.setLogoName("hdfc_logo.jpg");
				break;

			case "9":
				loan.setLogoName("icici_logo.png");
				break;

			case "15":
				loan.setLogoName("kotak_logo.png");
				break;

			case "32":
				loan.setLogoName("SBI-Logo.png");
				break;

			case "35":
				loan.setLogoName("bajaj_logo.png");
				break;

			case "36":
				loan.setLogoName("personal_logo.jpg");
				break;

			case "37":
				loan.setLogoName("ZestLogo.png");
				break;

			default:
				loan.setLogoName("marc.jpg");

			}
			loan.setBank(loanRequest.getBank());
			loan.setInterestRate(loanRequest.getInterestRate());
			loan.setLoanStatus(loanRequest.isLoanStatus());
			loan.setTotalEmi(loanRequest.getTotalEmi());
			loan.setStatus(StatusConstant.STATUS_ACTIVE);
			loan.setCreatedBy("sunilkmr5775");
			loan.setCreatedDate(LocalDateTime.now());
			loan.setModifiedBy(null);
			loan.setModifiedDate(null);
			loanId = loanRepository.save(loan).getLoanId();
			try {
				if (loanId > 0) {
					loanResponse.setStatus(StatusConstant.STATUS_SUCCESS);
					loanResponse.setLoanNo(loanRequest.getLoanNo());
					loanResponse.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
					loanResponse.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);

					return loanResponse;
				}
			} catch (Exception e) {
				loanResponse.setStatus(StatusConstant.STATUS_FAILURE);
				loanResponse.setLoanNo(loanRequest.getLoanNo());
				loanResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
				loanResponse.setErrorDescription(e.getMessage());
				return loanResponse;
			}
		} catch (Exception ex) {
			loanResponse.setStatus(StatusConstant.STATUS_FAILURE);
			loanResponse.setLoanNo(loanRequest.getLoanNo());
			loanResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
			loanResponse.setErrorDescription(ex.getMessage());
			return loanResponse;

		}
		return loanResponse;
	}

	public Loan updateLoan(Loan loan) {
		return this.loanRepository.save(loan);
	}

	@Override
	public List<Loan> getAllLoans() {
		return new ArrayList<>(this.loanRepository.findAllByLoanStatus(true));
//		return new ArrayList<>(this.loanRepository.findAllByLoanStatus(Sort.by(Sort.Direction.ASC, "bankName")));
	}

	@Override
	public List<Loan> getAllActiveLoans() {
		return new ArrayList<>(this.loanRepository.findByStatus(StatusConstant.STATUS_ACTIVE));
	}

	@Override
	public Loan getLoan(Long loanId) {
		Loan loan = this.loanRepository.findById(loanId).get();
		return loan;
	}

	@Override
	public void deleteLoan(Long loanId) {
		/*
		 * Loan loan = new Loan(); loan.setLoanId(loanId);
		 * this.loanRepository.delete(loan);
		 */
		
		Loan loan = this.loanRepository.findById(loanId).get();
		loan.setLoanStatus(false);
		loan.setStatus(StatusConstant.STATUS_CLOSED);
		this.loanRepository.save(loan);

	}
	@Override
	public void deleteByLoanNo(String loanNo) {
		Loan loan = new Loan();
		loan.setLoanNo(loanNo);
		this.loanRepository.deleteByLoanNo(loan);

	}

	@Override
	public Set<Loan> getLoansByStatus(String loanStatus) {
		if ("ALL".equalsIgnoreCase(loanStatus)) {
			return new LinkedHashSet<>(this.loanRepository.findAllByOrderByStatusAsc());
		} else {
			return new LinkedHashSet<>(this.loanRepository.findByStatus(loanStatus));
		}
	}

	@Override
	public List<Loan> findAllLoanStoredProcedure(String loanNo, String loanStatus, String bankName) {
		// TODO Auto-generated method stub
		ArrayList<Loan> result = this.loanRepository.findAllLoanStoredProcedure(loanNo, loanStatus, bankName);
		 return result;
	}

	@Override
	public List<LoanTypes> getAllLoanTypes() {

		return new ArrayList<>(loanTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "loanType")));
	}

}
