import React, { useEffect, useState } from "react";
import LogoutIcon from "@mui/icons-material/Logout";
import PersonIcon from "@mui/icons-material/Person";
import HighlightOffIcon from "@mui/icons-material/HighlightOff";
import FingerprintIcon from "@mui/icons-material/Fingerprint";
import SavingsIcon from "@mui/icons-material/Savings";
import BusinessIcon from "@mui/icons-material/Business";
import { json, useNavigate } from "react-router-dom";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import DeleteIcon from "@mui/icons-material/Delete";
import ModeEditIcon from "@mui/icons-material/ModeEdit";

import {
  Box,
  Button,
  Typography,
  Modal,
  TextField,
  Container,
  Grid,
  Card,
  CardContent,
  Icon,
  IconButton,
} from "@mui/material";
import { brown, red } from "@mui/material/colors";

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
const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: brown[400],
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const PortalEmpresaPage = () => {
  const [loading, setLoading] = useState(true);
  const [empresaData, setEmpresaData] = useState(null);
  const [vantagemData, setVantagemData] = useState(null);
  const [open, setOpen] = React.useState(false);
  const [openEdit, setOpenEdit] = React.useState(false);
  const [forceUpdate, setForceUpdate] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleCloseEdit = () => setOpenEdit(false);
  const nav = useNavigate();
  const [formValuesEdit, setFormValuesEdit] = useState({
    id: "",
    nome: "",
    descricao: "",
    valor: 0,
  });
  function handleOpenEdit(id) {
    const vantagem = vantagemData.find((lista) => lista.id === id);
    setFormValuesEdit({
      id: vantagem.id,
      nome: vantagem.nome,
      descricao: vantagem.descricao,
      valor: vantagem.valor,
    });
    console.log(formValuesEdit);
    setOpenEdit(true);
  }
  const userString = localStorage.getItem("user");
  const userObject = JSON.parse(userString);
  const userId = userObject.id;
  const [formValues, setFormValues] = useState({
    id_empresa: userId,
    nome: "",
    descricao: "",
    valor: 0,
  });
  useEffect(() => {
    async function fetchVantagem() {
      try {
        const res = await fetch(
          `http://localhost:7070/vantagem/getByEmpresaId/${userId}`
        );

        if (!res.ok) {
          throw new Error(`Erro ao obter dados da vantagem: ${res.statusText}`);
        }

        const json = await res.json();
        setVantagemData(json);
        console.log(json);
      } catch (error) {
        console.error("Erro na solicitação:", error);
      }
    }
    fetchVantagem();
  }, [userId, forceUpdate]);
  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setFormValues({ ...formValues, [id]: value });
  };
  const handleInputChangeEdit = (e) => {
    const { id, value } = e.target;
    setFormValuesEdit({ ...formValuesEdit, [id]: value });
  };

  function handleCreate() {
    fetch("http://localhost:7070/vantagem/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formValues),
    })
      .then((response) => {
        if (response.ok) {
          alert("Vantagem adicionada com sucesso.");
          setForceUpdate(!forceUpdate);
        } else {
          alert("Ocorreu um problema ao adicionar a vantagem.");
        }
      })
      .catch((error) => {
        alert("Houve um erro na solicitação:", error);
      });
  }
  function handleEdit(id) {
    fetch(`http://localhost:7070/vantagem/update/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formValuesEdit),
    })
      .then((response) => {
        if (response.ok) {
          alert("Vantagem atualizada com sucesso.");
          setForceUpdate(!forceUpdate);
        } else {
          alert("Ocorreu um problema ao adicionar a vantagem.");
        }
      })
      .catch((error) => {
        alert("Houve um erro na solicitação:", error);
      });
  }

  function handleDelete(id) {
    fetch(`http://localhost:7070/vantagem/delete/${id}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          alert(`Vantagem com ID ${id} foi deletada.`);
          setForceUpdate(!forceUpdate);
        } else {
          alert("Ocorreu um problema ao deletar a vantagem.");
        }
      })
      .catch((error) => {
        console.error("Houve um erro na solicitação:", error);
      });
  }
  const handleLogOut = () => {
    nav("/");
    localStorage.clear();
  };
  useEffect(() => {
    const empresa = JSON.parse(localStorage.getItem("user"));
    setEmpresaData(empresa);
  }, []);

  return (
    <Box sx={{ margin: 5 }}>
      <Container
        sx={{
          width: "100%",
          minHeight: "50px",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Typography variant="h4">Portal da Empresa</Typography>
      </Container>

      <Container sx={{ display: "flex", justifyContent: "end", mb: 3 }}>
        <Button
          variant="text"
          startIcon={<LogoutIcon />}
          size="large"
          color="inherit"
          onClick={handleLogOut}
        >
          Sair
        </Button>
      </Container>

      <Grid container spacing={2}>
        {empresaData && (
          <Grid item xs={4}>
            <Card>
              <CardContent>
                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    marginBottom: 1,
                  }}
                >
                  <Icon component={PersonIcon} sx={{ mr: 1 }} color="primary" />
                  <Typography sx={{ fontSize: "1rem" }} color="text.primary">
                    {empresaData.nome}
                  </Typography>
                </Box>

                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    marginBottom: 1,
                  }}
                >
                  <Icon
                    component={BusinessIcon}
                    sx={{ mr: 1 }}
                    color="primary"
                  />
                  <Typography
                    sx={{ fontSize: "1rem" }}
                    color="text.secondary"
                    gutterBottom
                  >
                    {empresaData.cnpj}
                  </Typography>
                </Box>

                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    marginBottom: 1,
                  }}
                >
                  <Icon
                    component={FingerprintIcon}
                    sx={{ mr: 1 }}
                    color="primary"
                  />
                  <Typography color="text.secondary">
                    System ID:{" "}
                    <input
                      id="empresa_id"
                      type="hidden"
                      value={empresaData.id}
                    />
                  </Typography>
                </Box>

                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    marginBottom: 1,
                  }}
                >
                  <Icon
                    component={SavingsIcon}
                    sx={{ mr: 1 }}
                    color="primary"
                  />
                  <Typography variant="body2">
                    Saldo: {empresaData.saldo}
                  </Typography>
                </Box>
              </CardContent>

              <Button fullWidth onClick={handleOpen}>
                Criar Nova vantagem
              </Button>
            </Card>
          </Grid>
        )}
        {/* Este código deve ser substituido por um componente */}
        <Grid item xs={8}>
          <Box
            sx={{
              maxHeight: "100%",
              minHeight: "800px",
              borderRadius: "10px",
              boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
              padding: 2,
              overflowY: "auto",
            }}
          >
            <Typography>Lista de vantagens criadas pela empresa</Typography>
            <TableContainer component={Paper}>
              <Table sx={{ minWidth: 700 }} aria-label="customized table">
                <TableHead>
                  <TableRow>
                    <StyledTableCell>Nome</StyledTableCell>
                    <StyledTableCell>Descrição</StyledTableCell>
                    <StyledTableCell>Valor</StyledTableCell>
                    <StyledTableCell align="right">
                      Editar || Deletar
                    </StyledTableCell>
                  </TableRow>
                </TableHead>
                {vantagemData && (
                  <TableBody>
                    {vantagemData.map((row) => (
                      <StyledTableRow key={row.id}>
                        <StyledTableCell component="th">
                          {row.nome}
                        </StyledTableCell>
                        <StyledTableCell>{row.descricao}</StyledTableCell>
                        <StyledTableCell>{row.valor}</StyledTableCell>
                        <StyledTableCell align="right">
                          <IconButton
                            color="primary"
                            onClick={() => handleOpenEdit(row.id)}
                          >
                            <ModeEditIcon sx={{ marginLeft: "auto" }} />
                          </IconButton>
                          <IconButton
                            color="primary"
                            onClick={() => handleDelete(row.id)}
                          >
                            <DeleteIcon sx={{ marginLeft: "auto" }} />
                          </IconButton>
                        </StyledTableCell>
                      </StyledTableRow>
                    ))}
                  </TableBody>
                )}
              </Table>
            </TableContainer>
          </Box>
        </Grid>
        {/* Este código deve ser substituido por um componente */}
      </Grid>

      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <IconButton color="primary" onClick={handleClose}>
            <HighlightOffIcon sx={{ marginLeft: "auto" }} />
          </IconButton>
          <Typography id="modal-modal-title" variant="h4" component="h2">
            Criar Vantagem
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            <TextField
              id="nome"
              label="Nome"
              variant="outlined"
              sx={{ mb: 2 }}
              onChange={handleInputChange}
            />
            <TextField
              id="descricao"
              label="descricao"
              variant="outlined"
              sx={{ mb: 2 }}
              onChange={handleInputChange}
            />
            <TextField
              id="valor"
              label="valor"
              variant="outlined"
              sx={{ mb: 2 }}
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              onChange={handleInputChange}
            />
          </Typography>
          <Button onClick={handleCreate} variant="contained">
            Criar
          </Button>
        </Box>
      </Modal>

      <Modal
        open={openEdit}
        onClose={handleCloseEdit}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <IconButton color="primary" onClick={handleCloseEdit}>
            <HighlightOffIcon sx={{ marginLeft: "auto" }} />
          </IconButton>
          <Typography id="modal-modal-title" variant="h4" component="h2">
            Editar Vantagem
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            <TextField
              id="id"
              label="id"
              variant="outlined"
              value={formValuesEdit.id}
              sx={{ mb: 2 }}
              onChange={handleInputChangeEdit}
              disabled
            />
            <TextField
              id="nome"
              label="Nome"
              variant="outlined"
              value={formValuesEdit.nome}
              sx={{ mb: 2 }}
              onChange={handleInputChangeEdit}
            />
            <TextField
              id="descricao"
              label="descricao"
              variant="outlined"
              value={formValuesEdit.descricao}
              sx={{ mb: 2 }}
              onChange={handleInputChangeEdit}
            />
            <TextField
              id="valor"
              label="valor"
              variant="outlined"
              value={formValuesEdit.valor}
              sx={{ mb: 2 }}
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              onChange={handleInputChangeEdit}
            />
          </Typography>
          <Button
            onClick={() => handleEdit(formValuesEdit.id)}
            variant="contained"
          >
            Editar
          </Button>
        </Box>
      </Modal>
    </Box>
  );
};

export default PortalEmpresaPage;
