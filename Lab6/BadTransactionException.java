package Lab6;

// an exception for depositing/withdrawing negative values

public class BadTransactionException extends Exception{

    public int value;

    public BadTransactionException(int badValue) {
        super("Invalid transaction value: " + badValue);
        value = badValue;
    }
}
