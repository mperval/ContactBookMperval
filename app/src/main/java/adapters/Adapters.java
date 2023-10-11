package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

import ies.carrillo.contactbookmperval.R;
import models.Contact;

public class Adapters extends ArrayAdapter<Contact> {

    public Adapters(Context context, SortedSet<Contact> contactos){
        super(context, 0, (List<Contact>) contactos.stream().collect(Collectors.toList()));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }
        TextView telefono = convertView.findViewById(R.id.telefono);
        TextView nombre = convertView.findViewById(R.id.nombre);
        TextView apellidos = convertView.findViewById(R.id.apellidos);
        TextView correo = convertView.findViewById(R.id.correo);

        // Establece los valores de los TextViews con los datos del contacto
        telefono.setText(String.valueOf(contact.getNumero()));
        nombre.setText(contact.getNombre());
        apellidos.setText(contact.getApellidos());
        correo.setText(contact.getCorreo());

        return convertView;
    }
}
