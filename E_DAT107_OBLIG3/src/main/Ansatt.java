package main;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Ansatt", schema = "oblig3")
public class Ansatt {
    
    @Id
    @GeneratedValue
    @Column(name = "ansattId")
    private Long ansattId;
	
	@Column(name = "fornavn")
	private String fornavn;
	
	@Column(name = "etternavn")
	private String etternavn;
	
	@Column(name = "stilling")
	private String stilling;
	
	@Column(name = "mndloenn")
	private int lonn;
	
    @ManyToOne
    @JoinColumn(name = "AnsattAvd")
	private Avdeling avdeling;
	
	private LocalDate ansattDato;
	// Konstruktør:
	public Ansatt() {
		
	}
	
	// Set- og get-metoder:
	public Long getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(Long ansattId) {
        this.ansattId = ansattId;
    }
    
    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }
    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }
    
    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }
    
    public int getLonn() {
        return lonn;
    }

    public void setLonn(int lonn) {
        this.lonn = lonn;
    }
    
    public void setAvdeling(Avdeling avdeling) {
    	this.avdeling = avdeling;
    }
    
    public LocalDate getAnsattDato() {
    	return ansattDato;
    }
    
    public void setAnsattDato(LocalDate ansattDato) {
    	this.ansattDato = ansattDato;
	}
    
    public void skrivUt() {
        System.out.println("Ansatt ID: " + ansattId);
        System.out.println("Navn: " + fornavn + " " + etternavn);
        System.out.println("Stilling: " + stilling);
        System.out.println("Lønn: " + lonn);
    }
}
