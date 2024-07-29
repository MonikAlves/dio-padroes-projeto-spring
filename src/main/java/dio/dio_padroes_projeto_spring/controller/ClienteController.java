package dio.dio_padroes_projeto_spring.controller;

import dio.dio_padroes_projeto_spring.model.Cliente;
import dio.dio_padroes_projeto_spring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/todos")
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        return ResponseEntity.ok(this.clienteService.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.clienteService.buscarPorId(id));
    }

    @PostMapping("/inserir")
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        this.clienteService.inserir(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        this.clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        this.clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
