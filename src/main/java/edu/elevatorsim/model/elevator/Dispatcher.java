/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.elevator;

import edu.elevatorsim.command.CommandExecutor;
import edu.elevatorsim.command.GetInElevator;
import edu.elevatorsim.command.GetOutElevator;
import edu.elevatorsim.command.MoveCommand;
import edu.elevatorsim.model.building.Building;
import edu.elevatorsim.model.floor.Floor;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;


public class Dispatcher {
  
    private static Building building;
    private static CommandExecutor executor;
    private static Deque<Request> callElevator;
    
    static {
        callElevator = new LinkedList<>();
    }
    
    public static void setBuilding(Building currentBuilding){
        building = currentBuilding;
    }
    
    public static void setCommandExecutor(CommandExecutor commandExecutor){
        executor = commandExecutor;
    }
    
    public static void addToCallList(Request request){
        if (!checkCallList(request)){
            callElevator.addLast(request);
        }
    }
    
    public static void removeFromCallList(Request request){
        callElevator.remove(request);
    }
    
    private static boolean checkCallList(Request request){
        for (Request req : callElevator){
            if (req.equals(request)){
                return true;
            }
        }
        return false;
    }
    
    private static boolean addRequestToElevatorQueue(Request request){
        for (ElevatorController controller : building.getControllers()){
            Elevator elevator = controller.getElevator();
            if (elevator.getDirection().equals(Direction.STOP)){
                controller.update(request);
                return true;
            } else {
                if (elevator.getCurrentFloor() < request.getLevel() && elevator.getDirection().equals(Direction.UP)) {
                    controller.update(request);
                    return true;
                }
                if (elevator.getCurrentFloor() > request.getLevel() && elevator.getDirection().equals(Direction.DOWN)) {
                    controller.update(request);
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void onCurrentFloor(){
        Iterator<Request> iterator;
        for (ElevatorController controller : building.getControllers()){
            iterator = callElevator.iterator();
            while (iterator.hasNext()){
                Request request = iterator.next();
                if (request.getLevel() == controller.getElevator().getCurrentFloor()){
                    controller.update(request);
                    iterator.remove();
                    removeFromCallList(request);
                    break;
                }
            }
        }
    }

    private static boolean checkPassengersOnFloor(Elevator elevator, Floor floor){
        if (!floor.getPassengers().isEmpty() && !elevator.isFull()){
            return true;
        }
        return false;
    }
    
    public static void startSimulation(){
        onCurrentFloor();
        while (isRunning()){
            if (!callElevator.isEmpty()){
                if (addRequestToElevatorQueue(callElevator.getFirst())){
                    callElevator.pollFirst();
                }
            }
            for (ElevatorController controller : building.getControllers()){
                Elevator elevator = controller.getElevator();
                Floor floor = building.getFloor(elevator.getCurrentFloor());
                if (!controller.checkCurrentFloor() && !checkPassengersOnFloor(elevator, floor)){
                    controller.calculateNextFloor();
                    executor.addCommand(new MoveCommand(elevator, floor));
                } else {
                    if (controller.checkPassengersInElevator()) {
                        executor.addCommand(new GetOutElevator(elevator, floor));
                    }
                    if (floor.getButton().isActive()) {
                        executor.addCommand(new GetInElevator(elevator, floor));
                    }
                }
            }
            executor.execute();
        }
    }
    
    public static boolean isRunning(){
        for (ElevatorController controller : building.getControllers()){
            if (!controller.checkElevatorEnd()){
                return true;
            }
            controller.getElevator().setDirection(Direction.STOP);
        }
        if (!callElevator.isEmpty()){
            return true;
        }
        return false;
    }
}
