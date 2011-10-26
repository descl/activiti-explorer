package hssc.activiti.identity;

import clojure.lang.RT;
import clojure.lang.Var;
import clojure.lang.Symbol;
import org.activiti.engine.IdentityService;
import org.activiti.explorer.cache.RadixTree;

public class ClojureBridge {

  static {
    Symbol ns = Symbol.intern("hssc.activiti.identity.identity-service");
    Symbol ns2 = Symbol.intern("hssc.activiti.identity.radix-tree");
    Var v = RT.var("clojure.core", "require");
    v.invoke(ns);
    v.invoke(ns2);
  }

  private static final String IDSER = "hssc.activiti.identity.identity-service";
  private static final String CRT = "hssc.activiti.identity.radix-tree";
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

  public static RadixTree createEmptyRadixTree(){
    try {
      return (RadixTree) RT.var(CRT, "create-empty-radix-tree").invoke();
    } catch(Exception e){
      return null;
    }
  }
}
