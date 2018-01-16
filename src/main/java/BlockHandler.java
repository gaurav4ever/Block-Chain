import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlockHandler {

    private BlockChain blockChain = BlockChain.getInstance();

    public void createTransaction(BufferedReader input) throws IOException {

        String from, to;
        double amount;

        System.out.println("Transfer Money from : ");
        from = input.readLine();
        System.out.println("Transfer Money from : ");
        to = input.readLine();
        System.out.println("Transfer amount : ");
        amount = Double.parseDouble(input.readLine());

        Transaction transaction = new Transaction();
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setAmount(amount);

        createBlock(transaction);
    }

    public void createBlock(Transaction transaction) {
        Block block=new Block(blockChain.getPreviousBlockId(),transaction,getDate(),"0");
        System.out.println("Adding Block to Block Chain");
        blockChain.addBlock(block);
        System.out.println("Block added");
    }

    public String getDate(){
        Date dateNow = new Date( );
        SimpleDateFormat format = new SimpleDateFormat ("E dd.MM.yyyy @ hh:mm:ss a Z");
        return format.format(dateNow);
    }
}
