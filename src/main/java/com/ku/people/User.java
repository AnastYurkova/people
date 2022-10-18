package com.ku.people;

public class User {
    private Long id;
    private String username;
    private String password;
    private String surname;
    private String name;

    public User() {
    }

    public User(Long id, String username, String password, String surname, String name) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
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
        User user = (User) o;

        if (getId() == null) {
            if (user.getId() != null) {
                return false;
            }
        } else if (!getId().equals(user.getId())) {
            return false;
        }

        if (getUsername() == null) {
            if (user.getUsername() != null) {
                return false;
            }
        } else if (!getUsername().equals(user.getUsername())) {
            return false;
        }

        if (getPassword() == null) {
            if (user.getPassword() != null) {
                return false;
            }
        } else if (!getPassword().equals(user.getPassword())) {
            return false;
        }

        if (getSurname() == null) {
            if (user.getSurname() != null) {
                return false;
            }
        } else if (!getSurname().equals(user.getSurname())) {
            return false;
        }

        return getName() == null ? user.getName() == null : getName().equals(user.getName());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result =  31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
    @Override
    public String toString()
    {
        return getClass().getSimpleName()+" "+"{"+ "id = " +getId()+ ", " +"Username = " +getUsername()+ ", "+ "Password = " +getPassword()+ ", " +"Surname = "+getSurname()+ ", " +"Name = "+getName()+ " "+"}";
    }
}
