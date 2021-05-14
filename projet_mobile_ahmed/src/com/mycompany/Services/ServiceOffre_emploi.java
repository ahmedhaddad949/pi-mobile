/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.mycompany.Models.offre_emploi;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author hadda
 */
public class ServiceOffre_emploi {
      public ArrayList<offre_emploi> offre_emplois;
    public static ServiceOffre_emploi instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServiceOffre_emploi() {
        req = new ConnectionRequest();
    }

    public static ServiceOffre_emploi getInstance() {
        if (instance == null) {
            instance = new ServiceOffre_emploi();
        }
        return instance;
    }
            public ArrayList<offre_emploi> parseoffre_emploi(String jsonText) {
        try {
            offre_emplois = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                offre_emploi emploi = new offre_emploi();

                float id = Float.parseFloat(obj.get("id").toString());
                emploi.setId((int) id);

                float nbrOffres = Float.parseFloat(obj.get("nbr_offres").toString());
                emploi.setNbr_offres((int) nbrOffres);

                emploi.setTitre_offre_emploi(obj.get("titre_offre_emploi").toString());
                emploi.setDescription_cat_em(obj.get("description_cat_em").toString());
           
        
             
              
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                offre_emplois.add(emploi);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return offre_emplois;
    }

    public ArrayList<offre_emploi> findAll(int id) {
        String url = Statics.BASE_URL + "frontCategorieDetailEmploi_Mobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offre_emplois = parseoffre_emploi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offre_emplois;
    }  
    
       public void postuller_emploi(int id_o , int id_u,String txt ) {
        String url = Statics.BASE_URL + "PostulerEmploi_Mobile/"+id_u+"/"+id_o+"/"+txt;
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     
    }  
    
    
    
}
