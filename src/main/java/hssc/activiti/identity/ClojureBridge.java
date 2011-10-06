package hssc.activiti.identity;

import clojure.lang.RT;
import clojure.lang.Var;
import clojure.lang.Symbol;
import org.activiti.engine.IdentityService;

public class ClojureBridge {

  static {
    spit("Here I gon in stackt");
    Symbol ns = Symbol.intern("hssc.activiti.identity.identity-service");
    RT.var("clojure.core", "require").invoke(ns);
  }

  private static final String IDSER = "hssc.activiti.identity.identity-service";
  public static IdentityService getIdentityService(){
    spit("Here I go in IdentityService");
    try {
      Var var = RT.var(IDSER, "get-instance");
      return (IdentityService) var.invoke();
    } catch(Exception e){
      System.out.println("AAAAH" + e.getMessage());
      return null;
    }
  };

  public ClojureBridge(){
    spit("HERE I GO IN THE CONSTRUCTOR");
  }

  public static void spit(String s){
    try {
      RT.var(IDSER, "append-spit").invoke("/tmp/clojure-bridge.txt", s);
    } catch(Exception e){}
  }
}
