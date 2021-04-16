package com.liuhaozzu.ddd.domain;

import com.liuhaozzu.ddd.validation.ParameterValidationResult;
import com.liuhaozzu.ddd.validation.VoBase;
import org.apache.commons.lang3.StringUtils;

/**
 * @author liuhao01
 * @date 4/15/21 2:18 PM
 */
public class OperationLogInfo extends VoBase {
    private String module;
    private String operatorId;
    private String operatorName;
    private String action;


    @Override
    public ParameterValidationResult validate() {
        if (StringUtils.isEmpty(module)) {
            return ParameterValidationResult.failed(OperationMessages.NO_MODULE_INFO);
        }

        if (StringUtils.isEmpty(operatorId)) {
            return ParameterValidationResult.failed(OperationMessages.NO_OPERATOR_ID_TYPE);
        }
        if (StringUtils.isEmpty(operatorName)) {
            return ParameterValidationResult.failed(OperationMessages.NO_OPERATOR_NAME_TYPE);
        }
        if (StringUtils.isEmpty(action)) {
            return ParameterValidationResult.failed(OperationMessages.NO_ACTION_TYPE);
        }
        return ParameterValidationResult.success();
    }

    public String getModule() {
        return module;
    }

    public OperationLogInfo setModule(String module) {
        this.module = module;
        return this;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public OperationLogInfo setOperatorId(String operatorId) {
        this.operatorId = operatorId;
        return this;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public OperationLogInfo setOperatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public String getAction() {
        return action;
    }

    public OperationLogInfo setAction(String action) {
        this.action = action;
        return this;
    }
}
