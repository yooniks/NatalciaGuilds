import org.junit.Assert;
import org.junit.Test;

public class LocationConverterTests {

  @Test
  public void locationTest() {
    final double simpleDouble = 1.29;
    System.out.println(String.valueOf(simpleDouble) + " | " + String.format("%f", simpleDouble));
    Assert.assertEquals(String.valueOf(simpleDouble), String.format("%f", simpleDouble));
  }

}
