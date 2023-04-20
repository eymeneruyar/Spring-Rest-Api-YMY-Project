package com.work.ymy.company.service;

import com.work.ymy.company.entity.Company;
import com.work.ymy.company.exception.CompanyNotFoundException;
import com.work.ymy.company.payload.request.CompanyRequest;
import com.work.ymy.company.payload.response.SuccessResponse;
import com.work.ymy.company.repository.CitiesRepository;
import com.work.ymy.company.repository.CompanyRepository;
import com.work.ymy.company.repository.TownsRepository;
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

    private final TownsRepository townsRepository;
    private final CitiesRepository citiesRepository;
    private final CompanyRepository companyRepository;
    private static final Logger LOGGER = LogManager.getLogger(CompanyService.class);
    //TODO: User id değeri micro'da uygun alınacka şekilde revize edilecek.

    @Override
    public ResponseEntity<?> createOrUpdateCompany(CompanyRequest request) {
        String message;
        Company company;
        boolean isBeforeCreated = request.getId() != null ? true : false;
        if(isBeforeCreated){ //Update process
            company = new Company(request.getId(),true, DateUtil.getDateFormatYYYYMMDD(),request.getUserId(), request.getCode(), request.getName(), request.getAuthorisedPerson(), request.getPhone(), request.getEmail(), request.getTaxOffice(), request.getTaxNumber(), request.getCity(), request.getTown(), request.getAddress());
            companyRepository.saveAndFlush(company);
            message = Constant.UPDATE_COMPANY_SUCCESS_RESPONSE_MESSAGE;
        }else{ //Save process
            company = new Company(true, DateUtil.getDateFormatYYYYMMDD(),request.getUserId(), request.getCode(), request.getName(), request.getAuthorisedPerson(), request.getPhone(), request.getEmail(), request.getTaxOffice(), request.getTaxNumber(), request.getCity(), request.getTown(), request.getAddress());
            companyRepository.save(company);
            message = Constant.CREATE_COMPANY_SUCCESS_RESPONSE_MESSAGE;
        }
        SuccessResponse response = SuccessResponse.builder()
                .httpStatusCode(HttpStatus.OK)
                .success(true)
                .data(company)
                .message(message)
                .build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getListCompany() {
        SuccessResponse response = SuccessResponse.builder()
                .message(Constant.LIST_COMPANY_SUCCESS_RESPONSE_MESSAGE)
                .success(true)
                .httpStatusCode(HttpStatus.OK)
                .data(companyRepository.findByStatusEqualsAndUserIdEqualsOrderByIdAsc(true,1))
                .build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> deleteCompany(String id) throws CompanyNotFoundException {
        long companyId = Long.parseLong(id);
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException(Constant.COMPANY_NOT_FOUND_MESSAGE));
        company.setStatus(false);
        companyRepository.saveAndFlush(company);
        SuccessResponse response = SuccessResponse.builder()
                .message(Constant.DELETE_COMPANY_SUCCESS_RESPONSE_MESSAGE)
                .success(true)
                .httpStatusCode(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getListCity() {
        SuccessResponse response = SuccessResponse.builder()
                .message(Constant.LIST_CITY_SUCCESS_RESPONSE_MESSAGE)
                .success(true)
                .httpStatusCode(HttpStatus.OK)
                .data(citiesRepository.findAll())
                .build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getListTownBySelectedCity(String id) {
        SuccessResponse response = SuccessResponse.builder()
                .message(Constant.LIST_TOWN_SUCCESS_RESPONSE_MESSAGE)
                .success(true)
                .httpStatusCode(HttpStatus.OK)
                .data(townsRepository.findByTownCityKeyOrderByIdAsc(Long.parseLong(id)))
                .build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getDetailCityByCityKey(String id) {
        SuccessResponse response = SuccessResponse.builder()
                .message(Constant.DETAIL_CITY_SUCCESS_RESPONSE_MESSAGE)
                .success(true)
                .httpStatusCode(HttpStatus.OK)
                .data(citiesRepository.findByCityKey(Long.parseLong(id)))
                .build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getDetailCompany(String id) throws CompanyNotFoundException {
        Company company = companyRepository.findById(Long.parseLong(id)).orElseThrow(() -> new CompanyNotFoundException(Constant.COMPANY_NOT_FOUND_MESSAGE));
        SuccessResponse response = SuccessResponse.builder()
                .message(Constant.DETAIL_COMPANY_SUCCESS_RESPONSE_MESSAGE)
                .success(true)
                .httpStatusCode(HttpStatus.OK)
                .data(company)
                .build();
        return ResponseEntity.ok(response);
    }

}
