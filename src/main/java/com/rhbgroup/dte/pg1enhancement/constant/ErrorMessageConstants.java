package com.rhbgroup.dte.pg1enhancement.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessageConstants {

  public static final String RESOURCE_NOT_FOUND_BY_ID = "Resource not found for action ID : ";
  public static final String RESOURCE_NOT_FOUND_BY_INFO = "Resource not found for : ";
  public static final String EXTERNAL_TRANSACTION_FAILED =
      "External transaction to service failed : %s";
  public static final String OBJECT_TO_JSON_CONVERSION_FAILED =
      "Not able to convert object to JSON string";
  public static final String JSON_TO_OBJECT_CONVERSION_FAILED =
      "Not able to convert JSON string to Object";
  public static final String ACTION_NOT_ALLOWED =
      "This action does not exist or allowed for this item ";
  public static final String INVALID_DECRYPTION_KEY = "Incorrect decryption key";
  public static final String INVALID_DECRYPTION_ALGORITHM = "Incorrect decryption algorithm";
  public static final String SOMETHING_WENT_WRONG = "Something went wrong";
  public static final String USER_NOT_AUTHORIZE_TO_ACCESS =
      "User is not authorize to access this resource";
  public static final String INVALID_TOKEN_EXPIRED = "Invalid token: Token has been expired";
  public static final String ITEM_ID = "item_id";
  public static final String ITEM_STATUS = " item_status";
  public static final String SIGNATURE_MISMATCH = "Invalid token signature: error: ";
  public static final String FIELD_CANNOT_BE_MODIFIED = "Field cannot be modified";

  //  -------------------------------------------------
  public static final int BAD_REQUEST_CODE = 1000;
  public static final int NOT_FOUND_CODE = 1001;
  public static final int INTERNAL_SERVER_CODE = 1003;
  public static final int ACTION_ITEM_ACTION_TAKEN = 13001;
  public static final int ITEM_PROPERTIES_INVALID = 13002;
  public static final int ITEM_WAITING_FOR_APPROVAL = 13003;
  public static final int INVALID_JWT_TOKEN = 12001;
  public static final int USER_NOT_AUTHORISED = 13004;
}
