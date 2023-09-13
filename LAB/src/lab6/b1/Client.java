package lab6.b1;


public class Client {
    public Client() {
    }

    public void doUnchecked(String value) {
        try {
            int result = canThrowUncheckedException(value);
            System.out.println("result=" + result);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private int canThrowUncheckedException(String value) {
        return Integer.parseInt(value);
    }

    public void doChecked() {
        try {
            canThrowCheckedException();
            System.out.println("OK");
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private int canThrowCheckedException() throws Exception{
        throw new Exception("Failure");
    }
}

