package controller;

import dao.VentaD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.VentaM;

@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    VentaM venta = new VentaM();
    private List<VentaM> lstVenta;
    private VentaM selectedVenta;

    @PostConstruct
    public void iniciar(){
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void limpiar() {
        venta = new VentaM();
    }

    public void guardar() throws Exception {
        VentaD dao;
        try {
            dao = new VentaD();
            dao.guardarVenta(venta);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GUARDADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO AGREGAR",null));
            throw e;
        }
    }

    public void modificar() {
        VentaD dao;
        try {
            dao = new VentaD();
            dao.modificarVenta(venta);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"MODIFICADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO MODIFICAR",null));
        }
    }
    
    public void eliminar() {
        VentaD dao;
        try {
            dao = new VentaD();
            dao.eliminarVenta(venta);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"ELIMINADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO ELIMINAR",null));
        }
    }

    public void listar() throws Exception {
        VentaD dao;
        try {
            dao = new VentaD();
            lstVenta = dao.listarVenta();
        } catch (Exception e) {
            throw e;
        }
    }

    public VentaM getVenta() {
        return venta;
    }

    public void setVenta(VentaM venta) {
        this.venta = venta;
    }

    public List<VentaM> getLstVenta() {
        return lstVenta;
    }

    public void setLstVenta(List<VentaM> lstVenta) {
        this.lstVenta = lstVenta;
    }

    public VentaM getSelectedVenta() {
        return selectedVenta;
    }

    public void setSelectedVenta(VentaM selectedVenta) {
        this.selectedVenta = selectedVenta;
    }
}
