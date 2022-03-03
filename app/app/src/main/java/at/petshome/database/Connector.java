package at.petshome.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    private static Connector mInstance = null;
    private Connection mConnection = null;
    private Statement mStatement = null;
    private boolean mConnected = false;

    private Connector() {

    }

    public static Connector getInstance() {
        if (mInstance == null) {
            mInstance = new Connector();
        }

        return mInstance;
    }

    public void openConnection() {
        try {
            Class.forName(MariaDB.driver);
            mConnection = DriverManager.getConnection(MariaDB.database_url, MariaDB.username, MariaDB.password);
            mConnected = true;
        } catch (SQLException e) {

        }
        catch (ClassNotFoundException e) {

        }
    }

    public void closeConnection() {
        try {
            mConnection.close();
            mConnected = false;
        } catch (SQLException e) {

        }
    }

    public boolean isConnected() {
        return mConnected;
    }

    public String executeQuery(String query) {
        StringBuilder output = null;
        try {
            mStatement = mConnection.createStatement();
            ResultSet set = mStatement.executeQuery(query);

            while (set.next()) {
                String current = null;
                for (int i = 0; i < set.getMetaData().getColumnCount(); i++) {
                    if (i + 1 == set.getMetaData().getColumnCount()) {
                        current += set.getString(i) + ";";
                    }
                    else {
                        current += (set.getString(i) + ";");
                    }
                }

                output.append(String.format("%s\n", current));
            }

        }
        catch (SQLException e) {

        }

        return output.toString();
    }

    public void executeCommand(String command) {
        try {
            mStatement = mConnection.createStatement();
            mStatement.executeUpdate(command);
        }
        catch (SQLException e) {

        }
    }
}
