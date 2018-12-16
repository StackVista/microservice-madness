package com.stackstate.mm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Dependency {
  private final static Logger logger = LoggerFactory.getLogger(Dependency.class);

  private String host;
  private String port;
  private Double probability;

  public Dependency(String s) {
      // Expected format: mad-2:8080|0.3
      logger.debug("Incoming dependency: " + s);
      String[] hostProbSplit = s.split("\\|");
      parseHost(hostProbSplit[0]);
      parseProbability(hostProbSplit[1]);
  }

  private void parseHost(String s) {
    logger.debug("Parsing host: " + s);
    String[] parts = s.split(":");
    host = parts[0];
    port = parts[1];
  }

  private void parseProbability(String s) {
    logger.debug("Parsing prob: " + s);
    probability = new Double(s);
  }

  public String getHost() {
    return host;
  }

  public String getPort() {
    return port;
  }

  public Double getProbability() {
    return probability;
  }

  public String toString() {
    return "Dependency on " + host + ":" + port + " with probability " + probability;
  }
}
