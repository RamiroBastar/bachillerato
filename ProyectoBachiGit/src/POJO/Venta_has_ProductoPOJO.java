package POJO;

/**
 *
 * @author Profesor Bastar
 */
public class Venta_has_ProductoPOJO {

    private int venta_idventa;
    private int producto_idproducto;
    private double cantidad;
    private double importe;

    public int getVenta_idventa() {
        return venta_idventa;
    }

    public void setVenta_idventa(int venta_idventa) {
        this.venta_idventa = venta_idventa;
    }

    public int getProducto_idproducto() {
        return producto_idproducto;
    }

    public void setProducto_idproducto(int producto_idproducto) {
        this.producto_idproducto = producto_idproducto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

}
