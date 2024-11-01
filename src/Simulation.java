public class Simulation {
    private Simulation simulation;
    private String fileName;
    private int temperature;
    private int humidity;
    private int water;

    public Simulation(int temperature, int humidity, int water) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.water = water;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s",temperature,humidity,water);
    }
}
