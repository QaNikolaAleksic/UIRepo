package com.projectname.api.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Custom annotation to mark required fields in models, for purposes of Integration testing
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//canBeEmpty if set to true means that object/property in Json schema is required and must have value
public @interface ResponseRequiredField {
    boolean canBeEmpty();
}
