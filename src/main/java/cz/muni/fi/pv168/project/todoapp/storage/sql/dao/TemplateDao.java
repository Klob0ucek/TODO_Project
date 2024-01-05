package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.TemplateEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.Duration;
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
            statement.setTime(6, newTemplate.time() == null ? null : Time.valueOf(newTemplate.time()));
            statement.setInt(7, (int) newTemplate.duration().toMinutes());


            statement.executeUpdate();
            try (var keyResultSet = statement.getGeneratedKeys()) {
                long templateId;

                if (keyResultSet.next()) {
                    templateId = keyResultSet.getLong(1);
                } else {
                    throw new DataStorageException("Failed to fetch generated key for: " + newTemplate);
                }
                if (keyResultSet.next()) {
                    throw new DataStorageException("Multiple keys returned for: " + newTemplate);
                }
                CategoryConnectionDao.createTemplateConnection(connections, templateId, newTemplate.categories());
                return findById(templateId).orElseThrow();
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

            List<TemplateEntity> templates = new ArrayList<>();
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var template = templateFromResultSet(resultSet);
                    templates.add(template);
                }
            }

            return templates;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load all templates", ex);
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
                return Optional.of(templateFromResultSet(resultSet));
            } else {
                resultSet.close();
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load template by id", ex);
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
                return Optional.of(templateFromResultSet(resultSet));
            } else {
                resultSet.close();
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load template by guid", ex);
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
            statement.setTime(5, entity.time() == null ? null : Time.valueOf(entity.time()));
            statement.setInt(6, (int) entity.duration().toMinutes());
            statement.setLong(7, entity.id());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new DataStorageException("Template not found, id: " + entity.id());
            }
            if (rowsUpdated > 1) {
                throw new DataStorageException("More then 1 template (rows=%d) has been updated: %s"
                        .formatted(rowsUpdated, entity));
            }
            CategoryConnectionDao.deleteTemplateConnectionsById(connections, entity.id());
            CategoryConnectionDao.createTemplateConnection(connections, entity.id(), entity.categories());
            return entity;
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to update template: " + entity, ex);
        }
    }

    @Override
    public void deleteByGuid(String guid) {
        Optional<TemplateEntity> entity = findByGuid(guid);
        CategoryConnectionDao.deleteTemplateConnectionsById(connections, entity.get().id());
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
                throw new DataStorageException("More then 1 template (rows=%d) has been deleted: %s"
                        .formatted(rowsUpdated, guid));
            }
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete template, guid: " + guid, ex);
        }
    }

    @Override
    public void deleteAll() {
        CategoryConnectionDao.deleteAllTemplateConnections(connections);
        var sql = "DELETE FROM Template";
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete all templates", ex);
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
            throw new DataStorageException("Failed to check if template exists: " + guid, ex);
        }
    }

    private TemplateEntity templateFromResultSet(ResultSet resultSet) throws SQLException {
        List<Category> cats = CategoryConnectionDao.findTemplateCategoriesById(connections, resultSet.getLong("id"));
        Time time = resultSet.getTime("time");
        return new TemplateEntity(
                resultSet.getString("guid"),
                resultSet.getLong("id"),
                resultSet.getBoolean("isDone"),
                resultSet.getString("templateName"),
                resultSet.getString("eventName"),
                cats,
                resultSet.getString("location"),
                time == null ? null : time.toLocalTime(),
                Duration.ofMinutes(resultSet.getInt("duration"))
        );
    }
}
