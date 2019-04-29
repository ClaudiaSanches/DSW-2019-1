package br.ufscar.dc.atv1.login;


/**
 *
 * @author Ellen-
 */

import br.ufscar.dc.atv1.login.AppConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppInitializer extends AbstractSecurityWebApplicationInitializer {
  public AppInitializer() {
      super(AppConfig.class);      
  }
}