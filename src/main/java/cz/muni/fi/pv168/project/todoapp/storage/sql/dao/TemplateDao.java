package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

import cz.muni.fi.pv168.project.todoapp.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.TemplateEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * DAO for {@link TemplateEntity} entity.
 */
public final class TemplateDao implements DataAccessObject<TemplateEntity> {

    private final Supplier<ConnectionHandler> connections;

    public TemplateDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public TemplateEntity create(TemplateEntity newTemplate) {
        var sql = """
                INSERT INTO Template(
                    guid,
                    templateName,
                    eventName,
                    isDone,
                    location,
                    time,
                    duration
                )
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, newTemplate.guid());
            statement.setString(2, newTemplate.templateName());
            statement.setString(3, newTemplate.eventName());
            statement.setBoolean(4, newTemplate.isDone());
            statement.setString(5, newTemplate.location());
            statement.setTime(6, Time.valueOf(newTemplate.time()));
            statement.setInt(7, (int) newTemplate.duration().toMinutes());
            statement.executeUpdate();

            try (var keyResultSet = statement.getGeneratedKeys()) {
                long eventId;

                if (keyResultSet.next()) {
                    eventId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + newTemplate);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + newTemplate);
                }

                return findById(eventId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + newTemplate, ex);
        }
    }

    @Override
    public Collection<TemplateEntity> findAll() {
        var sql = """
                SELECT  id,
                        guid,
                        templateName,
                        eventName,
                        isDone,
                        location,
                        time,
                        duration
                FROM Template
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            List<TemplateEntity> events = new ArrayList<>();
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
    public Optional<TemplateEntity> findById(long id) {
        var sql = """
                SELECT  id,
                        guid,
                        templateName,
                        eventName,
                        isDone,
                        location,
                        time,
                        duration
                FROM Template
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
    public Optional<TemplateEntity> findByGuid(String guid) {
        var sql = """
                SELECT  id,
                        guid,
                        templateName,
                        eventName,
                        isDone,
                        location,
                        time,
                        duration
                FROM Template
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
    public TemplateEntity update(TemplateEntity entity) {
        var sql = """
                UPDATE Template
                SET templateName = ?,
                    eventName = ?,
                    isDone = ?,
                    location = ?,
                    time = ?,
                    duration = ?
                WHERE id = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.templateName());
            statement.setString(2, entity.eventName());
            statement.setBoolean(3, entity.isDone());
            statement.setString(4, entity.location());
            statement.setTime(5, Time.valueOf(entity.time()));
            statement.setInt(6, (int) entity.duration().toMinutes());
            statement.setLong(7, entity.id());
            statement.executeUpdate();

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Template not found, id: " + entity.id());
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 event (rows=%d) has been updated: %s"
                        .formatted(rowsUpdated, entity));
            }
            return entity;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update event: " + entity, ex);
        }
    }

    @Override
    public void deleteByGuid(String guid) {
        var sql = "DELETE FROM Template WHERE guid = ?";
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Template not found, guid: " + guid);
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
        var sql = "DELETE FROM Template";
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
                FROM Template
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

    private static TemplateEntity eventFromResultSet(ResultSet resultSet) throws SQLException {
        return new TemplateEntity(
                resultSet.getString("guid"),
                resultSet.getLong("id"),
                resultSet.getBoolean("isDone"),
                resultSet.getString("templateName"),
                resultSet.getString("eventName"),
                List.of(),
                resultSet.getString("location"),
                LocalTime.of(15, 12),
                Duration.ofMinutes(resultSet.getInt("duration"))
        );
    }
}
