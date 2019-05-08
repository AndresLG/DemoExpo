package controller;

import dao.ProductoD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ProductoM;

@Named(value = "productoC")
@SessionScoped
public class ProductoC implements Serializable {

    ProductoM producto = new ProductoM();
    private List<ProductoM> lstProducto;
    private ProductoM selectedProducto;

    @PostConstruct
    public void iniciar(){
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void limpiar() {
        producto = new ProductoM();
    }

    public void guardar() throws Exception {
        ProductoD dao;
        try {
            dao = new ProductoD();
            dao.guardarProducto(producto);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GUARDADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO AGREGAR",null));
            throw e;
        }
    }

    public void modificar() {
        ProductoD dao;
        try {
            dao = new ProductoD();
            dao.modificarProducto(selectedProducto);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"MODIFICADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO MODIFICAR",null));
        }
    }
    
    public void eliminar() {
        ProductoD dao;
        try {
            dao = new ProductoD();
            dao.eliminarProducto(selectedProducto);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"ELIMINADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO ELIMINAR",null));
        }
    }

    public void listar() throws Exception {
        ProductoD dao;
        try {
            dao = new ProductoD();
            lstProducto = dao.listarProducto();
        } catch (Exception e) {
            throw e;
        }
    }

    public ProductoM getProducto() {
        return producto;
    }

    public void setProducto(ProductoM producto) {
        this.producto = producto;
    }

    public List<ProductoM> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<ProductoM> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public ProductoM getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoM selectedProducto) {
        this.selectedProducto = selectedProducto;
    }
}
