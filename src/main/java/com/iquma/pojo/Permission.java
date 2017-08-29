package com.iquma.pojo;

public class Permission {
    private Byte id;

    private String permission;

    private String description;

    private Boolean isAvailable;

    public Permission(){

    }

    public Permission(String permission) {
        this.permission = permission;
    }

    public Permission(String permission, String description, Boolean isAvailable) {
        this.permission = permission;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission role = (Permission) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }



}