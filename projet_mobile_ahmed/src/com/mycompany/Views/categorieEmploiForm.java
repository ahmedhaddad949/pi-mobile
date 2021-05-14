/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Views;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.Models.categorie_emploi;
import com.mycompany.Models.offre_emploi;
import com.mycompany.MyApplication;
import com.mycompany.Services.ServiceCategorie_emploi;
import com.mycompany.Services.ServiceOffre_emploi;

/**
 *
 * @author hadda
 */
public class categorieEmploiForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    public categorieEmploiForm(Form previous)
    {
           super("Categories",BoxLayout.y());
             for (categorie_emploi c : new ServiceCategorie_emploi().findAll()) {

            this.add(addItem_Categorie(c));

        }
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
    }
    
     public Container addItem_Categorie(categorie_emploi c) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label nom = new Label(c.getNomEmploi());
             Label description = new Label(c.getDescriptionEmploi());
        Button btn = new Button("Detail");
      
        

        cn2.add(nom).add(description).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        btn.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
           
         
        
            Label nom_lab = new Label("Categorie Nom :");
            Label nom_etxt = new Label(c.getNomEmploi());
            Label des_lab = new Label("Description :");
            
            Label des_etxt = new Label(c.getDescriptionEmploi());
            
          


           
                
            f2.add(nom_lab).add(nom_etxt).add(des_lab).add(des_etxt);
            
          
            
                 for (offre_emploi q : new ServiceOffre_emploi().findAll(c.getId())) {

            f2.add(addItem_offre(q));

        }
                  f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
 f2.show();
        });
      
         /*for (int i = 0; i < c.getQuestionnaires().size(); i++) {
              Container cnq = new Container(new BorderLayout());
        Container cnq2 = new Container(BoxLayout.y());
       
   
      
        

        cnq2.add(c.getQuestionnaires().get(i));
        cnq.add(BorderLayout.WEST, cn2);
      
       
         }*/
        //(String) c.getQuestionnaires().get("username");

        cn1.setLeadComponent(btn);
        return cn1;

    }
         public Container addItem_offre(offre_emploi q) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label nom = new Label("Titre offre :"+q.getTitre_offre_emploi());
        Label Description = new Label("Description offre :"+q.getTitre_offre_emploi()); 
        Label nombre = new Label("Nombre offre :"+String.valueOf(q.getNbr_offres()));
        Button btn_postuller = new Button("postuller");
        TextField motivation = new TextField("", "Motivation", 20, TextArea.TEXT_CURSOR);
  btn_postuller.addActionListener(lqo->{
    
                            if (motivation.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de motivation ", "OK", null);

                            } 
                            else
                            {
                                  ServiceOffre_emploi ser = new ServiceOffre_emploi();
            // 3 ray id user
            ser.postuller_emploi(q.getId(), 3,motivation.getText());
                   Dialog.show("postuller", "postuller ", "OK", null);
                     new MyApplication().start();
                            }
            
          
            });
        cn2.add(nom).add(Description).add(nombre).add("Motivation : ").add(motivation).add(btn_postuller);
        cn1.add(BorderLayout.WEST, cn2);
        return cn1;
         }
}
