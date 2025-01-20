/**
 * Clasa Question reprezintă o întrebare dintr-un chestionar.
 * Aceasta include textul întrebării, variantele de răspuns (A, B, C, D) 
 * și răspunsul corect.
 */
public class Question {
	private String question ;
	private String varA ;
	private String varB ;
	private String varC ;
	private String varD ;
	private char correct ;
    /**
     * Obține textul întrebării.
     *
     * @return textul întrebării.
     */
	public String getQuestion() {
		return question;
	}

    /**
     * Setează textul întrebării.
     *
     * @param question textul întrebării.
     */
	public void setQuestion(String question) {
		this.question = question;
	}
    /**
     * Obține varianta de răspuns A.
     *
     * @return varianta A.
     */
	public String getVarA() {
		return varA;
	}
    /**
     * Setează varianta de răspuns A.
     *
     * @param varA textul variantei A.
     */
	public void setVarA(String varA) {
		this.varA = varA;
	}
	
    /**
     * Obține varianta de răspuns B.
     *
     * @return varianta B.
     */
	public String getVarB() {
		return varB;
	}
    /**
     * Setează varianta de răspuns B.
     *
     * @param varA textul variantei B.
     */
	public void setVarB(String varB) {
		this.varB = varB;
	}
	
    /**
     * Obține varianta de răspuns C.
     *
     * @return varianta C.
     */
	public String getVarC() {
		return varC;
	}
    /**
     * Setează varianta de răspuns C.
     *
     * @param varA textul variantei C.
     */
	public void setVarC(String varC) {
		this.varC = varC;
	}
    /**
     * Obține varianta de răspuns D.
     *
     * @return varianta D.
     */
	public String getVarD() {
		return varD;
	}
    /**
     * Setează varianta de răspuns D.
     *
     * @param varA textul variantei D.
     */
	public void setVarD(String varD) {
		this.varD = varD;
	}
	
    /**
     * Obține răspunsul corect.
     *
     * @return răspunsul corect sub forma unei litere (A, B, C sau D).
     */
	public char getCorrect() {
		return correct;
	}
    /**
     * Setează răspunsul corect.
     *
     * @param correct litera care reprezintă răspunsul corect (A, B, C sau D).
     */
	public void setCorrect(char correct) {
		this.correct = correct;
	}
	
    /**
     * Constructor care inițializează toate câmpurile întrebării.
     *
     * @param question textul întrebării.
     * @param varA textul variantei A.
     * @param varB textul variantei B.
     * @param varC textul variantei C.
     * @param varD textul variantei D.
     * @param correct litera care reprezintă răspunsul corect (A, B, C sau D).
     */
	public Question(String question, String varA, String varB, String varC, String varD, char correct) {
		super();
		this.question = question;
		this.varA = varA;
		this.varB = varB;
		this.varC = varC;
		this.varD = varD;
		this.correct = correct;
	}
	
}
