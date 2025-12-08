    package com.pabloleal.ReparoPlus.models;

    import com.pabloleal.ReparoPlus.dto.PessoaCreateRequestDTO;
    import jakarta.persistence.*;
    import java.util.HashSet;
    import java.util.Set;

    @Entity
    @Table(name = "atendentes")
    public class Atendente extends Pessoa {

        @OneToMany(mappedBy = "atendente", fetch = FetchType.LAZY)
        private Set<OrdemServico> ordensServico = new HashSet<>();

        public Atendente() {
        }

        public Atendente(PessoaCreateRequestDTO pessoaDTO){
            super(pessoaDTO);
        }

        public Set<OrdemServico> getOrdensServico() {
            return ordensServico;
        }

        public void setOrdensServico(Set<OrdemServico> ordensServico) {
            this.ordensServico = ordensServico;
        }
    }
