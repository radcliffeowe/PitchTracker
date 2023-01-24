package edu.wpi.pitchtracker.database.Pitches;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IPitchesDAO {

    public void initTable(File file) throws SQLException, IOException;

    public void initTable(String filePath) throws SQLException, IOException;

    public ResultSet get() throws SQLException, IOException;

    public void add(Pitch pitch) throws SQLException;

    public void delete(String reqID) throws SQLException;

    public void update(ArrayList<String> fields) throws SQLException;

    public String generateInsertStatement(ArrayList<String> fields);

    public void backUpToCSV(String fileDir) throws SQLException, IOException;

    public void backUpToCSV(File file) throws SQLException, IOException;
}
