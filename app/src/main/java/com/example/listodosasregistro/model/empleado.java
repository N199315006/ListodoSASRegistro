package com.example.listodosasregistro.model;

public class empleado {


    private String id_empleado;
    private String nombre;
    private String apellido;
    private String celular;
    private String area;
    private String cargo;

    public empleado() {
    }

    public empleado(String id_empleado, String nombre, String apellido,String celular, String area, String cargo) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.area = area;
        this.cargo = cargo;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {return nombre +" " + apellido;
    }
}
