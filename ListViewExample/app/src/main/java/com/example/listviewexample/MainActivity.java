package com.example.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Contact> listContacts = getAllContacts();
        ListView listaDeCursos = (ListView) findViewById(R.id.lista);
        //Exemplo sem personalização
        //ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, listContacts);
        //Exemplo com personalização
        BaseAdapter adapter = new AdapterPersonalizado(listContacts, this);

        listaDeCursos.setAdapter(adapter);
    }


    public class AdapterPersonalizado extends BaseAdapter {


        private final List<Contact> contacts;
        private final Activity act;

        public AdapterPersonalizado(List<Contact> contacts, Activity act) {
            this.contacts = contacts;
            this.act = act;
        }

        @Override
        public int getCount() {
            return contacts.size();
        }

        @Override
        public Object getItem(int position) {
            return contacts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;

            if (convertView == null) {
                view = act.getLayoutInflater().inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            //Extraindo o contato da linha
            final Contact contact = contacts.get(position);
            //Colocando valores
            holder.nome.setText(contact.getNome());
            holder.telefone.setText(contact.getTelefone());
            holder.imagem.setImageResource(contact.getImgID());
            holder.email.setText(contact.getEmail());
            //Listener colocado para mostrar possibilidade de clique na lista
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(act, contact.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }

    }

    //Deixa as Views mais bem organizadas
    public class ViewHolder {

        final TextView nome;
        final TextView telefone;
        final ImageView imagem;
        final TextView email;

        public ViewHolder(View view) {
            nome = view.findViewById(R.id.txt_view_list_nome);
            telefone = view.findViewById(R.id.txt_view_list_tel);
            imagem = view.findViewById(R.id.img_list_contact);
            email = view.findViewById(R.id.txt_view_list_email);
        }

    }

    private List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        Contact contact;
        // 1
        contact = new Contact();
        contact.setNome("Sr. Wayne");
        contact.setTelefone("+1(45)91111-1111");
        contact.setImgID(R.drawable.img_batman);
        contact.setEmail("batman@gmail.com");
        contactList.add(contact);
        // 2
        contact = new Contact();
        contact.setNome("Tony Stark");
        contact.setTelefone("+1(47)97777-4343");
        contact.setImgID(R.drawable.img_ironman2);
        contact.setEmail("ironman@gmail.com");
        contactList.add(contact);
        // 3
        contact = new Contact();
        contact.setNome("Cris Evans");
        contact.setTelefone("+1(11)92222-1212");
        contact.setImgID(R.drawable.img_captain_america);
        contactList.add(contact);
        // 4
        contact = new Contact();
        contact.setNome("Thor");
        contact.setTelefone("chamar heimdall");
        contact.setImgID(R.drawable.img_thor);
        contactList.add(contact);
        // 5
        contact = new Contact();
        contact.setNome("Mulher Gato");
        contact.setTelefone("+55(65)91111-1111");
        contact.setImgID(R.drawable.img_catwoman);
        contactList.add(contact);
        // 6
        contact = new Contact();
        contact.setNome("Lanterna Verde");
        contact.setTelefone("+1(24)32323-3232");
        contact.setImgID(R.drawable.img_green_lantern);
        contactList.add(contact);
        //7
        contact = new Contact();
        contact.setNome("Peter Parker");
        contact.setTelefone("+1(12)91212-1212");
        contact.setImgID(R.drawable.img_spiderman);
        contactList.add(contact);
        //7
        contact = new Contact();
        contact.setNome("Kal-El");
        contact.setTelefone("+1(11)91111-0000");
        contact.setImgID(R.drawable.img_superman);
        contactList.add(contact);
        //8
        contact = new Contact();
        contact.setNome("The flash");
        contact.setTelefone("+1(99)99999-9999");
        contact.setImgID(R.drawable.img_the_flash);
        contactList.add(contact);
        //9
        contact = new Contact();
        contact.setNome("Tony Stark Backup");
        contact.setTelefone("+1(47)97777-4344");
        contact.setImgID(R.drawable.img_ironman);
        contactList.add(contact);
        return contactList;
    }
}
