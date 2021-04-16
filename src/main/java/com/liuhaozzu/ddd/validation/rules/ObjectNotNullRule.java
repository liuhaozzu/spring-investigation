package com.liuhaozzu.ddd.validation.rules;

import com.liuhaozzu.ddd.validation.ParameterValidationResult;

/**
 * @author liuhao01
 * @date 4/15/21 3:56 PM
 */
final public class ObjectNotNullRule<T> extends RuleBase<T>{
    public ObjectNotNullRule(String nameOfTarget, T target, String errorMessage) {
        super(nameOfTarget, target, errorMessage);
    }

    public ParameterValidationResult validate() {
        if (getTarget() == null) {
            return ParameterValidationResult.failed(null);
        }
        return ParameterValidationResult.success();
    }
}
