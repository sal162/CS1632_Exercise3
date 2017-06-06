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
		
		assertEquals(1809547264, test.hash("boo"));
		assertEquals(708575232, test.hash("Alice and Bob $10"));
		assertEquals(171966464, test.hash("Hello Hello Hello"));
	}
	
	@Test
	public void testCreateBlock(){
		LaboonCoin test = new LaboonCoin();
		
		assertEquals("Alice|0000000a|0000000a|0000000a" , test.createBlock("Alice", 10, 10, 10));
		assertEquals("Bill|00000064|00000064|00000064" , test.createBlock("Bill", 100, 100, 100));
		assertEquals("hello|00000000|0000001e|0000005a" , test.createBlock("hello", 0, 30, 90));
	}
	
	@Test
	public void testGetBlockChain(){
		LaboonCoin test = new LaboonCoin();
		
		test.blockchain.add("Alice");
		
		assertEquals("Alice\n", test.getBlockChain());
		
		test.blockchain.add("Jamie");
		assertEquals("Alice\nJamie\n", test.getBlockChain());
		
		test.blockchain.add("hello");
		assertEquals("Alice\nJamie\nhello\n", test.getBlockChain());
	}
    
}
