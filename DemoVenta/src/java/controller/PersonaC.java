package controller;

import dao.PersonaD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.PersonaM;

@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    PersonaM persona = new PersonaM();
    private List<PersonaM> lstPersona;
    private PersonaM selectedPersona;

    @PostConstruct
    public void iniciar(){
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void limpiar() {
        persona = new PersonaM();
    }

    public void guardar() throws Exception {
        PersonaD dao;
        try {
            dao = new PersonaD();
            dao.guardarPersona(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"GUARDADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO AGREGAR",null));
            throw e;
        }
    }

    public void modificar() {
        PersonaD dao;
        try {
            dao = new PersonaD();
            dao.modificarPersona(selectedPersona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"MODIFICADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO MODIFICAR",null));
        }
    }
    
    public void eliminar() {
        PersonaD dao;
        try {
            dao = new PersonaD();
            dao.eliminarPersona(selectedPersona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"ELIMINADO CON ÉXITO",null ));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"NO SE PUDO ELIMINAR",null));
        }
    }

    public void listar() throws Exception {
        PersonaD dao;
        try {
            dao = new PersonaD();
            lstPersona = dao.listarPersona();
        } catch (Exception e) {
            throw e;
        }
    }

    public PersonaM getPersona() {
        return persona;
    }

    public void setPersona(PersonaM persona) {
        this.persona = persona;
    }

    public List<PersonaM> getLstPersona() {
        return lstPersona;
    }

    public void setLstPersona(List<PersonaM> lstPersona) {
        this.lstPersona = lstPersona;
    }

    public PersonaM getSelectedPersona() {
        return selectedPersona;
    }

    public void setSelectedPersona(PersonaM selectedPersona) {
        this.selectedPersona = selectedPersona;
    }
}
