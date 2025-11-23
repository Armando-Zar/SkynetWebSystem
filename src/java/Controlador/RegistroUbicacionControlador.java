package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// El @WebServlet define la URL que usará el cliente para invocar el Servlet
@WebServlet(name = "registroUbicacionControlador", urlPatterns = {"/RegistroUbicacionControlador"})
public class RegistroUbicacionControlador extends HttpServlet {

    // Configuración de la Base de Datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/skynet"; // Cambia el nombre de tu base de datos
    private static final String DB_USER = "skynetdb"; // ¡Cámbialo!
    private static final String DB_PASS = "skynet"; // ¡Cámbialo!
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver"; // O "com.mysql.jdbc.Driver" si usas el conector de MySQL

    /**
     * Maneja las peticiones HTTP POST (usadas para guardar datos).
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain"); // Tipo de respuesta simple para el alert JS
        PrintWriter out = response.getWriter();

        // 1. Recibir los parámetros de cliente, latitud y longitud
        String clienteIdStr = request.getParameter("id_cliente");
        String latitudStr = request.getParameter("lat");
        String longitudStr = request.getParameter("lng");
        
         if (clienteIdStr == null || clienteIdStr.trim().isEmpty() || latitudStr == null || longitudStr == null || latitudStr.trim().isEmpty() || longitudStr.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Error: Faltan datos (ID de Cliente, Latitud o Longitud).");
            return;
        }
        
        // Conversión a tipo Double
        double lat = 0.0;
        double lng = 0.0;
        int clienteId = 0;
        try {
            clienteId = Integer.parseInt(clienteIdStr);
            lat = Double.parseDouble(latitudStr);
            lng = Double.parseDouble(longitudStr);
        } catch (NumberFormatException e) {
            out.println("Error: El ID del Cliente, Latitud o Longitud no son válidos.");
            return;
        }

        // 2. Conexión a la base de datos y guardado
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Cargar el driver JDBC
            Class.forName(DB_DRIVER);

            // Establecer la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Preparar la consulta SQL (Sentencia PreparedStatement para evitar SQL Injection)
            String sql = "UPDATE cliente SET lat = ?, lng = ? WHERE id_cliente = ?"; 
            stmt = conn.prepareStatement(sql);
            
            // Asignar los valores a los placeholders (?)
            stmt.setDouble(1, lat); // lat
            stmt.setDouble(2, lng); // lng
            stmt.setInt(3, clienteId); // id_cliente

            // Ejecutar la consulta
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                out.println("Ubicación del Cliente ID " + clienteId + " actualizado con éxito.");
            } else {
                out.println("Error: No se encontró al Cliente ID " + clienteId + " o no se pudo actualizar la ubicación.");
            }

        } catch (ClassNotFoundException e) {
            out.println("Error del Servidor: Driver de base de datos no encontrado. Asegúrate de añadir el JAR.");
            // e.printStackTrace(); // Descomentar para ver el error completo en el log de GlassFish
        } catch (SQLException e) {
            // Este es el mensaje genérico que se envía al cliente (JavaScript alert)
            out.println("Error de Base de Datos: Fallo en la conexión o en la consulta SQL.");
            
            // 🚨 MUY IMPORTANTE: Descomentar la traza del error en el log de GlassFish
            e.printStackTrace(); 
            
            // Opcionalmente, puedes mostrar un mensaje más específico al cliente:
             out.println("Error de BD: " + e.getMessage());
        } finally {
            // 3. Cerrar los recursos
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { /* Ignorar */ }
            try { if (conn != null) conn.close(); } catch (SQLException e) { /* Ignorar */ }
        }
    }
}