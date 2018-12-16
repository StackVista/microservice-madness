package com.stackstate.mm;

import java.nio.file.Files;
import java.io.File;

import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ConfigController {

  private final static Logger logger = LoggerFactory.getLogger(ConfigController.class);

  private final static Map<String, FailureConfig> configuration = new HashMap<String, FailureConfig>();
  private final static FailureConfig NONE_CONFIG = new FailureConfig("default", "NONE", "0.0");

  static {
    try {
      JSONObject obj = new JSONObject(new String(Files.readAllBytes(new File("/config/configuration-service.json").toPath())));
      JSONArray services = obj.getJSONArray("services");
      for (int i = 0; i < services.length(); i++) {
        JSONObject service = services.getJSONObject(i);
        String name = service.getString("service");
        configuration.put(name,
          new FailureConfig(name, service.getString("failureMode"), service.getString("failureModeParam")));
        logger.info("Configured service " + name);
      }
    } catch(Exception e) {
      logger.error("Error reading configuration: ", e);
    }
  }

  @GetMapping("/config")
  public FailureConfig getConfig(@RequestParam(value="service") String service) {
    logger.info("Config service request for service " + service);

    FailureConfig result = configuration.get(service);
    if (result == null) {
      result = NONE_CONFIG;
      logger.info("Returning NONE_CONFIG for service " + service);
    } else {
      logger.info("Found config for service " + service);
    }
    return result;
  }

  @PutMapping("/config")
  public void setConfig(@RequestParam(value="service") String service, @RequestBody FailureConfig config) {
    logger.info("Setting config for service " + service + " to " + config);
    configuration.put(service, config);
  }
}
