package com.eugene.сontroller.Listeners;

import com.eugene.сontroller.Entities.Lift;

/**
 * Interface for lift subscribers
 * notifies about any events of its subscribers
 */
public interface LiftListener {

    void liftMoved(Lift lift);

    void liftStopped(Lift lift);

    void liftOpenedDoors(Lift lift);

    void liftClosedDoors(Lift lift);

    void liftArrived(Lift lift);

}
