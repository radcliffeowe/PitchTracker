package edu.wpi.pitchtracker.database.Pitches;

public class Pitch {

    private String type;
    private int location;
    private Call call;
    private boolean swinging;
    private boolean barrell;
    private Outcome outcome;
    private int balls;
    private int strikes;
    private BatterHand batterHand;
    private int velocity;

    public Pitch(String type, int location, Call call, boolean swinging, boolean barrell, Outcome outcome, int balls, int strikes, BatterHand batterHand, int velocity) {
        this.type = type;
        this.location = location;
        this.call = call;
        this.swinging = swinging;
        this.barrell = barrell;
        this.outcome = outcome;
        this.balls = balls;
        this.strikes = strikes;
        this.batterHand = batterHand;
        this.velocity = velocity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public boolean isSwinging() {
        return swinging;
    }

    public void setSwinging(boolean swinging) {
        this.swinging = swinging;
    }

    public boolean isBarrell() {
        return barrell;
    }

    public void setBarrell(boolean barrell) {
        this.barrell = barrell;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public BatterHand getBatterHand() {
        return batterHand;
    }

    public void setBatterHand(BatterHand batterHand) {
        this.batterHand = batterHand;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
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

    public enum Call{
        STRIKE,
        BALL
    }

    public enum BatterHand{
        RIGHT,
        LEFT
    }



}
