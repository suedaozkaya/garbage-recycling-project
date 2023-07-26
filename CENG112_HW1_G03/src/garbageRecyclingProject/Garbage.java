package garbageRecyclingProject;

public class Garbage {

    private String garbageName;
    private String garbageType;
    private String garbageAmount;

    public Garbage(String garbageName, String garbageType, String garbageAmount) {
        this.garbageName = garbageName;
        this.garbageType = garbageType;
        this.garbageAmount = garbageAmount;
    }

    public Garbage(){

    }

    public String getGarbageName() {
        return garbageName;
    }

    public void setGarbageName(String garbageName) {
        this.garbageName = garbageName;
    }

    public String getGarbageType() {
        return garbageType;
    }

    public void setGarbageType(String garbageType) {
        this.garbageType = garbageType;
    }

    public String getGarbageAmount() {
        return garbageAmount;
    }

    public void setGarbageAmount(String garbageAmount) {
        this.garbageAmount = garbageAmount;
    }

    @Override
    public String toString() {
        return "Garbage Name: '" + garbageName + '\'' +
                ", Garbage Type: '" + garbageType + '\'' +
                ", Total Garbage Amount: '" + garbageAmount + '\'';
    }

    public boolean equals(Object obj){
        return true;
    }
}
