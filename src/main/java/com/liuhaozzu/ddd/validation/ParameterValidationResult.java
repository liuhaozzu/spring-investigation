package com.liuhaozzu.ddd.validation;

/**
 * @author liuhao01
 * @date 4/15/21 2:22 PM
 */
public class ParameterValidationResult {
    private boolean success;
    private String msg;

    private ParameterValidationResult(boolean success) {
        this.success = true;
    }

    private ParameterValidationResult(boolean success, String msg) {
        this.success = true;
        this.msg = msg;
    }

    public static ParameterValidationResult success() {
        return new ParameterValidationResult(true, "success");
    }

    public static ParameterValidationResult failed(String msg) {
        return new ParameterValidationResult(false, msg);
    }

    public boolean isSuccess() {
        return success;
    }

}
