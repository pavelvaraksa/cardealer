package by.varaksa.cardealer.repository.impl;

import by.varaksa.cardealer.entity.Body;
import by.varaksa.cardealer.entity.BodyType;
import by.varaksa.cardealer.entity.Color;
import by.varaksa.cardealer.repository.BodyRepository;
import by.varaksa.cardealer.util.DatabasePropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_DRIVER_NAME;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_URL;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_LOGIN;
import static by.varaksa.cardealer.util.DatabasePropertiesReader.DATABASE_PASSWORD;

public class BodyRepositoryImpl implements BodyRepository {
    private static Logger logger = LogManager.getLogger();
    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String COLOR = "color";
    private static final String BODY_TYPE = "body_type";
    private static final String VIN = "vin";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String CAR_ID = "car_id";

    @Override
    public List<Body> findAll() {
        final String findAllQuery = "select * from bodies";

        List<Body> result = new ArrayList<>();

        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException stackTrace) {
            String errorMessage = "JDBC driver can't be loaded." + stackTrace;
            logger.fatal(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL),
                    reader.getProperty(DATABASE_LOGIN),
                    reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            resultSet = statement.executeQuery(findAllQuery);

            while (resultSet.next()) {
                Body body = new Body();

                body.setId(resultSet.getLong(ID));
                body.setColor(Color.valueOf(resultSet.getString(COLOR)));
                body.setBodyType(BodyType.valueOf(resultSet.getString(BODY_TYPE)));
                body.setVin(resultSet.getString(VIN));
                body.setCreated(resultSet.getDate(CREATED));
                body.setChanged(resultSet.getDate(CHANGED));
                body.setCarId(resultSet.getLong(CAR_ID));

                result.add(body);
            }

            return result;
        } catch (SQLException stackTrace) {
            String errorMessage = "SQL exception." + stackTrace;
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    @Override
    public Body find(Long id) {
        return null;
    }

    @Override
    public Body save(Body body) {
        return null;
    }

    @Override
    public Body update(Long id) {
        return null;
    }

    @Override
    public Body delete(Long id) {
        return null;
    }
}
