package lab5.taxpayer;

public abstract class Taxpayer {
    public String id;

    public String getId() {
        return id;
    }
    public abstract double pay();
}

