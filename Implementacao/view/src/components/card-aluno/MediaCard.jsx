import HighlightOffIcon from "@mui/icons-material/HighlightOff";
import {
  Box,
  Button,
  Card,
  CardActions,
  CardContent,
  IconButton,
  Modal,
  TextField,
  Typography,
  CardMedia,
} from "@mui/material";
import React, { useState } from "react";
import baseUrl from "../../configs/config";
import LoadingComponent from "../loading/loading.component";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};
import axios from "axios";

const MediaCard = ({ vantagem }) => {
  const userString = localStorage.getItem("user");
  const userObject = JSON.parse(userString);
  const userId = userObject.id;
  const [open, setOpen] = useState(false);
  const getCurrentDate = () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = `${now.getMonth() + 1}`.padStart(2, "0");
    const day = `${now.getDate()}`.padStart(2, "0");
    return `${year}-${month}-${day}`;
  };
  const [loading, setLoading] = useState(false);

  const [formValues, setFormValues] = useState({
    id_vantagem: vantagem.id,
    data: getCurrentDate(),
    id_aluno: userId,
    description: vantagem.descricao,
  });
  const [selectedVantagemId, setSelectedVantagemId] = useState(null);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setLoading(false);
  };

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setFormValues((prevValues) => ({
      ...prevValues,
      [id]: value,
    }));
  };

  const handleCreate = () => {
    setLoading(true);
    fetch(`${baseUrl}/transacao/resgate`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formValues),
    })
      .then((response) => {
        if (response.ok) {
          alert("Vantagem resgatada com sucesso.");
          atualizarAuno();
        } else {
          alert("Ocorreu um problema ao resgatar a vantagem.");

          window.location.reload();
        }
      })
      .catch((error) => {
        alert("Houve um erro na solicitação:", error);
      });
    setFormValues({
      id: vantagem.id,
    });
    handleClose();
  };

  const atualizarAuno = async () => {
    const alunoId = JSON.parse(localStorage.getItem("user")).id;
    const { data } = await axios.get(`${baseUrl}/aluno/${alunoId}`);
    localStorage.setItem("user", JSON.stringify(data));
    window.location.reload();
  };

  const handleClick = () => {
    setSelectedVantagemId(vantagem.id);
    handleOpen();
  };

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        {loading ? (
          <LoadingComponent />
        ) : (
          <Box sx={style}>
            <IconButton color="primary" onClick={handleClose}>
              <HighlightOffIcon sx={{ marginLeft: "auto" }} />
            </IconButton>
            <Typography variant="h4" component="h2">
              Deseja realmente resgatar essa vantagem?
            </Typography>
            <TextField
              id="userId"
              label="UserID"
              variant="outlined"
              disabled
              sx={{ mb: 2 }}
              value={userId}
            />
            <TextField
              id="vantagemId"
              label="VantagemID"
              variant="outlined"
              disabled
              sx={{ mb: 2 }}
              value={selectedVantagemId}
            />
            <Button onClick={handleCreate} variant="contained">
              Resgatar
            </Button>
          </Box>
        )}
      </Modal>
      <Card sx={{ maxWidth: 250 }}>
        <CardMedia
          component="img"
          image={vantagem.imagem}
          alt={vantagem.nome}
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {vantagem.nome}
          </Typography>
          <Typography
            variant="body2"
            color="text.secondary"
            sx={{ display: "flex", flexWrap: "wrap" }}
          >
            Descrição: {vantagem.descricao}
          </Typography>
          <Typography variant="body3" color="text.secondary">
            Moedas: {vantagem.valor}
          </Typography>
        </CardContent>
        <CardActions>
          <Button onClick={handleClick} size="small">
            Resgatar
          </Button>
        </CardActions>
      </Card>
    </div>
  );
};

export default MediaCard;
