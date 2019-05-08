package controller;

import dao.VentaDetalleD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.VentaDetalleM;

@Named(value = "ventaDetalleDetalleC")
@SessionScoped
public class VentaDetalleC implements Serializable {

    VentaDetalleM ventaDetalle = new VentaDetalleM();
    private List<VentaDetalleM> lstVentaDetalle;
    private VentaDetalleM selectedVentaDetalle;

    @PostConstruct
    public void iniciar(){
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void limpiar() {
        ventaDetalle = new VentaDetalleM();
    }

    public void guardar() throws Exception {
        VentaDetalleD dao;
        try {
            dao = new VentaDetalleD();
            dao.guardarVentaDetalle(ventaDetalle);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GUARDADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO AGREGAR",null));
            throw e;
        }
    }

    public void modificar() {
        VentaDetalleD dao;
        try {
            dao = new VentaDetalleD();
            dao.modificarVentaDetalle(selectedVentaDetalle);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"MODIFICADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO MODIFICAR",null));
        }
    }
    
    public void eliminar() {
        VentaDetalleD dao;
        try {
            dao = new VentaDetalleD();
            dao.eliminarVentaDetalle(selectedVentaDetalle);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"ELIMINADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO ELIMINAR",null));
        }
    }

    public void listar() throws Exception {
        VentaDetalleD dao;
        try {
            dao = new VentaDetalleD();
            lstVentaDetalle = dao.listarVentaDetalle();
        } catch (Exception e) {
            throw e;
        }
    }

    public VentaDetalleM getVentaDetalle() {
        return ventaDetalle;
    }

    public void setVentaDetalle(VentaDetalleM ventaDetalle) {
        this.ventaDetalle = ventaDetalle;
    }

    public List<VentaDetalleM> getLstVentaDetalle() {
        return lstVentaDetalle;
    }

    public void setLstVentaDetalle(List<VentaDetalleM> lstVentaDetalle) {
        this.lstVentaDetalle = lstVentaDetalle;
    }

    public VentaDetalleM getSelectedVentaDetalle() {
        return selectedVentaDetalle;
    }

    public void setSelectedVentaDetalle(VentaDetalleM selectedVentaDetalle) {
        this.selectedVentaDetalle = selectedVentaDetalle;
    }
}
