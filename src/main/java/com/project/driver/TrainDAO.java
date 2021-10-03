package com.project.driver;
import com.project.model.Train;
import java.sql.*;
import java.util.ArrayList;

public class TrainDAO {
    public static Connection connection;
    public static Statement statement;

    public static void main(String[] args) {
        try {
            dataBaseConnection();
            System.out.println("connected to DB successfully");
            statement = connection.createStatement();
            ArrayList<Train> trainList = getTrain();
            //insertUsingPreparedStatement(connection, trainList);
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }

    private static void dataBaseConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/trainticketreservationsystem";
        String username = "root";
        String password = "Prasad@66";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }


    public static ArrayList<Train> getTrain() {
        Train t1 = new Train(1001, "Shatabdi Express", "Bangalore", "Delhi", 2500);
        Train t2 = new Train(1002, "Shatabdi Express", "Bangalore", "Delhi", 2500);
        Train t3 = new Train(1003, "Udyan Express", "Bangalore", "Mumbai", 1500);
        Train t4 = new Train(1004, "Udyan Expres", "Bangalore", "Mumbai", 1500);
        Train t5 = new Train(1005, "Brindavan Express", "Bangalore", "Chennai", 1000);
        Train t6 = new Train(1006, "Brindavan Express", "Chennai", "Bangalore", 1000);

        ArrayList<Train> trainArrayList = new ArrayList<>();
        trainArrayList.add(t1);
        trainArrayList.add(t2);
        trainArrayList.add(t3);
        trainArrayList.add(t4);
        trainArrayList.add(t5);

        return trainArrayList;
    }

    public static void insertUsingPreparedStatement(Connection connection, ArrayList<Train> trainList) throws SQLException {
        String pQuery = "insert into trains values(?,?,?,?,?)";
        PreparedStatement pstat = connection.prepareStatement(pQuery);
        for (Train trains : trainList) {
            pstat.setInt(1, trains.getTrainNo());
            pstat.setString(2, trains.getTrainName());
            pstat.setString(3, trains.getSource());
            pstat.setString(4, trains.getDestination());
            pstat.setDouble(5, trains.getTicketPrice());
            pstat.execute();

            System.out.println(trains);

        }
    }


}
