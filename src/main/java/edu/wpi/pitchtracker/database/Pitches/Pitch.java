package edu.wpi.pitchtracker.database.Pitches;

public class Pitch {

    private String type;
    private int location;
    private Call call;
    private boolean swinging;
    private boolean barrell;
    private int balls;
    private int strikes;
    private int velocity;

    public Pitch(String type, int location, Call call, boolean swinging, boolean barrell,  int balls, int strikes, int velocity) {
        this.type = type;
        this.location = location;
        this.call = call;
        this.swinging = swinging;
        this.barrell = barrell;
        this.balls = balls;
        this.strikes = strikes;
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

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }



    public enum Call{
        STRIKE,
        BALL
    }





}
