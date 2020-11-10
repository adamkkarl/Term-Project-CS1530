import org.junit.runner.*;
import org.junit.runner.notification.*;


public class TestRunner
{
  public static void main(String[] args)
  {
    Result result = JUnitCore.runClasses(SharklineTest.class);

    for(Failure failure : result.getFailures())
    {
      System.out.println(failure.toString());
    }

    System.out.println(result.wasSuccessful());
  }
}
