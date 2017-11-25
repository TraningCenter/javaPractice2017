package com.alegerd.view;

import com.alegerd.model.Floor;
import com.alegerd.model.House;
import com.alegerd.model.Lift;
import com.alegerd.model.Person;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads input file and constructs House object out of it
 */
public class Parser {

    private String pathToInputFile;

    public Parser(){

    }

    /**
     * Parses input file data and builds the house
     * @param pathToInputFile path to input file
     * @return new House
     */
    public House parseInputFile(String pathToInputFile){
        House newHouse = null;

        try{
            String data;
            StringBuilder sb = new StringBuilder();
            Files.lines(Paths.get(pathToInputFile), StandardCharsets.UTF_8).forEach(sb::append);
            data = sb.toString();
            String[] inputList = data.split(";");

            List<IFloor> floors = new ArrayList<>();
            List<ILift> lifts = new ArrayList<>();

            //заполнение списка лифтов
            Integer amountOfLifts = Integer.parseInt(inputList[0].split(" ")[1]);
            for (int i = 0; i < amountOfLifts; i++){
                Lift newLift = new Lift(i);
                lifts.add(newLift);
            }

            //заполнение списка этажей
            Integer amountOfFloors = Integer.parseInt(inputList[1].split(" ")[1]);
            for (int i = 0; i < amountOfFloors; i++){
                Floor newFloor = new Floor(i+1);
                floors.add(newFloor);
            }

            for(int i = 2; i < inputList.length; i++){
                Person newPerson = new Person(i,
                        Integer.parseInt(inputList[i].split(" ")[1]),
                        Integer.parseInt(inputList[i].split(" ")[2]),
                        Integer.parseInt(inputList[i].split(" ")[3]));

                addPersonToFloor(newPerson, floors);
            }

            newHouse = new House(floors, lifts); //создание дома

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return newHouse;
    }

    private void addPersonToFloor(IPerson person, List<IFloor> floors) throws Exception{

        boolean personAdded = false;

        for (IFloor floor :
                floors) {
            if(floor.getNumber().equals(person.getFloorNumber())){
                floor.addWaitingPerson(person);
                personAdded = true;
            }
        }

        if(!personAdded){
            throw new Exception("There is no " + person.getFloorNumber() + " floor in this House." +
                    "But person with id " + person.getId() + " is on it.");
        }
    }
}
