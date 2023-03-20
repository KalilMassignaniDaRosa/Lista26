package br.edu.unoesc.exemplo_H2.service_impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unoesc.exemplo_H2.dto.FuncionarioDTO;
import br.edu.unoesc.exemplo_H2.model.Funcionario;
import br.edu.unoesc.exemplo_H2.repository.FuncionarioRepository;
import br.edu.unoesc.exemplo_H2.service.FuncionarioService;


@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	@Autowired
	private FuncionarioRepository repositorio;
	
	@Override
	public void popularTabelaInicial() {
		java.sql.Date dt = new java.sql.Date(new java.util.Date().getTime());
		
		repositorio.saveAll(List.of(
				
				new Funcionario(null, "Fulano", 0, new BigDecimal("123.45"), dt),
				new Funcionario(null, "Beltrano", 2, new BigDecimal("42.42"),dt),
				new Funcionario(null, "Sicrano", 3, new BigDecimal("666"), dt)
		));
	}
	
	@Override
	public Funcionario incluir(Funcionario funcionario) {
		funcionario.setId(null);
		return repositorio.save(funcionario);
	}

	@Override
	public Funcionario alterar(Long id, Funcionario funcionario) {
		var f = repositorio.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Funcionário não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName(), null));

		// Atualiza os dados do banco com os dados vindos da requisição
		f.setNascimento(funcionario.getNascimento());
		f.setNome(funcionario.getNome());
		f.setNumDep(funcionario.getNumDep());
		f.setSalario(funcionario.getSalario());
		return repositorio.save(f);
		
	}


	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		return repositorio.findByNomeContainingIgnoreCase(nome);
	}

	@Override
	public List<Funcionario> buscarPorFaixaSalarial(BigDecimal salarioMinimo, BigDecimal salarioMaximo) {
		return repositorio.porFaixaSalarial(salarioMinimo, salarioMaximo);
	}

	@Override
	public List<Funcionario> buscarPossuiDependentes(Integer numDependentes) {
		
		return repositorio.possuiDependentes(numDependentes);
	}

	

	@Override
	public void excluir(Long id) {
		if (repositorio.existsById(id)) {
			repositorio.deleteById(id);
		}
		
	}

	@Override
	public List<Funcionario> listar() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Iterable<Funcionario> itens = repositorio.findAll();
		itens.forEach(funcionarios::add);

		return funcionarios;
	}

	@Override
	public Page<FuncionarioDTO> listarPaginado(Pageable pagina) {
		Page<Funcionario> lista = repositorio.findAll(pagina);

		Page<FuncionarioDTO> listaDTO = lista.map(funcionario -> new FuncionarioDTO(funcionario));

		return listaDTO;
	}

	@Override
	public Funcionario buscarPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Funcionário não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName(), null));
	}

	@Override
	public Funcionario buscar(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Funcionário não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName(), null));
	}

	@Override
	public Optional<Funcionario> porId(Long id) {
		return repositorio.findById(id);
	}
}
