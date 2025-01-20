import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clasa principala pentru lansarea aplicatiei Quiz.
 */
public class Main {
	/**
	 * Punctul de intrare al aplicatiei. Stabileste conexiunea la baza de date si initializeaza un obiect Quiz.
	 * Daca conexiunea este reusita, se creeaza un obiect Quiz. In caz contrar, se afiseaza un mesaj de eroare.
	 *
	 * @param args Argumentele din linia de comanda (neutilizate).
	 */
    public static void main(String[] args) {
        Connection conn = connectToDatabase();

        if (conn != null) {
            Quiz quiz = new Quiz(conn);
        } else {
            System.out.println("Nu s-a putut stabili conexiunea la baza de date.");
        }
    }
    /**
     * Stabileste o conexiune la baza de date MySQL.
     * 
     * @return Conexiunea la baza de date sau null daca nu s-a reusit conectarea.
     */
    private static Connection connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/quiz_game"; // 'quiz_game' este numele bazei de date
        String user = "root"; // Numele de utilizator pentru baza de date
        String password = "darius12"; // Parola de la baza de date

     // Inițializează conexiunea la baza de date
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectat la baza de date!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Eroare la conectarea la baza de date.");
            e.printStackTrace();
            return null;
        }
    }
}
















