import FingerprintIcon from "@mui/icons-material/Fingerprint";
import LogoutIcon from "@mui/icons-material/Logout";
import PersonIcon from "@mui/icons-material/Person";
import SavingsIcon from "@mui/icons-material/Savings";
import SchoolIcon from "@mui/icons-material/School";
import {
  Box,
  Button,
  Card,
  CardContent,
  Container,
  Grid,
  Icon,
  Typography,
} from "@mui/material";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import GenericTable from "../../components/generic-table/generic-table.component";
import BasicModal from "../../components/modal-deposito/modal-deposito.component";
import BasicModalAtualizarProfessor from "../../components/modal-editar-professor/modal-editar.component";
import baseUrl from "../../configs/config";

const PortalProfessorPage = () => {
  const [getAllAlunosbyIdProfessor, setListaDeAlunos] = useState([]);
  const [moedasDistribuidas, setMoedasDistribuidas] = useState([]);
  const [moedasRecebidas, setMoedasRecebidas] = useState([]);

  const [professor, setprofessor] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [modalOpenAtualizarProfessor, setModalOpenAtualizarProfessor] =
    useState(false);

  const [objDeposito, setObjDeposito] = useState({
    idProfessor: "",
    idAluno: "",
    nomeAluno: "",
  });

  const [objetoAtualizarProfessor, setAtualizarProfessor] = useState({
    idProfessor: "",
    nome: "",
    senha: "",
    email: "",
    departamento: "",
  });

  function formatarData(date) {
    const year = date.substring(0, 4);
    const month = date.substring(5, 7);
    const day = date.substring(8, 11);
    return `${day}/${month}/${year}`;
  }

  const nav = useNavigate();

  const handleLogOut = () => {
    nav("/");
    localStorage.clear();
  };

  const headerListaAlunos = [
    "id",
    "nome",
    "saldo",
    "curso",
    "instituicao",
    "depositar",
  ];

  const moedasDistribuidasHeader = [
    "nome",
    "valor",
    "description",
    "data",
    "curso",
  ];

  useEffect(() => {
    const professor = JSON.parse(localStorage.getItem("user"));
    if (professor) {
      setprofessor(professor);
    }

    const fetchData = async () => {
      try {
        const response = await axios.get(
          `${baseUrl}/aluno/getAll/${professor.cpf}`
        );
        setListaDeAlunos(
          response.data.map((aluno) => ({
            id: aluno.id,
            nome: aluno.nome,
            saldo: aluno.saldo,
            curso: aluno.curso,
            instituicao: aluno.instituicao.nome,
            depositar: (
              <Button
                variant="contained"
                color="primary"
                onClick={() => handleDeposit(aluno)}
              >
                Depositar
              </Button>
            ),
          }))
        );

        const responseMoedasDistribuidas = await axios.get(
          `${baseUrl}/transacao/retornaTodosDepositos/professor/${professor.id}`
        );
        const moedasDistribuidasData = responseMoedasDistribuidas.data.map(
          (item) => ({
            nome: item.aluno.nome,
            curso: item.aluno.curso,
            valor: item.valor,
            data: formatarData(item.data.toString()),
            description: item.description,
          })
        );

        setMoedasDistribuidas(moedasDistribuidasData);
      } catch (error) {
        console.error("Erro na solicitação GET:", error);
      }
    };
    fetchData();
  }, []);

  const handleDeposit = (aluno) => {
    const professor = JSON.parse(localStorage.getItem("user"));
    if (professor) {
      setprofessor(professor);
    }
    if (professor) {
      setObjDeposito({
        idAluno: aluno.id,
        nomeAluno: aluno.nome,
        idProfessor: professor.id,
      });
      setModalOpen(true);
    } else {
      console.error("Professor não está definido");
    }
  };

  const handleAtualizarProfessor = (professorAtualizar) => {
    if (professorAtualizar) {
      setAtualizarProfessor({
        idProfessor: professorAtualizar.id,
        nome: professorAtualizar.nome,
        senha: professorAtualizar.senha,
        email: professorAtualizar.email,
        departamento: professorAtualizar.departamento,
      });
      setModalOpenAtualizarProfessor(true);
    } else {
      console.error("Professor não está definido");
    }
  };

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
        <Typography variant="h4">Portal do Professor</Typography>
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
        {professor && (
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
                    {professor.nome}
                  </Typography>
                </Box>

                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    marginBottom: 1,
                  }}
                >
                  <Icon component={SchoolIcon} sx={{ mr: 1 }} color="primary" />
                  <Typography
                    sx={{ fontSize: "1rem" }}
                    color="text.secondary"
                    gutterBottom
                  >
                    {professor.instituicao.nome}
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
                    System ID: {professor.id}
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
                    Saldo: {professor.saldo}
                  </Typography>
                </Box>
              </CardContent>
              <Button
                fullWidth
                onClick={() => handleAtualizarProfessor(professor)}
              >
                Atualizar os dados
              </Button>
            </Card>

            <Box
              sx={{
                maxHeight: "100%",
                minHeight: "300px",
                marginTop: "10px",
                boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
                padding: 2,
                overflowY: "auto",
              }}
            >
              {moedasRecebidas.length > 0 ? (
                <Box>
                  {" "}
                  <Typography>Moedas recebidas</Typography>
                  <GenericTable
                    headers={headersTransacao}
                    data={dataTransacao}
                  />
                </Box>
              ) : (
                <Typography>Sem extrato</Typography>
              )}
            </Box>
            <Box
              sx={{
                maxHeight: "100%",
                minHeight: "300px",
                marginTop: "10px",
                boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
                padding: 2,
                overflowY: "auto",
              }}
            >
              {moedasDistribuidas.length > 0 ? (
                <Box>
                  <Typography>Moedas distribuídas</Typography>
                  <GenericTable
                    headers={moedasDistribuidasHeader}
                    data={moedasDistribuidas}
                  />
                </Box>
              ) : (
                <Box>
                  <Typography>Sem moedas distribuídas</Typography>
                </Box>
              )}
            </Box>
          </Grid>
        )}
        <Grid item xs={8}>
          {getAllAlunosbyIdProfessor.length > 0 ? (
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
              <Typography>Alunos disponíveis</Typography>
              <GenericTable
                headers={headerListaAlunos}
                data={getAllAlunosbyIdProfessor}
              />
            </Box>
          ) : (
            <Box>
              <Typography>Lista de alunos não disponíveis</Typography>
            </Box>
          )}
        </Grid>
      </Grid>
      <BasicModal
        open={modalOpen}
        onClose={() => setModalOpen(false)}
        objDeposito={objDeposito}
      />
      <BasicModalAtualizarProfessor
        open={modalOpenAtualizarProfessor}
        onClose={() => setModalOpenAtualizarProfessor(false)}
        objAtualizarProfessor={objetoAtualizarProfessor}
      />
    </Box>
  );
};

export default PortalProfessorPage;
