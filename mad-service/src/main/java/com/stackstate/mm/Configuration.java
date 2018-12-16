package com.stackstate.mm;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {

  private final static Logger logger = LoggerFactory.getLogger(Configuration.class);

  private String hostname = null;
  private String service = null;
  private Dependency[] dependencies = null;
  private long latency = 0;
  private Double errorRate = 0.0;

  public Configuration() {
    hostname = System.getenv("HOSTNAME");
    service = System.getenv("SERVICE");

    String dependenciesString = System.getenv("DEPENDENCIES");
    if (dependenciesString != null) {
      String[] dependencyStrings = dependenciesString.split(",");
      dependencies = new Dependency[dependencyStrings.length];
      for (int i=0; i<dependencyStrings.length; i++) {
        dependencies[i] = new Dependency(dependencyStrings[i]);
        logger.info("Configured dependency: " + dependencies[i]);
      }
    }

    String latencyConfig = System.getenv("LATENCY");
    if (latencyConfig != null) {
      latency = new Long(latencyConfig);
    }
    logger.info("Configured latency: " + latency);

    String errorConfig = System.getenv("ERROR_RATE");
    if (errorConfig != null) {
      errorRate = new Double(errorConfig);
    }
    logger.info("Configured error rate: " + errorRate);
  }

  public Dependency[] getDependencies() {
    return dependencies;
  }

  public Double getErrorRate() {
    return errorRate;
  }

  public void setErrorRate(Double rate) {
    this.errorRate = rate;
  }

  public long getLatency() {
    return latency;
  }

  public void setLatency(long latency) {
    this.latency = latency;
  }

  public String getHostname() {
    return hostname;
  }

  public String getService() {
    return service;
  }
}
