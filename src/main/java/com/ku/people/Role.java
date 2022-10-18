package com.ku.people;

public class Role {
    private Long id;
    private String name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;

        if (getId() == null) {
            if (role.getId() != null) {
                return false;
            }
        } else if (!getId().equals(role.getId())) {
            return false;
        }

        return getName() == null ? role.getName() == null : getName().equals(role.getName());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result =  31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public String toString()
    {
        return getClass().getSimpleName()+" "+"{"+ "id = " +getId()+ ", " +"name = " +getName()+ " "+"}";
    }

}
