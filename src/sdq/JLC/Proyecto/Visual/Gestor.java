package sdq.JLC.Proyecto.Visual;

import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import sdq.JLC.Proyecto.Sistema;
import sdq.JLC.Proyecto.Archivo.ExportPDF;
import sdq.JLC.Proyecto.Entidad.Persona;
import sdq.JLC.Proyecto.Modelo.ModeloAdmin;
import sdq.JLC.Proyecto.Modelo.ModeloEstudiante;
import sdq.JLC.Proyecto.Persistencia.Conexion;

import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.JComboBox;

public class Gestor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jspTabla = null;
	private JTable tblEstudiantes = null;
	private JTextField txtNombre = null;
	private JTextField txtApellido = null;
	private JLabel lblNombre = null;
	private JLabel lblApellido = null;
	private JLabel lblCurso = null;
	private JButton btnGuardar = null;
	private JButton btnEliminar = null;
	private JLabel lblBienvenida = null;
	private JButton lblLogoff = null;
	private JButton btnImprimir = null;
	public static boolean rolAdmin = false;
	public static boolean modelAdmin = false;
	static public String tituloRol;
	static public String userNombre;
	ModeloAdmin mAdministrador;
	ModeloEstudiante mEstudiante;
	DefaultComboBoxModel<String> mListaCurso;
	private JComboBox cboxCursos = null;
	/**
	 * This is the default constructor
	 */
	public Gestor(int codigoUsuario, String nombreUsuario, String rolUsuario) {
		
		userNombre = nombreUsuario;
		mAdministrador = new ModeloAdmin(codigoUsuario,rolUsuario);
		mEstudiante = new ModeloEstudiante(codigoUsuario);
		mListaCurso = new DefaultComboBoxModel(Conexion.ObtenerCursos());
		
		if (rolUsuario.equals("Secretaria")){
			rolAdmin = true;
			modelAdmin = true;
			tituloRol = "Administrador";
		}
		else if (rolUsuario.equals("Profesor")){
			rolAdmin = false;
			modelAdmin = true;
			tituloRol = "Administrador";
		}
		else if (rolUsuario.equals("Estudiante")){
			rolAdmin = false;
			modelAdmin = false;
			tituloRol = "Estudiante";
		}
		else{
			rolAdmin = false;
			modelAdmin = false;
			tituloRol = "Default";
		}
		
		initialize();
		if(rolAdmin){
			txtNombre.setEnabled(true);
			txtApellido.setEnabled(true);
			cboxCursos.setEnabled(true);
			btnGuardar.setEnabled(true);
			btnEliminar.setEnabled(true);
		}
		else{
			txtNombre.setEnabled(false);
			txtApellido.setEnabled(false);
			cboxCursos.setEnabled(false);
			btnGuardar.setEnabled(false);
			btnEliminar.setEnabled(false);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(717, 423);
		this.setContentPane(getJContentPane());
		this.setTitle("Gestion - " + tituloRol);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblBienvenida = new JLabel();
			lblBienvenida.setBounds(new Rectangle(15, 4, 266, 21));
			lblBienvenida.setText("Bienvenido " + userNombre);
			lblCurso = new JLabel();
			lblCurso.setBounds(new Rectangle(17, 157, 83, 22));
			lblCurso.setText("Curso:");
			lblApellido = new JLabel();
			lblApellido.setBounds(new Rectangle(16, 100, 93, 24));
			lblApellido.setText("Apellido:");
			lblNombre = new JLabel();
			lblNombre.setBounds(new Rectangle(14, 47, 99, 22));
			lblNombre.setText("Nombre:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJspTabla(), null);
			jContentPane.add(getTxtNombre(), null);
			jContentPane.add(getTxtApellido(), null);
			jContentPane.add(lblNombre, null);
			jContentPane.add(lblApellido, null);
			jContentPane.add(lblCurso, null);
			jContentPane.add(getBtnGuardar(), null);
			jContentPane.add(getBtnEliminar(), null);
			jContentPane.add(lblBienvenida, null);
			jContentPane.add(getLblLogoff(), null);
			jContentPane.add(getBtnImprimir(), null);
			jContentPane.add(getCboxCursos(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jspTabla	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJspTabla() {
		if (jspTabla == null) {
			jspTabla = new JScrollPane();
			jspTabla.setBounds(new Rectangle(150, 45, 544, 306));
			jspTabla.setViewportView(getTblEstudiantes());
		}
		return jspTabla;
	}

	/**
	 * This method initializes tblEstudiantes	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTblEstudiantes() {
		if (tblEstudiantes == null) {
			tblEstudiantes = new JTable();
			if(modelAdmin){
				tblEstudiantes.setModel(mAdministrador);
			}
			else{
				tblEstudiantes.setModel(mEstudiante);
			}
		}
		return tblEstudiantes;
	}

	/**
	 * This method initializes txtNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(new Rectangle(11, 69, 134, 26));
		}
		return txtNombre;
	}

	/**
	 * This method initializes txtApellido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtApellido() {
		if (txtApellido == null) {
			txtApellido = new JTextField();
			txtApellido.setBounds(new Rectangle(12, 124, 132, 27));
		}
		return txtApellido;
	}

	/**
	 * This method initializes btnGuardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton();
			btnGuardar.setBounds(new Rectangle(12, 260, 126, 31));
			btnGuardar.setText("Guardar");
			btnGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mAdministrador.Agregar(new Persona(txtNombre.getText(), txtApellido.getText(),cboxCursos.getSelectedItem().toString()));
					txtNombre.setText("");
					txtApellido.setText("");
				}
			});
		}
		return btnGuardar;
	}

	/**
	 * This method initializes btnEliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton();
			btnEliminar.setBounds(new Rectangle(12, 317, 125, 31));
			btnEliminar.setText("Eliminar");
			btnEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mAdministrador.Eliminar(tblEstudiantes.getSelectedRow());
				}
			});
		}
		return btnEliminar;
	}

	/**
	 * This method initializes lblLogoff	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLblLogoff() {
		if (lblLogoff == null) {
			lblLogoff = new JButton();
			lblLogoff.setBounds(new Rectangle(569, 8, 122, 26));
			lblLogoff.setText("Cerrar Sesion");
			lblLogoff.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Gestor.this.dispose();
					Sistema.main(null);
				}
			});
		}
		return lblLogoff;
	}

	/**
	 * This method initializes btnImprimir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnImprimir() {
		if (btnImprimir == null) {
			btnImprimir = new JButton();
			btnImprimir.setBounds(new Rectangle(576, 355, 116, 25));
			btnImprimir.setText("Imprimir PDF");
			btnImprimir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ExportPDF.ImprimirPDF(tblEstudiantes);
				}
			});
		}
		return btnImprimir;
	}

	/**
	 * This method initializes cboxCursos	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCboxCursos() {
		if (cboxCursos == null) {
			cboxCursos = new JComboBox();
			cboxCursos.setBounds(new Rectangle(12, 185, 132, 27));
			cboxCursos.setModel(mListaCurso);
		}
		return cboxCursos;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
