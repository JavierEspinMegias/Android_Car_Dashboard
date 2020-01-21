package com.android.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


public class FragUsers extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView text;
    private RecyclerView recycler;
    private GroupAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Persona mPersona = new Persona("noName");

    private OnFragmentInterfaceCom mListener;

    public FragUsers() {}
    public static FragUsers newInstance(String param1, String param2) {
        FragUsers fragment = new FragUsers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static FragUsers newInstanceData(String param1, Object param2) {
        FragUsers fragment = new FragUsers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, (Serializable) param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            if (mParam1.equals("persona")){
                mPersona = (Persona) getArguments().getSerializable(ARG_PARAM2);
            }else{
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment1, container, false);

        recycler = (RecyclerView)v.findViewById(R.id.recyler);
        recycler.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        Persona per1 = new Persona("yuiyui");
        Persona per2 = new Persona("gfdhgfg");
        Persona per3 = new Persona("asdasd");

        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(per1);
        personas.add(per2);
        personas.add(per3);
        personas.add(per3);
        personas.add(per3);
        personas.add(per3);


        adapter = new GroupAdapter(v.getContext(), personas);
        recycler.setAdapter(adapter);


        text = (TextView)v.findViewById(R.id.frag_text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed("Yeah");
            }
        });
        text.setText(mPersona.getNombre());
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String data) {
        if (mListener != null) {
            mListener.onFragmentMessage("TAGFragment1", data);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInterfaceCom) {
            mListener = (OnFragmentInterfaceCom) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
