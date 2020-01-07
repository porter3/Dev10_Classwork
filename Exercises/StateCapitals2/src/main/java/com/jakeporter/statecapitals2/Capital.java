package com.jakeporter.statecapitals2;

/**
 *
 * @author jake
 */
public class Capital {

    private String name;
    private int population;
    private int sqMileage;

    public Capital(String name, int population, int sqMileage) {
        this.name = name;
        this.population = population;
        this.sqMileage = sqMileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getSqMileage() {
        return sqMileage;
    }

    public void setSqMileage(int sqMileage) {
        this.sqMileage = sqMileage;
    }
    
    
}
