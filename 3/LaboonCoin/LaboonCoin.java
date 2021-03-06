import java.util.*;

public class LaboonCoin {

    public ArrayList<String> blockchain = new ArrayList<String>();

    /**
     * Given a block's data, the previous hash, a nonce, and a final
     * hash which is the hash of the previous three data elements,
     * will generate a String.  This String will contain all of the above
     * elements, with the hashes and nonce values displayed in 8-hexit
     * hexadecimal values.  This means that values must be prefixed with
     * 0's to ensure that they are padded to eight characters.
     * Each data element is separated by a pipe (|).
     * Example:
     * Bill Gave Joe $10|00000000|001aa59c|000e854a
     * ^data             ^prev    ^nonce   ^finalHash
     * @param data - Included block data
     * @param prevHash - The hash value of the previous block
     * @param nonce - the nonce value for this block
     * @param hash - the final hash value for this block
     * @return String - string representation of the block
     */

    public String createBlock(String data, int prevHash, int nonce, int hash) {

        StringBuilder dataBlock = new StringBuilder();

        String hex1 = String.format("%08x", prevHash);
        String hex2 = String.format("%08x", nonce);
        String hex3 = String.format("%08x", hash);



        dataBlock.append(data);
        dataBlock.append("|");

        dataBlock.append(hex1);
        dataBlock.append("|");

        dataBlock.append(hex2);
        dataBlock.append("|");

        dataBlock.append(hex3);

        String block = dataBlock.toString();

	   return block;
    }

    /**
     * Return the entire blockchain, separated by \n's
     * (carriage returns) as a String.
     * @return String - string format of the entire blockchain
     */
    public String getBlockChain() {
		String output = "";
		for(int i = 0; i < blockchain.size(); ++i){
			output += blockchain.get(i);
			output += "\n";
		}
		return output;
    }

    /**
     * Given a data in String format, find its LaboonHash value.
     * Note LaboonHash is generally not a hashing algorithm you would
     * want to use in real life.
     * The LaboonHash algorithm is as follows:
     *   1. Convert a String into a sequence of characters
     *   2. Initialize a starting value, n, of 10000000 (10 million)
     *   3. For each character, multiply n by its ASCII (char) value
     *   4. After multiplication, add the value of the ASCII (char) value to n
     *   5. Return n
     * For example, the LaboonHash of "boo" is:
     *   n = 1000000
     *   n = (n * 98) + 98 // 98 = ASCII of 'b'
     *   n = (n * 111) + 111 // 111 = ASCII of 'o'
     *   n = (n * 111) + 111 // 111 = ASCII of 'o'
     *   Final hash: 12074581219890
     * Or:
     *   n = 1000000
     *   n = (1000000 * 98) + 98 = 980000098
     *   n = (980000098 * 111) + 111 = 108780010989
     *   n = (108780010989 * 111) + 11 = 12074581219890
     *   n = 12074581219890
     * However, this is held in a 32-bit signed int and so will wrap around!
     * Thus it will be 1428150834, or 0x551fda32.
     * @param data - entire piece of data to hash
     * @return int - hash value using LaboonHash algorithm
     */

    public int hash(String data) {
	// TODO
        char[] dataNew = data.toCharArray();
        int n = 10000000;


        for(int i = 0; i < dataNew.length; i++){
            int ascii = dataNew[i];
            n = (n * ascii) + ascii;
        }

	return n;
    }

    /**
     * Given a certain level of difficulty, check to see if a given hash
     * has that many 0's at its beginning, when expressed as a hex String.
     * For example, assume difficulty level is set to 3.
     * 0x098ab873 is NOT valid - it only has one 0 at the beginning
     * 0xab000000 is NOT valid - despite having six 0's, they are not at the
     *                           beginning
     * 0x000fd98a IS valid     - it has three 0's at the beginning
     * 0x000000d4 IS valid     - it has three 0's at the beginning, plus more
     *
     * @param difficulty - Difficulty level (number of 0's)
     * @param hash - hash value to check
     * @return boolean - true if hash is valid for a block, false otherwise
     */

    public boolean validHash(int difficulty, int hash) {
	// TODO

		String hex = Integer.toHexString(hash);
        hex = String.format("%08X", hash);
		for(int i = 0; i < hex.length() && difficulty > 0; ++i){

			if(hex.charAt(i) == '0'){
				--difficulty;
			}else{
				return false;
			}
		}
		return true;

    }

        /**
     * Given some data and the previous hash value, find a nonce that
     * will make the data + hex string version of the previous hash
     * equal to a hash value with a given number of 0's at its front.
     * The difficulty is the number of 0's necessary.  Note that average
     * time to hash will increase by a factor of 16 for each additional
     * level of difficulty.
     * Note that if difficulty is set too high, a nonce may NEVER be found
     * since we are using ints which are finite.  I recommend you leave
     * the difficulty setting at the default (3).
     * @param data - The data in the block
     * @param prevHash - The hash value of the previous block
     * @param difficulty - The number of 0's to consider a hash valid
     * @return int - A nonce which makes the block's hash valid
     */

    public int mine(String data, int prevHash, int difficulty) {
    int nonce = 0;
    String toTry;
    int hashVal = 0;
    boolean foundNonce = false;
    while (!foundNonce) {
        toTry = String.format("%08x", prevHash) + String.format("%08x", nonce) + data;
        // Uncomment for debugging purposes
        // System.out.print("Trying: " + toTry + ".. ");

        hashVal = hash(toTry);
        System.out.println("hash: " + String.format("%08x", hashVal));
        if (validHash(difficulty, hashVal)) {
        foundNonce = true;
        } else {
        nonce++;
        }
        if (nonce == -1) {
        System.err.println("Could not find nonce");
        }
    }
    return nonce;
    }

    /**
     * Run the program.
     * Loop until the user types 'q' or "Q" to quit.
     * After entering the data, the
     * PrevHash for the first ("genesis") block is by default 0 (0x00000000).
     * Note that this is in fact a valid hash for any
     */

    public void run(int difficulty) {
    Scanner sc = new Scanner(System.in);
    boolean keepRunning = true;
    int prevHash = 0;
    while (keepRunning) {
        System.out.print("Enter data > ");
        String data = sc.nextLine();
        if (data.equalsIgnoreCase("q")) {
        System.out.println("Final Blockchain:");
        System.out.println(getBlockChain());
        keepRunning = false;
        } else {
        System.out.println("Hash (just data) = " + String.format("%08x", hash(data)));
        System.out.println("Mining..");
        int nonce = mine(data, prevHash, difficulty);
        System.out.println("Found nonce " + String.format("%08x", nonce) + "!");
        int finalHash = hash(
                     String.format("%08x", prevHash)
                     + String.format("%08x", nonce)
                     + data);
        System.out.println("Final hash " + String.format("%08x", finalHash) + "!");


        String newBlock = createBlock(data, prevHash, nonce, finalHash);

        //printing prev and final were switched
        prevHash = finalHash;

        blockchain.add(newBlock);
        }

    }
    }

    /**
     * Program entry point.
     * Creates a new LaboonCoin instance and runs it.
     * Accepts one command-line argument, the difficulty level
     * This corresponds to how many 0's must be at the beginning of a
     * hash for it to be valid and for the block to be mined.
     * If this cannot be read or parsed, it will default to a difficulty
     * of 1.
     * @param args[] - command line arguments
     */

    public static void main(String[] args) {
    int difficulty = 3;
    try {
        difficulty = Integer.parseInt(args[0]);
        if (difficulty < 0) {
        System.out.println("Negative numbers not allowed, defaulting to dificulty = 3");
        difficulty = 3;
        }
    } catch (ArrayIndexOutOfBoundsException oobex) {
        System.out.println("No argument detected, defaulting to difficulty = 3");
    } catch (NumberFormatException nfex) {
        System.out.println("Could not parse argument, defaulting to difficulty = 3");
    }
    LaboonCoin lc = new LaboonCoin();
    lc.run(difficulty);
    }
}
