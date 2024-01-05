package cz.muni.fi.pv168.project.todoapp.business.service.export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import cz.muni.fi.pv168.project.todoapp.business.service.export.batch.Batch;
import cz.muni.fi.pv168.project.todoapp.business.service.export.batch.BatchExporter;
import cz.muni.fi.pv168.project.todoapp.business.service.export.format.Format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * BatchExporter implementation using gson library gson.code.google.com
 */
public class JsonExporter implements BatchExporter {
    @Override
    public void exportBatch(Batch batch, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
            gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
            gsonBuilder.registerTypeAdapter(Duration.class, new DurationSerializer());
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            writer.write(gson.toJson(batch));
            writer.newLine();
            writer.flush();
        } catch (IOException ignored) {
        }
    }

    @Override
    public Format getFormat() {
        return new Format("json", List.of("json"));
    }

    private static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    private static class LocalTimeSerializer implements JsonSerializer<LocalTime> {
        public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    private static class DurationSerializer implements JsonSerializer<Duration> {
        public JsonElement serialize(Duration src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toMinutes());
        }
    }
}

