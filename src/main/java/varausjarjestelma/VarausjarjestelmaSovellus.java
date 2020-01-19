package varausjarjestelma;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VarausjarjestelmaSovellus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VarausjarjestelmaSovellus.class);
    }

    @Autowired
    Tekstikayttoliittyma tekstikayttoliittyma;

    @Override
    public void run(String... args) throws Exception {
        Scanner lukija = new Scanner(System.in);
        tekstikayttoliittyma.kaynnista(lukija);
    }
    
    
    private static void alustaTietokanta() {



        try (Connection conn = DriverManager.getConnection("jdbc:h2:./hotelliketju", "sa", "")) {

            conn.prepareStatement("DROP TABLE Huone IF EXISTS;").executeUpdate();

            conn.prepareStatement("DROP TABLE Varaus IF EXISTS;").executeUpdate();

            conn.prepareStatement("DROP TABLE Asiakas IF EXISTS;").executeUpdate();

            conn.prepareStatement("DROP TABLE Lisavaruste IF EXISTS;").executeUpdate();

            conn.prepareStatement("DROP TABLE VarausLisavaruste IF EXISTS;").executeUpdate();

            conn.prepareStatement("DROP TABLE HuoneVaraus IF EXISTS;").executeUpdate();
            
            

           
    

            conn.prepareStatement("CREATE TABLE Huone (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, huonetyyppi VARCHAR(50) NOT NULL, huonenumero INTEGER NOT NULL, pvahinta INTEGER NOT NULL);").executeUpdate();

            

            conn.prepareStatement("CREATE TABLE Asiakas (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, nimi VARCHAR(200) NOT NULL, puhnumero VARCHAR(20) NOT NULL, email VARCHAR(200) NOT NULL);").executeUpdate();           

            

            conn.prepareStatement("CREATE TABLE Varaus (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, asiakas_id INTEGER NOT NULL, alkpva DATE NOT NULL, loppupva DATE NOT NULL, huonenumero INTEGER NOT NULL, kesto INTEGER NOT NULL, yhthinta INTEGER NOT NULL, FOREIGN KEY (asiakas_id) REFERENCES Asiakas(id));").executeUpdate();

            conn.prepareStatement("CREATE INDEX idx_varaus_alkupvm ON Varaus (alkupvm);").executeUpdate();

            

            conn.prepareStatement("CREATE TABLE Lisavaruste (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, varustetyyppi VARCHAR(255) NOT NULL, lisavaruste_lkm INTEGER NOT NULL);").executeUpdate();

            conn.prepareStatement("CREATE INDEX idx_lisavaruste_lisavaruste_lkm ON Lisavaruste (lisavaruste_lkm);").executeUpdate();

            

            conn.prepareStatement("CREATE TABLE VarausLisavaruste (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, varaus_id INTEGER NOT NULL, lisavaruste_id INTEGER NOT NULL, FOREIGN KEY (varaus_id) REFERENCES Varaus (id), FOREIGN KEY (lisavaruste_id) REFERENCES Lisavaruste (id));").executeUpdate();

            

            conn.prepareStatement("CREATE TABLE HuoneVaraus (id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, varaus_id INTEGER NOT NULL, huone_id INTEGER NOT NULL, FOREIGN KEY (varaus_id) REFERENCES Varaus (id), FOREIGN KEY (huone_id) REFERENCES Huone (id));").executeUpdate();

         

            
          
            
            
            

        } catch (SQLException ex) {

            Logger.getLogger(VarausjarjestelmaSovellus.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}



