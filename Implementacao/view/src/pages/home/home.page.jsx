import React from "react";
import { Box, Grid, Button, Typography } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person"; // Importe o ícone PersonIcon
import "./home.page.css";
import { useNavigate } from "react-router-dom";

const HomePage = () => {
  const nav = useNavigate();
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
      }}
    >
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <Box
            className="fadeInAnimation"
            sx={{
              textAlign: "center",
              padding: "2rem",
              display: "flex",
              flexDirection: "column",
              justifyContent: "center",
              alignItems: "center",
              height: "100%", // Define a altura como 100% para centralizar verticalmente
            }}
          >
            <Typography variant="h4" gutterBottom>
              Bem-vindo ao Sistema de Mérito
            </Typography>
            <Typography variant="body1">
              Explore nossos recursos e descubra uma nova maneira de reconhecer
              o mérito.
            </Typography>
            <Button
              variant="contained"
              color="primary"
              startIcon={<PersonIcon />}
              size="large"
              sx={{ marginTop: "2rem" }}
              onClick={() => {
                nav("/auth");
              }}
            >
              Login
            </Button>
          </Box>
        </Grid>
        <Grid item xs={6}>
          <Box
            className="fadeInAnimation"
            sx={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "center",
              backgroundImage:
                'url("https://images.pexels.com/photos/159775/library-la-trobe-study-students-159775.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")',
              backgroundSize: "cover",
              backgroundPosition: "center",
              backgroundRepeat: "no-repeat",
              minHeight: "100vh",
              color: "#fff",
              textAlign: "center",
              padding: "2rem",
            }}
          >
            <Box
              sx={{
                backgroundColor: "#5c0c06",
                padding: 2,
                borderRadius: 2,
                opacity: "0.8",
              }}
            >
              <Typography variant="h3" gutterBottom>
                Descubra o Mérito
              </Typography>
              <Typography variant="body1">
                Reconhecendo e recompensando o mérito em todas as áreas.
              </Typography>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default HomePage;
