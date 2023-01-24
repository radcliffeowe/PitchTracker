package edu.wpi.pitchtracker.database.Pitches;

import edu.wpi.pitchtracker.database.CSV.CSVReader;
import edu.wpi.pitchtracker.database.CSV.CSVWriter;
import edu.wpi.pitchtracker.database.DatabaseManager;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PitchesDAOImpl implements IPitchesDAO{

    public void initTable(File file) throws SQLException, IOException {
        DatabaseManager.getInstance().dropTableIfExist("Pitches");
        DatabaseManager.getInstance()
                .runStatement(
                        "CREATE TABLE Pitches (pitch_num INTEGER PRIMARY KEY, pitcher_id INTEGER, type varChar(2), location INTEGER, call varChar(1), swinging varChar(1), barrell varChar(1), balls INTEGER, strikes INTEGER, velocity INTEGER DEFAULT 0, atBatId INTEGER, FOREIGN KEY (pitcher_id) REFERENCES Pitcher(p_id), FOREIGN KEY (atBatId) REFERENCES AtBat(atBatId))");

        List<String> lines = CSVReader.readFile(file);
        for (String currentLine : lines) {
            //      System.out.println(currentLine);
            addInit(makeArrayListFromString(currentLine));
        }
    }

    public void initTable(String filepath) throws SQLException, IOException {
        try {
            DatabaseManager.getInstance()
                    .runStatement(
                            "CREATE TABLE Pitches (pitch_num INTEGER PRIMARY KEY, pitcher_id INTEGER, type varChar(2), location INTEGER, pitch_call varChar(1), swinging varChar(1), barrell varChar(1), balls INTEGER, strikes INTEGER, velocity INTEGER DEFAULT 0, atBatId INTEGER, FOREIGN KEY (pitcher_id) REFERENCES Pitchers(p_id), FOREIGN KEY (atBatId) REFERENCES AtBat(atBatId))");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                return;
            }
            e.printStackTrace();
            System.out.println(e.getSQLState());
        }
        System.out.println("Table created, reading resource path");
        List<String> lines = CSVReader.readResourceFilepath(filepath);
        System.out.println("CSV read, inserting rows");
        for (String currentLine : lines) {
            System.out.println(currentLine);
            addInit(makeArrayListFromString(currentLine));
        }
    }

    public void addInit(ArrayList<String> fields) throws SQLException {
        ArrayList<String> pitchFields = new ArrayList<>();

        pitchFields.add(0, fields.get(0)); //pitcher id
        pitchFields.add(1, fields.get(1)); //first name
        pitchFields.add(2, fields.get(2)); //last name
        pitchFields.add(3, fields.get(3)); //pitcher id
        pitchFields.add(4, fields.get(4)); //first name
        pitchFields.add(5, fields.get(5)); //last name
        pitchFields.add(6, fields.get(6)); //pitcher id
        pitchFields.add(7, fields.get(7)); //first name
        pitchFields.add(8, fields.get(8)); //last name
        pitchFields.add(9, fields.get(9)); //pitcher id
        pitchFields.add(10, fields.get(10));

        DatabaseManager.getInstance().runStatement(generateInsertStatement(pitchFields));
    }

    private ArrayList<String> makeArrayListFromString(String currentLine) {
        ArrayList<String> fields = new ArrayList<>();
        String[] currentLineSplit = currentLine.split(",");
        String pitchNum = currentLineSplit[0];
        String pitcherID = currentLineSplit[1];
        String type = currentLineSplit[2];
        String loc = currentLineSplit[3];
        String call = currentLineSplit[4];
        String swinging = currentLineSplit[5];
        String barrell = currentLineSplit[6];
        String balls = currentLineSplit[7];
        String strikes = currentLineSplit[8];
        String velo = currentLineSplit[9];
        String atBatId = currentLineSplit[10];


        fields.add(pitchNum);
        fields.add(pitcherID);
        fields.add(type);
        fields.add(loc);
        fields.add(call);
        fields.add(swinging);
        fields.add(barrell);
        fields.add(balls);
        fields.add(strikes);
        fields.add(velo);
        fields.add(atBatId);

        return fields;
    }

    @Override
    public ResultSet get() throws SQLException, IOException {
        return DatabaseManager.getInstance().runQuery("SELECT * FROM Pitches");
    }

    @Override
    public void add(Pitch pitch) throws SQLException {

    }

    @Override
    public void delete(String pitchID) throws SQLException {
        DatabaseManager.getInstance().runQuery("DELETE * FROM Pitches WHERE pitch_id = '" + pitchID + "'");
    }

    @Override
    public void update(ArrayList<String> fields) throws SQLException {

    }

    @Override
    public String generateInsertStatement(ArrayList<String> fields) {
        return String.format(
                "INSERT INTO Pitches VALUES (%d, %d, '%s', %d, '%s', '%s', '%s', %d, %d, %d, %d)",
                Integer.parseInt(fields.get(0)), Integer.parseInt(fields.get(1)), fields.get(2), Integer.parseInt(fields.get(3)), fields.get(4), fields.get(5), fields.get(6), Integer.parseInt(fields.get(7)), Integer.parseInt(fields.get(8)), Integer.parseInt(fields.get(9)), Integer.parseInt(fields.get(10)));
    }

    @Override
    public void backUpToCSV(String fileDir) throws SQLException, IOException {

    }

    @Override
    public void backUpToCSV(File file) throws SQLException, IOException {
        ArrayList<String> toAdd = new ArrayList<>();
        ResultSet currentRow = get();
        toAdd.add("pitch_num,pitcher_id,type,location,call,swinging,barrell,balls,strikes,velocity,atBatId");

        while (currentRow.next()) {
            toAdd.add(
                    String.format(
                            "%d,%d,%s,%d,%s,%s,%s,%s,%d,%d,%s,%d",
                            currentRow.getInt("pitch_num"),
                            currentRow.getInt("pitcher_id"),
                            currentRow.getString("type"),
                            currentRow.getInt("location"),
                            currentRow.getString("pitch_call"),
                            currentRow.getString("swinging"),
                            currentRow.getString("barrell"),
                            currentRow.getInt("balls"),
                            currentRow.getInt("strikes"),
                            currentRow.getInt("velocity"),
                            currentRow.getInt("atBatId")));
        }
        currentRow.close();

        CSVWriter.writeAll(file, toAdd);
    }
}
