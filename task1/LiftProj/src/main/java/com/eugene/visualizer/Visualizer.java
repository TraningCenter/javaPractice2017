package com.eugene.visualizer;

import com.eugene.сontroller.Snapshot;

import java.util.List;

/**
 * Abstract class for entities actions visualization
 */
public abstract class Visualizer {

    protected List<Snapshot> snapshots;

    public Visualizer(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public abstract void paint();

}
