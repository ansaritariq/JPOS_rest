package org.pos.server;

import org.jpos.q2.Q2;

public class PosServer {
  
  private PosServer(){
    
  }
  public static void main(String[] args) throws Exception {
    Q2 q2 = new Q2("deploy");
    q2.start();
  
  }
  
}

