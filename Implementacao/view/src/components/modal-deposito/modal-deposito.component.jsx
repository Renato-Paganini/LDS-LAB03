import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import axios from "axios";
import * as React from "react";
import { useEffect, useState } from "react";
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

export default function BasicModal({ open, onClose, objDeposito }) {
  const currentDate = new Date().toISOString().split("T")[0]; // Obtém a data atual no formato YYYY-MM-DD

  const [formData, setFormData] = useState({
    id_professor: objDeposito ? objDeposito.idProfessor : "",
    id_aluno: objDeposito ? objDeposito.idAluno : "",
    nome_aluno: objDeposito ? objDeposito.nomeAluno : "",
    data: currentDate, // Define a data atual
    valor: "", // Defina um valor inicial apropriado
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

  useEffect(() => {
    setFormData({
      id_professor: objDeposito.idProfessor,
      id_aluno: objDeposito.idAluno,
      nome_aluno: objDeposito.nomeAluno,
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
      id_aluno: formData.id_aluno,
      nome_aluno: formData.nome_aluno,
      data: formData.data,
      valor: formData.valor,
      description: formData.description,
    };

    // Realize a solicitação POST para a URL desejada
    axios
      .post(`${baseUrl}/transacao/realizaDeposito`, data)
      .then((response) => {
        console.log("Resposta da solicitação POST:", response.data);
        // Lide com a resposta, se necessário
        atualizarSaldoProfessor();
        window.location.reload();
      })
      .catch((error) => {
        console.error("Erro na solicitação POST:", error);
        // Lide com o erro de acordo com os requisitos do seu aplicativo
      });

    onClose();
  };

  const atualizarSaldoProfessor = async () => {
    const profId = JSON.parse(localStorage.getItem("user")).id;
    const { data } = await axios.get(`${baseUrl}/professor/${profId}`);
    localStorage.setItem("user", JSON.stringify(data));

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
                name="id_aluno"
                label="ID do Aluno"
                variant="outlined"
                fullWidth
                value={formData.id_aluno}
                disabled
              />
              <TextField
                name="nome_aluno"
                label="Nome do Aluno"
                variant="outlined"
                fullWidth
                value={formData.nome_aluno}
                disabled
              />
              <TextField
                name="data"
                label=""
                type="date"
                variant="outlined"
                fullWidth
                value={formData.data}
                InputProps={{
                  readOnly: true,
                }}
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
                type="text"
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
