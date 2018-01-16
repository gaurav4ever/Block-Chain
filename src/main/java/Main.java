import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static BlockChain blockChain = BlockChain.getInstance();

    public static void main(String args[]) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        boolean exitProgram = false;
        while (!exitProgram) {
            System.out.println("Menu: ");
            System.out.println("1. Add Block");
            System.out.println("2. View Block chain");
            System.out.println("3. Check Block chain validity");
            System.out.println("4. Exit");
            int choice = Integer.parseInt(input.readLine());
            switch (choice) {
                case 1:
                    BlockHandler blockHandler = new BlockHandler();
                    blockHandler.createTransaction(input);
                    break;
                case 2:
                    blockChain.displayBlockChain();
                    break;
                case 3:
                    if (blockChain.isValid()) {
                        System.out.println("Block chain is Valid ");
                    } else {
                        System.out.println("Block chain is not Valid ");
                    }
                    break;
                case 4:
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
