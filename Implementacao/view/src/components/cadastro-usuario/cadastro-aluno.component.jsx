import React, { useEffect, useState } from "react";
import { TextField, Button, Box, MenuItem } from "@mui/material";
import { useNavigate } from "react-router-dom";

const initForm = {
  nome: "",
  email: "",
  senha: "",
  confirmarSenha: "",
  rg: "",
  cpf: "",
  endereco: "",
  curso: "",
  saldo: 0,
  instituicao: { id: "" },
};

const CadastroAlunoComponent = () => {
  const nav = useNavigate();

  const [formData, setFormData] = useState(initForm);
  const [universities, setUniversities] = useState([]);

  useEffect(() => {
    console.log(formData);
  }, [formData]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleInstituicaoChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, instituicao: { id: value } });
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

    const url = "http://localhost:7070/aluno";

    fetch(url, requestOptions)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
      })
      .catch((error) => {
        console.error("Erro:", error);
      });
  };

  const onClickBackHandle = () => {
    nav("/");
  };

  const getAllUniversities = async () => {
    const urlApi = "http://localhost:7070/instituicao/getAll";

    try {
      const response = await fetch(urlApi);

      if (!response.ok) {
        alert("Erro ao carregar as universidades cadastradas");
        // nav("/");
      }

      const data = await response.json();
      setUniversities(data);
      console.log(data);
    } catch (error) {
      console.error("Erro:", error);
      alert(
        "Houve um erro ao carregar as universidades cadastradas, tente novamente mais tarde"
      );
      // nav("/");
    }
  };

  useEffect(() => {
    getAllUniversities();
  }, []);

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
        label="Confirmar Senha"
        name="confirmarSenha"
        value={formData.confirmarSenha}
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
        margin="normal"
        required
        fullWidth
        name="instituicao"
        label="Escolha uma instituição"
        select
        value={formData.instituicao.id}
        onChange={handleInstituicaoChange}
      >
        {universities.map((option) => (
          <MenuItem key={option.id} value={option.id}>
            {option.nome}
          </MenuItem>
        ))}
      </TextField>

      <TextField
        fullWidth
        margin="normal"
        label="Curso"
        name="curso"
        value={formData.curso}
        onChange={handleChange}
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

export default CadastroAlunoComponent;
