package br.ufscar.dc.atv1;


/**
 *
 * @author Ellen-
 */

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
  public AppSecurityInitializer() {
      super(AppConfig.class);
  }
}