package com.pet.planos.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pet.planos.enuns.EnunsStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "planoEssencial")
public class EntityPlanoEssencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDog;
    private String tipoServico;
    private String raca;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime inicio;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fim;
    private BigDecimal preco;
    @ManyToOne
    @JoinColumn(name = "nome", nullable = false)
    private EntityCliente dono;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnunsStatus status;

    public EnunsStatus getStatus() {
        return status;
    }

    public void setStatus(EnunsStatus status) {
        this.status = status;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDog() {
        return nomeDog;
    }

    public void setNomeDog(String nomeDog) {
        this.nomeDog = nomeDog;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public EntityCliente getDono() {
        return dono;
    }

    public void setDono(EntityCliente dono) {
        this.dono = dono;
    }
}
