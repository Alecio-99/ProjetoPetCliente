package com.pet.planos.DTO;

import java.math.BigDecimal;

public class RelatorioFinanceiroDTO{

    private String tipoServico;
    private String mes;
    private BigDecimal total;

    public RelatorioFinanceiroDTO(String tipoServico, String mes, BigDecimal total){
        this.tipoServico = tipoServico;
        this.mes = mes;
        this.total = total;
    }

    public RelatorioFinanceiroDTO(String tipoServico, Integer ano, Integer mesNum, BigDecimal total) {
        this.tipoServico = tipoServico;
        this.mes = String.format("%d-%02d", ano, mesNum); // Formata para YYYY-MM
        this.total = total;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
