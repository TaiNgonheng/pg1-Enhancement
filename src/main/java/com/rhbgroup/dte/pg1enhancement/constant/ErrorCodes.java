package com.rhbgroup.dte.pg1enhancement.constant;

public class ErrorCodes {

  private ErrorCodes() {}

  public static final int UNABLE_TO_PROCEED_ = 1002;
  public static final int INVALID_CREDENTIALS = 1006;

  // Generic and for All error code
  public static final int BAD_REQUEST = 1000;
  public static final int INVALID_TOKEN = 1005;
  public static final int EXPIRED_TOKEN = 1004;
  public static final int INTERNAL_SERVER_ERROR = 1003;
  public static final int RESOURCE_NOT_FOUND_ERROR_CODE = 1001;

  public static final int UNAUTHORIZED_INVALID_LOGIN_CREDS = 2000;
  public static final int PRECONDITION_EMAIL_ALREADY_REGISTERED = 2001;
  public static final int PRECONDITION_MISSING_USER = 2002;
  public static final int PRECONDITION_BLOCKED_CREDS_FOR_LOGIN = 2003;
  public static final int PRECONDITION_USE_GOOGLE_LOGIN = 2005;
  public static final int PRECONDITION_USE_FACEBOOK_LOGIN = 2006;
  public static final int PRECONDITION_EXISTING_USER_FOR_SOCIAL_LOGIN = 2007;
  public static final int PRECONDITION_MUST_REAUTH = 2008;
  public static final int PRECONDITION_REQUIRE_FB_LOGIN = 2009;
  public static final int PRECONDITION_BLOCKED_CREDS_FOR_RESET_OR_FORGET_PASSWORD = 2010;
  public static final int INVALID_CURRENT_PASSWORD = 2012;
  public static final int PRECONDITION_PREVIOUS_PASSWORD_MATCH = 2013;
  public static final int PRECONDITION_FAILED_TO_VERIFY_DIGITAL_SIGNATURE = 2014;
  public static final int PRECONDITION_NO_ACTIVE_DEVICE = 2015;
  public static final int PRECONDITION_QUICK_LOGIN_ALREADY_SETUP = 2016;
  public static final int PRECONDITION_SAME_DEVICE_ALREADY_REGISTERED = 2017;
  public static final int PRECONDITION_QUICK_LOGIN_NOT_SETUP = 2018;
  public static final int UNAUTHORIZED_FAILED_CARD_DETAILS_VERIFICATION = 2019;
  public static final int PRECONDITION_QUICK_LOGIN_NOT_ENABLED = 2020;
  public static final int PRECONDITION_EXISTING_USERNAME_FOR_REGISTER = 2021;
  public static final int PRECONDITION_BU_INELIGIBLE_UPGRADE = 2022;
  public static final int WRONG_OTP_CODE = 3001;
  public static final int MOBILE_BLOCKED_CODE = 3000;
  public static final int MANUAL_UPGRADE_VALIDATION_FAILED = 3002;
  public static final int MANUAL_UPGRADE_VALIDATION_FAILED_CONFIRM = 3004;
  public static final int DUPLICATE_KEY = 3005;
  // Will be update as per new error codes
  public static final int RECORD_NOT_FOUND_ERROR_CODE = 50007;
  public static final int FORBIDDEN_ERROR_CODE = 403;

  public static final int FAVOURITE_PAYEE_DOES_NOT_EXIST_CODE = 9001;
  public static final int FAVOURITE_PAYEE_LIMIT_CODE = 9002;
  public static final int ACCOUNT_NUMBER_ALREADY_EXIST_CODE = 9004;
  public static final int FAVOURITE_INVALID_USER_ID_CODE = 9003;

  public static final int INVALIDE_FILE_DATA = 8000;
  public static final int INVALIDE_KEY = 8001;
  public static final int INVALIDE_ALGORITH = 8002;

  public static final int INVALIDE_DEVICE_ID = 8003;
  public static final int GAME_ID_NOT_EXIST_FOR_USER = 8004;
  public static final int GAME_RESULT_NOT_FOUND_FOR_USER_GAME_ID = 8005;
  public static final int UNABLE_TO_SHARE_GAME_RESULT_INVALID_USER = 8006;
  public static final int USER_GAME_ID_NOT_EXIST_FOR_USER_ID = 8007;

  public static final int REQUESTED_DISTRICT_NOT_SUPPORTED = 7000;
  public static final int REQUESTED_DISTRICTS_NOT_SUPPORTED = 7001;

  public static final int NO_SUCH_REFERRAL_EXIST = 5001;
  public static final int REFERRAL_CODE_IS_INVALID = 5002;
  public static final int REFERRAL_ALREADY_CLAIMED = 5000;
  public static final int NO_SUCH_REWARD_EXISTS = 6000;
  public static final int NO_SUCH_VENDOR_EXISTS = 6001;
  public static final int BOAUTH_INVALID_LOGIN_CREDS = 12000;
  public static final int FIELD_CANNOT_BE_MODIFIED = 13000;
  public static final int ACTION_ITEM_ALREADY_APPROVED_REJECTED = 13001;
  public static final int INVALID_NEW_ITEM_PROPERTIES = 13002;
  public static final int SIMILAR_ACTION_ITEM_EXIST_WAITING_FOR_APPROVAL = 13003;
  public static final int USER_IS_NOT_AUTHORIZED = 13004;

  // Please provide valid referral info
  public static final int INVALID_REFERRAL_INFO = 4000;

  public static final int ARTICLE_ALREADY_EXISTS = 10000;
  public static final int NO_CATEGORY_EXISTS = 10001;
  public static final int ARTICLE_IS_NOT_EXISTS = 10002;
  public static final int ARTICLE_ID_CAN_NOT_BE_NULL = 10003;
  public static final int TITLE_CAN_NOT_BE_NULL_OR_EMPTY = 10004;
  public static final int REQUIRED_VALID_SET_OF_CATEGORY_IDS = 10005;
  public static final int REQUIRED_CATEGORY_TYPE = 10006;
  public static final int REQUIRED_CATEGORY_WEB_URL = 10007;
  public static final int DUPLICATE_ENTRY_NOT_ALLOWED = 10008;

  public static final int NO_CONFIGURATION_EXISTS_FOR_KEY = 11000;

  // Default Values for Exception(In case developer not sending any custom error code)
  public static final int VALIDATION_EXCEPTION_DEFAULT_ERROR_CODE = 100;
  public static final int UNAUTHORIZATION_EXCEPTION_DEFAULT_ERROR_CODE = 101;
  public static final int INVALIDE_INPUT_EXCEPTION_DEFAULT_ERROR_CODE = 102;
  public static final int RECORD_NOT_FOUND_EXCEPTION_DEFAULT_ERROR_CODE = 103;
  public static final int PRECONDITION_FAILED_EXCEPTION_DEFAULT_ERROR_CODE = 104;
  public static final int CUSTOM_RUNTIME_EXCEPTION_DEFAULT_ERROR_CODE = 105;
}
