package org.pos.server;

import org.jpos.q2.QBeanSupportMBean;

public interface RestServerMBean extends QBeanSupportMBean{
  int getPort();
  void setPort(int port);
}
