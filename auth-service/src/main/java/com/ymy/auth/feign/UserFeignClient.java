package com.ymy.auth.feign;

import com.ymy.auth.payload.response.UserResponse;
import com.ymy.auth.utility.Constant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@RequestMapping(value = Constant.BASE_API_USER)
@FeignClient(name = Constant.USER_MICROSERVICE)
public interface UserFeignClient {
    @RequestMapping(Constant.USER_INFO_DETAIL_BY_USERNAME)
    Optional<UserResponse> getDetailOfUserByUsername(@PathVariable String username);
}
