package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.EventEntity;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * DAO for {@link EventEntity} entity.
 */
public final class StatisticsDao {

    private final Supplier<ConnectionHandler> connections;

    public StatisticsDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    public Collection<EventEntity> findAll() {
        var sql = """
                SELECT  id,
                        guid,
                        name,
                        isDone,
                        location,
                        date,
                        time,
                        duration
                FROM Event
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            List<EventEntity> events = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var event = eventFromResultSet(resultSet);
                    events.add(event);
                }
            }

            return events;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all events", ex);
        }
    }

    public Optional<EventEntity> findById(long id) {
        var sql = """
                SELECT  id,
                        guid,
                        name,
                        isDone,
                        location,
                        date,
                        time,
                        duration,
                FROM Event
                WHERE id = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(eventFromResultSet(resultSet));
            } else {
                // event not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load event by id", ex);
        }
    }

    public Optional<EventEntity> findByGuid(String guid) {
        var sql = """
                SELECT id,
                       guid,
                       name,
                       isDone,
                       location,
                       date,
                       time,
                       duration
                FROM Event
                WHERE guid = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(eventFromResultSet(resultSet));
            } else {
                // event not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load event by guid", ex);
        }
    }


    public boolean existsByGuid(String guid) {
        var sql = """
                SELECT id
                FROM Event
                WHERE guid = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            var resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to check if event exists: " + guid, ex);
        }
    }

    private EventEntity eventFromResultSet(ResultSet resultSet) throws SQLException {
        List<Category> cats = CategoryConnectionDao.findAllCategoryConnectionsByGuid(connections, resultSet.getString("guid"), true);
        Date date = resultSet.getDate("date");
        Time time = resultSet.getTime("time");
        return new EventEntity(
                resultSet.getString("guid"),
                resultSet.getLong("id"),
                resultSet.getBoolean("isDone"),
                resultSet.getString("name"),
                cats,
                resultSet.getString("location"),
                date == null ? null : date.toLocalDate(),
                time == null ? null : time.toLocalTime(),
                Duration.ofMinutes(resultSet.getInt("duration"))
        );
    }
}
