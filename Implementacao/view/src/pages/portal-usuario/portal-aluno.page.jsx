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

const PortalAlunoPage = () => {
  const [alunoData, setAlunoData] = useState(null);

  useEffect(() => {
    const aluno = JSON.parse(localStorage.getItem("user"));
    setAlunoData(aluno);
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
        <Typography variant="h4">Portal do Aluno</Typography>
      </Container>
      <Container sx={{ display: "flex", justifyContent: "end" }}>
        <Button
          variant="text"
          startIcon={<LogoutIcon />}
          size="large"
          color="inherit"
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
                  <Icon component={FingerprintIcon} sx={{ mr: 1 }} color="primary"/>
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
                  <Icon component={SavingsIcon} sx={{ mr: 1 }} color="primary"/>
                  <Typography variant="body2">
                    Saldo: {alunoData.saldo}
                  </Typography>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        )}
      </Grid>
    </Box>
  );
};

export default PortalAlunoPage;
