package com.netcracker.unc.commands;

import com.netcracker.unc.commands.interfaces.ICommand;

import java.util.LinkedList;
import java.util.Queue;

public class CommandManager {
    private Queue<ICommand> commands;

    public CommandManager() {
        this.commands = new LinkedList<ICommand>();
    }

    public ICommand getNextCommand(){
        return commands.peek();
    }

    public void executeNextCommand() {
        ICommand command = commands.poll();
        if (command != null) {
            command.execute();
        }
    }

    public boolean addCommand(ICommand command) {
        return commands.offer(command);
    }

    public boolean isEmpty() {
        return commands.size() == 0;
    }
}
