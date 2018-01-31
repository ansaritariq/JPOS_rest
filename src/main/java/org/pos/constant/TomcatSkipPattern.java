package org.pos.constant;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class TomcatSkipPattern {
  
  private TomcatSkipPattern(){
    
  }
  public static final Set<String> JARS_TO_SKIP;

  static {
    Set<String> patterns = new LinkedHashSet<>();
    patterns.add("jdom2-*.jar");
    patterns.add("jdbm-*.jar");
    patterns.add("je*.jar");
    patterns.add("commons-cli*.jar");
    patterns.add("jline*.jar");
    patterns.add("bsh*.jar");
    patterns.add("javatuples*.jar");
    patterns.add("org.osgi.core*.jar");
    patterns.add("bcpg-jdk15on*.jar");
    patterns.add("bcprov-jdk15on*.jar");
    patterns.add("slf4j-api*.jar");
    patterns.add("javassist*.jar");
    patterns.add("HdrHistogram*.jar");
    patterns.add("sshd-core*.jar");
    JARS_TO_SKIP = Collections.unmodifiableSet(patterns);
  }

}
