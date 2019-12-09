package com.jakeporter.factorizerrefactored;

import java.util.ArrayList;
/**
 *
 * @author jake
 */
public class Factorizer {

    private int numToCheck;
    private int numOfFactors;
    private int sumOfFactors;
    private ArrayList<Integer> primeList;
    
    public Factorizer(int numToCheck){
        this.numToCheck = numToCheck;
    }
    
    public void listPrimeFactors(){
        ArrayList<Integer> primeList = new ArrayList();
        for (int i = 1; i <= this.numToCheck; i++){
            if (this.numToCheck % i == 0){
                primeList.add(i);
                this.numOfFactors++;
            }
        }
        this.primeList = primeList;
    }
    
    public void printPrimeFactors(){
        for (int i = 0; i < primeList.size()-1; i++){
            System.out.printf("%d, ", this.primeList.get(i));
        }
        System.out.println(this.primeList.get(primeList.size()-1));
    }
    
    public void sumPrimeFactors(){
        int sumPrimeFactors = 0;
        for (int i = 0; i < this.primeList.size()-1; i++){
            sumPrimeFactors += this.primeList.get(i);
        }
        this.sumOfFactors =  sumPrimeFactors;
    }
    
    public void printPerfectStatus(){
        if (this.sumOfFactors == this.numToCheck){
            System.out.printf("%d is a perfect number.\n", this.numToCheck);
        }
        else{
            System.out.printf("%d is not a perfect number.\n", this.numToCheck);
        }
    }
        
    public void printPrimeStatus(){
        if (this.numOfFactors <= 2){
            System.out.printf("%d is a prime number.\n", this.numToCheck);
        }
        else{
            System.out.printf("%d is not a prime number.\n", this.numToCheck);
        }
    }

    public int getNumToCheck() {
        return numToCheck;
    }

    public void setNumToCheck(int numToCheck) {
        this.numToCheck = numToCheck;
    }

    public int getNumOfFactors() {
        return numOfFactors;
    }

    public void setNumOfFactors(int numOfFactors) {
        this.numOfFactors = numOfFactors;
    }

    public int getSumOfFactors() {
        return sumOfFactors;
    }

    public void setSumOfFactors(int sumOfFactors) {
        this.sumOfFactors = sumOfFactors;
    }

    public ArrayList<Integer> getPrimeList() {
        return primeList;
    }

    public void setPrimeList(ArrayList<Integer> primeList) {
        this.primeList = primeList;
    }
    
    
    
}
