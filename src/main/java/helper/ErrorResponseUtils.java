public class ErrorResponseUtils {

    public static ErrorResponse construct(String businessPartnerErrorMsg, int businessPartnerErrorCode, String errorMsg, int code) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail();

        errorResponse.setMessage(businessPartnerErrorMsg);
        errorResponse.setErrorCode(businessPartnerErrorCode);

        errorDetail.setMessage(errorMsg);
        errorDetail.setCode(code);
        errorResponse.setErrors(List.of(errorDetail));
        return errorResponse;
    }

}
