package dio.dio_padroes_projeto_spring.service.impl;

import dio.dio_padroes_projeto_spring.model.Cliente;

public interface ClientServiceInterface {
    public Iterable<Cliente> buscarTodos();
    public Cliente buscarPorId(Long id);
    public void inserir(Cliente cliente);
    public void atualizar(Long id, Cliente cliente);
    public void deletar(Long id);
}