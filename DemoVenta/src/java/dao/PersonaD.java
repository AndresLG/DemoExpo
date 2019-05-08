package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PersonaM;

public class PersonaD extends Dao{
    
    public void guardarPersona(PersonaM persona) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO PERSONA (NOMPER,APEPER,DNIPER,FECNACPER,DIRPER,TELPER,ESTPER) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getDNIPER());
            ps.setString(4, persona.getFECNACPER());
            ps.setString(5, persona.getDIRPER());
            ps.setString(6, persona.getTELPER());
            ps.setString(7, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificarPersona(PersonaM persona) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE PERSONA SET NOMPER=?, APEPER=?, DIRPER=?, TELPER=? WHERE CODPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getDIRPER());
            ps.setString(4, persona.getTELPER());
            ps.setInt(5, persona.getCODPER());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminarPersona(PersonaM persona) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE PERSONA SET ESTPER= ? WHERE CODPER= ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, persona.getCODPER());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
 
    public List<PersonaM> listarPersona() throws Exception {
        List<PersonaM> lista;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM vw_Persona ORDER BY CODPER";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                PersonaM persona = new PersonaM();
                persona.setCODPER(rs.getInt("CODPER"));
                persona.setNOMPER(rs.getString("NOMPER"));
                persona.setAPEPER(rs.getString("APEPER"));
                persona.setDNIPER(rs.getString("DNIPER"));
                persona.setFECNACPER(rs.getString("FECNACPER"));
                persona.setDIRPER(rs.getString("DIRPER"));
                persona.setTELPER(rs.getString("TELPER"));
                persona.setESTPER(rs.getString("ESTPER"));
                lista.add(persona);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
