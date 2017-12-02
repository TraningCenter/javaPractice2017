package tadite.javase.elevatorSimulator.view;


import java.util.LinkedList;
import java.util.List;

public class BuildingPrintGrid {

    private char[][] grid;
    private int slotsCount;
    private int floorsCount;
    private int slotSize;

    public BuildingPrintGrid(int slotsCount, int floorsCount, int slotSize, char whiteSpace){
        this.slotsCount = slotsCount;
        this.floorsCount = floorsCount;
        this.slotSize = slotSize;
        initGrid(slotsCount, floorsCount, slotSize,whiteSpace);
    }

    private void initGrid(int slotsCount, int floorsCount, int slotSize, char whiteSpace) {
        grid=new char[slotsCount*slotSize][floorsCount*slotSize];
        for (int j=0;j<floorsCount * slotSize;j++)
            for (int i=0;i<slotsCount * slotSize;i++)
                grid[i][j]=whiteSpace;
    }

    public void addCharsToSlot(int slot, int level, char[][] chars){
        int slotOffset = slot*slotSize;
        int levelOffset = (level-1)*slotSize;

        for (int i=0;i<chars[0].length && i<slotSize;i++){
            for (int j=0;j<chars.length && j<slotSize;j++){
                grid[slotOffset+i][levelOffset+j] = chars[i][j]=='\0'?grid[slotOffset+i][levelOffset+j]:chars[i][j];
            }
        }
    }

    public char[][] getCharGrid(){
        return grid;
    }

    public int getWidth(){
        return slotsCount*slotSize;
    }

    public int getHeight(){
        return floorsCount*slotSize;
    }

    public int getFloorsCount(){
        return floorsCount;
    }

    public int getSlotsCount(){
        return slotsCount;
    }


}
