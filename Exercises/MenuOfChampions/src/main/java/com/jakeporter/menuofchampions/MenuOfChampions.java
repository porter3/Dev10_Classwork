package com.jakeporter.menuofchampions;

/**
 *
 * @author jake
 */
public class MenuOfChampions {
    public static void main(String[] args){
        
        String cerealA = "Frosted Flakes";
        float priceA = (float) 1.75;
        String cerealB = "Cocoa Puffs";
        float priceB = (float) 2.0;
        String cerealC = "Raisin Bran";
        float priceC = (float) 2.25;
        
        System.out.println("  __    __   __   __   __   __   __   __    __\n" +
" _\\/_  _\\/_ _\\/_ _\\/_ _\\/_ _\\/_ _\\/_ _\\/_  _\\/_\n" +
" \\/\\/  \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/  \\/\\/" + "\n");
        
        System.out.println("Welcome to Jake's Cereal Shack!\n" + "Today's Menu Is:");
        
        System.out.println("  __    __   __   __   __   __   __   __    __\n" +
" _\\/_  _\\/_ _\\/_ _\\/_ _\\/_ _\\/_ _\\/_ _\\/_  _\\/_\n" +
" \\/\\/  \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/ \\/\\/  \\/\\/" + "\n");
        
        System.out.println(cerealA + "   " + "$ " + priceA);
        System.out.printf("%s      $ %.2f%n", cerealB, priceB);
        System.out.println(cerealC + "      " + "$ " + priceC);
    }
    
}
