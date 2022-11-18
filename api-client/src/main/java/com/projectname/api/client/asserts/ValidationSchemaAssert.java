package com.projectname.api.client.asserts;

import com.projectname.api.client.utils.ResponseValidation;
import org.testng.asserts.SoftAssert;

//Asserts used to validate json schema for Integration tests
public class ValidationSchemaAssert {

    private SoftAssert softAssert;

    public ValidationSchemaAssert() {
        this.softAssert = new SoftAssert();
    }

    public void assertResponseStructure(ResponseValidation responseValidation) {
        if (!responseValidation.getUnknownFields().isEmpty()) {
            for (int i = 0; i < responseValidation.getUnknownFields().size(); i++) {
                this.softAssert.fail(responseValidation.getUnknownFields().get(i));
            }
        }
        if (!responseValidation.getClassRequiredFields().isEmpty()) {
            for (int i = 0; i < responseValidation.getClassRequiredFields().size(); i++) {
                this.softAssert.fail("Required field " + responseValidation.getClassRequiredFields().get(i) + " missing in response");
            }
        }
        if (!responseValidation.getRequiredFieldMissingValue().isEmpty()) {
            for (int i = 0; i < responseValidation.getRequiredFieldMissingValue().size(); i++) {
                this.softAssert.fail(responseValidation.getRequiredFieldMissingValue().get(i));
            }
        }
        if (!responseValidation.getWrongTypeFields().isEmpty()) {
            for (int i = 0; i < responseValidation.getWrongTypeFields().size(); i++) {
                this.softAssert.fail(responseValidation.getWrongTypeFields().get(i));
            }
        }
        this.softAssert.assertAll();
    }
}
