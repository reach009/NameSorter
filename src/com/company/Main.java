package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The Goal : Name Sorter
 * Given a set of names, order that set first by last name, then by any given names the person may have.
 * A name must have at least 1 given name and may have up to 3 given names.
 *
 * @author Reach
 * @version 1.0
 * @since 27 August 2020
 */
public class Main {

    public static void main(String[] args) {

        ArrayList<String> nameList = new ArrayList<String>();

        // get the name list from txt file and store them into an ArrayList for processing
        nameList = readNameFromTextFile("unsorted-names-list.txt");

        // sort the name list
        if(!nameList.isEmpty()){
            nameList = sortedNameList(nameList);
        }

        // display the sorted names
        for(String tempName : nameList) {
            System.out.println(tempName);
        }

        // generate txt file containing sorted name list
        writeArrayListToTextFile(nameList, "sorted-names-list.txt");
    }

    /**
     * Read txt file and store the list of names into an ArrayList
     *
     * @param txtFileLocation This is a text file location.
     * @return nameList This is an array list of unsorted names from txt file.
     * @throws FileNotFoundException On input error.
     * @see FileNotFoundException
     */
    private static ArrayList readNameFromTextFile(String txtFileLocation) {
        ArrayList<String> nameList = new ArrayList<String>();

        try {
            File myObj = new File(txtFileLocation);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String name = myReader.nextLine();
                nameList.add(name);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        return nameList;
    }

    /**
     * Take in ArrayList of names and sort them first by last name, then follow by first name
     *
     * @param nameList This is an unsorted list of names.
     * @return sortedNames This is a sorted list of names.
     */
    private static ArrayList sortedNameList(ArrayList<String> nameList) {
        ArrayList<String> sortedNames = new ArrayList<String>();
        ArrayList<PersonName> nameCollection = new ArrayList<PersonName>();

        // break first name and last name, then store them as separate name entity
        for (String tempName : nameList) {
            int lastIndexOfSpace = tempName.lastIndexOf(" ");
            String firstName = tempName.substring(0, lastIndexOfSpace);
            String lastName = tempName.substring(lastIndexOfSpace + 1);
//            System.out.println(firstName + " --- " + lastName);
            PersonName name = new PersonName(firstName, lastName);
            nameCollection.add(name);
        }

        // sort the names
        Collections.sort(nameCollection);

        // copy them to our array list of strings
        for (PersonName tempName : nameCollection) {
            sortedNames.add(tempName.toString());
        }

        return sortedNames;
    }

    /**
     * Write the ArrayList of names to the output text file
     *
     *
     * @param nameList This is a name list to be stored into the output text file.
     * @param fileName This is a file to be generated.
     */
    private static void writeArrayListToTextFile(ArrayList<String> nameList, String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for(String name : nameList) {
                myWriter.write(name);
                myWriter.write(String.format("%n"));
            }
            myWriter.close();
            System.out.println("********************************************");
            System.out.println("Successfully wrote to the file " + fileName);
            System.out.println("********************************************");
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
