package main.java.com.labSoftware.services;

import java.util.Optional;

import com.labSoftware.models.Aluno;
import com.labSoftware.repositories.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findbyIdAluno(String cpf) {
        Optional<Aluno> aluno = this.alunoRepository.buscarAlunoPeloCpf(cpf);

        return aluno.orElseThrow(
                () -> new RuntimeException("Aluno não encontrado" + cpf + "Tipo: " + Aluno.class.getName()));
    }

    @Transactional
    public Aluno createAluno(Aluno obj) {
        obj.setCpf(null);
        obj = this.alunoRepository.salvar(obj);
        return obj;
    }

    @Transactional
    public Aluno updateAluno(Aluno obj) {
        Aluno aluno = findbyIdAluno(obj.getCpf());
        aluno.setCpf(obj.getCpf());
        aluno.setCurso(obj.getCurso());
        aluno.setEndereco(obj.getEndereco());
        aluno.setInstituicao(obj.getInstituicao());
        aluno.setRg(obj.getRg());
    }

    public void deleteAluno(String cpf) {
        Aluno aluno = findbyIdAluno(obj.getCpf());
        try {
            this.alunoRepository.deletarAlunoPeloCpf(aluno.getCpf());
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
