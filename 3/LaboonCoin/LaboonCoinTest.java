import static org.junit.Assert.*;

import org.junit.*;

public class LaboonCoinTest {

    // Assert that creating a new LaboonCoin instance
    // does not return a null reference
    @Test
    public void testLaboonCoinExists() {
	LaboonCoin l = new LaboonCoin();
	assertNotNull(l);
    }
	
	@Test
	public void testValidHash(){
		LaboonCoin test = new LaboonCoin();
		
		assertEquals(true, test.validHash(2, 3));
		assertEquals(false, test.validHash(2, 300000000));
		assertEquals(false, test.validHash(3, 3000000));

	}
	
	@Test
	public void testHash(){
		LaboonCoin test = new LaboonCoin();
		
		//assertEquals();
	}
    
}
