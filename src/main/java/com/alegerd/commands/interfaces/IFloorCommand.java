package com.alegerd.commands.interfaces;

import com.alegerd.model.interfaces.IFloor;

/**
 * Interface for FloorCommand
 */
public interface IFloorCommand extends ICommand {
    void setFloor(IFloor floor);
}
