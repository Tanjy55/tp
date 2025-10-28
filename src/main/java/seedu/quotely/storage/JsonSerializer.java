package seedu.quotely.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.quotely.data.QuoteList;

/**
 * Serializes and deserializes the QuoteList to/from JSON format.
 */
public class JsonSerializer {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Converts a QuoteList object into a JSON string.
     */
    public String serialize(QuoteList quoteList) {
        return gson.toJson(quoteList);
    }

    /**
     * Converts a JSON string back into a QuoteList object.
     */
    public QuoteList deserialize(String json) {
        if (json == null || json.isEmpty()) {
            return new QuoteList();
        }
        return gson.fromJson(json, QuoteList.class);
    }
}