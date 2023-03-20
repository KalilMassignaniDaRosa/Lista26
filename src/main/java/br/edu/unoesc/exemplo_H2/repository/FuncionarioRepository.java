package br.edu.unoesc.exemplo_H2.repository;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.exemplo_H2.model.Funcionario;

@EnableJpaRepositories
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	public List<Funcionario> findByNomeContainingIgnoreCase(String funcionario);
	
	
	@Query("Select f from Funcionario f where f.salario >= :salarioMinimo and f.salario <= :salarioMaximo")
	public List<Funcionario> porFaixaSalarial(@Param("salarioMinimo") BigDecimal salarioMinimo,
											  @Param("salarioMaximo")BigDecimal salarioMaximo);
	
	
	@Query("Select f from Funcionario f where f.numDep > :dependente")
	public List<Funcionario> possuiDependentes(@Param("dependente") Integer dependente);
	
}
