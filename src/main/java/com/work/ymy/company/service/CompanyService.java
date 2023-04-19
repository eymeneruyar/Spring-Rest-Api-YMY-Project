package com.work.ymy.company.service;

import com.work.ymy.company.entity.Company;
import com.work.ymy.company.payload.request.CompanyRequest;
import com.work.ymy.company.payload.response.SuccessResponse;
import com.work.ymy.company.repository.CompanyRepository;
import com.work.ymy.company.utility.Constant;
import com.work.ymy.company.utility.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService{

    //private final Logger LOGGER = (Logger) LoggerFactory.getLogger(Company.class);
    private final CompanyRepository companyRepository;

    @Override
    public ResponseEntity<?> createOrUpdateCompany(CompanyRequest request) {
        Company company = new Company();
        SuccessResponse response = new SuccessResponse();
        boolean isBeforeCreated = request.getId() != null ? true : false;
        try {
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
        }catch (Exception e){
            //LOGGER.error(Constant.GENERAL_ERROR_MESSAGE + ": " + e.getMessage());
            response.setData(company);
            response.setMessage(Constant.GENERAL_ERROR_MESSAGE + ": " + e);
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setSuccess(false);
        }
        return ResponseEntity.ok(response);
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
