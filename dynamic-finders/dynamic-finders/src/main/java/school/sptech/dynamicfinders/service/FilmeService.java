package school.sptech.dynamicfinders.service;

/*
    @Component
    @Controller
    @RestController
    @Service
    @Repository
    @Configuration
    ...
 */

import org.springframework.stereotype.Service;
import school.sptech.dynamicfinders.entity.Filme;
import school.sptech.dynamicfinders.exception.EntidadeNaoEncontradaException;
import school.sptech.dynamicfinders.repository.FilmeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class FilmeService {
  // Lógica de negócios do serviço

  private final FilmeRepository repository;

  public FilmeService(FilmeRepository filmeRepository) {
    this.repository = filmeRepository;
  }

  public Filme salvar(Filme filme) {
    Filme filmeSalvo = this.repository.save(filme);
    return filmeSalvo;
  }

  public List<Filme> listar() {
    List<Filme> filmes = this.repository.findAll();
    return filmes;
  }

  public Filme buscarPorId(int id) {
    Filme filme = this.repository.findById(id).orElseThrow(
            () -> new EntidadeNaoEncontradaException("Filme não encontrado")
    );

//        Optional<Filme> filmeOpt = this.repository.findById(id);
//
//        if (filmeOpt.isEmpty()) {
//            throw new EntidadeNaoEncontradaException("Filme não encontrado");
//        }
//
//        return filmeOpt.get();

    return filme;
  }

  public Filme atualizar(int id, Filme filmeAtualizado) {

    Filme filme = this.buscarPorId(id);

    filmeAtualizado.setId(filme.getId());

    return repository.save(filmeAtualizado);
  }

  public void deletar(int id) {
    Filme filme = this.buscarPorId(id);
    repository.deletarFilmePorId(id);
  }

  public List<Filme> buscarPorTitulo(String nome) {
    List<Filme> filmesEncontrados = this.repository.findByNomeContainsIgnoreCase(nome);

    return filmesEncontrados;
  }

  public List<Filme> buscarPorDiretor(String nome) {
    List<Filme> filmesEncontrados = this.repository.findByDiretorContainsIgnoreCase(nome);

    return filmesEncontrados;
  }

  public List<Filme> buscarPorPeriodo(LocalDate data) {
    List<Filme> filmesEncontrados = this.repository.findByDataLancamentoLessThanEqual(data);
    return filmesEncontrados;
  }

  public List<Filme> buscarPorIndicados() {
    List<Filme> filmesEncontrados = this.repository.findByIndicacaoOscarTrue();
    return filmesEncontrados;
  }

  public long buscarPorNaoIndicados() {
    long qtdNaoIndicados = this.repository.countByIndicacaoOscarFalse();
    return qtdNaoIndicados;
  }

  public Filme maiorCusto() {
    Filme qtdNaoIndicados =
            this.repository.findFirstByOrderByCustoProducaoDesc().orElseThrow(
                    () -> new EntidadeNaoEncontradaException("Filme de maior custo não encontrado")
            );

    return qtdNaoIndicados;
  }

  public List<Filme> buscarOs3MaisCaros() {
    List<Filme> filmesEncontrados = this.repository.findTop3ByOrderByCustoProducaoDesc();
    return filmesEncontrados;
  }

  public List<Filme> buscarPorDiretorIndicacao(String nome, Boolean indicado) {
    List<Filme> filmesEncontrados = this.repository.buscarPorDiretorIndicadosOcar(nome, indicado);
    return filmesEncontrados;
  }

  public void atualizarNome(int id, String novoNome) {
    this.buscarPorId(id);
    this.repository.renomearFilme(novoNome, id);
  }
}
