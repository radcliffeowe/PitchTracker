package edu.wpi.pitchtracker.database.AtBat;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IAtBatDAO {
    public void initTable(File file) throws SQLException, IOException;

    public void initTable(String filePath) throws SQLException, IOException;

    public ResultSet get() throws SQLException, IOException;

    public void add(AtBat atBat) throws SQLException;

    public void delete(String reqID) throws SQLException;

    public void update(ArrayList<String> fields) throws SQLException;

    public String generateInsertStatement(ArrayList<String> fields);

    public void backUpToCSV(String fileDir) throws SQLException, IOException;

    public void backUpToCSV(File file) throws SQLException, IOException;
}
