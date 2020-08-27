package com.company;

import java.util.Comparator;

/**
 * Class to store a name as an entity
 *
 * @author Reach
 * @version 1.0
 * @since 27 August 2020
 */
public class PersonName implements Comparable<PersonName>{
    private String firstName;
    private String lastName;

    public PersonName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int compareTo(PersonName otherName) {
        return Comparator.comparing(PersonName::getLastName)
                            .thenComparing(PersonName::getFirstName)
                            .compare(this,otherName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
