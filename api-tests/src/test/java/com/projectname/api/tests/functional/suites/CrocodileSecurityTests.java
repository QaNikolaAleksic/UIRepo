package com.projectname.api.tests.functional.suites;

import com.projectname.api.client.calls.CrocodilesAPI;
import com.projectname.api.client.data.model.crocodile.*;
import com.projectname.api.tests.constants.DataProviderNames;
import com.projectname.api.tests.constants.ErrorMessages;
import com.projectname.api.tests.data.provider.CrocodileProvider;
import com.projectname.api.tests.environment.ConfigSetup;
import com.projectname.api.tests.functional.asserts.CommonErrorAssert;
import com.projectname.api.tests.init.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.CompletionException;

public class CrocodileSecurityTests extends TestBase {
    String accessTokenMainUser;
    String accessTokenSecondUser;

    @BeforeClass
    public void prepareDataForTest() {
        accessTokenMainUser = CrocodilesAPI.loginUser(new LoginRequest(ConfigSetup.getMainUser(), ConfigSetup.getPass())).getAccess();
        accessTokenSecondUser = CrocodilesAPI.loginUser(new LoginRequest(ConfigSetup.getSecondUser(), ConfigSetup.getPass())).getAccess();
    }

    @Test
    public void verifyCannotGetCrocodileFromAnotherUser() {
        CrocodileResponse crocodileResponse = CrocodilesAPI.createCrocodile(accessTokenMainUser, CrocodileProvider.prepareCrocodileRequest());

        CrocodileErrorResponse actualError = CrocodilesAPI.getSingleCrocWithError(accessTokenSecondUser, crocodileResponse.getId());

        CommonErrorAssert commonErrorAssert = new CommonErrorAssert();
        commonErrorAssert.assertCrocodileErrorResponse(actualError, new CrocodileErrorResponse(ErrorMessages.NOT_FOUND));
    }

    @Test(dataProvider = DataProviderNames.VERIFY_CANNOT_CREATE_USER_WITHOUT_REQUIRED_FIELD, dataProviderClass = CrocodileProvider.class)
    public void verifyCannotCreateCrocodileWithoutRequiredFields(String suffix, CrocodileRequest crocodileRequest, RequiredFieldErrorResponse expectedError) {
        RequiredFieldErrorResponse actualError = CrocodilesAPI.createCrocodileWithRequiredFieldError(accessTokenMainUser, crocodileRequest);

        CommonErrorAssert commonErrorAssert = new CommonErrorAssert();
        commonErrorAssert.assertRequiredFieldErrors(actualError, expectedError);
    }

    @Test(dataProvider = DataProviderNames.VERIFY_CANNOT_UPDATE_USER_WITHOUT_REQUIRED_FIELD, dataProviderClass = CrocodileProvider.class)
    public void verifyCannotUpdateCrocodileWithoutRequiredFields(String suffix, CrocodileResponse crocodileResponse, RequiredFieldErrorResponse expectedError) {
        CrocodilesAPI.createCrocodile(accessTokenMainUser, CrocodileProvider.prepareCrocodileRequest());
        RequiredFieldErrorResponse actualError = CrocodilesAPI.updateCrocodileWithRequiredFieldError(accessTokenMainUser, crocodileResponse, crocodileResponse.getId());

        CommonErrorAssert commonErrorAssert = new CommonErrorAssert();
        commonErrorAssert.assertRequiredFieldErrors(actualError, expectedError);
    }
}
