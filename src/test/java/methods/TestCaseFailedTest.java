package methods;
import static org.junit.Assert.*;
import org.junit.*;
public class TestCaseFailedTest {
    @Test
    public void testCaseFail() {
        TestCaseFailed tester = new TestCaseFailed("This is a failure of a test");
        // assert statements
        assertEquals(tester.message, "This is a failure of a test");
    }
}