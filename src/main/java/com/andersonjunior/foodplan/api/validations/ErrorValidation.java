package com.andersonjunior.foodplan.api.validations;

import java.util.ArrayList;
import java.util.List;

import com.andersonjunior.foodplan.domain.models.FieldMessage;
import com.andersonjunior.foodplan.domain.models.StandardError;

public class ErrorValidation extends StandardError {

	private List<FieldMessage> errors = new ArrayList<>();

	public ErrorValidation(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName, messagem));
	}

}
