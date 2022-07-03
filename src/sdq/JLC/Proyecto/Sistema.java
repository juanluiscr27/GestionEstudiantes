package sdq.JLC.Proyecto;

import javax.swing.JFrame;
import sdq.JLC.Proyecto.Visual.Login;

public class Sistema {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login ventanaLogin = new Login();
		ventanaLogin.setVisible(true);
		ventanaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*Conexion conect = new Conexion();
		Conexion.ObtenerCursos();*/
	}

}
