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
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import GenericTable from "../../components/generic-table/generic-table.component";
import baseUrlGetAll from "../../services/professorService/getall";

const PortalProfessorPage = () => {
  const [getAllAlunosbyIdProfessor, setListaDeAlunos] = useState(null);

  const [professor, setprofessor] = useState(null);
  const nav = useNavigate();

  const handleLogOut = () => {
    nav("/");
    localStorage.clear();
  };

  useEffect(() => {
    const professor = JSON.parse(localStorage.getItem("user"));
    setprofessor(professor);
  }, []);

  useEffect(() => {
    const url = baseUrlGetAll + professor.cpf;

    axios
      .get(url)
      .then((response) => {
        const data = response.data;
        setListaDeAlunos(data); // Atualize o estado com os dados recebidos
      })
      .catch((error) => {
        console.error("Erro na solicitação GET:", error);
      });
  }, [getAllAlunosbyIdProfessor]);

  const headersListadealunos = ["cpf", "nome", "curso", "realizar depósito"];

  const dataFormatted = getAllAlunosbyIdProfessor.map((aluno) => ({
    cpf: aluno.cpf,
    nome: aluno.nome,
    curso: aluno.curso,
    realizarDeposito: (
      <Button variant="outlined" onClick={() => handleDeposito(aluno)}>
        Realizar Depósito
      </Button>
    ),
  }));

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
                    CPF: {professor.cpf}
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
              <Typography>Moedas recebidas</Typography>
              <GenericTable headers={headersTransacao} data={dataTransacao} />
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
              <Typography>Moedas distribuídas</Typography>
              <GenericTable
                headers={headersTransacao}
                data={getAllAlunosbyIdProfessor}
              />
            </Box>
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
            <Typography>Lista de alunos disponíveis</Typography>
            <GenericTable headers={headersListadealunos} data={dataFormatted} />
          </Box>
        </Grid>
        {/* Este código deve ser substituido por um componente */}
      </Grid>
    </Box>
  );
};

export default PortalProfessorPage;
