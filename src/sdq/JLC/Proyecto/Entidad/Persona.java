package sdq.JLC.Proyecto.Entidad;

public class Persona {
	
	int codigo;
	String nombre;
	String apellido;
	String curso;
	String clave;
	String rol;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Persona(String nombre, String apellido, String curso) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.curso = curso;
	}
	public Persona(int codigo, String nombre, String apellido, String curso) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.curso = curso;
	}
	public Persona(int codigo, String nombre, String apellido, String curso, String clave) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.curso = curso;
		this.clave = clave;
	}
	public Persona(int codigo, String nombre, String apellido, String clave, String rol, String variable) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.clave = clave;
		this.rol = rol;
	}

}
