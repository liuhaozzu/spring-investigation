package com.liuhaozzu.ddd.validation;

import com.google.gson.Gson;

/**
 * @author liuhao01
 * @date 4/15/21 2:25 PM
 */
public abstract class VoBase implements Validatable {

    @Override
    public ParameterValidationResult validate() {
        return ParameterValidationResult.success();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
