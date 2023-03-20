package br.edu.unoesc.exemplo_H2.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import br.edu.unoesc.exemplo_H2.model.Funcionario;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class FuncionarioDTO implements Serializable {
	String nome;
	Integer num_dep;
	BigDecimal salario;
	Date nascimento;
	
	public FuncionarioDTO(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.num_dep = funcionario.getNumDep();
		this.salario = funcionario.getSalario();
		this.nascimento = funcionario.getNascimento();
		
	}
}
