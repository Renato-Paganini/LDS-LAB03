import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import axios from "axios";
import React, { useEffect, useState } from "react";
import baseUrl from "../../configs/config";

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

export default function BasicModal({ open, onClose, objAtualizarProfessor }) {
  const [formData, setFormData] = useState({
    id_professor: "",
    email: "",
    nome: "",
    senha: "",
    departamento: "",
  });

  // Atualiza o formData sempre que objAtualizarProfessor mudar
  useEffect(() => {
    if (objAtualizarProfessor) {
      setFormData({
        id_professor: objAtualizarProfessor.idProfessor || "",
        email: objAtualizarProfessor.email || "",
        nome: objAtualizarProfessor.nome || "",
        senha: objAtualizarProfessor.senha || "",
        departamento: objAtualizarProfessor.departamento || "",
      });
    }
  }, [objAtualizarProfessor]);

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleClose = () => {
    onClose();
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const dataToUpdate = {
      id_professor: formData.id_professor,
      email: formData.email,
      nome: formData.nome,
      senha: formData.senha,
      departamento: formData.departamento,
    };
    axios
      .put(
        `${baseUrl}/professor/update/${dataToUpdate.id_professor}`,
        dataToUpdate
      )
      .then((response) => {
        console.log("Resposta da solicitação POST:", response.data);
      })
      .catch((error) => {
        console.error("Erro na solicitação POST:", error);
        // Lide com o erro aqui
      });

    onClose();
  };

  return (
    <ThemeProvider theme={theme}>
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
            Atualizando os dados
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
              name="email"
              label="E-mail"
              variant="outlined"
              fullWidth
              value={formData.email}
              onChange={handleFormChange}
            />
            <TextField
              name="nome"
              label="Nome"
              variant="outlined"
              fullWidth
              value={formData.nome}
              onChange={handleFormChange}
            />
            <TextField
              name="senha"
              label="Senha"
              variant="outlined"
              fullWidth
              value={formData.senha}
              onChange={handleFormChange}
            />
            <TextField
              name="departamento"
              label="Departamento"
              variant="outlined"
              fullWidth
              value={formData.departamento}
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
    </ThemeProvider>
  );
}
