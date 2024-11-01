import java.util.Arrays;

public class Organism {
    private String name;
    private String organismType;
    private String[] diet;

    @Override
    public String toString(){
        return String.format("НАЗВАНИЕ ОРГАНИЗМА: %s\nТИП ОРГАНИЗМА:%s\nПИТАНИЕ:%s",name,organismType,String.join(", ", diet));
    }

    public Organism(String name, String organismType, String[] diet){
        this.name=name;
        if(organismType.equals("animal")||organismType.equals("plant")) this.organismType=organismType;
        else this.organismType="animal";
        this.diet=diet;
    }

    public String getName() {
        return name;
    }
    public String getOrganismType() {
        return organismType;
    }
    public String[] getDiet() {
        return diet;
    }
}
