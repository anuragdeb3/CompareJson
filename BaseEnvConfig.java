@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class BaseEnvConfig {

    private String envToExecute;
    private int maxRetries = 2;

    private static BaseEnvConfig instance;

    private BaseEnvConfig() {}
    private BaseEnvConfig(String envToExecute) {
        this.envToExecute = envToExecute;
    }

    public static BaseEnvConfig getInstance() {

        if (instance == null ) {

            String envFilePath = "src/test/resources/env-Config.json";

            if(!Files.exists(Path.of(envFilePath))) {
                throw new RuntimeException("src/test/resources/env-Config.json is NOT available.");
            }

            updateEnvConfig(envFilePath);

            BaseEnvConfig baseEnvConfig = CommonUtil.getObject(new File(envFilePath), BaseEnvConfig.class);
            instance = new BaseEnvConfig(baseEnvConfig.getEnvToExecute());
        }

        return instance;
    }
