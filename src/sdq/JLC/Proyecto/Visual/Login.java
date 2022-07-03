package sdq.JLC.Proyecto.Visual;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sdq.JLC.Proyecto.Entidad.Persona;
import sdq.JLC.Proyecto.Persistencia.Conexion;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lblUsuario = null;
	private JLabel lblClave = null;
	private JTextField txtUsuario = null;
	private JPasswordField jpfClave = null;
	private JButton btnIniciar = null;
	static public boolean logged;
	Conexion conexion = new Conexion();
	
	/**
	 * This is the default constructor
	 */
	public Login() {
		super();
		logged = false;
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Login");
		this.setLocationRelativeTo(jContentPane);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblClave = new JLabel();
			lblClave.setBounds(new Rectangle(16, 77, 90, 26));
			lblClave.setText("Contraseña");
			lblUsuario = new JLabel();
			lblUsuario.setBounds(new Rectangle(15, 30, 89, 26));
			lblUsuario.setText("Usuario");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblUsuario, null);
			jContentPane.add(lblClave, null);
			jContentPane.add(getTxtUsuario(), null);
			jContentPane.add(getJpfClave(), null);
			jContentPane.add(getBtnIniciar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes txtUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtUsuario() {
		if (txtUsuario == null) {
			txtUsuario = new JTextField();
			txtUsuario.setBounds(new Rectangle(140, 31, 125, 29));
		}
		return txtUsuario;
	}

	/**
	 * This method initializes jpfClave	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJpfClave() {
		if (jpfClave == null) {
			jpfClave = new JPasswordField();
			jpfClave.setBounds(new Rectangle(138, 77, 125, 28));
		}
		return jpfClave;
	}

	/**
	 * This method initializes btnIniciar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnIniciar() {
		if (btnIniciar == null) {
			btnIniciar = new JButton();
			btnIniciar.setBounds(new Rectangle(77, 119, 122, 30));
			btnIniciar.setText("Iniciar Sesion");
			btnIniciar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!logged){
						Login.this.CheckUsuario("CALL `sp_tbladmin` ()");
					}
					if(!logged){
						Login.this.CheckUsuario("CALL `sp_tblestudent` ()");
					}
					if(!logged) {
						JOptionPane.showMessageDialog(Login.this, "El Usuario o la contraseña son incorretos.", "Error de acceso", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
		}
		return btnIniciar;
	}
	
	public void CheckUsuario(String storedProcd){
		
		ArrayList <Persona> listaDeUsuarios = Conexion.getUsuario(storedProcd);
		
		for (int i = 0; i < listaDeUsuarios.size(); i++) {
			Persona Usuario = listaDeUsuarios.get(i);
			if(txtUsuario.getText().equals(Usuario.getNombre()) && GetClave().equals(Usuario.getClave()))
				{
					Login.this.dispose();
					Gestor ventanaAdmin = new Gestor(Usuario.getCodigo(), Usuario.getNombre(),Usuario.getRol());
					ventanaAdmin.setVisible(true);
					ventanaAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					logged = true;
				}
			}
		}
	
	public String GetClave(){
		String stringClave = "";
		char[]password = jpfClave.getPassword();
		for (int i = 0; i < password.length; i++) {
			stringClave = stringClave + password[i];
		}
		return stringClave;
	}
	
}
