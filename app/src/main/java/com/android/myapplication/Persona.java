package com.android.myapplication;

import android.content.pm.PackageManager;

import java.io.Serializable;

public class Persona implements Serializable {
    String nombre;

    public Persona(){
        this.nombre = "persona_test";
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
