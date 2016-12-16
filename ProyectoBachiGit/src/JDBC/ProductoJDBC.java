/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

/**
 *
 * @author Profesor Bastar
 */
public class ProductoJDBC {
    
    private static final String TABLE = "Producto";

    private static final String SQL_INSERT = "Insert into " + TABLE
            + " (nombre,existencia,costo)"
            + " values (?,?,?)";

    private static final String SQL_DELETE = "Delete from " + TABLE
            + " where idProducto=?";

    private static final String SQL_QUERY = "Select * from " + TABLE;

    
    
}
