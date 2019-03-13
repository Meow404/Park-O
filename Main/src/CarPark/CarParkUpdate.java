package CarPark;

public class CarParkUpdate {
    private int lotsAvailable;
    private int totalLots;
    private String lotType;

    public int getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(int lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String lastUpdate;

    public CarParkUpdate() {
        lotsAvailable = 0;
        totalLots = 0;
        lotType = "";
        lastUpdate = "";
    }

    public CarParkUpdate(int lotsAvailable, int totalLots, String lotType, String lastUpdate) {
        this.lotsAvailable = lotsAvailable;
        this.totalLots = totalLots;
        this.lotType = lotType;
        this.lastUpdate = lastUpdate;
    }
}
