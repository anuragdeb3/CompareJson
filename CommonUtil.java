
public class CommonUtil {

    public static AppResponse mapResponse(Response response) {

        AppResponse appResponse = new AppResponse();
        appResponse.setData(response.getBody().asString());
        appResponse.setHeaders(
                response.getHeaders().asList().stream()
                        .collect(Collectors.toMap(
                                Header::getName,
                                Header::getValue,
                                (existingValue, newValue) -> existingValue,
                                LinkedHashMap::new
                        ))
        );
        appResponse.setHttpStatusCode(response.getStatusCode());
        return appResponse;
    }
@SneakyThrows
    public static <T> T getObject(String jsonString, Class<T> cls) {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, cls);
    }

    @SneakyThrows
    public static <T> List<T> getObjects(String jsonString, Class<T> cls) {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(jsonString,
                TypeFactory.defaultInstance().constructCollectionType(List.class, cls));
    }

    @SneakyThrows
    public static <T> String getJson(T t) {

        return new ObjectMapper().writeValueAsString(t);
    }

    @SneakyThrows
    public static <T> T getObject(File fileObject, Class<T> cls) {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileObject, cls);
    }


}
