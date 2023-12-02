package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

import cz.muni.fi.pv168.project.todoapp.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.IntervalEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * DAO for {@link IntervalEntity} entity.
 */
public final class IntervalDao implements DataAccessObject<IntervalEntity> {

    private final Supplier<ConnectionHandler> connections;

    public IntervalDao(Supplier<ConnectionHandler> connections) {
        this.connections = connections;
    }

    @Override
    public IntervalEntity create(IntervalEntity newInterval) {
        var sql = """
                INSERT INTO Intervalz(
                    guid,
                    name,
                    abbreviation,
                    duration
                )
                VALUES (?, ?, ?, ?);
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, newInterval.guid());
            statement.setString(2, newInterval.name());
            statement.setString(3, newInterval.abbreviation());
            statement.setInt(4, (int) newInterval.duration().toMinutes());

            statement.executeUpdate();

            try (var keyResultSet = statement.getGeneratedKeys()) {
                long eventId;

                if (keyResultSet.next()) {
                    eventId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + newInterval);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + newInterval);
                }

                return findById(eventId).orElseThrow();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to store: " + newInterval, ex);
        }
    }

    @Override
    public Collection<IntervalEntity> findAll() {
        var sql = """
                SELECT  id,
                        guid,
                        name,
                        abbreviation,
                        duration
                FROM Intervalz
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            List<IntervalEntity> events = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var event = intervalFromResultSet(resultSet);
                    events.add(event);
                }
            }

            return events;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all intervals", ex);
        }
    }

    @Override
    public Optional<IntervalEntity> findById(long id) {
        var sql = """
                SELECT  id,
                        guid,
                        name,
                        abbreviation,
                        duration
                FROM Intervalz
                WHERE id = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(intervalFromResultSet(resultSet));
            } else {
                // event not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load interval by id", ex);
        }
    }

    @Override
    public Optional<IntervalEntity> findByGuid(String guid) {
        var sql = """
                SELECT  id,
                        guid,
                        name,
                        abbreviation,
                        duration
                FROM Intervalz
                WHERE guid = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(intervalFromResultSet(resultSet));
            } else {
                // event not found
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load interval by guid", ex);
        }
    }

    @Override
    public IntervalEntity update(IntervalEntity entity) {
        var sql = """
                UPDATE Intervalz
                SET name = ?,
                    abbreviation = ?,
                    duration = ?
                WHERE id = ?
                """;
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, entity.name());
            statement.setString(2, entity.abbreviation());
            statement.setInt(3, (int) entity.duration().toMinutes());
            statement.setLong(4, entity.id());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Interval not found, id: " + entity.id());
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 interval (rows=%d) has been updated: %s"
                        .formatted(rowsUpdated, entity));
            }
            return entity;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update interval: " + entity, ex);
        }
    }

    @Override
    public void deleteByGuid(String guid) {
        var sql = "DELETE FROM Intervalz WHERE guid = ?";
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Interval not found, guid: " + guid);
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 interval (rows=%d) has been deleted: %s"
                        .formatted(rowsUpdated, guid));
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete interval, guid: " + guid, ex);
        }
    }

    @Override
    public void deleteAll() {
        var sql = "DELETE FROM Intervalz";
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete all intervals", ex);
        }
    }

    @Override
    public boolean existsByGuid(String guid) {
        var sql = """
                SELECT id
                FROM Intervalz
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
            throw new DataStorageException("Failed to check if interval exists: " + guid, ex);
        }
    }

    private static IntervalEntity intervalFromResultSet(ResultSet resultSet) throws SQLException {
        return new IntervalEntity(
                resultSet.getString("guid"),
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("abbreviation"),
                Duration.ofMinutes(resultSet.getInt("duration"))
        );
    }
}
