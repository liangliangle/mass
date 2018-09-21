package com.mass.dto.login;

import com.mass.common.dto.BaseDto;

import java.util.List;

public class MenuDto extends BaseDto {
  private String title;
  private String name;
  private String url;
  private String appIcon;
  private String pcIcon;
  private String parentId;
  private List<MenuDto> children;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getAppIcon() {
    return appIcon;
  }

  public void setAppIcon(String appIcon) {
    this.appIcon = appIcon;
  }

  public String getPcIcon() {
    return pcIcon;
  }

  public void setPcIcon(String pcIcon) {
    this.pcIcon = pcIcon;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public List<MenuDto> getChildren() {
    return children;
  }

  public void setChildren(List<MenuDto> children) {
    this.children = children;
  }


}
