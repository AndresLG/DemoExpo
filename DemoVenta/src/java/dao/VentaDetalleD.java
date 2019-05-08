package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.VentaDetalleM;

public class VentaDetalleD extends Dao{
    public void guardarVentaDetalle(VentaDetalleM ventaDetalle) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO DETALLE_VENTA (CANVEN,SBTVEN,CODPRO) VALUES(?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, ventaDetalle.getCANVEN());
            ps.setInt(2, ventaDetalle.getSBTVEN());
            ps.setString(3, ventaDetalle.getCODPRO());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificarVentaDetalle(VentaDetalleM ventaDetalle) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE DETALLE_VENTA SET CANVEN=?,SBTVEN=?,CODPRO=? WHERE CODDETVEN=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, ventaDetalle.getCANVEN());
            ps.setInt(2, ventaDetalle.getSBTVEN());
            ps.setString(3, ventaDetalle.getCODPRO());
            ps.setInt(4, ventaDetalle.getCODDETVEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminarVentaDetalle(VentaDetalleM ventaDetalle) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM DETALLE_VENTA WHERE CODDETVEN=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, ventaDetalle.getCODDETVEN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
 
    public List<VentaDetalleM> listarVentaDetalle() throws Exception {
        List<VentaDetalleM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM vw_VentaDetalle ORDER BY CODDETVEN";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                VentaDetalleM ventaDetalle = new VentaDetalleM();
                ventaDetalle.setCODDETVEN(rs.getInt("CODDETVEN"));
                ventaDetalle.setCANVEN(rs.getInt("CANVEN"));
                ventaDetalle.setSBTVEN(rs.getInt("SBTVEN"));
                ventaDetalle.setCODPRO(rs.getString("CODPRO"));
                lista.add(ventaDetalle);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
