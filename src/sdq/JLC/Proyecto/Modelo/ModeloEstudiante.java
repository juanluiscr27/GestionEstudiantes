package sdq.JLC.Proyecto.Modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import sdq.JLC.Proyecto.Entidad.Persona;
import sdq.JLC.Proyecto.Persistencia.Conexion;

public class ModeloEstudiante extends AbstractTableModel{

	private static final long serialVersionUID =1L;
	
	String [] encabezados = {"Codigo", "Nombre", "Apellido", "Curso", "Clave"};
	ArrayList <Persona> listaDeEstudiantes = null;
	
	public ModeloEstudiante(int codigoEst){
		
		listaDeEstudiantes = Conexion.MostrarEst(codigoEst);
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
		case 4:
			retorno = estudiante.getClave();
			break;
		default:
			break;
		}
		return retorno;
	}
	
	public void Actualizar(){
		
		fireTableDataChanged();
	}
}
