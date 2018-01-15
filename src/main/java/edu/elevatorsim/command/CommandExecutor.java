/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.command;

import edu.elevatorsim.model.interfaces.DrawCommand;
import edu.elevatorsim.model.interfaces.ICommand;
import edu.elevatorsim.output.BuildingVisualization;
import edu.elevatorsim.output.FrameBuffer;
import java.util.Deque;
import java.util.LinkedList;


public class CommandExecutor {
    
    private Deque<ICommand> commands;
    private BuildingVisualization building;
    private FrameBuffer buffer;
    
    public CommandExecutor(BuildingVisualization building){
        commands = new LinkedList<>();
        this.building = building;
        buffer = new FrameBuffer();
    }
    
    public void addCommand(ICommand command){
        commands.addLast(command);
    }
    
    public void execute(){
        for (ICommand command : commands){
            DrawCommand dCommand = parse(command);
            command.execute();
            dCommand.execute();
        }
        commands.clear();
        buffer.write(building.build());
    }

    public void startBuilding(){
        buffer.write(building.build());
    }
    
    private DrawCommand parse(ICommand command){
        DrawCommand dCommand = null;
        if (command.getClass().equals(MoveCommand.class)){
            dCommand = new DrawMoveCommand(command.getElevator(), command.getFloor(), building);
        }
        if (command.getClass().equals(GetInElevator.class)){
            dCommand = new DrawGetInElevator(command.getElevator(), command.getFloor(), building);
        }
        if (command.getClass().equals(GetOutElevator.class)){
            dCommand = new DrawGetOutElevator(command.getElevator(), command.getFloor(), building);
        }
        return dCommand;
    }
}
