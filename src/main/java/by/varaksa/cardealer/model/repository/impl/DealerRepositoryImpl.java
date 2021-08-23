package by.varaksa.cardealer.model.repository.impl;

import by.varaksa.cardealer.model.connection.ConnectionPool;
import by.varaksa.cardealer.model.entity.City;
import by.varaksa.cardealer.model.entity.Dealer;
import by.varaksa.cardealer.exception.RepositoryException;
import by.varaksa.cardealer.model.repository.DealerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DealerRepositoryImpl implements DealerRepository {
    private static final Logger logger = LogManager.getLogger();

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String FOUNDATION_DATE = "foundation_date";
    private static final String CITY = "city";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String CAR_ID = "car_id";

    private Dealer parseResultSet(ResultSet resultSet) throws SQLException {
        Dealer dealer = new Dealer();
        dealer.setId(resultSet.getLong(ID));
        dealer.setName(resultSet.getString(NAME));
        dealer.setAddress(resultSet.getString(ADDRESS));
        dealer.setFoundationDate(resultSet.getDate(FOUNDATION_DATE).toLocalDate());
        dealer.setCity(City.valueOf(resultSet.getString(CITY)));
        dealer.setCreated(resultSet.getTimestamp(CREATED).toLocalDateTime());
        dealer.setChanged(resultSet.getTimestamp(CHANGED).toLocalDateTime());
        dealer.setCarId(resultSet.getLong(CAR_ID));
        return dealer;
    }

    private static final String SAVE_DEALER = "insert into dealers (name, address, foundation_date, city, created, changed, car_id) " +
            "values (?,?,?,?,?,?,?)";
    private static final String FIND_ALL_DEALERS = "select * from dealers";
    private static final String FIND_DEALER_BY_ID = "select * from dealers where id = ?";
    private static final String UPDATE_DEALER_BY_ID = "update dealers " +
            "set " +
            "name = ?,  " +
            "address = ?,  " +
            "foundation_date = ?,  " +
            "city = ?,  " +
            "changed = ?,  " +
            "car_id = ?  " +
            "where id = ?";
    private static final String DELETE_DEALER_BY_ID = "delete from dealers where id = ?";

    @Override
    public Dealer save(Dealer dealer) throws RepositoryException {
        Timestamp creationTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_DEALER)) {

            statement.setString(1, dealer.getName());
            statement.setString(2, dealer.getAddress());
            statement.setDate(3, Date.valueOf(dealer.getFoundationDate()));
            statement.setString(4, String.valueOf(dealer.getCity()));
            statement.setTimestamp(5, creationTimestamp);
            statement.setTimestamp(6, creationTimestamp);
            statement.setLong(7, dealer.getCarId());
            statement.executeUpdate();

            return dealer;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public List<Dealer> findAll() {
        List<Dealer> result = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_DEALERS);

            while (resultSet.next()) {
                result.add(parseResultSet(resultSet));
            }

            return result;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Dealer find(Long id) throws RepositoryException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DEALER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return parseResultSet(resultSet);
            } else {
                throw new RepositoryException("Dealer with id " + id + " wasn't found");
            }

        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Dealer update(Dealer dealer) {
        Timestamp updateTimestamp = Timestamp.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DEALER_BY_ID)) {

            statement.setString(1, dealer.getName());
            statement.setString(2, dealer.getAddress());
            statement.setDate(3, Date.valueOf(dealer.getFoundationDate()));
            statement.setString(4, String.valueOf(dealer.getCity()));
            statement.setTimestamp(5, updateTimestamp);
            statement.setLong(6, dealer.getCarId());
            statement.setLong(7, dealer.getId());
            statement.executeUpdate();

            return dealer;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Dealer delete(Long id) {
        Dealer dealer = new Dealer();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DEALER_BY_ID)) {

            statement.setLong(1, id);
            statement.executeUpdate();

            return dealer;
        } catch (SQLException exception) {
            String errorMessage = "SQL exception." + exception;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }
}
