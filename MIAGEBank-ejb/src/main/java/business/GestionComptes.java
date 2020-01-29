/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Client;
import entities.Compte;
import fr.miage.toulouse.jee_test.miagebankshared.Position;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import repositories.ClientFacadeLocal;
import repositories.CompteFacade;
import repositories.CompteFacadeLocal;

/**
 *
 * @author trongvo
 */
@Stateless
@LocalBean
public class GestionComptes {

    @EJB
    private CompteFacadeLocal compteFacadeLocal;
    @EJB
    private CompteFacade compteFacade;
    @EJB
    private ClientFacadeLocal clientFacadeLocal;
    public void crediter(Long id, double montant){
        Compte compte = compteFacadeLocal.find(id);
        //compte.setSolde(compte.getSolde()+montant);
        compteFacade.crediter(compte, montant);
    }
    
    public void debiter(Long id, double montant){
        Compte compte = compteFacadeLocal.find(id);
        compteFacade.debiter(compte,montant);
    }
    
    public void ouvrir(double montant, double idClient){
        Client client = clientFacadeLocal.find(idClient);
        compteFacadeLocal.create(new Compte(montant,client));
        
    }
    
    public void virement(Long idSource, Long idDest, double montant){
        Compte compteSource = compteFacadeLocal.find(idSource);
        Compte compteDest = compteFacadeLocal.find(idDest);
        compteFacade.crediter(compteDest, montant);
        compteFacade.debiter(compteSource, montant);
    }
    
    public Position consulter(Long idCompte){
        Compte compte = compteFacadeLocal.find(idCompte);
        return compte.getPosition();
    }
}
