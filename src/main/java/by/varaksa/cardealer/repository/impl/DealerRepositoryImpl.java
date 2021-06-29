package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.City;
import by.varaksa.cardealer.entity.Dealer;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.repository.DealerRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.*;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class DealerRepositoryImpl implements DealerRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String FOUNDATION_YEAR = "foundation_year";
    private static final String CITY = "city";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String CAR_ID = "car_id";

    private Dealer parseResultSet(ResultSet resultSet) throws SQLException {
        Dealer dealer = new Dealer();
        dealer.setId(resultSet.getLong(ID));
        dealer.setName(resultSet.getString(NAME));
        dealer.setAddress(resultSet.getString(ADDRESS));
        dealer.setFoundationYear(resultSet.getInt(FOUNDATION_YEAR));
        dealer.setCity(City.valueOf(resultSet.getString(CITY)));
        dealer.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        dealer.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        dealer.setCarId(resultSet.getLong(CAR_ID));
        return dealer;
    }

    @Override
    public Dealer save(Dealer dealer) throws RepositoryException {
        final String saveDealer = "insert into dealers (name, address, foundation_year, city, created, changed, car_id) " +
                "values (?,?,?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(saveDealer);

            statement.setString(1, dealer.getName());
            statement.setString(2, dealer.getAddress());
            statement.setInt(3, dealer.getFoundationYear());
            statement.setString(4, String.valueOf(dealer.getCity()));
            statement.setTimestamp(5, creationTimestamp);
            statement.setTimestamp(6, creationTimestamp);
            statement.setLong(7, dealer.getCarId());
            statement.executeUpdate();

            logger.info("Dealer with id " + dealer.getId() + " was saved");
            return dealer;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Dealer> findAll() {
        final String findAllDealers = "select * from dealers";

        List<Dealer> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(findAllDealers);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            logger.info("All dealers: " + result);
            return result;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Dealer find(Long id) throws RepositoryException {
        final String findDealerById = "select * from dealers where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findDealerById);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                logger.info("Dealer with id " + id + " was found");
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Dealer with id " + id + " wasn't found");
            }

        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Dealer update(Dealer dealer) {
        final String updateDealerById = "update dealers " +
                "set " +
                "name = ?,  " +
                "address = ?,  " +
                "foundation_year = ?,  " +
                "city = ?,  " +
                "changed = ?,  " +
                "car_id = ?  " +
                "where id = ?";

        Connection connection;
        PreparedStatement statement;
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(updateDealerById);

            statement.setString(1, dealer.getName());
            statement.setString(2, dealer.getAddress());
            statement.setInt(3, dealer.getFoundationYear());
            statement.setString(4, String.valueOf(dealer.getCity()));
            statement.setTimestamp(5, updateTimestamp);
            statement.setLong(6, dealer.getCarId());
            statement.setLong(7, dealer.getId());
            statement.executeUpdate();

            logger.info("Dealer with id " + dealer.getId() + " was updated");
            return dealer;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Long delete(Dealer dealer) {
        final String deleteDealerById = "delete from dealers where id = ?";

        Connection connection;
        PreparedStatement statement;

        connect();

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(deleteDealerById);
            statement.setLong(1, dealer.getId());

            int deletedRows = statement.executeUpdate();
            logger.info("Dealer with id " + dealer.getId() + " was deleted");
            return (long) deletedRows;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    private void connect() {
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            logger.info("JDBC driver be loaded");
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
