package br.edu.unoesc.exemplo_H2.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unoesc.exemplo_H2.dto.FuncionarioDTO;
import br.edu.unoesc.exemplo_H2.model.Funcionario;
@Service
public interface FuncionarioService {
	void popularTabelaInicial();
	
	Funcionario incluir(Funcionario funcionario);
	Funcionario alterar(Long id, Funcionario funcionario);
	void excluir(Long id);
	
	List<Funcionario> listar();
	Page<FuncionarioDTO> listarPaginado(Pageable pagina);
	
	Funcionario buscar(Long id); //Lança uma excessão caso o não exista o funcionario com id procurado
	Funcionario buscarPorId(Long id); //Retorna um novo Funcionario caso id não seja encontrado
	Optional<Funcionario> porId(Long id);
	
	List<Funcionario> buscarPorNome(String nome);
	List<Funcionario> buscarPorFaixaSalarial(BigDecimal salarioMinimo, BigDecimal salarioMaximo);
	List<Funcionario> buscarPossuiDependentes(Integer numDependentes);
	
	
	
}
