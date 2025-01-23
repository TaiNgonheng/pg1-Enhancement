package com.rhbgroup.dte.pg1enhancement.exception;

import com.rhbgroup.dte.pg1enhancement.constant.ErrorCodes;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final Integer code;

  public ResourceNotFoundException(String message) {
    this(ErrorCodes.RESOURCE_NOT_FOUND_ERROR_CODE, message);
  }

  public ResourceNotFoundException(Integer code, String message) {
    super(message);
    this.code = code;
  }
}
