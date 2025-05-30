import java.util.*;

public class Member {

    private int ID;
    private String name;
    private String email;
    private List<Loan> loans;

    public Member(int id, String name, String email) {
        this.ID = id;
        this.name = name;
        this.email = email;
        this.loans = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }


}
