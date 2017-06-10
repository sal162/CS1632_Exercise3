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

	}
    @Test
    public void testValidHash2(){
        LaboonCoin test = new LaboonCoin();
        assertEquals(false, test.validHash(3, 4548489));
    }

    @Test
    public void testValidHash3(){
        LaboonCoin test = new LaboonCoin();
        assertEquals(true, test.validHash(2, 4548489));
    }

    @Test
    public void testValidHash4(){
        LaboonCoin test = new LaboonCoin();
        assertEquals(true, test.validHash(3, 354185));
    }

	@Test
	public void testHash(){
		LaboonCoin test = new LaboonCoin();
		assertEquals(1428150834, test.hash("boo"));
	}

    @Test
	public void testHash2(){
		LaboonCoin test = new LaboonCoin();
		assertEquals(923946016, test.hash("Alice and Bob $10"));
	}

    @Test
	public void testHash3(){
		LaboonCoin test = new LaboonCoin();
		assertEquals(-1941252253, test.hash("Hello Hello Hello"));
	}

	@Test
	public void testCreateBlock(){
		LaboonCoin test = new LaboonCoin();
		assertEquals("Alice|0000000a|0000000a|0000000a" , test.createBlock("Alice", 10, 10, 10));
	}

    @Test
	public void testCreateBlock2(){
		LaboonCoin test = new LaboonCoin();
		assertEquals("Bill|00000064|00000064|00000064" , test.createBlock("Bill", 100, 100, 100));
	}

    @Test
	public void testCreateBlock3(){
		LaboonCoin test = new LaboonCoin();
		assertEquals("hello|00000000|0000001e|0000005a" , test.createBlock("hello", 0, 30, 90));
	}

	@Test
	public void testGetBlockChain(){
		LaboonCoin test = new LaboonCoin();

		test.blockchain.add("Alice");
		assertEquals("Alice\n", test.getBlockChain());
	}

    @Test
	public void testGetBlockChain2(){
		LaboonCoin test = new LaboonCoin();

		test.blockchain.add("Alice");
		test.blockchain.add("Jamie");
		assertEquals("Alice\nJamie\n", test.getBlockChain());
	}

    @Test
	public void testGetBlockChain3(){
		LaboonCoin test = new LaboonCoin();

		test.blockchain.add("Alice");
		test.blockchain.add("Jamie");
		test.blockchain.add("hello");
		assertEquals("Alice\nJamie\nhello\n", test.getBlockChain());
	}

}
