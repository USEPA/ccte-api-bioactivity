package gov.epa.ccte.api.bioactivity.config;

import org.springframework.boot.SpringApplication;

import java.util.HashMap;
import java.util.Map;

public final class DefaultProfileUtil {
    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private DefaultProfileUtil() {
    }

    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap();
        defProperties.put("spring.profiles.default", "dev");
        app.setDefaultProperties(defProperties);
    }
}