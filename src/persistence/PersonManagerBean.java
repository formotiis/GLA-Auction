package persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless(name = "PersonManager")
@LocalBean
public class PersonManagerBean implements PersonManager {

    @Resource(lookup = "jdbc/auctions")
    private DataSource dataSource;
    private Connection connection;
    public PersonManagerBean() {
    }


    @PostConstruct
    public void initialize() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @PreDestroy
    public void cleanup() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace(); }
    }

    @Override
    public String addPerson(Person person, String password) {
        try {
                Statement statement = connection.createStatement();
                StringBuilder sb = new StringBuilder();

                sb.append("INSERT INTO USERS(LOGIN,PASSWORD,FIRSTNAME,LASTNAME,EMAIL");
                if (person.isPhoneSet()) {
                    sb.append(",PHONE");
                }
                if (person.isAddressSet()) {
                    sb.append(",ADDRESS");
                }
                if (person.isCitySet()) {
                    sb.append(",CITY");
                }
                if (person.isZipSet()) {
                    sb.append(",ZIP");
                }
                if (!person.isCardSet()) {
                    sb.append(",CARD");
                }
                sb.append(") VALUES(" + "\'")
                        .append(person.getUserName())
                        .append("\',\'")
                        .append(password)
                        .append("\',\'")
                        .append(person.getFirstName())
                        .append("\',\'")
                        .append(person.getLastName())
                        .append("\',\'")
                        .append(person.getEmail()).append('\'');
                if (person.isPhoneSet()) {
                    sb.append(",\'").append(person.getPhone()).append('\'');
                }
                if (person.isAddressSet()) {
                    sb.append(",\'").append(person.getAddress()).append('\'');
                }
                if (person.isCitySet()) {
                    sb.append(",\'").append(person.getCity()).append('\'');
                }
                if (person.isZipSet()) {
                    sb.append(',').append(person.getZip());
                }
                if (!person.isCardSet()) {
                    sb.append(",\'").append(person.getCard()).append('\'');
                }
                sb.append(")");
                statement.execute(sb.toString());
        } catch (SQLException sqle) {
        }
        return null;
    }

    // TODO: Implement
    @Override
    public String getUserByID(int id) {
        return null;
    }

    // TODO: Implement
    @Override
    public String getUserByCreditentials(String user, String password) {
        return null;
    }

}
