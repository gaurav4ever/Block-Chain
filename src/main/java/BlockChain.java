import java.util.LinkedList;

public class BlockChain {

    private LinkedList<Block> blocks;
    private int difficulty = 4;

    private static BlockChain blockChain = new BlockChain();

    private BlockChain() {
        createGenesisBlock();
    }

    public static BlockChain getInstance() {
        return blockChain;
    }

    /**
     * Function to create Genesis Block
     * Genesis Block
     *      - First block in the chain
     *      - No previous hash reference
     */
    public void createGenesisBlock() {

        Transaction transaction = new Transaction();
        transaction.setFrom("a");
        transaction.setTo("b");
        transaction.setAmount(120.45);

        Block block = new Block(1, transaction, "16/01/2018", "0");

        blocks = new LinkedList<>();
        blocks.addLast(block);
    }

    /**
     * Function to add block into chain
     * add previous hash for reference
     * and create a valid hash through mining
     * @param block
     */
    public void addBlock(Block block) {
        block.setPreviousHash(this.blocks.getLast().getHash());
        block.mine(this.difficulty);
        blocks.addLast(block);
    }

    /**
     * @return ID of the last block in chain
     */
    public int getPreviousBlockId() {
        return this.blocks.getLast().getId();
    }

    /**
     * Tells if chain is valid or not
     * @return true if chain is valid, false otherwise
     */
    public boolean isValid() {
        for (int i = 1; i < this.blocks.size(); i++) {
            Block currentBlock = this.blocks.get(i);
            Block previousBlock = this.blocks.get(i - 1);

            if (currentBlock.getPreviousHash() != previousBlock.getHash()) {
                return false;
            }

            String currentBlockOldHash = currentBlock.getHash();
            currentBlock.calculateHash();
            String currentBlockNewHash = currentBlock.getHash();
            if (!currentBlockOldHash.equals(currentBlockNewHash)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Displays block details
     */
    public void displayBlockChain() {
        int i = 0;
        for (Block block : this.blocks) {
            System.out.println("\n*** Block " + (i++) + " ***");

            System.out.println("\tID: " + block.getId());
            System.out.println("\tDate Created: " + block.getDate());
            System.out.println("\tHash: " + block.getHash());
            System.out.println("\tPrevious Hash: " + block.getPreviousHash());

            Transaction transaction = block.getTransaction();

            System.out.println("- Transaction Details -");
            System.out.println("\tFrom: " + transaction.getFrom());
            System.out.println("\tTo: " + transaction.getTo());
            System.out.println("\tAmount: " + transaction.getAmount());
        }
    }
}
