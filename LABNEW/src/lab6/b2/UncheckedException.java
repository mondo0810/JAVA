package lab6.b2;

public class UncheckedException{
    public UncheckedException () {
        // To do:
    }
    public static void main(String[] args) {
        int i, n = 2;
        int a[] = new int[n];

        //Declare Scanner Object named input
        java.util.Scanner input = new java.util.Scanner(System.in);

        for (i = 0; i < n; i++) {
            try {
                System.out.printf("a[%d] = ", i);
                a[i] = input.nextInt();
            } catch (java.util.InputMismatchException ex) {
                System.out.println("Input mismatch. Please enter an integer.");
                input.nextLine();
                i--;
            }
        }

        input.close();

        System.out.print("a[] = ");
        for (i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
