package be.odisee.travelbase.domain;

import java.io.Serializable;
import javax.persistence.*;

// comment van M.A
// Duizendste comment
// laatste test
//COmment van Ralph
//Comment van thomasBranch

@Entity
@Table(name="evaluatiefiches")
public class Evaluatiefiche {

    @Id
    // HV 20200228 veranderd van AUTO naar IDENTITY wegens com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException
    // zie ook https://stackoverflow.com/questions/49813666/table-dbname-hibernate-sequence-doesnt-exist
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    //test

    @Column
    private String beoordeling;

    @Column
    private String feedback;

    @Column
    private String oordeel;

    public Evaluatiefiche() {
    }

    public Evaluatiefiche(String feedback, String beoordeling, String oordeel) {
        this.feedback = feedback;
        this.beoordeling = beoordeling;
        this.oordeel = oordeel;
    }


    public Evaluatiefiche(int id, String feedback, String beoordeling, String oordeel) {
        this.id = id;
        this.feedback = feedback;
        this.beoordeling = beoordeling;
        this.oordeel = oordeel;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getBeoordeling() {
        return beoordeling;
    }

    public void setBeoordeling(String beoordeling) {
        this.beoordeling = beoordeling;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getOordeel() {
        return oordeel;
    }

    public void setOordeel(String oordeel) {
        this.oordeel = oordeel;
    }
}
