package models;

public class Contact implements Comparable<Contact> {
    private int id;
    private String numero;
    private String nombre;
    private String apellidos;
    private String correo;

    public Contact() {

    }
    public Contact(Contact c) {

    }
    public Contact(int id, String numero, String nombre, String apellidos, String correo) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "numero = " + numero +
                ", nombre = '" + nombre + '\'' +
                ", apellidos = '" + apellidos + '\'' +
                ", correo = '" + correo + '\'' +
                ", id = '" + id + '\'' +
                '}';
    }

    public int compareTo(Contact c) {
        int result = 0;

        if (this.getNombre().compareTo(c.getNombre()) == 0) {
            if (this.getApellidos().compareTo(c.getApellidos()) == 0) {
                result = this.getNumero().compareTo(c.getNumero());
            }
            result = this.getApellidos().compareTo(c.getApellidos());
        } else {
            result = this.getNombre().compareTo(c.getNombre());
        }

        return result;
    }

    public boolean equals(Contact c) {
        return this.getNumero().equals(c.getNumero());
    }
}
