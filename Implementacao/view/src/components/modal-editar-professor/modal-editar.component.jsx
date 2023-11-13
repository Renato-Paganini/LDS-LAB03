import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import axios from "axios";
import * as React from "react";
import { useEffect, useState } from "react";

const theme = createTheme({
  components: {
    MuiTextField: {
      styleOverrides: {
        root: {
          margin: "10px",
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          width: "100%",
          backgroundColor: "green",
          color: "white",
        },
      },
    },
  },
});

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 600,
  bgcolor: "background.paper",
  borderRadius: "8px",
  p: 5,
};

const buttonStyle = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
};

export default function BasicModal({ open, onClose, objDeposito }) {
  const currentDate = new Date().toISOString().split("T")[0]; // Obtém a data atual no formato YYYY-MM-DD

  const [formData, setFormData] = useState({
    id_professor: objDeposito ? objDeposito.idProfessor : "",
    email: objDeposito ? objDeposito.idAluno : "",
    nome: objDeposito ? objDeposito.nomeAluno : "",
    senha: objDeposito ? objDeposito.senha : "", // Define a data atual
    departamento: objDeposito ? objDeposito.departamento : "", // Defina um valor inicial apropriado
  });

  const handleClose = () => {
    onClose();
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  useEffect(() => {
    setFormData({
      id_professor: objDeposito.idProfessor,
      email: objDeposito.idAluno,
      nome: objDeposito.nomeAluno,
      data: currentDate, // Define a data atual
      valor: "", // Defina um valor inicial apropriado
      description: "",
    });
  }, [objDeposito]);

  const handleSubmit = (e) => {
    e.preventDefault();

    // Construa um objeto com os dados do formulário
    const data = {
      id_professor: formData.id_professor,
      email: formData.email,
      nome: formData.nome,
      data: formData.data,
      valor: formData.valor,
      description: formData.description,
    };

    // Realize a solicitação POST para a URL desejada
    axios
      .post("http://localhost:7070/transacao/realizaDeposito", data)
      .then((response) => {
        console.log("Resposta da solicitação POST:", response.data);
        // Lide com a resposta, se necessário
      })
      .catch((error) => {
        console.error("Erro na solicitação POST:", error);
        // Lide com o erro de acordo com os requisitos do seu aplicativo
      });

    onClose();
  };

  return (
    <ThemeProvider theme={theme}>
      <div>
        <Modal
          open={open}
          onClose={handleClose}
          aria-labelledby="modal-modal-title"
          aria-describedby="modal-modal-description"
        >
          <Box sx={style}>
            <Typography
              id="modal-modal-title"
              variant="h6"
              component="h2"
              sx={{ textAlign: "center" }}
            >
              Preencha os campos para realizar um depósito
            </Typography>
            <form onSubmit={handleSubmit}>
              <TextField
                name="id_professor"
                label="ID do Professor"
                variant="outlined"
                fullWidth
                value={formData.id_professor}
                disabled
              />
              <TextField
                name="email_professor"
                label="E-mail"
                variant="outlined"
                fullWidth
                value={formData.email}
              />
              <TextField
                name="nome"
                label="Nome"
                variant="outlined"
                fullWidth
                value={formData.nome}
              />
              <TextField
                name="senha"
                label="Senha"
                type="password"
                variant="outlined"
                fullWidth
                value={formData.data}
                sx={{ width: "100%" }}
              />
              <TextField
                name="departamento"
                label="Departamento"
                variant="outlined"
                fullWidth
                value={formData.valor}
                onChange={handleFormChange}
              />
              <Box sx={buttonStyle}>
                <Button type="submit" variant="contained">
                  Atualizar
                </Button>
              </Box>
            </form>
          </Box>
        </Modal>
      </div>
    </ThemeProvider>
  );
}
