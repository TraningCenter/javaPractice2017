package tadite.javase.elevatorSimulator.model.misc;

import com.sun.deploy.model.LocalApplicationProperties;

public class Location {
    private int position;
    private int level;

    public Location (int position, int level){
        this.position = position;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || !(obj instanceof Location))
            return false;

        Location other = (Location)obj;

        return other.level==this.level && other.position==this.position;
    }
}
