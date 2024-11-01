import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла симуляции (например, simulation.txt):");
        String fileName = scanner.nextLine();
        if(fileName.isEmpty()) return;

        File file=new File(fileName);
        if(!file.exists()){
            try{
                if(file.createNewFile()){System.out.println("Файл "+fileName+" был создан");}
                else{System.out.println("Не удалось создать файл "+fileName);}
            }
             catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        EcoSystem ecosystem = new EcoSystem(fileName);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить организм");
            System.out.println("2. Удалить организм");
            System.out.println("3. Обновить организм");
            System.out.println("4. Показать организмы");
            System.out.println("5. Настроить условия экосистемы");
            System.out.println("6. Показать условия экосистемы");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Введите имя организма:");
                    String name = scanner.nextLine();
                    System.out.println("Введите тип организма (plant/animal):");
                    String type = scanner.nextLine();
                    System.out.println("Введите диету (через запятую):");
                    String[] diet = scanner.nextLine().split(",");
                    ecosystem.AddOrganism(new Organism(name, type, diet));
                    break;

                case 2:
                    System.out.println("Введите имя организма для удаления:");
                    String removeName = scanner.nextLine();
                    ecosystem.DeleteOrganism(removeName);
                    break;

                case 3:
                    System.out.println("Введите имя организма для обновления:");
                    String updateName = scanner.nextLine();
                    System.out.println("Введите новое имя организма:");
                    String newName = scanner.nextLine();
                    System.out.println("Введите новый тип организма (plant/animal):");
                    String newType = scanner.nextLine();
                    System.out.println("Введите новую диету (через запятую):");
                    String[] newDiet = scanner.nextLine().split(",");
                    ecosystem.UpdateOrganism(updateName, new Organism(newName, newType, newDiet));
                    break;
                case 4:
                    ecosystem.DisplayOrganisms();
                    break;
                case 5:
                    System.out.println("Введите температуру:");
                    int temperature = scanner.nextInt();
                    System.out.println("Введите влажность:");
                    int humidity = scanner.nextInt();
                    System.out.println("Введите доступную воду:");
                    int water = scanner.nextInt();
                    ecosystem.setSimulation(new Simulation(temperature, humidity, water));
                    break;
                case 6:
                    System.out.println("Температура, влажность, доступность воды: "+ecosystem.getSimulation());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Некорректный выбор.");
            }
        }
    }
}
