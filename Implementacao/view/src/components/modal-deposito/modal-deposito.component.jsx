import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import * as React from "react";
import { useState } from "react";

const theme = createTheme({
  components: {
    MuiTextField: {
      styleOverrides: {
        root: {
          margin: "10px", // Adiciona uma margem de 10px a todos os TextField
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          width: "100%", // Define a largura para 100% para que seja igual aos textFields
          backgroundColor: "green", // Define a cor de fundo como verde
          color: "white", // Define a cor do texto como branco
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
  borderRadius: "8px", // Adiciona cantos arredondados
  p: 5,
};

const buttonStyle = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center", // Centraliza verticalmente
};

export default function BasicModal({ open, onClose }) {
  const [selectedAluno, setSelectedAluno] = useState(null);

  const [formData, setFormData] = useState({
    id_professor: undefined,
    id_aluno: undefined,
    data: "",
    valor: undefined,
    description: "",
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

  const handleSubmit = (e) => {
    e.preventDefault();
    // Faça algo com os dados do formulário, por exemplo, envie para o servidor
    console.log("Dados do formulário submetidos: ", formData);
    onClose(); // Fecha o modal após o envio
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
                onChange={handleFormChange}
                disabled
              />
              <TextField
                name="id_aluno"
                label="ID do Aluno"
                variant="outlined"
                fullWidth
                value={formData.id_aluno}
                onChange={handleFormChange}
                disabled
              />
              <TextField
                name="data"
                label=""
                type="date"
                variant="outlined"
                fullWidth
                value={formData.data}
                onChange={handleFormChange}
                sx={{ width: "100%" }}
              />
              <TextField
                name="valor"
                label="Valor"
                type="number"
                variant="outlined"
                fullWidth
                value={formData.valor}
                onChange={handleFormChange}
              />
              <TextField
                name="description"
                label="Descrição"
                variant="outlined"
                fullWidth
                value={formData.description}
                onChange={handleFormChange}
              />
              <Box sx={buttonStyle}>
                <Button type="submit" variant="contained">
                  Enviar
                </Button>
              </Box>
            </form>
          </Box>
        </Modal>
      </div>
    </ThemeProvider>
  );
}
