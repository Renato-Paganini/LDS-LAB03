import { Box, Button, TextField } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import baseUrl from "../../configs/config";

const initForm = {
  nome: "",
  cnpj: "",
  email: "",
  senha: "",
  confirmarSenha: "",
  saldo: 0,
};

const CadastroEmpresaComponent = () => {
  const nav = useNavigate();

  const [formData, setFormData] = useState(initForm);

  useEffect(() => {
    console.log(formData);
  }, [formData]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(formData);

    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    };

    const url = `${baseUrl}/empresa/create`;

    fetch(url, requestOptions)
      .then((response) => response.json())
      .then((data) => {
        localStorage.setItem("user", JSON.stringify(data));
        localStorage.setItem("userType", "Empresa");
        console.log(data);
        nav("/portalProfessor");
        console.log(data);
      })
      .catch((error) => {
        console.error("Erro:", error);
      });
  };

  const onClickBackHandle = () => {
    nav("/");
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
        required
      />
      <TextField
        fullWidth
        margin="normal"
        label="Email"
        name="email"
        value={formData.email}
        onChange={handleChange}
        type="email"
        required
      />
      <TextField
        fullWidth
        margin="normal"
        label="Senha"
        name="senha"
        value={formData.senha}
        onChange={handleChange}
        type="password"
        required
      />
      <TextField
        fullWidth
        margin="normal"
        label="Confirmar Senha"
        name="confirmarSenha"
        value={formData.confirmarSenha}
        onChange={handleChange}
        type="password"
        required
      />

      <TextField
        fullWidth
        margin="normal"
        label="CNPJ"
        name="cnpj"
        value={formData.cnpj}
        onChange={handleChange}
        required
      />

      <Box mt={2} sx={{ display: "flex", justifyContent: "space-between" }}>
        <Button
          variant="contained"
          color="primary"
          type="submit"
          onClick={handleSubmit}
        >
          Cadastrar
        </Button>

        <Button
          variant="contained"
          color="inherit"
          type="submit"
          onClick={onClickBackHandle}
        >
          Voltar
        </Button>
      </Box>
    </form>
  );
};

export default CadastroEmpresaComponent;
