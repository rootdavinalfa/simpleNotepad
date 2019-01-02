/*
 *
 *  * Copyright 2018,Davin Alfarizky Putra Basudewa
 *  * Davin OpenSource Project
 *  * 2018(c) DVNLABS.ml (DVNLABS)
 *  * ============================
 *  * mail : moshi_moshidavin@dvnlabs.ml / dbasudewa@gmail.com / rootdavin@yahoo.co.jp
 *  * ============================
 *  * Please remind this source only for educational purposes only.
 *  * Any breakthrough of rule on http://dvnlabs.ml/licenses.html may violation of crime
 *  * action on Indonesia or other respective countries.
 *
 */

package core.command;

public class copyTF_find {
    String finder;
    public void setNeed2find(String need2find){
        finder = need2find;
        System.out.println(finder);
    }
    public String getNeed2find(){
        return finder;
    }
}
