package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.SortedSet;
import java.util.stream.Collectors;

import ies.carrillo.contactbookmperval.R;
import models.Contact;

public class Adapters extends ArrayAdapter<Contact> {

    public Adapters(Context context, SortedSet<Contact> contactos){
        super(context, 0, contactos.stream().collect(Collectors.toList()));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        TextView textNombre = convertView.findViewById(R.id.textNombre);
        textNombre.setText(contact.getNumero());
        return convertView;
    }
}
