package com.stackstate.mm;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MadController {

  private final static Logger logger = LoggerFactory.getLogger(MadController.class);

  private final AtomicLong counter = new AtomicLong();
  private final Configuration config = new Configuration();
  private FailureConfig failureConfig = new FailureConfig();
  private String configurationServiceURL = System.getenv("CONFIGURATION_SERVICE_URL");

  private void processFailureConfig() {
    if (failureConfig.isFailureMode("GRADUAL_SLOWDOWN")) {
      config.setLatency(config.getLatency() + (long) (config.getLatency() * failureConfig.getFailureModeParamAsDouble()));
      log("Simulating gradual slowdown, latency is now max " + config.getLatency() + " millis");
    } else if (failureConfig.isFailureMode("TRANSIENT_SLOWDOWN")) {
      if (Math.random() < failureConfig.getFailureModeParamAsDouble()) {
        log("Simulating transient slowdown, waiting up to 30000 millis");
        randomWait(30000);
      }
    } else if (failureConfig.isFailureMode("GRADUAL_FAILURES")) {
      config.setErrorRate(config.getErrorRate() + (Double) (config.getErrorRate() * failureConfig.getFailureModeParamAsDouble()));
      log("Simulating gradual failure increase, failure rate is now " + config.getErrorRate());
    }
  }

  private void randomWait(long max) {
    try {
      long sleep = (long) (Math.random() * max);
      log("Waiting for " + sleep + " millis");
      Thread.sleep(sleep);
    } catch(Exception e) {
      // ignore
    }
  }

  private void log(String message) {
    logger.info("[" + config.getService() + "] " + message);
  }

  private void fetchConfigurationIfNeeded() {
    long counterValue = counter.incrementAndGet();
    if (counterValue % 10 == 0) {
      // Fetch new configuration
      log("Fetching configuration");
      String url = configurationServiceURL + "/config?service=" + config.getService();
      failureConfig = new RestTemplate().getForObject(url, FailureConfig.class);
    }
  }

  @RequestMapping("/invoke")
  public Reply invoke(@RequestParam(value="source") String source) {
    log("Invoked by " + source);

    // Process failure config
    processFailureConfig();

    // Generate random error, if configured
    if (Math.random() < config.getErrorRate()) {
      log("Simulating error rate, throwing exception");
      throw new MadException("Simulating error rate");
    }

    // Invoke dependencies
    if (config.getDependencies() != null) {
      for (Dependency d : config.getDependencies()) {
        if (Math.random() < d.getProbability()) {
          //logger.info("Invoking dependency " + d);
          String url = "http://" + d.getHost() + ":" + d.getPort() + "/invoke?source=" + config.getService();
          new RestTemplate().getForObject(url, String.class);
        }
      }
    }

    // Wait random latency, if configured
    if (config.getLatency() > 0) {
      randomWait(config.getLatency());
    }

    fetchConfigurationIfNeeded();

    return new Reply(counter.get(), source);
  }
}
