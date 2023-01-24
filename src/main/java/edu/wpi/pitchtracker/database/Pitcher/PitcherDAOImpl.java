package edu.wpi.pitchtracker.database.Pitcher;

import edu.wpi.pitchtracker.database.CSV.CSVReader;
import edu.wpi.pitchtracker.database.DatabaseManager;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PitcherDAOImpl implements IPitcherDAO{

    public void initTable(File file) throws SQLException, IOException {
        DatabaseManager.getInstance().dropTableIfExist("Pitchers");
        DatabaseManager.getInstance()
                .runStatement(
                        "CREATE TABLE Pitchers (p_id INTEGER PRIMARY KEY, first_name varChar(16), last_name varChar(32)");

        List<String> lines = CSVReader.readFile(file);
        for (String currentLine : lines) {
            //      System.out.println(currentLine);
            addInit(makeArrayListFromString(currentLine));
        }
    }

    public void initTable(String filepath) throws SQLException, IOException {
        System.out.println("Initializing Pitcher table");
        try {
            DatabaseManager.getInstance()
                    .runStatement(
                            "CREATE TABLE Pitchers (p_id INTEGER PRIMARY KEY, first_name varChar(16), last_name varChar(32))");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                return;
            }
            e.printStackTrace();
            System.out.println(e.getSQLState());
        }
        System.out.println("Table created, reading resource filepath");
        List<String> lines = CSVReader.readResourceFilepath(filepath);
        System.out.println("CSV read");
        for (String currentLine : lines) {
            System.out.println(currentLine);
            addInit(makeArrayListFromString(currentLine));
        }
    }

    public void addInit(ArrayList<String> fields) throws SQLException {
        ArrayList<String> pitcherFields = new ArrayList<>();

        pitcherFields.add(0, fields.get(0)); //pitcher id
        pitcherFields.add(1, fields.get(1)); //first name
        pitcherFields.add(2, fields.get(2)); //last name
        System.out.println(pitcherFields);

        DatabaseManager.getInstance().runStatement(generateInsertStatement(pitcherFields));
    }

    private ArrayList<String> makeArrayListFromString(String currentLine) {
        ArrayList<String> fields = new ArrayList<>();
        String[] currentLineSplit = currentLine.split(",");
        String pitcherID = currentLineSplit[0];
        String firstName = currentLineSplit[1];
        String lastName = currentLineSplit[2];

        fields.add(pitcherID);
        fields.add(firstName);
        fields.add(lastName);

        return fields;
    }

    @Override
    public ResultSet get() throws SQLException, IOException {
        return DatabaseManager.getInstance().runQuery("SELECT * FROM Pitchers");
    }

    @Override
    public void add(Pitcher pitcher) throws SQLException {

    }

    @Override
    public void delete(String pitcherID) throws SQLException {
        DatabaseManager.getInstance().runQuery("DELETE * FROM Pitchers WHERE pitcher_id = '" + pitcherID + "'");
    }

    @Override
    public void update(ArrayList<String> fields) throws SQLException {

    }

    @Override
    public String generateInsertStatement(ArrayList<String> fields) {
        return String.format(
                "INSERT INTO Pitchers VALUES (%d, '%s', '%s')",
                Integer.parseInt(fields.get(0)), fields.get(1), fields.get(2));
    }

    @Override
    public void backUpToCSV(String fileDir) throws SQLException, IOException {

    }

    @Override
    public void backUpToCSV(File file) throws SQLException, IOException {

    }
}
