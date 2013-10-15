/*
 * Copyright 2007-2008 Amazon Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 * 
 * http://aws.amazon.com/apache2.0
 * 
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */ 


package com.amazonaws.mturk.service.exception;


@SuppressWarnings("serial")
public class AccessKeyException extends ServiceException {

  //-------------------------------------------------------------
  // Constructors
  //-------------------------------------------------------------

  public AccessKeyException() {
  }

  public AccessKeyException(String message) {
    super(message);
  }

  public AccessKeyException(Throwable cause) {
    super(cause);
  }

  public AccessKeyException(String message, Throwable cause) {
    super(message, cause);
  }

}
