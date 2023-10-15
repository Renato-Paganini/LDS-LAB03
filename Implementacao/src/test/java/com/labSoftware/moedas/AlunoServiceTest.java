package com.labSoftware.moedas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.labSoftware.models.Aluno;
import com.labSoftware.repositories.AlunoRepository;
import com.labSoftware.services.AlunoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest
public class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    public void testReceberMoedas() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setSaldo(100);

        when(alunoRepository.buscarPeloId(1L)).thenReturn(Optional.of(aluno));
        when(alunoRepository.salvar(any(Aluno.class))).thenAnswer(i -> i.getArguments()[0]);

        Aluno updatedAluno = alunoService.receberMoedas(1L, 50);

        assertEquals(150, updatedAluno.getSaldo());
        verify(alunoRepository, times(1)).buscarPeloId(1L);
        verify(alunoRepository, times(1)).salvar(any(Aluno.class));
    }
}