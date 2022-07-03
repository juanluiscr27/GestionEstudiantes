package sdq.JLC.Proyecto.Modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import sdq.JLC.Proyecto.Entidad.Persona;
import sdq.JLC.Proyecto.Persistencia.Conexion;

public class ModeloAdmin extends AbstractTableModel{

	private static final long serialVersionUID =1L;
	
	public String [] encabezados = {"Codigo", "Nombre", "Apellido", "Curso"};
	public ArrayList <Persona> listaDeEstudiantes = null;
	
	public ModeloAdmin(int codigo, String rol){
		if(rol.equals("Secretaria")){
			listaDeEstudiantes = Conexion.Mostrar();
		}
		else if(rol.equals("Profesor")){
			listaDeEstudiantes = Conexion.Mostrar(codigo);
		}
		else{
			listaDeEstudiantes = Conexion.Mostrar();
		}
	}

	@Override
	public String getColumnName(int nombEncabezado) {
		// TODO Auto-generated method stub
		return encabezados [nombEncabezado];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return encabezados.length;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaDeEstudiantes.size();
	}
	@Override
	public Object getValueAt(int fila, int columna) {
		// TODO Auto-generated method stub
		Object retorno= null;
		Persona estudiante = listaDeEstudiantes.get(fila);
		switch (columna) {
		case 0:
			retorno = estudiante.getCodigo();
			break;
		case 1:
			retorno = estudiante.getNombre();
			break;
		case 2:
			retorno = estudiante.getApellido();
			break;
		case 3:
			retorno = estudiante.getCurso();
			break;
		default:
			break;
		}
		return retorno;
	}
	
	public void Agregar (Persona estudiante){
		
		Conexion.AgregarDB(estudiante);
		listaDeEstudiantes = Conexion.Mostrar();
		this.Actualizar();
	}
	
	public void Eliminar (int fila){
		
		Conexion.EliminarDB(listaDeEstudiantes.get(fila).getCodigo(),listaDeEstudiantes.get(fila).getCurso());
		listaDeEstudiantes.remove(fila);
		fireTableRowsDeleted(fila,fila);
	}

	public void Actualizar(){
		
		fireTableDataChanged();
	}
}
