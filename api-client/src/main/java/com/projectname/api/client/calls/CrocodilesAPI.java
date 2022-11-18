package com.projectname.api.client.calls;

import com.projectname.api.client.constants.ApiEndpoints;
import com.projectname.api.client.data.model.crocodile.*;
import com.projectname.api.client.utils.GsonFunctions;
import com.projectname.api.client.utils.RestAssuredFunctions;

public class CrocodilesAPI {

    public static LoginResponse loginUser(LoginRequest loginRequest) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post(loginRequest, ApiEndpoints.LOGIN_USER), LoginResponse.class);
    }

    public static CrocodileResponse createCrocodile(String accessToken, CrocodileRequest createCrocodile) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post(createCrocodile, accessToken, ApiEndpoints.CREATE_CROCODILE), CrocodileResponse.class);
    }

    public static CrocodileResponse updateCrocodile(String accessToken, CrocodileRequest updateCrocodile, Integer id) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.put(updateCrocodile,accessToken,ApiEndpoints.GET_MY_CROCODILE(id)), CrocodileResponse.class);
    }

    public static CrocodileErrorResponse getSingleCrocWithError(String accessToken, Integer id) {
        return GsonFunctions.parseErrorResponseToModel(RestAssuredFunctions.get(accessToken, ApiEndpoints.GET_MY_CROCODILE(id)), CrocodileErrorResponse.class);
    }

    public static RequiredFieldErrorResponse createCrocodileWithRequiredFieldError(String accessToken, CrocodileRequest crocodileRequest) {
        return GsonFunctions.parseErrorResponseToModel(RestAssuredFunctions.post(crocodileRequest, accessToken, ApiEndpoints.CREATE_CROCODILE), RequiredFieldErrorResponse.class);
    }

    public static RequiredFieldErrorResponse updateCrocodileWithRequiredFieldError(String accessToken, CrocodileResponse crocodileResponse, Integer id) {
        return GsonFunctions.parseErrorResponseToModel(RestAssuredFunctions.put(crocodileResponse, accessToken, ApiEndpoints.UPDATE_CROCODILE(id)), RequiredFieldErrorResponse.class);
    }
}
