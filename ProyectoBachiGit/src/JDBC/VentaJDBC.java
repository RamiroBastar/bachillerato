/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import POJO.VentaPOJO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Profesor Bastar
 */
public class VentaJDBC {

    private static final String TABLE = "Venta";

    private static final String SQL_INSERT = "Insert into " + TABLE
            + " (total_articulos,pago_total,Empleado_id)"
            + " values (?)";

    private static final String SQL_DELETE = "Delete from " + TABLE
            + " where idVenta=?";

    
    private static final String SQL_QUERY = "Select * from " + TABLE;

    public static int insertar(VentaPOJO p) {

        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();//Obtengo la conexion a la bd
            ps = con.prepareStatement(SQL_INSERT);
            //Llenamos el preparedStatement
            ps.setDouble(1, p.getTotal_articulos());
            ps.setDouble(2, p.getPago_total());
            ps.setDouble(3, p.getEmpleado_id());
            //Metodo para ejecutar cnsulta
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error al insertar = " + e);
        } finally {
            Conexion.close(con);
            Conexion.close(rs);
            Conexion.close(ps);
        }
        return id;
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

    
}
