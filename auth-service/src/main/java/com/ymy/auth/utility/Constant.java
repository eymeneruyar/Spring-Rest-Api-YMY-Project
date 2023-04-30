package com.ymy.auth.utility;

public class Constant {

    private Constant(){}

    //Header Constants
    public static final String HEADER_AUTH = "Authorization";

    //Error Constants
    public static final String ERROR_UNAUTHORIZED = "Unauthorized";

    //Role case type constants
    public static final String CASE_ROLE_ADMIN = "admin";
    public static final String CASE_ROLE_MODERATOR = "mod";

    //Encoding constants
    public static final String ENCODING_UTF8 = "UTF-8";

    //Microservice constants
    public static final String BASE_API_USER = "/api/v1/user";
    public static final String USER_MICROSERVICE = "user-service";
    public static final String USER_INFO_DETAIL_BY_USERNAME = "/detailOfUserByUsername/{username}";

}
