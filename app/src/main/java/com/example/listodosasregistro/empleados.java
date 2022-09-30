package com.example.listodosasregistro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listodosasregistro.model.empleado;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class empleados extends AppCompatActivity {

    EditText id_card, name, lastname, phone, areap, position;
    ListView lista_Empleados;

    //Metodos Listar Empleados
    private List<empleado> listarEmpleado = new ArrayList<empleado>();
    ArrayAdapter<empleado> arrayAdapterEmpleado;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        id_card = findViewById(R.id.Fcedula);
        name = findViewById(R.id.Fnombre);
        lastname = findViewById(R.id.Fapellido);
        phone = findViewById(R.id.Fcelular);
        areap = findViewById(R.id.Farea);
        position = findViewById(R.id.Fcargo);

        lista_Empleados = findViewById(R.id.listaEmpleados);
        iniFaribase();

        listarDatosEmpleados();
    }

    //listar empleados en la aplicacion
    private void listarDatosEmpleados() {
        databaseReference.child("Empleado").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listarEmpleado.clear();//limpieza de la memoria cache generando persistencia de datos
                for (DataSnapshot objSnaptshot : snapshot.getChildren()){
                    empleado emp = objSnaptshot.getValue(empleado.class);
                    listarEmpleado.add(emp);//adicionar la instancia

                    arrayAdapterEmpleado = new ArrayAdapter<empleado>(empleados.this, android.R.layout.simple_list_item_1, listarEmpleado);
                    lista_Empleados.setAdapter(arrayAdapterEmpleado);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //Inicializando la conexion con FIREBASE
    private void iniFaribase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    //Mostrar el menu
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opc, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Seleccion de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String cedula = id_card.getText().toString();
        String nombre = name.getText().toString();
        String apellido = lastname.getText().toString();
        String celular = phone.getText().toString();
        String area = areap.getText().toString();
        String cargo = position.getText().toString();

        switch (item.getItemId()){
            case R.id.icono_add:
                if(cedula.equals("") || nombre.equals("") || apellido.equals("")|| celular.equals("") || area.equals("") || cargo.equals("")){
                    validar();
                }else{
                    empleado emp = new empleado();
                    emp.setId_empleado(cedula.toString());
                    emp.setNombre(nombre);
                    emp.setApellido(apellido);
                    emp.setCelular(celular);
                    emp.setArea(area);
                    emp.setCargo(cargo);
                    databaseReference.child("Empleado").child(emp.getId_empleado()).setValue(emp);
                    Toast.makeText(this,"Agregado",Toast.LENGTH_SHORT).show();
                    Limpiar();
                }

                break;
            case R.id.icono_save:
                Toast.makeText(this,"Guardado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.icono_delete:
                Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
                break;

            default:break;
        }

        return true;
    }

    private void Limpiar() {

        id_card.setText("");
        name.setText("");
        lastname.setText("");
        phone.setText("");
        areap.setText("");
        position.setText("");


    }

    //validar campos vacios
    private void validar() {
        String cedula = id_card.getText().toString();
        String nombre = name.getText().toString();
        String apellido = lastname.getText().toString();
        String celular = phone.getText().toString();
        String area = areap.getText().toString();
        String cargo = position.getText().toString();

        if (TextUtils.isEmpty(id_card.getText())){
            id_card.setError("Campo Cedula Obligatorio");
        }else if(nombre.equals("")){
            name.setError("Campo Nombre Obligatorio");
        }else if(apellido.equals("")){
            lastname.setError("Campo Apellido Obligatorio");
        }else if(celular.equals("")){
            phone.setError("Campo Celular Obligatorio");
        }else if(area.equals("")){
            areap.setError("Campo Área Obligatorio");
        }else if(cargo.equals("")){
            position.setError("Campo Cargo Obligatorio");
        }
    }
}