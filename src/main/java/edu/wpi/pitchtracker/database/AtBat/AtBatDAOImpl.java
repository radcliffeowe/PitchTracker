package edu.wpi.pitchtracker.database.AtBat;

import edu.wpi.pitchtracker.database.CSV.CSVReader;
import edu.wpi.pitchtracker.database.DatabaseManager;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtBatDAOImpl implements IAtBatDAO{
    @Override
    public void initTable(File file) throws SQLException, IOException {
        try {
            DatabaseManager.getInstance()
                    .runStatement(
                            "CREATE TABLE AtBat (atBatId INTEGER PRIMARY KEY, outcome VARCHAR(3), batterHand VARCHAR(1))");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                return;
            }
            e.printStackTrace();
            System.out.println(e.getSQLState());
        }
        System.out.println("Table created, reading resource path");
        List<String> lines = CSVReader.readFile(file);
        System.out.println("CSV read, inserting rows");
        for (String currentLine : lines) {
            System.out.println(currentLine);
            addInit(makeArrayListFromString(currentLine));
        }
    }

    @Override
    public void initTable(String filePath) throws SQLException, IOException {
        try {
            DatabaseManager.getInstance()
                    .runStatement(
                            "CREATE TABLE AtBat (atBatId INTEGER PRIMARY KEY, outcome VARCHAR(3), batterHand VARCHAR(1))");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                return;
            }
            e.printStackTrace();
            System.out.println(e.getSQLState());
        }
        System.out.println("Table created, reading resource path");
        List<String> lines = CSVReader.readResourceFilepath(filePath);
        System.out.println("CSV read, inserting rows");
        for (String currentLine : lines) {
            System.out.println(currentLine);
            addInit(makeArrayListFromString(currentLine));
        }
    }

    private ArrayList<String> makeArrayListFromString(String currentLine) {
        ArrayList<String> fields = new ArrayList<>();
        String[] currentLineSplit = currentLine.split(",");
        String atBatId = currentLineSplit[0];
        String outcome = currentLineSplit[1];
        String batterHand = currentLineSplit[2];

        fields.add(atBatId);
        fields.add(outcome);
        fields.add(batterHand);


        return fields;
    }

    public void addInit(ArrayList<String> fields) throws SQLException {
        ArrayList<String> atBatFields = new ArrayList<>();

        atBatFields.add(0, fields.get(0)); //pitcher id
        atBatFields.add(1, fields.get(1)); //first name
        atBatFields.add(2, fields.get(2)); //last name


        DatabaseManager.getInstance().runStatement(generateInsertStatement(atBatFields));
    }

    @Override
    public ResultSet get() throws SQLException, IOException {
        return null;
    }

    @Override
    public void add(AtBat atBat) throws SQLException {

    }

    @Override
    public void delete(String reqID) throws SQLException {

    }

    @Override
    public void update(ArrayList<String> fields) throws SQLException {

    }

    @Override
    public String generateInsertStatement(ArrayList<String> fields) {
        return String.format(
                "INSERT INTO AtBat VALUES (%d, '%s', '%s')",
                Integer.parseInt(fields.get(0)), fields.get(1), fields.get(2));
    }

    @Override
    public void backUpToCSV(String fileDir) throws SQLException, IOException {

    }

    @Override
    public void backUpToCSV(File file) throws SQLException, IOException {

    }
}
