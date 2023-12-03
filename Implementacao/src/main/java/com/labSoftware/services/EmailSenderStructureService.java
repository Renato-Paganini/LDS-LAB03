package com.labSoftware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.labSoftware.models.Aluno;
import com.labSoftware.models.Deposito;
import com.labSoftware.models.EmailSenderStructure;
import com.labSoftware.models.Empresa;
import com.labSoftware.models.Professor;
import com.labSoftware.models.Vantagem;

@Service
public class EmailSenderStructureService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("$(Mérito System)")
    private String fromMail;

    public void sendMail(String mail, EmailSenderStructure emailSenderStructure) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject(emailSenderStructure.getSubject());
        simpleMailMessage.setText(emailSenderStructure.getMessage());
        simpleMailMessage.setTo(mail);
        mailSender.send(simpleMailMessage);
    }

    public void backGroudSenderResgate(Aluno aluno, Empresa empresa, Vantagem vantagem) {
        StringBuilder msgAluno = new StringBuilder();
        msgAluno.append("Prezado(a), " + aluno.getNome());
        msgAluno.append(System.lineSeparator());
        msgAluno.append(System.lineSeparator());
        msgAluno.append("Você resgatou a vantagem que fornece: " + vantagem.getDescricao());
        msgAluno.append(System.lineSeparator());
        msgAluno.append(
                "Informe o código: MS" + vantagem.getId() + " Para resgatar sua vantagem com " + empresa.getNome());
        msgAluno.append(System.lineSeparator());
        msgAluno.append("Altenciosamente, \nMérito System");
        EmailSenderStructure toAluno = new EmailSenderStructure();
        toAluno.setMessage(msgAluno.toString());
        toAluno.setSubject("Resgate de vantagens: " + vantagem.getNome());
        sendMail(aluno.getEmail(), toAluno);

        // /// ---------------------------

        StringBuilder msgEmpresa = new StringBuilder();
        msgEmpresa.append("Prezado(a), " + empresa.getNome());
        msgEmpresa.append(System.lineSeparator());
        msgEmpresa.append(System.lineSeparator());
        msgEmpresa.append("Uma vantagem da qual vc é responsável foi resgatada");
        msgEmpresa.append(System.lineSeparator());
        msgEmpresa.append(
                "O(A) portador(a) " + aluno.getNome() + " com CPF: " + aluno.getCpf() + " Fornecerá o código: MS"
                        + vantagem.getId() + " para resgatá-la");
        msgEmpresa.append(System.lineSeparator());
        msgEmpresa.append(System.lineSeparator());
        msgEmpresa.append("Altenciosamente, \nMérito System");

        EmailSenderStructure toEmpresa = new EmailSenderStructure();
        toEmpresa.setMessage(msgEmpresa.toString());
        toEmpresa.setSubject("Resgate de vantagens: " + vantagem.getNome());
        sendMail(empresa.getEmail(), toEmpresa);

    }

    public void backGroudSenderDeposito(Aluno aluno, Professor professor, Deposito deposito) {
        StringBuilder msgAluno = new StringBuilder();
        msgAluno.append("Prezado(a), " + aluno.getNome());
        msgAluno.append(System.lineSeparator());
        msgAluno.append(System.lineSeparator());
        msgAluno.append(
                "Você recebeu um depósito de " + deposito.getValor() + " do Professor(a) " + professor.getNome());
        msgAluno.append(System.lineSeparator());
        msgAluno.append(System.lineSeparator());
        msgAluno.append("Altenciosamente, \nMérito System");

        EmailSenderStructure toAluno = new EmailSenderStructure();
        toAluno.setMessage(msgAluno.toString());
        toAluno.setSubject("Depósito recebido");
        sendMail(aluno.getEmail(), toAluno);

        // /// ---------------------------

        StringBuilder msgProf = new StringBuilder();
        msgProf.append("Prezado(a), " + professor.getNome());
        msgProf.append(System.lineSeparator());
        msgProf.append(System.lineSeparator());
        msgProf.append(
                "Você enviou um depósito de " + deposito.getValor() + " para o aluno(a) " + aluno.getNome());
        msgProf.append(System.lineSeparator());
        msgProf.append(System.lineSeparator());
        msgProf.append("Altenciosamente, \nMérito System");

        EmailSenderStructure toProf = new EmailSenderStructure();
        toProf.setMessage(msgProf.toString());
        toProf.setSubject("Depósito enviado");
        sendMail(aluno.getEmail(), toProf);

    }
}
