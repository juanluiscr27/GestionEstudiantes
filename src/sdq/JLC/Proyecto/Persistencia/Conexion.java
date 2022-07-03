package sdq.JLC.Proyecto.Persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import sdq.JLC.Proyecto.Entidad.Persona;

public class Conexion {
	static Connection conn;
	static Statement sentencia;
	static PreparedStatement psAgregar;
	static CallableStatement llamada;
	static CallableStatement llamadaLogin;
	 
	
	public  static ArrayList<Persona> getUsuario(String argLlamada) {
		
		ArrayList<Persona> listaDeUsuarios = new ArrayList<Persona>();
		ResultSet resultado;
		String variable = null;
		try {
			llamadaLogin = conn.prepareCall(argLlamada);
			resultado = llamadaLogin.executeQuery();	
			
			while(resultado.next()){
				listaDeUsuarios.add(new Persona(resultado.getInt("Codigo"),resultado.getString("Nombre"),resultado.getString("Apellido"),resultado.getString("Clave"), resultado.getString("Rol"),variable));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDeUsuarios;
	}
	
	public Conexion(){
		
	try {
		// Class.forName("com.mysql.jdbc.Driver").newInstance();
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			// conn=DriverManager.getConnection("jdbc:mysql://localhost/gestionsdq?user=root&password=");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/gestionsdq?user=root&password=JuanL123456");
			sentencia=conn.createStatement();
		
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ArrayList<Persona> Mostrar(){
		
		ArrayList<Persona> listaDeEstudiantes = new ArrayList<Persona>();
		ResultSet resultado = null;
		
		try {
			llamada = conn.prepareCall("CALL `sp_infoadmin` ();");
			resultado = llamada.executeQuery();
			while(resultado.next()){
				listaDeEstudiantes.add(new Persona(resultado.getInt("Codigo"),resultado.getString("Nombre"),resultado.getString("Apellido"),resultado.getString("Nombre_curso")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDeEstudiantes;
	}

	public static ArrayList<Persona> Mostrar(int argCodigo) {
		
		ArrayList<Persona> listaDeEstudiantes = new ArrayList<Persona>();
		ResultSet resultado = null;
		
		try {
			llamada = conn.prepareCall("CALL `sp_infoadmin2(in codigo int)` (" + argCodigo +")");
			resultado = llamada.executeQuery();
			while(resultado.next()){
				listaDeEstudiantes.add(new Persona(resultado.getInt("Codigo"),resultado.getString("Nombre"),resultado.getString("Apellido"),resultado.getString("Nombre_curso")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDeEstudiantes;
	}

	public static ArrayList<Persona> MostrarEst(int argCodigo){
		
		ArrayList<Persona> listaDeEstudiantes = new ArrayList<Persona>();
		ResultSet resultado = null;
		
		try {
			llamada = conn.prepareCall("call `sp_infoestudent(in prmcodigo int)` (" + argCodigo +")");
			resultado = llamada.executeQuery();
			while(resultado.next()){
				listaDeEstudiantes.add(new Persona(resultado.getInt("Codigo"),resultado.getString("Nombre"),resultado.getString("Apellido"),resultado.getString("Nombre_curso"),resultado.getString("Clave")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDeEstudiantes;
	}
	public static void AgregarDB(Persona estudiante){
		try {
			psAgregar = conn.prepareStatement("insert into estudiantes (Nombre, Apellido, Clave, Rol) values (?,?,?,?)");
			psAgregar.setString(1, estudiante.getNombre());
			psAgregar.setString(2, estudiante.getApellido());
			psAgregar.setString(3, estudiante.getApellido());
			psAgregar.setString(4, "Estudiante");
			psAgregar.execute();
			Inscribir(estudiante.getNombre(), estudiante.getApellido(), estudiante.getCurso());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void EliminarDB(int codigo, String curso){
		
		try {
			sentencia.execute("DELETE FROM clases WHERE clases.estudiante_id IN (SELECT clases.estudiante_id FROM cursos WHERE clases.estudiante_id =" + codigo + " AND cursos.nombre_curso = '" + curso + "' AND clases.curso_id = cursos.curso_id);");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Inscribir(String nombre, String apellido, String curso){
		try {
			llamada = conn.prepareCall("CALL `sp_newclasestudent(nomestudent, appestudent, curestudent)` ('"+nombre+"',  '"+apellido+"',  '"+curso+"');");
			llamada.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public static Vector<String> ObtenerCursos(){
		ResultSet resultado = null;
		Vector<String> listaDeCursos = new Vector<String>();
		try {
			resultado = sentencia.executeQuery("SELECT nombre_curso FROM cursos;");
			while(resultado.next()){
				String curso = resultado.getString("nombre_curso");			
				listaDeCursos.add(curso);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaDeCursos;
	}

}