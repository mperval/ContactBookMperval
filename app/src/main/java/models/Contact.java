package models;

public class Contact implements Comparable<Contact> {
    private int id;
    private String number;
    private String name;
    private String lastName;
    private String email;

    public Contact() {

    }

    public Contact(int id, String number, String name, String lastName, String email) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int compareTo(Contact c) {
        int result = 0;

        if (this.getName().compareTo(c.getName()) == 0) {
            if (this.getLastName().compareTo(c.getLastName()) == 0) {
                result = this.getNumber().compareTo(c.getNumber());
            }
            result = this.getLastName().compareTo(c.getLastName());
        } else {
            result = this.getName().compareTo(c.getName());
        }

        return result;
    }

    public boolean equals(Contact c) {
        return this.getNumber().equals(c.getNumber());
    }
}
