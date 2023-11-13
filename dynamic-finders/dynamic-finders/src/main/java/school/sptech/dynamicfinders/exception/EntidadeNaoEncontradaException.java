package school.sptech.dynamicfinders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Controller Advice
// A anotação @ResponseStatus indica que essa exceção deve resultar em uma resposta HTTP 404 NOT_FOUND.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

    // Construtor que recebe uma mensagem de erro como argumento.
    public EntidadeNaoEncontradaException(String message) {
        // Chama o construtor da classe pai (RuntimeException) com a mensagem de erro.
        super(message);
    }
}
