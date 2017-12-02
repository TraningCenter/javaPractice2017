package com.alegerd.mainData;

public class ViewController {

    private static final Integer LIFT_SIZE = 3;
    private static final Integer SPACE_BETWEEN_PEOPLE = 1;

    static Integer floorLength;
    static Integer numberOfLifts;
    static Integer sizeOfSection;
    static Integer numberOfPeopleInSection;
    static Integer numberOfFloors;

    /**
     *
     * @return number of spaces in general on the floor
     */
    public static Integer getFloorLenght() {
        return floorLength;
    }

    /**
     *
     * @param floorSize number of spaces on the floor
     */
    public static void setFloorLength(Integer floorSize) throws IllegalArgumentException{
        if(floorSize < 4) throw new IllegalArgumentException("Too small floor size");
        else
            floorLength = floorSize;
    }

    public static Integer getNumberOfLifts(){
        return numberOfLifts;
    }

    /**
     * sets number of lifts on the floor
     * @param number how many lifts
     * @throws Exception if number is bigger than floor can handle
     */
    public static void setNumberOfLifts(Integer number) throws IllegalArgumentException{
        if(number < 1) throw new IllegalArgumentException("Amount of lifts should be more than zero");
        if(!checkNumberOfLifts(number)) throw new IllegalArgumentException("Too many lifts for this house");
        else
            numberOfLifts = number;
            countSizeOfSection();
            countNumberOfPeopleInSection();
    }

    /**
     *
     * @return how many people can be in one floor section
     */
    public static Integer getNumberOfPeopleInSection() {
        return numberOfPeopleInSection;
    }

    /**
     *
     * @return How many sections on this floor
     */
    public static Integer getNumberOfSections(){
        return numberOfLifts;
    }

    /**
     *
     * @return How many floors in this house
     */
    public static Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     *
     * @param numberOfFloors Number of floors in house
     */
    public static void setNumberOfFloors(Integer numberOfFloors) {
        ViewController.numberOfFloors = numberOfFloors;
    }

    private static boolean checkNumberOfLifts(Integer num){

        return num <= floorLength/LIFT_SIZE;
    }

    private static void countSizeOfSection() throws IllegalArgumentException{
        Integer cell = floorLength/numberOfLifts;
        cell = cell - LIFT_SIZE;
        if(cell < 1) throw new IllegalArgumentException("Not enough space on the floor");
        else {
            sizeOfSection = cell;
        }
    }


    private static void countNumberOfPeopleInSection() throws IllegalArgumentException{
        if(sizeOfSection < 1) throw new IllegalArgumentException("Not enough space on the floor");
        else {
            numberOfPeopleInSection = sizeOfSection;
        }
    }
}
