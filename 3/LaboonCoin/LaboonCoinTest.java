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
		assertEquals(test.validHash(2, 0000001), true);
	}
    
}
