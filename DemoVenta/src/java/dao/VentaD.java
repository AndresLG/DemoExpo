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
            ps.setInt(2, venta.getTOTVEN());
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
            ps.setInt(2, venta.getTOTVEN());
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
            String sql = "SELECT * FROM vw_Venta ORDER BY CODVEN";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                VentaM venta = new VentaM();
                venta.setCODVEN(rs.getInt("CODVEN"));
                venta.setFECVEN(rs.getString("FECVEN"));
                venta.setTOTVEN(rs.getInt("TOTVEN"));
                venta.setCODPER(rs.getString("CODPER"));
                venta.setCODDETVEN(rs.getString("CODDETVEN"));
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
