package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import models.Contact;

public class Database {

    public static SortedSet<Contact> lista = new TreeSet<>();



   public static int getID(){
       int result = 0;
       for(Contact c:lista){
           if(result < c.getId()){
               result = getID();
           }
       }
       return result;
   }
    public static void populateDatabase() {
        for(int i=1; i<20; i++) {
            Contact c = new Contact();
            c.setId(4 +i);
            c.setNombre("Persona " +i);
            c.setApellidos("Apellidos" +i);
            c.setNumero("654345234");
            c.setCorreo("persona" + i + "@iescarrillo.es");
            lista.add(c);
        }
    }
   public SortedSet<Contact> getContactList(){
       return lista;
   }

   public static void addContact(Contact contact){
       lista.add(contact);
   }
}
