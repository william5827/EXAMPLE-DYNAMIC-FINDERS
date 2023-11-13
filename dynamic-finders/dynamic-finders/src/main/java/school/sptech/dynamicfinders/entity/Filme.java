package school.sptech.dynamicfinders.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Filme {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Size(min = 3)
  private String nome;

  @NotBlank
  @Size(min = 3)
  private String diretor;

  @PastOrPresent
  private LocalDate dataLancamento;

  @PositiveOrZero
  private Double custoProducao;

  private boolean indicacaoOscar;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDiretor() {
    return diretor;
  }

  public void setDiretor(String diretor) {
    this.diretor = diretor;
  }

  public LocalDate getDataLancamento() {
    return dataLancamento;
  }

  public void setDataLancamento(LocalDate dataLancamento) {
    this.dataLancamento = dataLancamento;
  }

  public Double getCustoProducao() {
    return custoProducao;
  }

  public void setCustoProducao(Double custoProducao) {
    this.custoProducao = custoProducao;
  }

  public boolean isIndicacaoOscar() {
    return indicacaoOscar;
  }

  public void setIndicacaoOscar(boolean indicacaoOscar) {
    this.indicacaoOscar = indicacaoOscar;
  }
}
