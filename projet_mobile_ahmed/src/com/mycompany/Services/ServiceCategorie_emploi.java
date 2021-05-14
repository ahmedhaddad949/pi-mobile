/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Models.categorie_emploi;
import com.mycompany.Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hadda
 */
public class ServiceCategorie_emploi {
        public ArrayList<categorie_emploi> categorie_emplois;
    public static ServiceCategorie_emploi instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public ServiceCategorie_emploi() {
        req = new ConnectionRequest();
    }

    public static ServiceCategorie_emploi getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie_emploi();
        }
        return instance;
    }
            public ArrayList<categorie_emploi> parsecategorie_emploi(String jsonText) {
        try {
            categorie_emplois = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                categorie_emploi categorie = new categorie_emploi();

                float id = Float.parseFloat(obj.get("id").toString());
                categorie.setId((int) id);

                float nbrOffres = Float.parseFloat(obj.get("nbrOffres").toString());
                categorie.setNbrOffres((int) nbrOffres);

                categorie.setNomEmploi(obj.get("nomEmploi").toString());
                categorie.setDescriptionEmploi(obj.get("descriptionEmploi").toString());
           
        
             
              
                
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                categorie_emplois.add(categorie);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return categorie_emplois;
    }

    public ArrayList<categorie_emploi> findAll() {
        String url = Statics.BASE_URL + "afficherCTE_Mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categorie_emplois = parsecategorie_emploi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categorie_emplois;
    }
    
}
