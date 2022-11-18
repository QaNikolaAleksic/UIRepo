package com.projectname.api.tests.data.provider;

import com.projectname.api.client.data.model.crocodile.CrocodileRequest;
import com.projectname.api.client.data.model.crocodile.CrocodileResponse;
import com.projectname.api.client.data.model.crocodile.RequiredFieldErrorResponse;
import com.projectname.api.client.utils.Dates;
import com.projectname.api.client.utils.RandomStringGenerator;
import com.projectname.api.tests.constants.DataProviderNames;
import com.projectname.api.tests.constants.ErrorMessages;
import groovy.transform.ToString;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class CrocodileProvider {

    @DataProvider(name = DataProviderNames.VERIFY_CANNOT_CREATE_USER_WITHOUT_REQUIRED_FIELD)
    public static Object[][] verifyCannotCreateUserWithoutRequiredField() {
        return new Object[][] {
                {"whenNameIsNull", new CrocodileRequest(null, RandomStringGenerator.getRandomSex(), Dates.getRandomDate()), RequiredFieldErrorResponse.prepareErrorForName(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
                {"whenSexIsNull", new CrocodileRequest(RandomStringGenerator.createRandomStringWithLen(6), null, Dates.getRandomDate()), RequiredFieldErrorResponse.prepareErrorForSex(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
                {"whenDateIsNull", new CrocodileRequest(RandomStringGenerator.createRandomStringWithLen(6), RandomStringGenerator.getRandomSex(), null), RequiredFieldErrorResponse.prepareErrorForDate(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))}
        };
    }

    @DataProvider(name = DataProviderNames.VERIFY_CANNOT_UPDATE_USER_WITHOUT_REQUIRED_FIELD)
    public static Object[][] verifyCannotUpdateUserWithoutRequiredField() {
        return new Object[][] {
                {"whenNameIsNull", new CrocodileResponse(12162490, null, RandomStringGenerator.getRandomSex().toString(), Dates.getRandomDate(), 1), RequiredFieldErrorResponse.prepareErrorForName(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
                {"whenSexIsNull", new CrocodileResponse(12162490, RandomStringGenerator.createRandomStringWithLen(6), null, Dates.getRandomDate(), 1), RequiredFieldErrorResponse.prepareErrorForSex(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))},
                {"whenDateIsNull", new CrocodileResponse(12162490, RandomStringGenerator.createRandomStringWithLen(6), RandomStringGenerator.getRandomSex().toString(), null, 1), RequiredFieldErrorResponse.prepareErrorForDate(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED))}
        };
    }

    public static CrocodileRequest prepareCrocodileRequest() {
        CrocodileRequest createCrocodile = new CrocodileRequest(RandomStringGenerator.createRandomStringWithLen(6), RandomStringGenerator.getRandomSex(), Dates.getRandomDate());
        return createCrocodile;
    }

    public static RequiredFieldErrorResponse prepareErrorMessageForSex() {
        RequiredFieldErrorResponse errorResponse = new RequiredFieldErrorResponse();
        errorResponse.setSex(Arrays.asList(ErrorMessages.FIELD_IS_REQUIRED));
        return errorResponse;
    }

}
