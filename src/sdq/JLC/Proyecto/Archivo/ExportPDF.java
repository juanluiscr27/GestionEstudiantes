package sdq.JLC.Proyecto.Archivo;

import java.awt.Graphics2D;
import java.io.FileOutputStream;
import javax.swing.JTable;

import sdq.JLC.Proyecto.Visual.Gestor;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportPDF {

	public static void ImprimirPDF(JTable tablaEstudiante){
		
		Document documento = new Document();
		
		try {
			FileOutputStream flujoDeSalida = new FileOutputStream("Tabla_"+Gestor.userNombre+".pdf");
			PdfWriter escritorPDF = PdfWriter.getInstance(documento, flujoDeSalida);
			documento.open();
			PdfContentByte contenidoPDF = escritorPDF.getDirectContent();
			PdfTemplate plantillaPDF = contenidoPDF.createTemplate(tablaEstudiante.getWidth(), tablaEstudiante.getHeight());
			Graphics2D tablaDeGraficos = new PdfGraphics2D(contenidoPDF, 612, 792);
			tablaEstudiante.print(tablaDeGraficos);
			tablaDeGraficos.dispose();
			contenidoPDF.addTemplate(plantillaPDF, 30, 300);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
			documento.close();
	}
}

