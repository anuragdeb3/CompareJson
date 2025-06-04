

public class ApiExecuteUtil {
  public ApiExecuteUtil() {
  }

  public static AppResponse get(String endPoint) {
    return get(endPoint, new HashMap(), new HashMap());
  }

  public static AppResponse get(String endPoint, Map<String, Object> headers) {
    return get(endPoint, headers, new HashMap());
  }

  public static AppResponse get(String endPoint, Map<String, Object> headers, Map<String, Object> queryParams, boolean... isLogResponse) {
    if (toLogResponse(isLogResponse)) {
      ApiAutomationLogger.log("EndPoint :: " + endPoint);
      ApiAutomationLogger.log("Headers :: " + headers);
      ApiAutomationLogger.log("QueryParam :: " + queryParams);
    }

    Response response = (Response)((ValidatableResponse)((Response)requestSpecification().headers(headers).queryParams(queryParams).when().get(endPoint, new Object[0])).then()).extract().response();
    TestSetupListener.updateApiVsTestsMap(endPoint, "GET");
    return CommonUtil.mapResponse(response);
  }

  public static AppResponse post(String endPoint, String requestBody) {
    return post(endPoint, (Map)(new HashMap()), (String)requestBody, (boolean[])());
  }

  public static AppResponse post(String endPoint, Map<String, Object> headers, String requestBody, boolean... isLogResponse) {
    if (toLogResponse(isLogResponse)) {
      ApiAutomationLogger.log("POST :: " + endPoint);
      ApiAutomationLogger.log("Headers :: " + headers);
      ApiAutomationLogger.log("Request-Body :: " + requestBody);
    }

    Response response = (Response)((ValidatableResponse)((Response)requestSpecification().headers(headers).and().body(requestBody).when().post(endPoint, new Object[0])).then()).extract().response();
    TestSetupListener.updateApiVsTestsMap(endPoint, "POST");
    return CommonUtil.mapResponse(response);
  }



private static RequestSpecification requestSpecification() {
  return RestAssured.given().contentType(ContentType.JSON).relaxedHTTPSValidation();
}

private static RequestSpecification requestSpecificationWithBasicAuth(String userName, String password) {
  return RestAssured.given().auth().basic(userName, password).contentType(ContentType.JSON).relaxedHTTPSValidation();
}

private static RequestSpecification requestSpecificationWithBasicAuthAndPreemptive(String userName, String password) {
  return RestAssured.given().auth().preemptive().basic(userName, password).contentType(ContentType.URLENC).relaxedHTTPSValidation();
}

private static RequestSpecification requestSpecificationWithOuAuth2(String accessToken) {
  return RestAssured.given().auth().oauth2(accessToken).contentType(ContentType.JSON).relaxedHTTPSValidation();
}

private static RequestSpecification requestSpecificationWithEncoding(String encodingType) {
  return RestAssured.given().config(RestAssuredConfig.config().encoderConfig((new EncoderConfig()).encodeContentTypeAs(encodingType, ContentType.JSON)));
}
