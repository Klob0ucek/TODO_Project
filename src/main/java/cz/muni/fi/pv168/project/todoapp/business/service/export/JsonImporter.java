package cz.muni.fi.pv168.project.todoapp.business.service.export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import cz.muni.fi.pv168.project.todoapp.business.service.export.batch.Batch;
import cz.muni.fi.pv168.project.todoapp.business.service.export.batch.BatchImporter;
import cz.muni.fi.pv168.project.todoapp.business.service.export.format.Format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * BatchImporter implementation using gson library gson.code.google.com
 */
public class JsonImporter implements BatchImporter {
    @Override
    public Batch importBatch(String filePath) {
        Batch batch;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath, Charset.defaultCharset()))) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
            gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeDeserializer());
            gsonBuilder.registerTypeAdapter(Duration.class, new DurationDeserializer());
            Gson gson = gsonBuilder.create();

            batch = gson.fromJson(reader, Batch.class);
        } catch (IOException | JsonSyntaxException e) {
            int colonIndex = e.getMessage().indexOf(":");
            int pathIndex = e.getMessage().indexOf("path");
            String newMsg = e.getMessage().substring(colonIndex + 1, pathIndex).trim();
            throw new DataManipulationException(newMsg, e);
        }
        return batch;
    }

    @Override
    public Format getFormat() {
        return new Format("json", List.of("json"));
    }

    private static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            Integer[] parsed = Arrays.stream(json.getAsString().split("-")).map(Integer::parseInt).toArray(Integer[]::new);
            return LocalDate.of(parsed[0], parsed[1], parsed[2]);
        }
    }

    private static class LocalTimeDeserializer implements JsonDeserializer<LocalTime> {
        public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            Integer[] parsed = Arrays.stream(json.getAsString().split(":")).map(Integer::parseInt).toArray(Integer[]::new);
            return LocalTime.of(parsed[0], parsed[1]);
        }
    }

    private static class DurationDeserializer implements JsonDeserializer<Duration> {
        public Duration deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return Duration.ofMinutes(json.getAsInt()); //json.getAsJsonPrimitive().getAsString());
        }
    }
}
