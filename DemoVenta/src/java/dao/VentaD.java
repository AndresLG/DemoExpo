package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.VentaM;

public class VentaD extends Dao{
    public void guardarVenta(VentaM venta) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO VENTA (FECVEN,TOTVEN,CODPER,CODDETVEN) VALUES(?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, venta.getFECVEN());
            ps.setString(2, venta.getTOTVEN());
            ps.setString(3, venta.getCODPER());
            ps.setString(4, venta.getCODDETVEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificarVenta(VentaM venta) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE VENTA SET FECVEN=?,TOTVEN=?,CODPER=?,CODDETVEN=? WHERE CODVEN=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, venta.getFECVEN());
            ps.setString(2, venta.getTOTVEN());
            ps.setString(3, venta.getCODPER());
            ps.setString(4, venta.getCODDETVEN());
            ps.setInt(5, venta.getCODVEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminarVenta(VentaM venta) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM VENTA WHERE CODVEN=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, venta.getCODVEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
 
    public List<VentaM> listarVenta() throws Exception {
        List<VentaM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM vw_Venta_Final ORDER BY CODVEN";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                VentaM venta = new VentaM();
                venta.setCODVEN(rs.getInt("CODVEN"));
                venta.setFECVEN(rs.getString("FECVEN"));
                venta.setTOTVEN(rs.getString("TOTVEN"));
                venta.setCODPER(rs.getString("CODPER"));
                venta.setNOMBREPER(rs.getString("Persona"));
                venta.setCODDETVEN(rs.getString("CODDETVEN"));
                venta.setDESCPRO(rs.getString("Producto"));
                venta.setCOSTPRO(rs.getString("Costo"));
                venta.setCANTPRO(rs.getString("Cantidad"));
                venta.setSUBTDETVEN(rs.getString("Subtotal"));
                lista.add(venta);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
