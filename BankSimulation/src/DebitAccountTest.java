import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DebitAccountTest {
    Bank bank = new Bank();
    Account debitAccount;

    @Before
    public void SetUp() throws Exception{
        debitAccount = bank.addAccount("Adam", 500.0, 0);
    }

    @Test
    public void Withdraw() throws Exception{
        //Test for withdrawing too much money
        Exception exception = assertThrows(RuntimeException.class, () -> {
            debitAccount.withdraw(70000.0);
        });
        Exception exception1 = assertThrows(RuntimeException.class, () -> {
            debitAccount.withdraw(-1.0);
        });
        debitAccount.withdraw(200.0);
        assertEquals("Account Name: Adam Balance: €300.0\n", debitAccount.toString());
        debitAccount.withdraw(45.32);
        assertEquals("Account Name: Adam Balance: €254.68\n", debitAccount.toString());
    }

    @Test
    public void Deposit() throws Exception{
        Exception exception1 = assertThrows(RuntimeException.class, () -> {
            debitAccount.deposit(-1.0);
        });
        debitAccount.deposit(200.0);
        assertEquals("Account Name: Adam Balance: €700.0\n", debitAccount.toString());
        debitAccount.deposit(45.32);
        assertEquals("Account Name: Adam Balance: €745.32\n", debitAccount.toString());
    }

    @Test
    public void DisplayBalance() throws Exception{
        assertEquals("Balance: €500.0", debitAccount.displayBalance());
    }

    @Test
    public void Equals() throws Exception{
        Account debitAccount1 = bank.addAccount("Dylan", 300.0, 0);
        assertTrue(debitAccount.equals(debitAccount));
        assertFalse(debitAccount.equals(debitAccount1));
    }

}
