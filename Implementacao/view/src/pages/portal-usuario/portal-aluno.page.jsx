import React, { useEffect, useState } from "react";
import {
  Box,
  Typography,
  Grid,
  Container,
  Button,
  Card,
  CardContent,
  Icon,
} from "@mui/material";
import LogoutIcon from "@mui/icons-material/Logout";
import PersonIcon from "@mui/icons-material/Person";
import SchoolIcon from "@mui/icons-material/School";
import FingerprintIcon from "@mui/icons-material/Fingerprint";
import SavingsIcon from "@mui/icons-material/Savings";
import { useNavigate } from "react-router-dom";
import GenericTable from "../../components/generic-table/generic-table.component";

const PortalAlunoPage = () => {
  const [alunoData, setAlunoData] = useState(null);
  const nav = useNavigate();

  const handleLogOut = () => {
    nav("/");
    localStorage.clear();
  };

  useEffect(() => {
    const aluno = JSON.parse(localStorage.getItem("user"));
    setAlunoData(aluno);
  }, []);

  const headersTransacao = ["origem", "valor", "data"]
  const dataTransacao = [
  {
    origem: "Professor A",
    valor: 100,
    data: "2023-10-22T10:30:00Z"
  },
  {
    origem: "Professor B",
    valor: 200,
    data: "2023-10-21T14:45:00Z"
  },
  {
    origem: "Professor C",
    valor: 50,
    data: "2023-10-20T16:15:00Z"
  }
]


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
        <Typography variant="h4">Portal do Aluno</Typography>
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
        {alunoData && (
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
                    {alunoData.nome}
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
                    {alunoData.instituicao.nome}
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
                    System ID: {alunoData.id}
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
                    Saldo: {alunoData.saldo}
                  </Typography>
                </Box>
              </CardContent>
            </Card>

            <Box
              sx={{
                maxHeight: "100%",
                minHeight: "300px",
                borderRadius: "10px",
                boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
                padding: 2,
                overflowY:"auto"
              }}
            >
              <Typography>Extrato de depósitos de moedas</Typography>
              <GenericTable headers={headersTransacao} data={dataTransacao}/>


            </Box>

            <Box
              sx={{
                maxHeight: "100%",
                minHeight: "300px",
                borderRadius: "10px",
                boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
                padding: 2,
                overflowY:"auto"
              }}
            >
              <Typography>Extrato de consumo de vantagens</Typography>
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
              overflowY:"auto"
            }}
          >
            <Typography>
              Lista de vantagens para os alunos escolherem
            </Typography>
          </Box>
        </Grid>
        {/* Este código deve ser substituido por um componente */}
      </Grid>
    </Box>
  );
};

export default PortalAlunoPage;
