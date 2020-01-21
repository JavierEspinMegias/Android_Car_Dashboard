package com.android.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{


    private ArrayList<Persona> users;



    //El constructor deberá enlazar los datos del modelos con los del controlador
    public GroupAdapter(Context activity, ArrayList<Persona> users) {
        this.users = users;
    }



    //En un adaptador es obligatorio definir una clase que herede de RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        //La clase ViewHolder hará referencia a los elementos de la vista creada para el recycler view
        public TextView name;
        public TextView id;
        public ImageView imageGroup;
        public LinearLayout background;

        //Su constructor debera enlazar las variables del controlador con la vista
        public ViewHolder(final View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.user_name);
            this.id = (TextView) itemView.findViewById(R.id.user_id);
            this.imageGroup = (ImageView)itemView.findViewById(R.id.image_view_adapter);
            this.background = (LinearLayout)itemView.findViewById(R.id.adapter_linear_layout);

        }
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Especificamos el fichero XML que se utilizará como vista
        View contactView = inflater.inflate(R.layout.adapter_groups, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(GroupAdapter.ViewHolder holder, final int position) {
        final Persona persona = this.users.get(position);
        holder.name.setText(persona.getNombre());

    }



    @Override
    public int getItemCount() {
        return users.size();
    }
}