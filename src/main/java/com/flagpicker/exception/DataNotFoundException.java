package com.flagpicker.exception;

public class DataNotFoundException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Create a new <code>DataNotFoundException</code> with a message text.
   * 
   * @param message
   *            Message text as String
   */
  public DataNotFoundException(String message) {
    super(message);
  }

  /**
   * Create a new <code>DataNotFoundException</code> with a message text and the root exception.
   * 
   * @param message
   *            Message text as String
   * @param cause
   *            The root exception
   */
  public DataNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
