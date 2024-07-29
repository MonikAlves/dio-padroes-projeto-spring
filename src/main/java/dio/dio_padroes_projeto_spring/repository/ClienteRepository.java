package dio.dio_padroes_projeto_spring.repository;

import dio.dio_padroes_projeto_spring.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {}