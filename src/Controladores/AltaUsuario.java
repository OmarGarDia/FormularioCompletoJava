package Controladores;

public class AltaUsuario {

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private int telefono;
	private String direccion;
	private String sexo;
	
	public AltaUsuario(int id, String nombre, String apellidos, String email, int telefono, String direccion,
			String sexo) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.direccion = direccion;
		this.sexo = sexo;
	}

	public AltaUsuario() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return String.format(
				"AltaUsuario [id=%s, nombre=%s, apellidos=%s, email=%s, telefono=%s, direccion=%s, sexo=%s]", id,
				nombre, apellidos, email, telefono, direccion, sexo);
	}
	
	
	
}
