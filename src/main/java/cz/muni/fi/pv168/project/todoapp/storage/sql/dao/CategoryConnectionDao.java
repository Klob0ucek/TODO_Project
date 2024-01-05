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
    public static void createEventConnection(Supplier<ConnectionHandler> connections, long id, Iterable<Category> categories) {
        String sql = """
                INSERT INTO "EventCategories"(
                    eventId,
                    categoryId
                )
                VALUES  (?, ?);
                """;
        createCategoryConnection(connections, id, categories, sql);
    }

    public static void createTemplateConnection(Supplier<ConnectionHandler> connections, long id, Iterable<Category> categories) {
        String sql = """
                INSERT INTO "TemplateCategories"(
                    templateId,
                    categoryId
                )
                VALUES  (?, ?);
                """;
        createCategoryConnection(connections, id, categories, sql);
    }

    private static void createCategoryConnection(Supplier<ConnectionHandler> connections, long id, Iterable<Category> categories, String sql) {
        for (var category : categories) {
            try (
                    var connection = connections.get();
                    var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ) {
                statement.setLong(1, id);
                long catId = CategoryDao.findByGuid(category.getGuid(), connections).get().id();
                statement.setLong(2, catId);

                statement.executeUpdate();
                try (var keyResultSet = statement.getGeneratedKeys()) {
                    if (!keyResultSet.next()) {
                        throw new DataStorageException("Failed to fetch generated key for category connection");
                    }
                    if (keyResultSet.next()) {
                        throw new DataStorageException("Multiple keys returned");
                    }
                }
            } catch (SQLException ex) {
                throw new DataStorageException("Failed to store category connection for template/event with id: " + id, ex);
            }
        }
    }

    public static void deleteTemplateConnectionsById(Supplier<ConnectionHandler> connections, long id) {
        String sql = "DELETE FROM TemplateCategories WHERE templateId = ?";
        deleteAllCategoryConnectionsByGuid(connections, id, sql);
    }

    public static void deleteEventConnectionsById(Supplier<ConnectionHandler> connections, long id) {
        String sql = "DELETE FROM EventCategories WHERE eventId = ?";
        deleteAllCategoryConnectionsByGuid(connections, id, sql);
    }

    private static void deleteAllCategoryConnectionsByGuid(Supplier<ConnectionHandler> connections, long id, String sql) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete category connection, id of associated entity: " + id, ex);
        }
    }

    public static void deleteAllEventConnections(Supplier<ConnectionHandler> connections) {
        String sql = "DELETE FROM EventCategories";
        deleteAllCategoryConnections(connections, sql);
    }

    public static void deleteAllTemplateConnections(Supplier<ConnectionHandler> connections) {
        String sql = "DELETE FROM TemplateCategories";
        deleteAllCategoryConnections(connections, sql);
    }

    private static void deleteAllCategoryConnections(Supplier<ConnectionHandler> connections, String sql) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataStorageException("Failed to delete all categoryConnections", ex);
        }
    }

    public static List<Category> findEventCategoriesById(Supplier<ConnectionHandler> connections, long id) {
        String sql = """
                SELECT "eventId",
                       "categoryId"
                FROM "EventCategories"
                WHERE "eventId" = ?
                """;
        return findAllCategoryConnectionsById(connections, id, sql);
    }

    public static List<Category> findTemplateCategoriesById(Supplier<ConnectionHandler> connections, long id) {
        String sql = """
                SELECT "templateId",
                       "categoryId"
                FROM "TemplateCategories"
                WHERE "templateId" = ?
                """;
        return findAllCategoryConnectionsById(connections, id, sql);
    }


    private static List<Category> findAllCategoryConnectionsById(Supplier<ConnectionHandler> connections, long id, String sql) {
        try (
                var connection = connections.get();
                var statement = connection.use().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            List<CategoryEntity> entityList = new ArrayList<>();
            while (resultSet.next()) {
                long catId = resultSet.getLong("categoryId");
                CategoryEntity categoryEntity = CategoryDao.findById(catId, connections).orElseThrow();
                entityList.add(categoryEntity);
            }
            resultSet.close();
            var mapper = new CategoryMapper();
            return entityList.stream().map(mapper::mapToBusiness).toList();

        } catch (SQLException ex) {
            throw new DataStorageException("Failed to load categories by id", ex);
        }
    }
}
