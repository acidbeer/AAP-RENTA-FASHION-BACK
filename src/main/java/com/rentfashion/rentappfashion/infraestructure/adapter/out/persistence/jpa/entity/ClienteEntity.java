package com.rentfashion.rentappfashion.infraestructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @Column(name = "persona_id", length = 20, nullable = false)
    private String personaId;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "persona_id")
    private PersonaEntity persona;

    @Column(name = "mail", length = 120, nullable = false, unique = true)
    private String mail;

    public String getPersonaId() { return personaId; }
    public void setPersonaId(String personaId) { this.personaId = personaId; }

    public PersonaEntity getPersona() { return persona; }
    public void setPersona(PersonaEntity persona) { this.persona = persona; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
}
