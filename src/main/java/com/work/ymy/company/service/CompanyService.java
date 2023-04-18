package com.work.ymy.company.service;

import com.work.ymy.company.entity.Company;
import com.work.ymy.company.payload.request.CompanyRequest;
import com.work.ymy.company.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public ResponseEntity<?> createOrUpdateCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        boolean isBeforeCreated = companyRequest.getId() != null ? true : false;
        try {
            if(isBeforeCreated){
                company = new Company();
            }else{
                company = new Company();
            }
        }catch (Exception e){
            //TODO: Working requirement
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getListCompany() {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCompany(String id) {
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
