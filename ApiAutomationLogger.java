@Slf4j
public class ApiAutomationLogger {

    public static void log(String logStatement) {

        // Get the stack trace
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // The 0th element is the getStackTrace method itself, and the 1st element is the method that called it
        if (stackTrace.length >= 3) {
            StackTraceElement caller = stackTrace[2];
            String callerClassName = caller.getClassName();
            String callerMethodName = caller.getMethodName();
            logger.info(callerClassName+"."+callerMethodName + "--" +logStatement);
        }
        else {
            logger.info(logStatement);
        }
        Reporter.log(logStatement);
    }

}
