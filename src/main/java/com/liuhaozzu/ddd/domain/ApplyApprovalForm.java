package com.liuhaozzu.ddd.domain;

import com.liuhaozzu.ddd.validation.rules.ObjectNotNullRule;

import java.time.LocalDateTime;

/**
 * @author liuhao01
 * @date 4/15/21 3:32 PM
 */
public class ApplyApprovalForm extends ApprovalFormBase {
    private LocalDateTime applyDateTime;
    private ProcessStage stage=ProcessStage.INIT;


    ApplyApprovalForm(Long id, String appId) {

    }

    @Override
    protected void addRule(RuleManager ruleManager) {
        super.addRule(ruleManager);
        super.addRule(ruleManager);
        ruleManager.addRule(new ObjectNotNullRule("stage", this.stage, OperationMessages.INVALID_STAGE));

    }
}
