public class AssertionUtil {

    public static void assertEquals(String message, Object expected, Object actual) {

        ApiAutomationLogger.log("Expected  :: " + expected);
        ApiAutomationLogger.log("Actual  :: " + actual);
        Assert.assertEquals(actual, expected, message);
    }
