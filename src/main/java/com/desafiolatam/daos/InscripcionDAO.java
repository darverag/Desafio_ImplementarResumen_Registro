package com.desafiolatam.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.entidades.CursoDTO;
import com.desafiolatam.entidades.InscripcionDTO;
import com.desafiolatam.entidades.InscripcionDTO2;
import com.desafiolatam.entidades.FormaDePagoDTO;

public class InscripcionDAO {
	public int insertarInscripcion(InscripcionDTO dto) throws SQLException, ClassNotFoundException {
		int max = 0;
		//Query para obtener una secuencia y asignar un id. La funcion MAX solo obtiene el valor de id_inscripcion
		//y le suma 1, con eso hacemos el incremento
		//String consultaProximoId = " SELECT MAX(id_inscripcion)+1 FROM DESAFIO.inscripcion ";
		//Query que insertara el valor
		String insertarInscripcion = " INSERT INTO javag6.inscripcion("
				                   + " nombre, telefono, id_curso, id_forma_pago )"
				                   + " VALUES (?,?,?,?) ";
		//conexion a la base de datos y ejecucion de la sentencia
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection  conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javag6","root","123456");
		try(
				//PreparedStatement stmt1 = conexion.prepareStatement(consultaProximoId);
				PreparedStatement stmt2 = conexion.prepareStatement(insertarInscripcion);
		   ){
	
			//ResultSet resultado = stmt1.executeQuery();
			//if(resultado.next()) {
			//  max = resultado.getInt(1);
			  //stmt2.setInt(1, max);
				stmt2.setString(1, dto.getNombre());
				stmt2.setString(2, dto.getCelular());
				stmt2.setInt(3, dto.getIdCurso());
				stmt2.setInt(4, dto.getIdFormaDePago());
				
				if(stmt2.executeUpdate() != 1) {
					throw new RuntimeException("A ocurrido un error inesperado");
				}
			//}	
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("A ocurrido un error inesperado" + ex);
		}
		return max;
	}
	
public List obtieneInscripciones() throws SQLException, ClassNotFoundException {
		
		//creamos la lista de objetos que devolveran los resultados
		List<InscripcionDTO> inscripciones = new ArrayList<InscripcionDTO>();
		
		//creamos la consulta a la base de datos
		String consultaSql = " SELECT * " 
				   		   + " FROM javag6.inscripcion ";
		
		//conexion a la base de datos y ejecucion de la sentencia
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection  conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javag6","root","123456");
		
		try(PreparedStatement stmt = conexion.prepareStatement(consultaSql)){
	
			ResultSet resultado = stmt.executeQuery();
			while(resultado.next()) {
				InscripcionDTO inscripcion = new InscripcionDTO();
				inscripcion.setIdCurso(resultado.getInt("id_curso"));
				inscripcion.setIdInsc(resultado.getInt("id_inscripcion"));
				inscripcion.setNombre(resultado.getString("nombre"));
				inscripcion.setCelular(resultado.getString("telefono"));
				inscripcion.setIdFormaDePago(resultado.getInt("id_forma_pago"));
				inscripciones.add(inscripcion);
			}	
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return inscripciones;
	}
	
	



public InscripcionDTO2 obtieneUltimoInscrito() throws SQLException, ClassNotFoundException {
	InscripcionDTO2 inscripcion = new InscripcionDTO2();
	//creamos la consulta a la base de datos
	String consultaSql = "SELECT  inscripcion.id_inscripcion,inscripcion.nombre,inscripcion.telefono, curso.descripcion AS nombre_curso, forma_pago.descripcion AS forma_de_pago "
			+ "FROM INSCRIPCION " + "INNER JOIN CURSO " + "ON INSCRIPCION.id_curso = CURSO.id_curso "
			+ "INNER JOIN forma_pago " + "ON inscripcion.id_forma_pago = forma_pago.id_forma_pago "
			+ "order by id_inscripcion desc limit 1";

			
			
	//conexion a la base de datos y ejecucion de la sentencia
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection  conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javag6","root","123456");
	
	try(PreparedStatement stmt = conexion.prepareStatement(consultaSql)){

		ResultSet resultado = stmt.executeQuery();
		while(resultado.next()) {
			
			inscripcion.setCurso(resultado.getString("nombre_curso"));
			inscripcion.setIdInsc(resultado.getInt("id_inscripcion"));
			inscripcion.setNombre(resultado.getString("nombre"));
			inscripcion.setCelular(resultado.getString("telefono"));
			inscripcion.setFormaDePago(resultado.getString("forma_de_pago"));
			
		}	
		
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	return inscripcion;
}
}
