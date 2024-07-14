package sql;

import getset.variables;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class crudsql extends conexionsql {

    java.sql.Statement st;
    ResultSet rs;
    variables var = new variables();

    public void insertar(String nombre, String cedula, String fecha_de_nacimiento) {
        try {
            Connection conexion = conectar();
            st = conexion.createStatement();
            String sql = "insert into empleados(nombre,cedula,fecha_de_nacimiento) values('" + nombre + "','" + cedula + "','" + fecha_de_nacimiento + "');";
            st.execute(sql);
            st.close();
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro se guardo correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El registro no se guardo " + e, "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar(String idempleado) {
        try {
            Connection conexion = conectar();
            st = conexion.createStatement();
            String sql = "select * from empleados where idempleado='" + idempleado + "';";
            rs = st.executeQuery(sql);
            if (rs.next()) {
                var.setIdempleado(rs.getString("idempleado"));
                var.setNombre(rs.getString("nombre"));
                var.setCedula(rs.getString("cedula"));
                var.setFecha_de_nacimiento(rs.getString("fecha_de_nacimiento"));
            } else {
                var.setIdempleado("");
                var.setNombre("");
                var.setCedula("");
                var.setFecha_de_nacimiento("");
                JOptionPane.showMessageDialog(null, "no se encontro registro", "sin registro", JOptionPane.INFORMATION_MESSAGE);
            }
            st.close();
            conexion.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error en programa " + e, "Erro de sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizar(String nombre, String cedula, String fecha_de_nacimiento, String idempleado) {
        try {
            Connection conexion = conectar();
            st = conexion.createStatement();
            String sql = "update empleados set nombre='" + nombre + "',cedula='" + cedula + "',fecha_de_nacimiento='" + fecha_de_nacimiento + "' where idempleado='" + idempleado + "'; ";
            st.executeUpdate(sql);
            st.close();
            conexion.close();
            JOptionPane.showMessageDialog(null, "El registro se actualizo", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminar(String idempleado){
        try {
            Connection conexion=conectar();
            st=conexion.createStatement();
            String sql="delete from empleados where idempleado='"+idempleado+"'; ";
            st.executeUpdate(sql);
            st.close();
            conexion.close();
            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente","Eliminado",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro "+ e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
