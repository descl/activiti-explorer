package hssc.activiti.identity;

import clojure.lang.RT;
import clojure.lang.Var;
import clojure.lang.Symbol;
import org.activiti.engine.IdentityService;

public class ClojureBridge {

  static {
    Symbol ns = Symbol.intern("hssc.activiti.identity.identity-service");
    RT.var("clojure.core", "require").invoke(ns);
  }

  private static final String IDSER = "hssc.activiti.identity.identity-service";
  public static IdentityService getIdentityService(){
    try {
      Var var = RT.var(IDSER, "get-instance");
      return (IdentityService) var.invoke();
    } catch(Exception e){
      return null;
    }
  };

  public IdentityService getIdentityServiceNonStatically() throws Exception{
    return getIdentityService();
  }
}
