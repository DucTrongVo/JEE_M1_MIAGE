/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Compte;
import fr.miage.toulouse.jee_test.miagebankshared.Position;
import java.time.Instant;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author trongvo
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> implements CompteFacadeLocal {

    @EJB
    CompteFacadeLocal compteFacadeLocal;
    
    @PersistenceContext(unitName = "fr.miage.toulouse.JEE_Test_MIAGEBank-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }
    
    @Override
    public void crediter(Compte compte, double montant){
        compte.setSolde(compte.getSolde()+montant);
        Position position = new Position(compte.getSolde(),Date.from(Instant.now()));
        compte.setPosition(position);
        System.out.println("Créditer : "+montant+" du compte "+compte.toString());
    };
    
    @Override
    public void debiter(Compte compte, double montant){
        compte.setSolde(compte.getSolde()-montant);
        Position position = new Position(compte.getSolde(),Date.from(Instant.now()));
        compte.setPosition(position);
        System.out.println("Débiter : "+montant+" du compte "+compte.toString());
    };
    
    @Override
    public void fermer(Compte compte){
        compteFacadeLocal.remove(compte);
        System.out.println("Compte fermé!");
    };
}
