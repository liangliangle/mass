package com.mass.common.model;

/**
 * @author 李亮亮
 *  token的信息
 */
public class MassJwtModel {

  private String sno;
  private String userName;
  private Long id;
  private String currentRole;
  private String roles;

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public String getSno() {
    return sno;
  }

  public void setSno(String sno) {
    this.sno = sno;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCurrentRole() {
    return currentRole;
  }

  public void setCurrentRole(String currentRole) {
    this.currentRole = currentRole;
  }
}
