package com.work.ymy.company.service;

import com.work.ymy.company.entity.Company;
import com.work.ymy.company.exception.CompanyNotFoundException;
import com.work.ymy.company.payload.request.CompanyRequest;
import com.work.ymy.company.payload.response.SuccessResponse;
import com.work.ymy.company.repository.CompanyRepository;
import com.work.ymy.company.utility.Constant;
import com.work.ymy.company.utility.DateUtil;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService{

    private final CompanyRepository companyRepository;
    private static final Logger LOGGER = LogManager.getLogger(CompanyService.class);
    //TODO: User id değeri micro'da uygun alınacka şekilde revize edilecek.

    @Override
    public ResponseEntity<?> createOrUpdateCompany(CompanyRequest request) {
        Company company = new Company();
        SuccessResponse response = new SuccessResponse();
        boolean isBeforeCreated = request.getId() != null ? true : false;
        if(isBeforeCreated){ //Update process
            company = new Company(request.getId(),true, DateUtil.getDateFormatYYYYMMDD(),request.getUserId(), request.getCode(), request.getName(), request.getAuthorisedPerson(), request.getPhone(), request.getEmail(), request.getTaxOffice(), request.getTaxNumber(), request.getCity(), request.getTown(), request.getAddress());
            companyRepository.saveAndFlush(company);
            response.setMessage(Constant.UPDATE_COMPANY_SUCCESS_RESPONSE_MESSAGE);
        }else{ //Save process
            company = new Company(true, DateUtil.getDateFormatYYYYMMDD(),request.getUserId(), request.getCode(), request.getName(), request.getAuthorisedPerson(), request.getPhone(), request.getEmail(), request.getTaxOffice(), request.getTaxNumber(), request.getCity(), request.getTown(), request.getAddress());
            companyRepository.save(company);
            response.setMessage(Constant.CREATE_COMPANY_SUCCESS_RESPONSE_MESSAGE);
        }
        response.setData(company);
        response.setHttpStatusCode(HttpStatus.OK);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getListCompany() {
        SuccessResponse response = new SuccessResponse();
        response.setData(companyRepository.findByStatusEqualsAndUserIdEqualsOrderByIdAsc(true,1));
        response.setSuccess(true);
        response.setHttpStatusCode(HttpStatus.OK);
        response.setMessage(Constant.LIST_COMPANY_SUCCESS_RESPONSE_MESSAGE);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> deleteCompany(String id) throws CompanyNotFoundException {
        SuccessResponse response = new SuccessResponse();
        long companyId = Long.parseLong(id);
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException(Constant.DELETE_COMPANY_ERROR_NOT_FOUND_RESPONSE_MESSAGE));
        company.setStatus(false);
        companyRepository.saveAndFlush(company);
        response.setSuccess(true);
        response.setHttpStatusCode(HttpStatus.OK);
        response.setMessage(Constant.DELETE_COMPANY_SUCCESS_RESPONSE_MESSAGE);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getListCity() {
        return null;
    }

    @Override
    public ResponseEntity<?> getListTownBySelectedCity(String id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getDetailCityByCityKey(String id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getDetailCompany(String id) {
        return null;
    }

}
