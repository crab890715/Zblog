package com.zblog.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zblog.core.WebConstants;

public class InitApplicationListener implements ServletContextListener{
  
  @Override
  public void contextInitialized(ServletContextEvent sce){
    WebConstants.APPLICATION_PATH = sce.getServletContext().getRealPath("/");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce){

  }

}
