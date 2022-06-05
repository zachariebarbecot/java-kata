package fr.zbar.kata.employeereport;

public record Employee(String name, Integer age) {

    private static final int ADULT_AGE = 18;

    public Boolean isAdult() {
        return this.age >= ADULT_AGE;
    }

    @Override
    public String name() {
        return name.toUpperCase();
    }
}
