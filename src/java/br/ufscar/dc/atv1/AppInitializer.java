package br.ufscar.dc.atv1;


/**
 *
 * @author Ellen-
 */

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppInitializer extends AbstractSecurityWebApplicationInitializer {
  public AppInitializer() {
      super(AppConfig.class);      
  }
}