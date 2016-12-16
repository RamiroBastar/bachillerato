/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import POJO.EmpleadoPOJO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Profesor Bastar
 */
public class EmpleadoJDBC {

    private static final String TABLE = "Empleado";

    private static final String SQL_INSERT = "Insert into " + TABLE
            + " (nombre)"
            + " values (?)";

    private static final String SQL_DELETE = "Delete from " + TABLE
            + " where id=?";

    private static final String SQL_QUERY = "Select * from " + TABLE;

    public static boolean insertar(EmpleadoPOJO pojo) {
        Connection con = null;
        PreparedStatement ps = null;
        int x;
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, pojo.getNombre());
            x = ps.executeUpdate();//Aquí se ejecuta la inserción
            if (x == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al insertar " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(ps);
        }
    }

    public static boolean eliminar(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        int x;
        try {
            con = Conexion.getConnection();//Obtengo la conexion a la bd
            ps = con.prepareStatement(SQL_DELETE);//Preparo el insert
            ps.setString(1, id);
            x = ps.executeUpdate();
            if (x == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e);
            return false;
        } finally {
            Conexion.close(con);
            Conexion.close(ps);
        }
        return true;
    }

    public static DefaultTableModel cargarTabla() {
        Connection con = null;
        PreparedStatement ps = null;
        DefaultTableModel modelo = null;
        //Como me gustaría que salieran 
        String encabezados[] = {"ID", "Nombre"};
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(SQL_QUERY);
            modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(encabezados);//Aqui asigno los encabe
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object ob[] = new Object[2];//Número de campos
                //A partir de aquí son IDENTICOS a la BD
                ob[0] = rs.getObject("id");
                ob[1] = rs.getObject("nombre");
                modelo.addRow(ob);
            }
            Conexion.close(rs);
        } catch (Exception e) {
            System.out.println("Error al consultar " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(ps);
        }
        return modelo;
    }

    public static DefaultComboBoxModel cargarCombo() {
        Connection con = null;
        PreparedStatement ps = null;
        DefaultComboBoxModel modelo = null;
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(SQL_QUERY);
            modelo = new DefaultComboBoxModel();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EmpleadoPOJO pojo = new EmpleadoPOJO();
                pojo.setId(rs.getInt("id"));
                pojo.setNombre(rs.getString("nombre"));
                modelo.addElement(pojo);
            }

        } catch (Exception e) {
            System.out.println("Error al cargar combo " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(ps);
        }
        return modelo;
    }

}
