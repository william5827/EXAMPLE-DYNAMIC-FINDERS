package school.sptech.dynamicfinders.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.dynamicfinders.entity.Filme;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {

  // SELECT * FROM Filme WHERE LOWER(nome) LIKE LOWER('%titulo%');
  // Retorna uma lista de filmes cujo título contém (ignorando maiúsculas/minúsculas) a string fornecida.
  List<Filme> findByNomeContainsIgnoreCase(String titulo);

  // SELECT * FROM Filme WHERE LOWER(diretor) LIKE LOWER('%diretor%');
  // Retorna uma lista de filmes cujo diretor contém (ignorando maiúsculas/minúsculas) a string do diretor fornecida.
  List<Filme> findByDiretorContainsIgnoreCase(String diretor);

  // SELECT * FROM Filme WHERE data_lancamento <= :periodo;
  // Retorna uma lista de filmes com data de lançamento menor ou igual à data fornecida.
  List<Filme> findByDataLancamentoLessThanEqual(LocalDate periodo);

  // SELECT * FROM Filme WHERE indicacao_oscar = true;
  // Retorna uma lista de filmes que foram indicados ao Oscar.
  List<Filme> findByIndicacaoOscarTrue();

  // SELECT COUNT(*) FROM Filme WHERE indicacao_oscar = false;
  // Conta a quantidade de filmes que não foram indicados ao Oscar.
  Long countByIndicacaoOscarFalse();

  // SELECT * FROM Filme ORDER BY custo_producao DESC LIMIT 1;
  // Retorna o primeiro filme encontrado ordenado pelo custo de produção em ordem decrescente.
  Optional<Filme> findFirstByOrderByCustoProducaoDesc();

  // SELECT * FROM Filme ORDER BY custo_producao DESC LIMIT 3;
  // Retorna uma lista dos top 3 filmes ordenados pelo custo de produção em ordem decrescente.
  List<Filme> findTop3ByOrderByCustoProducaoDesc();

  // busca por nome do diretor e indicacao ao oscar
  // contagem de filmes indicados por diretor
  // alteração do nome do filme
  // deletar filme por id

  // JPQL - Java Persistence Query Language

  // Dynamic finder
  // Specification
  // JPQL - SELECT FROM
  // native

//  List<Filme> findByDiretorContainsIgnoreAndIndicacaoOscar(String nome, boolean indicado);

  @Query("SELECT f FROM Filme f " +
          "where f.diretor ilike %:nome% and f.indicacaoOscar = :indicado")
  List<Filme> buscarPorDiretorIndicadosOcar(
          String nome, boolean indicado);

  @Query("SELECT count(f) FROM Filme f " +
          "where f.diretor ilike %:nome% and f.indicacaoOscar = :indicado")
  Integer contagemPorDiretorIndicadosOcar(
          String nome, boolean indicado);

  // a anotação @Modifying é usada para sinalizar que uma consulta
  // JPQL ou SQL nativa (geralmente uma operação de atualização ou exclusão)
  // modificará o estado do banco de dados
  @Modifying
  /*
    O @Transactional é usado para indicar que um método deve ser envolto
    em uma transação. Quando você executa um método anotado com @Transactional,
    o Spring gerencia automaticamente a abertura, commit ou rollback da transação,
    dependendo do sucesso ou falha do método.
   */
  @Transactional
  @Query("UPDATE Filme f SET f.nome = :nome WHERE f.id = :id")
  Integer renomearFilme(String nome, Integer id);

  @Modifying
  @Transactional
  @Query("DELETE FROM Filme f WHERE f.id = :id")
  void deletarFilmePorId(Integer id);

//  @Query("SELECT f FROM Filme f where f.dataLancamento <= :data")
//  List<Filme> buscarPorPeriodo(LocalDate data);

}
