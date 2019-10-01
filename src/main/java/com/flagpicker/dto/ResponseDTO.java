package com.flagpicker.dto;

import com.flagpicker.utils.Constants.ReturnCode;

public class ResponseDTO<T> {

  private T response;
  private ReturnCode returnCode;

  public T getResponse() {
    return response;
  }

  public void setResponse(T response) {
    this.response = response;
  }

  public ReturnCode getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(ReturnCode returnCode) {
    this.returnCode = returnCode;
  }

  @Override
  public String toString() {
    return "ResponseDto [response=" + response + ", returnCode=" + returnCode + "]";
  }

}
