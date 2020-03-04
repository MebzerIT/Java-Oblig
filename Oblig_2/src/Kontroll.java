
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Kontroll {
	private String databasenavn = "jdbc:mysql://localhost:3306/hobbyhuset?autoReconnect=true&useSSL=false";
    private String databasedriver = "com.mysql.jdbc.Driver";
    private Connection forbindelse;
    private ResultSet resultat;
    private Statement utsagn;
    
    public void lagForbindelse() throws Exception {        
    	try {
                forbindelse = DriverManager.getConnection(databasenavn,"root","Mebrahtu");
    		} catch(Exception e) {                    
                throw new Exception("Kan ikke oppnå kontakt med databasen");                        
    		}            
        }
    
    public void lukk() throws Exception {
        try {
            if(forbindelse != null) {
                forbindelse.close();
                //resultat.close();
                //utsagn.close();
            }
        }catch(Exception e) {
            throw new Exception("Kan ikke lukke databaseforbindelse");
        }        
    }
    
    public ResultSet leseVarer() throws Exception {
    	resultat = null;
    	try {
                String sqlSetning = "select vare.*, Navn from hobbyhuset.vare INNER JOIN hobbyhuset.kategori ON vare.KatNr = kategori.KatNr ;";
                utsagn = forbindelse.createStatement();
                resultat = utsagn.executeQuery(sqlSetning);
    	} catch(Exception e){
                throw new Exception("kan ikke utføre spørringen");
        } //catch                 
                return resultat;        
    } //metode
    
    public ResultSet leseOrdre() throws Exception {
    	resultat = null;
    	try {
                String sqlSetning = "select * from hobbyhuset.ordre;";
                utsagn = forbindelse.createStatement();
                resultat = utsagn.executeQuery(sqlSetning);
    	} catch(Exception e){
                throw new Exception("kan ikke utføre spørringen");
            } //catch                 
                return resultat;        
        } //metode
    
    //**-------- slett kunde her!
    public ResultSet finnKunde(int kundenr) throws Exception{
    	resultat = null;
    	try {
                String sqlSetning = "select KNr,Etternavn,Adresse from hobbyhuset.kunde WHERE kunde.KNr = " + kundenr +";";
                utsagn = forbindelse.createStatement();
                resultat = utsagn.executeQuery(sqlSetning);            
    	} catch(Exception e){
                throw new Exception("kan ikke utføre spørringen");
            } //catch                 
                return resultat;   
    	}
    
    public void slettKunde(int kundenr) throws Exception{
               String sqlSetning = "DELETE hobbyhuset.ordrelinje FROM hobbyhuset.ordre,hobbyhuset.ordrelinje WHERE ordre.ordreNr = ordrelinje.ordreNr AND ordre.KNr = " + kundenr +";";
               String sqlSetning1 = "DELETE FROM hobbyhuset.ordre WHERE KNr = " + kundenr +";";
               String sqlSetning2 = "DELETE FROM hobbyhuset.kunde WHERE KNr = " + kundenr +";";
               System.out.println(sqlSetning2);
          try {
                Statement utsagn = forbindelse.createStatement();
                utsagn.executeUpdate(sqlSetning);      
                utsagn.executeUpdate(sqlSetning1);  
                utsagn.executeUpdate(sqlSetning2); 
                JOptionPane.showMessageDialog(null,"Kunde er slettet");
    	  }catch(Exception ex){throw new Exception("kan ikke utføre spørringen");} //catch                       
        } //metode
    
    public void lagreVarer(int varenr,String betegnelse, String pris, String katnr, int antall, String hylle) throws Exception {
    	String sqlsetning = "INSERT INTO hobbyhuset.vare VALUES(" + varenr + ",'" + betegnelse + "','" + pris + "','" + katnr + "','" + antall + "','" + hylle + "');";
    	System.out.println(sqlsetning);
    	try {
    		Statement utsagn = forbindelse.createStatement();
    		utsagn.executeUpdate(sqlsetning);    		
    	}catch(Exception ex) {throw new Exception("Kan ikke oppdatere varer");}
    }
	
    public static Kontroll getInstance() {
		    return KontrollHolder.INSTANCE;
		   }   
		  private static class KontrollHolder {
         
		  private static final Kontroll INSTANCE = new Kontroll();
		    }
	

}

	


