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
public final class EventDao implements DataAccessObject<EventEntity> {

    private final Supplier<ConnectionHandler> connections;

    public EventDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public EventEntity create(EventEntity newEvent) {
        var sql = """
                INSERT INTO Event(
                    guid,
                    name,
                    isDone,
                    location,
                    date,
                    time,
                    duration
                )
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, newEvent.guid());
            statement.setString(2, newEvent.name());
            statement.setBoolean(3, newEvent.isDone());
            statement.setString(4, newEvent.location());
            statement.setDate(5, newEvent.date() == null ? null : Date.valueOf(newEvent.date()));
            statement.setTime(6, newEvent.time() == null ? null : Time.valueOf(newEvent.time()));
            statement.setInt(7, (int) newEvent.duration().toMinutes());
            statement.executeUpdate();

            try (var keyResultSet = statement.getGeneratedKeys()) {
                long eventId;

                if (keyResultSet.next()) {
                    eventId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + newEvent);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + newEvent);
                }
                CategoryConnectionDao.createGuidConnection(connections, newEvent.guid(), newEvent.categories(), true);
                return findById(eventId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + newEvent, ex);
        }
    }

    @Override
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

    @Override
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

    @Override
    public Optional<EventEntity> findByGuid(String guid) {
        var sql = """
                SELECT id,
                       guid,
                       name,
                       isDone,
                       location,
                       date,
                       time,
                       duration,
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
            throw new DataStorageException("Failed to load event by id", ex);
        }
    }

    @Override
    public EventEntity update(EventEntity entity) {
        var sql = """
                UPDATE Event
                SET name = ?,
                    isDone = ?,
                    location = ?,
                    date = ?,
                    time = ?,
                    duration = ?
                WHERE id = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.name());
            statement.setBoolean(2, entity.isDone());
            statement.setString(3, entity.location());
            statement.setDate(4, entity.date() == null ? null : Date.valueOf(entity.date()));
            statement.setTime(5, entity.time() == null ? null : Time.valueOf(entity.time()));
            statement.setInt(6, (int) entity.duration().toMinutes());
            statement.setLong(7, entity.id());
            statement.executeUpdate();

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Event not found, id: " + entity.id());
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 event (rows=%d) has been updated: %s"
                        .formatted(rowsUpdated, entity));
            }
            CategoryConnectionDao.deleteAllCategoryConnectionsByGuid(connections, entity.guid(), true);
            CategoryConnectionDao.createGuidConnection(connections, entity.guid(), entity.categories(), true);
            return entity;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update event: " + entity, ex);
        }
    }

    @Override
    public void deleteByGuid(String guid) {
        CategoryConnectionDao.deleteAllCategoryConnectionsByGuid(connections, guid, true);
        var sql = "DELETE FROM Event WHERE guid = ?";
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Event not found, guid: " + guid);
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 event (rows=%d) has been deleted: %s"
                        .formatted(rowsUpdated, guid));
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete event, guid: " + guid, ex);
        }
    }

    @Override
    public void deleteAll() {
        CategoryConnectionDao.deleteAllCategoryConnections(connections, true);
        var sql = "DELETE FROM Event";
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete all events", ex);
        }
    }

    @Override
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
