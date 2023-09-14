package lab5.taxpayer;

public class Main {
    public static void main(String[] args) {
FreeLand fl1 = new FreeLand();
        TaxManager tm1 = new TaxManager();

        System.out.println(fl1.pay());;
        System.out.println(tm1.addTaxpayer(fl1));;
        System.out.println(tm1.addTaxpayer(fl1));;
        System.out.println(tm1.addTaxpayer(fl1));;
        System.out.println(tm1.getTax());;
    }
}
