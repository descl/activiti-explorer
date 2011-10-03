package org.activiti.explorer.identity;
import org.activiti.explorer.identity.LoggedInUser;

public class ShibbolethUser implements LoggedInUser {
  final String _id, _firstName, _lastName, _fullName;
  public ShibbolethUser(String id,
                        String firstName,
                        String lastName,
                        String fullName){
    _id = id;
    _firstName = firstName;
    _lastName = lastName;
    _fullName = fullName;
  }

  public String getId(){return _id;}
  public String getFirstName(){return _firstName;}
  public String getLastName(){return _lastName;}
  public String getFullName(){return _fullName;}
  public String getPassword(){return null;}
  public boolean isAdmin(){return false;}
  public boolean isUser(){return true;}
}
