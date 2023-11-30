package cz.muni.fi.pv168.project.todoapp.storage.sql.dao;

import cz.muni.fi.pv168.project.todoapp.business.model.Category;
import cz.muni.fi.pv168.project.todoapp.storage.sql.db.ConnectionHandler;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.CategoryEntity;
import cz.muni.fi.pv168.project.todoapp.storage.sql.entity.mapper.CategoryMapper;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CategoryConnectionDao {
    public static void createGuidConnection(Supplier<ConnectionHandler> connections, String guid, Iterable<Category> cats, boolean event) {
        String sql;
        for (var cat : cats) {
            if (event) {
                sql = """
                        INSERT INTO EntityCats(
                            eventGuid,
                            categoryGuid
                        )
                        VALUES  (?, ?);
                        """;
            } else {
                sql = """
                        INSERT INTO EntityCats(
                            templateGuid,
                            categoryGuid
                        )
                        VALUES  (?, ?);
                        """;
            }

            try (
                    var connection = connections.get();
                    var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ) {
                statement.setString(1, guid);
                statement.setString(2, cat.getGuid());

                statement.executeUpdate();
                try (var keyResultSet = statement.getGeneratedKeys()) {
                    long eventId;

                    if (keyResultSet.next()) {
                        eventId = keyResultSet.getLong(1);
                    } else {
                        throw new DataStorageException("Failed to fetch generated key for entity with guid: " + guid);
                    }
                    if (keyResultSet.next()) {
                        throw new DataStorageException("Multiple keys returned for entity with guid: " + guid);
                    }

                }
            } catch (SQLException ex) {
                throw new DataStorageException("Failed to store entity with guid: " + guid, ex);
            }
        }
    }

    public static void deleteAllCategoryConnectionsByGuid(Supplier<ConnectionHandler> connections, String guid, boolean event) {
        String sql;
        if (event) {
            sql = "DELETE FROM EntityCats WHERE eventGuid = ?";
        } else {
            sql = "DELETE FROM EntityCats WHERE templateGuid = ?";
        }
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete template, guid: " + guid, ex);
        }
    }

    public static void deleteAllCategoryConnections(Supplier<ConnectionHandler> connections, boolean event) {
        String sql;
        if (event) {
            sql = "DELETE FROM EntityCats WHERE templateGuid IS null";
        } else {
            sql = "DELETE FROM EntityCats WHERE eventGuid IS null";
        }
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete all categoryConnections", ex);
        }
    }

    public static List<Category> findAllCategoryConnectionsByGuid(Supplier<ConnectionHandler> connections, String guid, boolean event) {
        String sql;
        if (event) {
            sql = """
                    SELECT eventGuid,
                           categoryGuid
                    FROM EntityCats
                    WHERE eventGuid = ?
                    """;
        } else {
            sql = """
                    SELECT templateGuid,
                           categoryGuid
                    FROM EntityCats
                    WHERE templateGuid = ?
                    """;
        }
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, guid);
            var resultSet = statement.executeQuery();
            List<CategoryEntity> entityList = new ArrayList<>();
            while (resultSet.next()) {
                String catGuid = resultSet.getString("categoryGuid");
                CategoryEntity categoryEntity = CategoryDao.findByGuid(catGuid, connections).orElseThrow();
                entityList.add(categoryEntity);
            }
            var mapper = new CategoryMapper();
            return entityList.stream().map(mapper::mapToBusiness).toList();

        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load categories by id", ex);
        }
    }
}
