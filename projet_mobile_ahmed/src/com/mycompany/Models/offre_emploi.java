/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Models;

/**
 *
 * @author hadda
 */
public class offre_emploi {
       private int id ;
    private String titre_offre_emploi;
    private String description_cat_em;
    private int nbr_offres;

    public offre_emploi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_offre_emploi() {
        return titre_offre_emploi;
    }

    public void setTitre_offre_emploi(String titre_offre_emploi) {
        this.titre_offre_emploi = titre_offre_emploi;
    }

    public String getDescription_cat_em() {
        return description_cat_em;
    }

    public void setDescription_cat_em(String description_cat_em) {
        this.description_cat_em = description_cat_em;
    }

    public int getNbr_offres() {
        return nbr_offres;
    }

    public void setNbr_offres(int nbr_offres) {
        this.nbr_offres = nbr_offres;
    }

}
