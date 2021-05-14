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
public class categorie_emploi {
    private int id ;
    private String nomEmploi;
    private String descriptionEmploi;
    private int nbrOffres;

    public categorie_emploi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEmploi() {
        return nomEmploi;
    }

    public void setNomEmploi(String nomEmploi) {
        this.nomEmploi = nomEmploi;
    }

    public String getDescriptionEmploi() {
        return descriptionEmploi;
    }

    public void setDescriptionEmploi(String descriptionEmploi) {
        this.descriptionEmploi = descriptionEmploi;
    }

    public int getNbrOffres() {
        return nbrOffres;
    }

    public void setNbrOffres(int nbrOffres) {
        this.nbrOffres = nbrOffres;
    }

}
