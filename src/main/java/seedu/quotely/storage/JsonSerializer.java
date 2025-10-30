package seedu.quotely.storage;

import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import seedu.quotely.data.QuoteList;
import seedu.quotely.util.LoggerConfig;

/**
 * Serializes and deserializes the QuoteList to/from JSON format.
 */
public class JsonSerializer {
    private static final Logger logger = LoggerConfig.getLogger(JsonSerializer.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Converts a QuoteList object into a JSON string.
     */
    public String serialize(QuoteList quoteList) {
        return gson.toJson(quoteList);
    }

    /**
     * Converts a JSON string back into a QuoteList object.
     * Defensive: catches parse errors, returns an empty QuoteList on failure,
     * and calls a validate() routine on QuoteList to enforce invariants.
     */
    public QuoteList deserialize(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new QuoteList();
        }

        try {
            QuoteList quoteList = gson.fromJson(json, QuoteList.class);
            if (quoteList == null) {
                return new QuoteList();
            }
            quoteList.validate();
            return quoteList;
        } catch (JsonParseException e) {
            logger.warning("Failed to parse QuoteList from JSON: " + e.getMessage());
            return new QuoteList();
        }
    }
}
