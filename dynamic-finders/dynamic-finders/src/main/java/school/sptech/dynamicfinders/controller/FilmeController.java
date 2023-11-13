package school.sptech.dynamicfinders.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import school.sptech.dynamicfinders.entity.Filme;
import school.sptech.dynamicfinders.service.FilmeService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    /*
    @Autowired
    private FilmeService service;

    @Autowired
    private FilmeRepository filmeRepository;

     */

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Filme> criar(@Valid @RequestBody Filme novoFilme) {
        Filme filmeSalvo = this.service.salvar(novoFilme);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(filmeSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(filmeSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        List<Filme> filmes = this.service.listar();

        if (filmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Integer id) {
        Filme filmeEncontrado = this.service.buscarPorId(id);
        return ResponseEntity.ok(filmeEncontrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Filme filmeAtualizado) {

        Filme filmeSalvo = this.service.atualizar(id, filmeAtualizado);
        return ResponseEntity.ok(filmeSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        this.service.deletar(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/titulo")
    public ResponseEntity<List<Filme>> buscarPorTitulo(@RequestParam String nome) {
        List<Filme> filmes = this.service.buscarPorTitulo(nome);

        if (filmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/diretor")
    public ResponseEntity<List<Filme>> buscarPorDiretor(@RequestParam String nome) {
        List<Filme> filmes = this.service.buscarPorDiretor(nome);

        if (filmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Filme>> buscarPorPeriodo(@RequestParam LocalDate data) {
        List<Filme> filmes = this.service.buscarPorPeriodo(data);

        if (filmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/indicados")
    public ResponseEntity<List<Filme>> buscarPorIndicados() {
        List<Filme> filmes = this.service.buscarPorIndicados();

        if (filmes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/nao-indicados/quantidade")
    public ResponseEntity<Long> buscarPorNaoIndicados() {
        Long qtdNaoIndicados = this.service.buscarPorNaoIndicados();
        return ResponseEntity.ok(qtdNaoIndicados);
    }

    @GetMapping("/custo-producao")
    public ResponseEntity<Filme> maiorCusto() {
        Filme filme = this.service.maiorCusto();
        return ResponseEntity.ok(filme);
    }

    @GetMapping("/custo-producao/mais-caros")
    public ResponseEntity<List<Filme>> buscarOs3MaisCaros() {

        List<Filme> maisCaros = this.service.buscarOs3MaisCaros();

        if (maisCaros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(maisCaros);
    }

    @GetMapping("/diretor/indicacao")
    public ResponseEntity<List<Filme>> buscarPorDiretorIndicacao(
            @RequestParam String nome,
            @RequestParam(
                    required = false,
                    defaultValue = "false") Boolean indicado
    ) {

        List<Filme> filmesEncontrados = this.service.buscarPorDiretorIndicacao(nome, indicado);

        if (filmesEncontrados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filmesEncontrados);
    }

    @PatchMapping("/{id}/nome")
    public ResponseEntity<Void> atualizarNome(
            @PathVariable int id,
            @RequestParam String nome) {

      this.service.atualizarNome(id, nome);
      return ResponseEntity.noContent().build();
    }
}
