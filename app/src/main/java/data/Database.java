package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import models.Contact;

public class Database {

    public static SortedSet<Contact> lista = new TreeSet<>();

    public void addContact(Contact contact){
        lista.add(contact);
    }
    // Obtener todos los contactos como una lista
    public static SortedSet<Contact> getContacts() {
        return lista;
    }

}
