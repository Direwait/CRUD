import java.util.*;
import java.io.*;
public class EcoSystem {
    private List<Organism> organisms;

    private Simulation simulation;
    private String fileName;

    public EcoSystem(String fileName){
        this.fileName = fileName;
        //this.simulation=setSimulation();
        organisms = new ArrayList<>();
        loadFromFile();
    }
    //create
    public void AddOrganism(Organism organism){
        organisms.add(organism);
        //loadFromFile();
        SaveToFile();
    }
    //read
    public void DisplayOrganisms(){
        for(Organism organism:organisms){
            System.out.println(organism+"\n------------");
        }
    }
    //update
    public void UpdateOrganism(String name, Organism newOrganism) {
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).getName().equals(name)) {
                organisms.set(i, newOrganism);
                SaveToFile(); // Сохраняем изменения в файл
                return;
            }
        }
        System.out.println("Организм с именем " + name + " не найден.");
    }

    //delete
    public void DeleteOrganism(String name) {
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).getName().equals(name)) {
                organisms.remove(i);
                SaveToFile(); // Сохраняем изменения в файл
                return;
            }
        }
        System.out.println("Организм с именем " + name + " не найден.");
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
        SaveToFile();
    }
    public Simulation getSimulation() {
        return simulation;
    }

    private void SaveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Organism organism : organisms) {
                writer.write(organism.getName() + "," + organism.getOrganismType() + "," + String.join(",", organism.getDiet()));
                writer.newLine();
            }
            if (simulation != null) {
                writer.write("Simulation:" + simulation.toString());
                writer.newLine();
            }
        }
        catch (IOException e) {
            System.err.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Simulation:")) {
                    String[] parts = line.substring(11).split(",");
                    try {
                        int temperature = Integer.parseInt(parts[0].trim());
                        int humidity = Integer.parseInt(parts[1].trim());
                        int water = Integer.parseInt(parts[2].trim());
                        simulation = new Simulation(temperature, humidity, water);
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка формата числа в строке: " + line);
                    }
                }
                else {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String type = parts[1];
                    String[] diet = new String[parts.length - 2];
                    System.arraycopy(parts, 2, diet, 0, parts.length - 2);
                    AddOrganism(new Organism(name, type, diet));
                }
            }
        }
        catch (IOException e) {
            System.err.println("Ошибка при загрузке из файла: " + e.getMessage());
        }
    }
}
