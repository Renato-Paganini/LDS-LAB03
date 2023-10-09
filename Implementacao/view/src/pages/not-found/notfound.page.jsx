import React from "react";
import { Link } from "react-router-dom";
import { Typography, Button, Container, Box } from "@mui/material";

const NotFoundPage = () => {
  return (
    <Container>
      <Box textAlign="center" my={8}>
        <Typography variant="h1" component="h1" gutterBottom>
          404 - Página não encontrada
        </Typography>
        <Typography variant="h4" component="p" paragraph>
          A página que você está procurando não foi encontrada.
        </Typography>
        <Button variant="contained" color="primary" component={Link} to="/">
          Voltar para a página inicial
        </Button>
      </Box>
    </Container>
  );
};

export default NotFoundPage;
