package com.ymy.company.service;

import com.ymy.company.exception.CompanyNotFoundException;
import com.ymy.company.payload.request.CompanyRequest;
import org.springframework.http.ResponseEntity;

public interface ICompanyService {
    ResponseEntity<?> createOrUpdateCompany(CompanyRequest companyRequest);
    ResponseEntity<?> getListCompany();
    ResponseEntity<?> deleteCompany(String id) throws CompanyNotFoundException;
    ResponseEntity<?> getListCity();
    ResponseEntity<?> getListTownBySelectedCity(String id);
    ResponseEntity<?> getDetailCityByCityKey(String id);
    ResponseEntity<?> getDetailCompany(String id) throws CompanyNotFoundException;
}
