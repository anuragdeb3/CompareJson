package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.*;

public class ResponseHandler {

    /**
     * Extract value(s) by key from JSON.
     * @param response RestAssured response
     * @param targetKey Key to search
     * @param returnAll If true, return all matches; else return first
     * @return List of values or singleton list
     */
    public static List<String> extractValue(Response response, String targetKey, boolean returnAll) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.asString());
            return findValueIteratively(root, targetKey, returnAll);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    private static List<String> findValueIteratively(JsonNode root, String targetKey, boolean returnAll) {
        List<String> result = new ArrayList<>();
        Deque<JsonNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            JsonNode current = stack.pop();

            if (current.isObject()) {
                Iterator<Map.Entry<String, JsonNode>> fields = current.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    if (entry.getKey().equals(targetKey)) {
                        result.add(entry.getValue().asText());
                        if (!returnAll) return result;
                    }
                    stack.push(entry.getValue());
                }
            } else if (current.isArray()) {
                for (JsonNode item : current) {
                    stack.push(item);
                }
            }
        }

        return result;
    }
}
