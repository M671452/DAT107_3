package main;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Avdeling", schema = "oblig3")
public class Avdeling {
	
	@Id
    @GeneratedValue
    @Column(name = "AvdelingId")
    private int avdelingId;

    @Column(name = "AvdNavn")
    private String avdNavn;

    @OneToOne
    @JoinColumn(name = "SjefId")
    private Ansatt sjef;
    
    @OneToMany(mappedBy = "avdeling")
    private List<Ansatt> ansatte;

    public Avdeling() {
    }
    
    public List<Ansatt> getAnsatte() {
        return ansatte;
    }

    public int getAvdelingId() {
        return avdelingId;
    }

    public void setAvdelingId(int avdelingId) {
        this.avdelingId = avdelingId;
    }

    public String getAvdNavn() {
        return avdNavn;
    }

    public void setAvdNavn(String avdNavn) {
        this.avdNavn = avdNavn;
    }

    public Ansatt getSjef() {
        return sjef;
    }

    public void setSjef(Ansatt sjef) {
        this.sjef = sjef;
    }
}
