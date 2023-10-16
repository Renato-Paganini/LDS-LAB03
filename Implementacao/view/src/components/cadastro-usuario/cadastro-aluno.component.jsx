import React, { useState } from "react";
import { TextField, Button, Box } from "@mui/material";
import axios from "axios";

const initForm = {
  nome: "",
  email: "",
  senha: "",
  rg: "",
  cpf: "",
  endereco: "",
  curso: "",
  saldo: 0,
  instituicao: "",
};

const CadastroAlunoComponent = () => {
  const [formData, setFormData] = useState(initForm);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <TextField
        fullWidth
        margin="normal"
        label="Nome"
        name="nome"
        value={formData.nome}
        onChange={handleChange}
      />
      <TextField
        fullWidth
        margin="normal"
        label="Email"
        name="email"
        value={formData.email}
        onChange={handleChange}
        type="email"
      />
      <TextField
        fullWidth
        margin="normal"
        label="Senha"
        name="senha"
        value={formData.senha}
        onChange={handleChange}
        type="password"
      />
      <TextField
        fullWidth
        margin="normal"
        label="RG"
        name="rg"
        value={formData.rg}
        onChange={handleChange}
      />
      <TextField
        fullWidth
        margin="normal"
        label="CPF"
        name="cpf"
        value={formData.cpf}
        onChange={handleChange}
      />
      <TextField
        fullWidth
        margin="normal"
        label="Endereço"
        name="endereco"
        value={formData.endereco}
        onChange={handleChange}
      />
      <TextField
        fullWidth
        margin="normal"
        label="Instituição"
        name="instituicao"
        value={formData.instituicao}
        onChange={handleChange}
      />
      <TextField
        fullWidth
        margin="normal"
        label="Curso"
        name="curso"
        value={formData.curso}
        onChange={handleChange}
      />

      <Box mt={2}>
        <Button variant="contained" color="primary" type="submit">
          Cadastrar
        </Button>
      </Box>
    </form>
  );
};

export default CadastroAlunoComponent;
