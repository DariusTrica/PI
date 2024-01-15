import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.lang.System;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;



/**
 * @author Darius
 */
public class Quiz implements ActionListener {

  String[] questions = {
    "Care este cea mai mare planeta din sistemul solar?",
    "Cate continente exista pe Pamant?",
    "In ce an s-a sfarsit Al Doilea Razboi Mondial?",
    "In ce anotimp incepe sa ninga?",
    "Ce planeta este cunoscuta ca \"Planeta Rosie\"?",
    "Care este cel mai lung râu din lume?",
    "Care este capitala Frantei?",
    "Câte elemente sunt în tabelul periodic?",
    "Care este cel mai mare ocean din lume?",
    "Care este elementul chimic cu simbolul \"O\"?",
  };
  String[][] options = {
    { "Jupiter", "Pamant", "Venus", "Marte" },
    { "5", "7", "6", "8" },
    { "1943", "1947", "1945", "1950" },
    { "Primavara", "Vara", "Iarna", "Toamna" },
    { "Jupiter", "Saturn", "Venus", "Marte" },
    { "Nil", "Amazon", "Yangtze", "Mississippi" },
    { "Berlin", "Londra", "Madrid", "Paris" },
    { "105", "118", "112", "300" },
    { "Oceanul Pacific", "Oceanul Indian", "Oceanul Atlantic", "Oceanul Arctic" },
    { "Aur", "Osmiu", "Oxigen", "Olmiu" },
  };
  char[] answers = { 'A', 'B', 'C', 'C','D','B','D','B','A','C' };

  char guess;
  char answer;
  int index;
  int correct_guesses = 0;
  int total_questions = questions.length;
  int result;
  int seconds = 15;


  JButton buttonStart = new JButton();
  JButton buttonQuit = new JButton();
  JButton buttonBack = new JButton("Back to Menu");

  JFrame frame = new JFrame();
  JTextField textfield = new JTextField();
  JTextArea textarea = new JTextArea();
  JButton buttonA = new JButton();
  JButton buttonB = new JButton();
  JButton buttonC = new JButton();
  JButton buttonD = new JButton();
  JLabel answer_labelA = new JLabel();
  JLabel answer_labelB = new JLabel();
  JLabel answer_labelC = new JLabel();
  JLabel answer_labelD = new JLabel();
  JLabel time_label = new JLabel();
  JLabel seconds_left = new JLabel();
  JTextField number_right = new JTextField();
  JTextField percentage = new JTextField();

  Timer timer = new Timer(
    1000,
    new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        seconds--;
        seconds_left.setText(String.valueOf(seconds));
        if (seconds <= 0) {
          displayAnswer();
        }
      }
    }
  );
/**
 * Setarea de frame uri
 */
  public Quiz() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(650,650);
    frame.getContentPane().setBackground(new Color(50, 50, 50));
    frame.setLayout(null);
    frame.setResizable(false);

   
    buttonStart.setBounds(220, 150, 200, 100);
    buttonStart.setFont(new Font("MV Boli", Font.BOLD, 35));
    buttonStart.setFocusable(false);
    buttonStart.addActionListener(this);
    buttonStart.setText("Play");

    buttonQuit.setBounds(220, 350, 200, 100);
    buttonQuit.setFont(new Font("MV Boli", Font.BOLD, 35));
    buttonQuit.setFocusable(false);
    buttonQuit.addActionListener(this);
    buttonQuit.setText("Quit");
    
    buttonBack.setBounds(220, 450, 200, 100);
    buttonBack.setFont(new Font("MV Boli", Font.BOLD, 20));
    buttonBack.setFocusable(false);
    buttonBack.addActionListener(this);

    frame.add(buttonStart);
    frame.add(buttonQuit);
    frame.setVisible(true);
  }
/**
 * Setarea tuturor frameurilor
 */
  
  public void playGame() {

     // remove start and quit buttons
     frame.remove(buttonStart);
     frame.remove(buttonQuit);

    // adds the other buttons, texts, timers for the game logic
    textfield.setBounds(0, 0, 650, 50);
    textfield.setBackground(new Color(25, 25, 25));
    textfield.setForeground(new Color(25, 255, 0));
    textfield.setFont(new Font("Ink Free", Font.BOLD, 30));
    textfield.setBorder(BorderFactory.createBevelBorder(1));
    textfield.setHorizontalAlignment(JTextField.CENTER);
    textfield.setEditable(false);

    textarea.setBounds(0, 50, 800, 50);
    textarea.setLineWrap(true);
    textarea.setWrapStyleWord(true);
    textarea.setBackground(new Color(25, 25, 25));
    textarea.setForeground(new Color(25, 255, 0));
    textarea.setFont(new Font("MV Boli", Font.BOLD, 25));
    textarea.setBorder(BorderFactory.createBevelBorder(1));
    textarea.setEditable(false);

    buttonA.setBounds(0, 100, 100, 100);
    buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
    buttonA.setFocusable(false);
    buttonA.addActionListener(this);
    buttonA.setText("A");

    buttonB.setBounds(0, 200, 100, 100);
    buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
    buttonB.setFocusable(false);
    buttonB.addActionListener(this);
    buttonB.setText("B");

    buttonC.setBounds(0, 300, 100, 100);
    buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
    buttonC.setFocusable(false);
    buttonC.addActionListener(this);
    buttonC.setText("C");

    buttonD.setBounds(0, 400, 100, 100);
    buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
    buttonD.setFocusable(false);
    buttonD.addActionListener(this);
    buttonD.setText("D");

    answer_labelA.setBounds(125, 100, 500, 100);
    answer_labelA.setBackground(new Color(50, 50, 50));
    answer_labelA.setForeground(new Color(25, 255, 0));
    answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

    answer_labelB.setBounds(125, 200, 500, 100);
    answer_labelB.setBackground(new Color(50, 50, 50));
    answer_labelB.setForeground(new Color(25, 255, 0));
    answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

    answer_labelC.setBounds(125, 300, 500, 100);
    answer_labelC.setBackground(new Color(50, 50, 50));
    answer_labelC.setForeground(new Color(25, 255, 0));
    answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

    answer_labelD.setBounds(125, 400, 500, 100);
    answer_labelD.setBackground(new Color(50, 50, 50));
    answer_labelD.setForeground(new Color(25, 255, 0));
    answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 35));

    seconds_left.setBounds(535,510,100,100);
    seconds_left.setBackground(new Color(25, 25, 25));
    seconds_left.setForeground(new Color(255, 0, 0));
    seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
    seconds_left.setBorder(BorderFactory.createBevelBorder(1));
    seconds_left.setOpaque(true);
    seconds_left.setHorizontalAlignment(JTextField.CENTER);
    seconds_left.setText(String.valueOf(seconds));

    time_label.setBounds(535,475,100,25);
    time_label.setBackground(new Color(50, 50, 50));
    time_label.setForeground(new Color(255, 0, 0));
    time_label.setFont(new Font("MV Boli", Font.PLAIN, 16));
    time_label.setHorizontalAlignment(JTextField.CENTER);
    time_label.setText("<TIMP>");

    number_right.setBounds(225, 225, 200, 100);
    number_right.setBackground(new Color(25, 25, 25));
    number_right.setForeground(new Color(25, 255, 0));
    number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
    number_right.setBorder(BorderFactory.createBevelBorder(1));
    number_right.setHorizontalAlignment(JTextField.CENTER);
    number_right.setEditable(false);

    percentage.setBounds(225, 325, 200, 100);
    percentage.setBackground(new Color(25, 25, 25));
    percentage.setForeground(new Color(25, 255, 0));
    percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
    percentage.setBorder(BorderFactory.createBevelBorder(1));
    percentage.setHorizontalAlignment(JTextField.CENTER);
    percentage.setEditable(false);

    frame.add(time_label);
    frame.add(seconds_left);
    frame.add(answer_labelA);
    frame.add(answer_labelB);
    frame.add(answer_labelC);
    frame.add(answer_labelD);
    frame.add(buttonA);
    frame.add(buttonB);
    frame.add(buttonC);
    frame.add(buttonD);
    frame.add(textarea);
    frame.add(textfield);

    // bring new changes to the frame
    frame.repaint();

    nextQuestion();
  }
/**
 * Intrebarile
 */
  public void nextQuestion() {
    if (index >= total_questions) {
      results();
    } else {
      textfield.setText("Intrebarea " + (index + 1));
      textarea.setText(questions[index]);
      answer_labelA.setText(options[index][0]);
      answer_labelB.setText(options[index][1]);
      answer_labelC.setText(options[index][2]);
      answer_labelD.setText(options[index][3]);
      timer.start();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    
    if (e.getSource() == buttonStart) {
      playGame();
    } else if (e.getSource() == buttonQuit) {
      System.exit(0);
    } else if (e.getSource() == buttonBack) {
        frame.dispose();
        new Quiz();
    } else {
      buttonA.setEnabled(false);
      buttonB.setEnabled(false);
      buttonC.setEnabled(false);
      buttonD.setEnabled(false);
      if (e.getSource() == buttonA) {
        answer = 'A';
        if (answer == answers[index]) {
          correct_guesses++;
        }
      }
      if (e.getSource() == buttonB) {
        answer = 'B';
        if (answer == answers[index]) {
          correct_guesses++;
        }
      }
      if (e.getSource() == buttonC) {
        answer = 'C';
        if (answer == answers[index]) {
          correct_guesses++;
        }
      }
      if (e.getSource() == buttonD) {
        answer = 'D';
        if (answer == answers[index]) {
          correct_guesses++;
        }
      }
      displayAnswer();
    }
  }
/**
 * Butoanele de raspuns
 */
  public void displayAnswer() {
    timer.stop();

    buttonA.setEnabled(false);
    buttonB.setEnabled(false);
    buttonC.setEnabled(false);
    buttonD.setEnabled(false);

    if (answers[index] != 'A') answer_labelA.setForeground(
      new Color(255, 0, 0)
    );
    if (answers[index] != 'B') answer_labelB.setForeground(
      new Color(255, 0, 0)
    );
    if (answers[index] != 'C') answer_labelC.setForeground(
      new Color(255, 0, 0)
    );
    if (answers[index] != 'D') answer_labelD.setForeground(
      new Color(255, 0, 0)
    );

    Timer pause = new Timer(
      2000,
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          answer_labelA.setForeground(new Color(25, 255, 0));
          answer_labelB.setForeground(new Color(25, 255, 0));
          answer_labelC.setForeground(new Color(25, 255, 0));
          answer_labelD.setForeground(new Color(25, 255, 0));

          answer = ' ';
          seconds = 15;
          seconds_left.setText(String.valueOf(seconds));
          buttonA.setEnabled(true);
          buttonB.setEnabled(true);
          buttonC.setEnabled(true);
          buttonD.setEnabled(true);
          index++;
          nextQuestion();
        }
      }
    );
    pause.setRepeats(false);
    pause.start();
  }
/**
 * Afisarea rezultatelor
 */
  public void results() {
	    result = (int) ((correct_guesses / (double) total_questions) * 100);

	    textfield.setText("RESULTS!");
	    number_right.setText("(" + correct_guesses + "/" + total_questions + ")");
	    percentage.setText(result + "%");

	    // Elimină componentele anterioare din frame
	    frame.remove(textarea);
	    frame.remove(buttonA);
	    frame.remove(buttonB);
	    frame.remove(buttonC);
	    frame.remove(buttonD);
	    frame.remove(answer_labelA);
	    frame.remove(answer_labelB);
	    frame.remove(answer_labelC);
	    frame.remove(answer_labelD);
	    frame.remove(seconds_left);
	    frame.remove(time_label);

	    // Adaugă noile componente pentru afișarea rezultatelor
	    frame.add(number_right);
	    frame.add(percentage);
	    frame.add(buttonBack);
	    
	    frame.repaint();

	    // Solicită numele jucătorului și salvează scorul
	    String numeJucator = JOptionPane.showInputDialog(frame, "Introduceți numele pentru a salva scorul:");
	    if (numeJucator != null && !numeJucator.trim().isEmpty()) {
	        salveazaScor(numeJucator, correct_guesses);
	    }

	    // Afișează top 10 scoruri
	    afiseazaTopScoruri();

	}

//Clasa ScorJucator
public static class ScorJucator implements Comparable<ScorJucator> {
   private String nume;
   private int scor;

   public ScorJucator(String nume, int scor) {
       this.nume = nume;
       this.scor = scor;
   }

   public String getNume() {
       return nume;
   }

   public int getScor() {
       return scor;
   }

   @Override
   public int compareTo(ScorJucator altScor) {
       return altScor.scor - this.scor; // Sortare descrescătoare
   }
}

//Metoda pentru salvarea scorurilor
public void salveazaScor(String numeJucator, int scor) {
   try (BufferedWriter writer = new BufferedWriter(new FileWriter ("leaderboard.txt", true))) {
       writer.write(numeJucator + " - " + scor);
       writer.newLine();
   } catch (IOException e) {
       e.printStackTrace();
   }
}

//Metoda pentru citirea clasamentului
public List<ScorJucator> citesteClasament() {
   List<ScorJucator> scoruri = new ArrayList<>();
   try (BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"))) {
       String linie;
       while ((linie = reader.readLine()) != null) {
           String[] parts = linie.split(" - ");
           scoruri.add(new ScorJucator(parts[0], Integer.parseInt(parts[1])));
       }
   } catch (IOException e) {
       e.printStackTrace();
   }
   Collections.sort(scoruri);
   return scoruri;
}

//Metoda pentru afișarea top scorurilor
public void afiseazaTopScoruri() {
    List<ScorJucator> topScoruri = citesteClasament();
    JFrame leaderboardFrame = new JFrame("Leaderboard");
    leaderboardFrame.setSize(400, 600);
    leaderboardFrame.getContentPane().setBackground(new Color(50, 50, 50));
    leaderboardFrame.setLayout(new BorderLayout());
    leaderboardFrame.setLocationRelativeTo(frame); // Centrarea față de frame-ul principal

    JTextArea scoruriText = new JTextArea();
    scoruriText.setEditable(false);
    scoruriText.setBackground(new Color(25, 25, 25));
    scoruriText.setForeground(new Color(25, 255, 0));
    scoruriText.setFont(new Font("MV Boli", Font.BOLD, 20));
    scoruriText.setMargin(new Insets(10, 10, 10, 10)); // Adaugă margini pentru text

    StringBuilder sb = new StringBuilder();
    sb.append("Top 10 Scoruri:\n\n");
    for (int i = 0; i < Math.min(topScoruri.size(), 10); i++) {
        ScorJucator scor = topScoruri.get(i);
        sb.append(i + 1).append(". ").append(scor.getNume()).append(" - ").append(scor.getScor()).append("\n");
    }

    scoruriText.setText(sb.toString());

    JScrollPane scrollPane = new JScrollPane(scoruriText);
    scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Înlătură border-ul implicit
    leaderboardFrame.add(scrollPane, BorderLayout.CENTER);

    leaderboardFrame.setVisible(true);
}

}
