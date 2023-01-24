package edu.wpi.pitchtracker.database.AtBat;

public class AtBat {

    private Outcome outcome;
    private BatterHand batterHand;

    public AtBat(Outcome outcome, BatterHand batterHand) {
        this.outcome = outcome;
        this.batterHand = batterHand;
    }

    public enum BatterHand{
        RIGHT,
        LEFT
    }

    public enum Outcome{
        FB,
        LD,
        GB,
        K,
        BB,
        HBP,
        CI
    }
}
