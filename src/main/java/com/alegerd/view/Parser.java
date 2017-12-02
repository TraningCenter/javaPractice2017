package com.alegerd.view;

import com.alegerd.ViewController;
import com.alegerd.model.Floor;
import com.alegerd.model.House;
import com.alegerd.model.Lift;
import com.alegerd.model.Person;
import com.alegerd.model.interfaces.IFloor;
import com.alegerd.model.interfaces.ILift;
import com.alegerd.model.interfaces.IPerson;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.lang.ClassLoader;
import java.util.stream.Collectors;

/**
 * Reads input file and constructs House object out of it
 */
public class Parser {

    public Parser(){

    }

    /**
     * Parses input file data and builds the house
     * @param pathToInputFile path to input file
     * @return new House
     */
    public House parseInputFile(String pathToInputFile) throws IllegalArgumentException{
        House newHouse = null;
        Integer maxPeopleInSection;
        List<Integer> sections;

        try{
            String data;
            StringBuilder sb = new StringBuilder();

            if(pathToInputFile == null) {
                InputStream file = ClassLoader.getSystemResourceAsStream("input");
                InputStreamReader isr = new InputStreamReader(file);
                BufferedReader br = new BufferedReader(isr);
                data = br.lines().collect(Collectors.joining("\n"));
            }
            else {
                Files.lines(Paths.get(pathToInputFile), StandardCharsets.UTF_8).forEach(sb::append);
                data = sb.toString();
            }

            String[] inputList = data.split(";");

            List<IFloor> floors = new ArrayList<>();
            List<ILift> lifts = new ArrayList<>();

            Integer amountOfLifts = Integer.parseInt(inputList[0].split(" ")[1]);
            //заполнение списка этажей
            Integer amountOfFloors = Integer.parseInt(inputList[1].split(" ")[1]);

            ViewController.setNumberOfFloors(amountOfFloors);

            for (int i = 0; i < amountOfFloors; i++){
                Floor newFloor = new Floor(i);
                floors.add(newFloor);
            }

            ViewController.setFloorLength(amountOfLifts * 12);

            ViewController.setNumberOfLifts(amountOfLifts);
            maxPeopleInSection = ViewController.getNumberOfPeopleInSection();
            Integer size = ViewController.getNumberOfSections();
            sections = new ArrayList<>(size);

            //заполняем секции нулями
            for (int i = 0; i < size; i++){
                sections.add(i, 0);
            }

            for (int i = 0; i < amountOfLifts; i++){
                Lift newLift = new Lift(i, floors);
                lifts.add(newLift);
            }

            for(int i = 2; i < inputList.length; i++){

                String[] line = inputList[i].split(" ");
                Integer num = Integer.parseInt(line[4]);
                if(num > sections.size()) throw new IllegalArgumentException("Person waits for not existing lift");
                Integer newNum = sections.get(num)+1;
                sections.add(num, newNum);

                Person newPerson = new Person(i,
                        Integer.parseInt(line[1]),
                        Integer.parseInt(line[2]) - 1,
                        Integer.parseInt(line[3]) - 1,
                        num);

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
