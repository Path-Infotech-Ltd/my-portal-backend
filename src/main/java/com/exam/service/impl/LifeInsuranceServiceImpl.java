package com.exam.service.impl;

import com.exam.constant.ExceptionConstant;
import com.exam.constant.StatusConstant;
import com.exam.dto.LifeInsuranceRequest;
import com.exam.dto.LifeInsuranceResponse;
import com.exam.model.LifeInsurance;
import com.exam.repository.LifeInsuranceRepository;
import com.exam.service.LifeInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LifeInsuranceServiceImpl implements LifeInsuranceService {

	@Autowired
	private LifeInsuranceRepository lifeInsuranceRepository;
	

	@Override
	public LifeInsuranceResponse addPolicy(LifeInsuranceRequest lifeInsuranceRequest)
			throws UnsupportedOperationException, URISyntaxException, IOException {

		LifeInsuranceResponse lifeInsuranceResponse = new LifeInsuranceResponse();

		LifeInsurance lifeInsurance = new LifeInsurance();
		Long policyId = 0L;
		String bankId = lifeInsuranceRequest.getBank();

		try {

			lifeInsurance.setPolicyNo(lifeInsuranceRequest.getPolicyNo());
			lifeInsurance.setPlanName(lifeInsuranceRequest.getPlanName());
			lifeInsurance.setSumAssured(lifeInsuranceRequest.getSumAssured() != null
					? lifeInsuranceRequest.getSumAssured() : new BigDecimal("0.0"));
			lifeInsurance.setCommencementDate(lifeInsuranceRequest.getCommencementDate());
			lifeInsurance.setLastPaymentDate(lifeInsuranceRequest.getLastPaymentDate());
			lifeInsurance.setMaturityDate(lifeInsuranceRequest.getMaturityDate());
			lifeInsurance.setPremium(lifeInsuranceRequest.getPremium());
			lifeInsurance.setBank(lifeInsuranceRequest.getBank());
			lifeInsurance.setDueDateMode(lifeInsuranceRequest.getDueDateMode());
			lifeInsurance.setNominee(lifeInsuranceRequest.getNominee());
			lifeInsurance.setPolicyTerm(lifeInsuranceRequest.getPolicyTerm());
			lifeInsurance.setPremiumPayingTerm(lifeInsuranceRequest.getPremiumPayingTerm());
			lifeInsurance.setPremiumsPaid(lifeInsuranceRequest.getPremiumsPaid());
			lifeInsurance.setPremiumsRemaining(lifeInsuranceRequest.getPremiumsRemaining());
			lifeInsurance.setImgPath(lifeInsuranceRequest.getImgPath());
			lifeInsurance.setHtmlColor1(lifeInsuranceRequest.getHtmlColor1());
			lifeInsurance.setHtmlColor2(lifeInsuranceRequest.getHtmlColor2());
			lifeInsurance.setHexColor(lifeInsuranceRequest.getHexColor());

			lifeInsurance.setStatus(lifeInsuranceRequest.isStatus());
			lifeInsurance.setCreatedBy("sunilkmr5775");
			lifeInsurance.setCreatedDate(LocalDateTime.now());
			lifeInsurance.setModifiedBy(null);
			lifeInsurance.setModifiedDate(null);
			policyId = lifeInsuranceRepository.save(lifeInsurance).getPolicyId();
			try {
				if (policyId > 0) {
					lifeInsuranceResponse.setStatus(StatusConstant.STATUS_SUCCESS);
					lifeInsuranceResponse.setPolicyNo(lifeInsuranceRequest.getPolicyNo());
					lifeInsuranceResponse.setErrorCode(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_EC);
					lifeInsuranceResponse.setErrorDescription(ExceptionConstant.DATA_SAVED_SUCCESSFULLY_ED);

					return lifeInsuranceResponse;
				}
			} catch (Exception e) {
				lifeInsuranceResponse.setStatus(StatusConstant.STATUS_FAILURE);
				lifeInsuranceResponse.setPolicyNo(lifeInsuranceRequest.getPolicyNo());
				lifeInsuranceResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
				lifeInsuranceResponse.setErrorDescription(e.getMessage());
				return lifeInsuranceResponse;
			}
		} catch (Exception ex) {
			lifeInsuranceResponse.setStatus(StatusConstant.STATUS_FAILURE);
			lifeInsuranceResponse.setPolicyNo(lifeInsuranceRequest.getPolicyNo());
			lifeInsuranceResponse.setErrorCode(ExceptionConstant.FILE_NOT_SAVED_EC);
			lifeInsuranceResponse.setErrorDescription(ex.getMessage());
			return lifeInsuranceResponse;

		}

		return lifeInsuranceResponse;
	}

//	Find all policies
	@Override
	public List<LifeInsurance> getAllPolicies() {
		return new ArrayList<>(this.lifeInsuranceRepository.findAll());
//		return new ArrayList<>(this.loanRepository.findAllByLoanStatus(Sort.by(Sort.Direction.ASC, "bankName")));
	}

//	Delete a single policy by id
	@Override
	public void deletePolicy(Long policyId) {
		LifeInsurance lifeInsurance = this.lifeInsuranceRepository.findById(policyId).get();
		lifeInsurance.setStatus(false);
		lifeInsurance.setModifiedBy("sunilkmr5775");
		lifeInsurance.setModifiedDate(LocalDateTime.now());
		this.lifeInsuranceRepository.save(lifeInsurance);

	}

//	Get All Active Policies
	@Override
	public List<LifeInsurance> getAllActivePolicies() {
		return new ArrayList<>(this.lifeInsuranceRepository.findAllByStatus(true));
	}

//	Filter Policies
	@Override
	public List<LifeInsurance> findAllLifeInsurancePoliciesProcedure(String policyNo, boolean policyStatus, String bankName) {

		ArrayList<LifeInsurance> result = this.lifeInsuranceRepository.findAllLifeInsurancePoliciesProcedure(policyNo, policyStatus, bankName);
		return result;
	}
/*

//  Update policy
	@Override
	public Loan updatePolicy(LifeInsurance lifeInsurance) {
		return this.loanRepository.save(loan);
	}



	@Override
	public Loan getLoan(Long loanId) {
		Loan loan = this.loanRepository.findById(loanId).get();
		return loan;
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
	public List<LoanTypes> getAllLoanTypes() {

		return new ArrayList<>(loanTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "loanType")));
	}
*/


}
