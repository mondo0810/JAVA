package lab2.b2;

public class Main {
    public static void main(String[] args) {
        FlashLamp flashLamp = new FlashLamp();
        Battery battery = new Battery(100);
        flashLamp.setBattery(battery);
        flashLamp.turnOn();
        flashLamp.light();
        flashLamp.turnOff();
        System.out.println(flashLamp.getBatteryInfo() + "%");
    }
}
