package com.liuhaozzu.ddd.validation.rules;

import com.liuhaozzu.ddd.validation.ParameterValidationResult;

/**
 * @author liuhao01
 * @date 4/15/21 4:02 PM
 */
public abstract class RuleBase<T> {
    protected String nameOfTarget;
    protected T target;
    protected String errorMessage;

    protected RuleBase(String nameOfTarget, T target, String errorMessage) {
        this.nameOfTarget=nameOfTarget;
        this.target=target;
        this.errorMessage = errorMessage;
    }


    protected T getTarget() {
        return target;
    }
}
