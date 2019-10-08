package com.studium.coursesservice;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScraperService {
    Document doc;
    private final String DIV_CLASS_DEP = "home_cats";
    public ScraperService() {
    
    }
    
    
    public void startService(String anno, String id) {
        String link = assemblerLink(anno,id);
        Document doc = getDocument(link);
        doc.getElementsByClass(DIV_CLASS_DEP)
                .get(0)
                .getElementsByTag("ul")
                .get(0)
                .getElementsByTag("a")
                .stream()
                .forEach(x -> System.out.println(x.text() + " " + x.attr("href").split("=")[1]));
                
        
        
        
                   
                
    }
    
    private Document getDocument(String link) {
        try {
            return Jsoup.connect(link).get();
        } catch (IOException e) {
            System.err.println("Errore nel caricare la pagina dei CdL");
        }
        return null; 
    }
    
    private String assemblerLink(String anno,String id) {
        return "http://studium.unict.it/dokeos/"
                .concat(anno)
                .concat("/index.php?category=")
                .concat(id);
    }
    
    public static void main(String args[]) {
        new ScraperService().startService("2019", "D251");
    }
}


class Prova {
    String uno,due;
    public Prova(String uno, String due) {
        this.uno = uno;
        this.due = due;
    }
    public String toString() {
        return uno + due +"\n";
    }
}