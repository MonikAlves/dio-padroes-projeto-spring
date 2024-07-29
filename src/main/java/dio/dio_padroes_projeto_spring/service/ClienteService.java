package dio.dio_padroes_projeto_spring.service;

import dio.dio_padroes_projeto_spring.model.Cliente;
import dio.dio_padroes_projeto_spring.model.Endereco;
import dio.dio_padroes_projeto_spring.repository.ClienteRepository;
import dio.dio_padroes_projeto_spring.repository.EnderecoRepository;
import dio.dio_padroes_projeto_spring.service.impl.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements ClientServiceInterface {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public void inserir(Cliente cliente) {
        salvar(cliente);
    }

    private void salvar(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = this.enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            this.enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        this.clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente novoCliente) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        if (cliente.isPresent()) {
            cliente.get().setId(id);
            salvar(novoCliente);
        }
    }

    @Override
    public void deletar(Long id) {
        this.clienteRepository.deleteById(id);
    }
}