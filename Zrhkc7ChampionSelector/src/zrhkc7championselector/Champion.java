/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zrhkc7championselector;

/**
 *
 * @author Zach-MacBook
 */
public class Champion implements java.io.Serializable {
    
    private String supportchampionName;
    private String marksmanchampionName;
    private String topchampionName;
    private String midchampionName;
    private String junglechampionName;
    
    public Champion() {
        
    }
    
    public void setSupportChampionName(String supportchampionName) {
        this.supportchampionName = supportchampionName;
    }
    
    public String getSupportChampionName() {
        return supportchampionName;
    }
    
     public void setMarksmanChampionName(String marksmanchampionName) {
        this.marksmanchampionName = marksmanchampionName;
    }
    
    public String getMarksmanChampionName() {
        return marksmanchampionName;
    }
    
     public void setMidChampionName(String midchampionName) {
        this.midchampionName = midchampionName;
    }
    
    public String getMidChampionName() {
        return midchampionName;
    }
    
     public void setTopChampionName(String topchampionName) {
        this.topchampionName = topchampionName;
    }
    
    public String getTopChampionName() {
        return topchampionName;
    }
    
     public void setJungleChampionName(String junglechampionName) {
        this.junglechampionName = junglechampionName;
    }
    
    public String getJungleChampionName() {
        return junglechampionName;
    }  
}

