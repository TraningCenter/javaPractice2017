/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elevatorsim.model.elevator;


public class Request implements Comparable<Request>{
    
    private int level;
    
    public Request(int level){
        this.level = level;
    }
    
    public int getLevel(){
        return level;
    }
    
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Request request = (Request) obj;
        if (level != request.getLevel()) return false;
        return true;
    }

    @Override
    public int compareTo(Request o) {
        return this.getLevel() - o.getLevel();
    }
}
