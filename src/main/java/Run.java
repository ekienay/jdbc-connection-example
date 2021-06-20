import com.unnurnment.connection.DBConnection;
import com.unnurnment.model.Massage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws SQLException {

        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.open();

        /*
        SELECT DATA FROM DB!
         */

        String SQLSelect = "SELECT * FROM massage";
        PreparedStatement statement;
        ResultSet list;

        statement = connection.prepareStatement(SQLSelect);
        list = statement.executeQuery();

        while (list.next()){
            int id = list.getInt(1);
            String text = list.getString(2);
            String title = list.getString(3);
            int themeID = list.getInt(4);
            System.out.printf("ID: %d\n text: %s \n title: %s \n theme_id: %d\n",id,text,title,themeID);
        }

        /*
        INPUT DATA INTO DB!
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("input massage text: ");
        String masText = scanner.nextLine();
        System.out.println("input massage title: ");
        String masTitle = scanner.nextLine();
        System.out.println("input theme_id for massage: ");
        int masThemeId = scanner.nextInt();

        Massage massage = new Massage(list.getFetchSize() + 1 , masText, masTitle, masThemeId);

        String SQLInsert = String.format("INSERT INTO %s (%s, %s , %s) VALUES (?,?,?)", Massage.TABLE_NAME,Massage.TEXT,Massage.TITLE,Massage.THEME_ID);
        statement = connection.prepareStatement(SQLInsert);
        statement.setString(1,massage.getText());
        statement.setString(2,massage.getTitle());
        statement.setInt(3,massage.getThemeId());
        statement.executeUpdate();

        /*
        UPDATE DATA INTO DB!
         */

        System.out.println("input updatable massage text: ");
        String updateText = scanner.nextLine();
        System.out.println("input updatable massage title: ");
        String updateTitle = scanner.nextLine();
        System.out.println("input updatable themeID for massage: ");
        int updateThemeId = scanner.nextInt();
        System.out.println("input id");
        int id = scanner.nextInt();

        String SQLUpdate = String.format("UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = %d", Massage.TABLE_NAME,Massage.TEXT,Massage.TITLE,Massage.THEME_ID,Massage.ID,id);
        statement = connection.prepareStatement(SQLUpdate);
        statement.setString(1,updateText);
        statement.setString(2,updateTitle);
        statement.setInt(3,updateThemeId);
        statement.executeUpdate();

         /*
        DELETE DATA FROM DB!
         */

        System.out.println("Input id massage to be deleted: ");
        int deletedId = scanner.nextInt();
        String SQLDelete = String.format("DELETE FROM %s WHERE %s = %d",Massage.TABLE_NAME,Massage.ID,deletedId);
        statement = connection.prepareStatement(SQLDelete);
        statement.executeUpdate();


        /*
        COUNT DATA IN TABLE :>
         */

        String SQLCount = String.format("SELECT COUNT(*) FROM %s",Massage.TABLE_NAME);
        ResultSet size = statement.executeQuery(SQLCount);
        while (size.next()){
            int count = size.getInt(1);
            System.out.printf("table size: %d ",count);
        }


        /*
        CLOSE CONNECTION..
         */
        dbConnection.close(connection);

    }
}

