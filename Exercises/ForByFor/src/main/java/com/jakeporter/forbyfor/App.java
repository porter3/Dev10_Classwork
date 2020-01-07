package com.jakeporter.forbyfor;

/**
 *
 * @author jake
 */
public class App {

    public static void main(String[] args){
        
        //rows
        for (int i = 1; i <= 3; i++){
            
            System.out.print("|");
            
            //first and third rows
            if ((i == 1) || (i ==3)){
                for (int j = 1; j <= 3; j++){

                    //stars in first and third columns on first and third column iterations
                    if ((j == 1) || (j == 3)){
                        for (int k = 0; k < 3; k++){
                            System.out.print("*");
                        }
                    }
                    else{
                        for (int l = 1; l <= 3; l++){
                            System.out.print("$");
                        }
                    }
                    System.out.print("|");
                }
            }
            
            //second row
            else{
                for (int m = 1; m <= 3; m++){
                    
                    //@ symbol in 1st and 3rd columns
                    if ((m == 1) || (m == 3)){
                        for (int k = 1; k <= 3; k++){
                            System.out.print("@");
                        }
                    }
                    else{
                        for (int l = 1; l <= 3; l++){
                            System.out.print("#");
                        }
                    }
                    System.out.print("|");
                }
            }
            
            System.out.println("");
        }
    }
}