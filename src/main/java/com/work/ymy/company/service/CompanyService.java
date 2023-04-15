package com.work.ymy.company.service;

import com.work.ymy.company.payload.request.CompanyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService{
    @Override
    public ResponseEntity<?> createOrUpdateCompany(CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getListCompany() {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCompany() {
        return null;
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
