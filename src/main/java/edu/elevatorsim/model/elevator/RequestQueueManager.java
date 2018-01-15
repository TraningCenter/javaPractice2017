/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.elevator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;


public class RequestQueueManager {
    
    private Deque<Request> deque;
    private Request[] start;
    private Request[] end;
    
    public Deque<Request> sorting(Deque<Request> deque, int currFloor, Direction dir){
        this.deque = deque;
        switch (dir){
            case UP:
                upDirection(currFloor);
                break;
            case DOWN:
                downDirection(currFloor);
                break;
            case STOP:
                return this.deque;
        }
        
        this.deque.clear();
        for (int i = 0; i < start.length; i++){
            this.deque.add(start[i]);
        }
        for (int i = 0; i < end.length; i++){
            this.deque.add(end[i]);
        }
        
        return this.deque;
    }
    
    private void downDirection(int currFloor){
        Iterator<Request> iterator = deque.iterator();
        int startLength = 0;
        int endLength = 0;
        while (iterator.hasNext()){
            Request req = iterator.next();
            if (req.getLevel() > currFloor){
                endLength++;
            }
            if (req.getLevel() <= currFloor){
                startLength++;
            }
        }
        start = new Request[startLength];
        end = new Request[endLength];
        
        int i = 0;
        int j = 0;
        iterator = deque.iterator();
        while (iterator.hasNext()){
            Request req = iterator.next();
            if (req.getLevel() <= currFloor){
                start[i] = req;
                i++;
            }
            if (req.getLevel() > currFloor){
                end[j] = req;
                j++;
            }
        }
        
        start = arraySort(start, "reverse");
        end = arraySort(end, "straight");
    }
    
    private void upDirection(int currFloor){
        Iterator<Request> iterator = deque.iterator();
        int startLength = 0;
        int endLength = 0;
        while (iterator.hasNext()){
            Request req = iterator.next();
            if (req.getLevel() >= currFloor){
                startLength++;
            }
            if (req.getLevel() < currFloor){
                endLength++;
            }
        }
        start = new Request[startLength];
        end = new Request[endLength];
        
        int i = 0;
        int j = 0;
        iterator = deque.iterator();
        while (iterator.hasNext()){
            Request req = iterator.next();
            if (req.getLevel() >= currFloor){
                start[i] = req;
                i++;
            }
            if (req.getLevel() < currFloor){
                end[j] = req;
                j++;
            }
        }
        
        start = arraySort(start, "straight");
        end = arraySort(end, "reverse");
    }
    
    private Request[] arraySort(Request[] array, String direction){
        switch (direction){
            case "straight":
                Arrays.sort(array);
                break;
            case "reverse":
                Arrays.sort(array, Collections.reverseOrder());
                break;
        }
        
        return array;
    }
}
