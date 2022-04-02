package com.desafiolatam.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.desafiolatam.entidades.InscripcionDTO;
import com.desafiolatam.entidades.InscripcionDTO2;
import com.desafiolatam.facade.Facade;
@WebServlet("/posInscripcion")
public class PosInscripcion extends HttpServlet {

	private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
	   
      // obtengo los datos del formulario
      String nombre = request.getParameter("nombre");
      String celular = request.getParameter("celular");
      int idCurso = Integer.parseInt(request.getParameter("idCurso"));
      int idFormaDePago = Integer.parseInt(request.getParameter("idFormaPago"));
       // instancio el DTO y le asigno los datos
      InscripcionDTO dto = new InscripcionDTO();
      dto.setNombre(nombre);
      dto.setCelular(celular);
      dto.setIdCurso(idCurso);
      dto.setIdFormaDePago(idFormaDePago);
      // invoco al facade para procesar la inscripcion
      Facade facade = new Facade();
      int idInsc = 0;
      InscripcionDTO2 dto2 = new InscripcionDTO2();
		try {
			idInsc = facade.registrarInscripcion(dto);
			dto2=facade.obtieneUltimoInscrito();
					      
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
         //el resultado lo adjunto como atributo en el request
        request.setAttribute("idInsc", idInsc);
  	    request.setAttribute("ultimoInscrito", dto2);
      //request.setAttribute("dto2", dto2);
      
       // redirecciono el control hacia la siguiente vista,
       // es decir: hacia su servlet de pre-confirmacion
       request.getRequestDispatcher("/preConfirmacion").forward(request, response);
       //request.getRequestDispatcher("confirmacion.jsp").forward(request, response);
   }
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Metodo doPost");
		doGet(request, response);
	}
}
