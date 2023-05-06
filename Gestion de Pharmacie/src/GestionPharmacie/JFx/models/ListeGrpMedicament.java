package GestionPharmacie.JFx.models;

import io.github.palexdev.materialfx.utils.RandomUtils;

import java.util.Objects;

public class ListeGrpMedicament {

    private final String name;
    private final String surname;
    private int age;

    public ListeGrpMedicament(String name) {
        this.name = name;
        this.surname = "";
    }

    public ListeGrpMedicament(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public ListeGrpMedicament(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public static ListeGrpMedicament ofSplit(String fullName, String split) {
        String[] fNameArray = fullName.split(split);
        return new ListeGrpMedicament(fNameArray[0], fNameArray[1]);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ListeGrpMedicament randomAge() {
        setAge(RandomUtils.random.nextInt(18, 81));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListeGrpMedicament GrpMedicament = (ListeGrpMedicament) o;
        return getName().equals(GrpMedicament.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public String getNomGrpMedicament() {
        return null;
    }

}
