package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProductoM;

public class ProductoD extends Dao{
    public void guardarProducto(ProductoM producto) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO PRODUCTO (DESPRO,COSPRO,STOPRO,ESTPRO) VALUES(?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getDESPRO());
            ps.setString(2, producto.getCOSPRO());
            ps.setString(3, producto.getSTOPRO());
            ps.setString(4, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificarProducto(ProductoM producto) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE PRODUCTO SET DESPRO=?, COSPRO=?, STOPRO=? WHERE CODPRO=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getDESPRO());
            ps.setString(2, producto.getCOSPRO());
            ps.setString(3, producto.getSTOPRO());
            ps.setInt(4, producto.getCODPRO());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminarProducto(ProductoM producto) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE PRODUCTO SET ESTPRO= ? WHERE CODPRO= ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, producto.getCODPRO());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
 
    public List<ProductoM> listarProducto() throws Exception {
        List<ProductoM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM vw_Producto ORDER BY CODPRO";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                ProductoM producto = new ProductoM();
                producto.setCODPRO(rs.getInt("CODPRO"));
                producto.setDESPRO(rs.getString("DESPRO"));
                producto.setCOSPRO(rs.getString("COSPRO"));
                producto.setSTOPRO(rs.getString("STOPRO"));
                producto.setESTPRO(rs.getString("ESTPRO"));
                lista.add(producto);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
