package testmodule;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddTest {

	Calculator calc = new Calculator();
	int sum = calc.add(4, 7);
	int testsum = 11;
	
	@Test
	public void testSum()
	{
		System.out.println("@Test " + sum + " = " + testsum);
		assertEquals(sum, testsum);
	}
	
}
