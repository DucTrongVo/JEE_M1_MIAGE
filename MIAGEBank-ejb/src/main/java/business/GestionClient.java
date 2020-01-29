/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;


import entities.Client;
import entities.Compte;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import repositories.ClientFacadeLocal;

/**
 *
 * @author trongvo
 */
@Stateless
@LocalBean
public class GestionClient {
    
    @EJB
    private ClientFacadeLocal clientFacadeLocal;
    
    public void creer(String nom, String prenom){
        Client client = new Client(nom,prenom);
        clientFacadeLocal.create(client);
    }
    
    public List<Compte> listerComptes(Long idClient){
        Client client = clientFacadeLocal.find(idClient);
        return client.getComtpes();
    }
}
