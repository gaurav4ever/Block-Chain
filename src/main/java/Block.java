import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

    private int id;
    private Transaction transaction;
    private String date;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block(int id, Transaction transaction, String date, String previousHash) {
        this.id = id;
        this.transaction = transaction;
        this.date = date;
        this.previousHash = previousHash;
        this.nonce = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    /**
     * function to provide data string to calculate SHA256 hash
     */
    public void calculateHash() {
        String data = id + transaction.getFrom() + transaction.getTo() + transaction.getAmount() + previousHash + date + nonce;
        this.hash = getSHA265Hash(data);
    }

    /**
     * Mining function
     * --> this function check the count of zeros in front of hash of the block
     * --> Number of zeros depends on difficulty provided
     * --> random value "nonce" is incremented to meet the criteria
     * @param difficulty
     */
    public void mine(int difficulty) {

        this.calculateHash();

        String validFrontHashPart = "";
        for (int i = 0; i < difficulty; i++) {
            validFrontHashPart += "0";
        }

        while (!this.hash.substring(0, difficulty).equals(validFrontHashPart)) {
            this.nonce++;
            this.calculateHash();
        }
    }

    /**
     * Function to calculate SHA256 Hash for the given data
     * @param data
     * @return encrypted byte array
     */
    public String getSHA265Hash(String data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashByte = messageDigest.digest(data.getBytes("UTF-8"));
            return getHexHash(hashByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * function to convert byte array to hex string
     * @param hashByte
     * @return hex string
     */
    public String getHexHash(byte[] hashByte) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : hashByte) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
