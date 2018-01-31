package org.pos.server;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.scan.StandardJarScanFilter;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;
import org.pos.constant.TomcatSkipPattern;
import org.springframework.util.StringUtils;

public class RestServer extends QBeanSupport implements RestServerMBean{
  
  private static Logger logger = Logger.getLogger(RestServer.class);
  private int port;
  private Tomcat tomcat;
  
  @Override
  protected void initService() throws Exception {
    logger.info("Starting tomcat...");
    String appBase = ".";
    tomcat = new Tomcat();
    tomcat.setPort(getPort());
    tomcat.getHost().setAppBase(appBase);
    tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
    Context context = tomcat.addWebapp("/pos-app", new File(System.getProperty("java.io.tmpdir")).getAbsolutePath());
    //Skip jar scanning
    StandardJarScanner jarScanner = new StandardJarScanner();
    StandardJarScanFilter jarScanFilter = new StandardJarScanFilter();
    jarScanFilter.setPluggabilitySkip(StringUtils.collectionToCommaDelimitedString(TomcatSkipPattern.JARS_TO_SKIP));
    jarScanFilter.setTldSkip(StringUtils.collectionToCommaDelimitedString(TomcatSkipPattern.JARS_TO_SKIP));
    jarScanner.setJarScanFilter(jarScanFilter);
    context.setJarScanner(jarScanner);
    tomcat.start();
    NameRegistrar.register(getName(), this);
  }
  
  @Override
  public void destroy() {
    if(tomcat != null){
      try {
        stopTomcat();
      } catch (Exception e) {
        logger.error("", e);
      }
    }
    NameRegistrar.unregister(getName());
  }
  private void stopTomcat()throws Exception{
    try{
      tomcat.stop();
    }
    finally {
      tomcat.destroy();
    }
  }
  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
