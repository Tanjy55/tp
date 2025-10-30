package seedu.quotely.storage;

import org.junit.jupiter.api.Test;
import seedu.quotely.command.AddItemCommand;
import seedu.quotely.command.DeleteItemCommand;
import seedu.quotely.command.DeleteQuoteCommand;
import seedu.quotely.data.CompanyName;
import seedu.quotely.data.Item;
import seedu.quotely.data.Quote;
import seedu.quotely.data.QuoteList;
import seedu.quotely.data.QuotelyState;
import seedu.quotely.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JsonSerializerTest {

    private final String referenceJsonString1 = "{\n" +
            "  \"quotes\": []\n" +
            "}";
    private final String referenceJsonString2 = "{\n" +
            "  \"quotes\": [\n" +
            "    {\n" +
            "      \"quoteName\": \"1\",\n" +
            "      \"customerName\": \"1\",\n" +
            "      \"items\": []\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    private final String referenceJsonString3 = "{\n" +
            "  \"quotes\": [\n" +
            "    {\n" +
            "      \"quoteName\": \"1\",\n" +
            "      \"customerName\": \"1\",\n" +
            "      \"items\": [\n" +
            "        {\n" +
            "          \"itemName\": \"TestItem\",\n" +
            "          \"price\": 10.0,\n" +
            "          \"quantity\": 2,\n" +
            "          \"taxRate\": 0.0\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    /**
     * Invalid input not tested as proper QuoteList is expected always
     */
    @Test
    public void serialize_validInput_success() {
        try {
            //setup test
            QuoteList quoteList = new QuoteList();
            JsonSerializer serializer = new JsonSerializer();
            Ui ui = Ui.getInstance();
            CompanyName companyName = new CompanyName("default");
            QuotelyState state = QuotelyState.getInstance();

            //test 1: empty QuoteList
            String testJson1 = serializer.serialize(quoteList);
            assertEquals(referenceJsonString1, testJson1);

            //test 2: add 1 quote and test
            Quote quote = new Quote("1", "1");
            quoteList.addQuote(quote);
            String testJson2 = serializer.serialize(quoteList);
            assertEquals(referenceJsonString2, testJson2);

            //test 3: add 1 item and test
            AddItemCommand addItemCommand = new AddItemCommand("TestItem", quote, 10.0, 2, 0);
            addItemCommand.execute(ui, quoteList, companyName, state);
            String testJson3 = serializer.serialize(quoteList);
            assertEquals(referenceJsonString3, testJson3);

            //test 4: delete the item and assert that saved condition is identical to test 2
            DeleteItemCommand deleteItemCommand = new DeleteItemCommand("TestItem", quote);
            deleteItemCommand.execute(ui, quoteList, companyName, state);
            assertEquals(referenceJsonString2, testJson2);

            //test 5: delete the quote and assert that the saved condition is identical to test 1
            DeleteQuoteCommand deleteQuoteCommand = new DeleteQuoteCommand(quote);
            deleteQuoteCommand.execute(ui, quoteList, companyName, state);
            assertEquals(referenceJsonString1, testJson1);
        } catch (Exception e) {
            assert false : "Execution should not fail.";
        }
    }

    @Test
    public void deserialize_validInput_success() {
        try {
            //setup test
            JsonSerializer serializer = new JsonSerializer();

            //test 1: deserialize empty QuoteList
            QuoteList testQuoteList1 = serializer.deserialize(referenceJsonString1);
            assertEquals(0, testQuoteList1.getQuotes().size());

            //test 2: deserialize QuoteList with 1 quote
            QuoteList testQuoteList2 = serializer.deserialize(referenceJsonString2);
            assertEquals(1, testQuoteList2.getQuotes().size());
            assertEquals("1", testQuoteList2.getQuotes().get(0).getQuoteName());
            assertEquals("1", testQuoteList2.getQuotes().get(0).getCustomerName());

            //test 3: deserialize QuoteList with item added
            QuoteList testQuoteList3 = serializer.deserialize(referenceJsonString3);
            assertEquals(1, testQuoteList3.getQuotes().size());
            Quote quote = testQuoteList3.getQuotes().get(0);
            Item item = quote.getItems().get(0);
            assertEquals("TestItem", item.getItemName());
            assertEquals(10.0, item.getPrice());
            assertEquals(2, item.getQuantity());
            assertFalse(item.hasTax());

            //test 4: deserialize QuoteList with item removed and assert that saved condition is identical to test 2
            QuoteList testQuoteList4 = serializer.deserialize(referenceJsonString2);
            assertEquals(1, testQuoteList4.getQuotes().size());
            assertEquals("1", testQuoteList4.getQuotes().get(0).getQuoteName());
            assertEquals("1", testQuoteList4.getQuotes().get(0).getCustomerName());

            //test 5: deserialize QuoteList with quote removed and assert that saved condition is identical to test 1
            QuoteList testQuoteList5 = serializer.deserialize(referenceJsonString1);
            assertEquals(0, testQuoteList5.getQuotes().size());

        } catch (Exception e) {
            assert false : "Execution should not fail.";
        }
    }
}
