package com.rhbgroup.dte.pg1enhancement.exception;

import com.rhbgroup.dte.pg1enhancement.constant.ErrorCodes;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final Integer code;

  public BadRequestException(String message) {
    this(ErrorCodes.BAD_REQUEST, message);
  }

  public BadRequestException(Integer code, String message) {
    super(message);
    this.code = code;
  }
}
