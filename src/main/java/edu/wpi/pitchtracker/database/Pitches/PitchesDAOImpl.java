package edu.wpi.pitchtracker.database.Pitches;

import edu.wpi.pitchtracker.database.CSV.CSVReader;
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
                        "CREATE TABLE Pitches (pitch_num INTEGER PRIMARY KEY, pitcher_id INTEGER, type varChar(2), location INTEGER, call varChar(1), swinging varChar(1), barrell varChar(1), outcome varChar(2), balls INTEGER, strikes INTEGER, batter_hand varChar(1), velocity INTEGER, FOREIGN KEY (pitcher_id) REFERENCES Pitcher(p_id))");

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
                            "CREATE TABLE Pitches (pitch_num INTEGER PRIMARY KEY, pitcher_id INTEGER, type varChar(2), location INTEGER, pitch_call varChar(1), swinging varChar(1), barrell varChar(1), outcome varChar(4), balls INTEGER, strikes INTEGER, batter_hand varChar(1), velocity INTEGER, FOREIGN KEY (pitcher_id) REFERENCES Pitchers(p_id))");
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
        pitchFields.add(10, fields.get(10)); //first name
        pitchFields.add(11, fields.get(11)); //last name

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
        String outcome = currentLineSplit[7];
        String balls = currentLineSplit[8];
        String strikes = currentLineSplit[9];
        String batterHand = currentLineSplit[10];
        String velo = currentLineSplit[11];


        fields.add(pitchNum);
        fields.add(pitcherID);
        fields.add(type);
        fields.add(loc);
        fields.add(call);
        fields.add(swinging);
        fields.add(barrell);
        fields.add(outcome);
        fields.add(balls);
        fields.add(strikes);
        fields.add(batterHand);
        fields.add(velo);

        return fields;
    }

    @Override
    public ResultSet get() throws SQLException, IOException {
        return DatabaseManager.getInstance().runQuery("SELECT * FROM Pitches");
    }

    @Override
    public void add(ArrayList<String> fields) throws SQLException {

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
                "INSERT INTO Pitches VALUES (%d, %d, '%s', %d, '%s', '%s', '%s', '%s', %d, %d, '%s', %d)",
                Integer.parseInt(fields.get(0)), Integer.parseInt(fields.get(1)), fields.get(2), Integer.parseInt(fields.get(3)), fields.get(4), fields.get(5), fields.get(6), fields.get(7), Integer.parseInt(fields.get(8)), Integer.parseInt(fields.get(9)), fields.get(10), Integer.parseInt(fields.get(11)));
    }

    @Override
    public void backUpToCSV(String fileDir) throws SQLException, IOException {

    }

    @Override
    public void backUpToCSV(File file) throws SQLException, IOException {

    }
}
