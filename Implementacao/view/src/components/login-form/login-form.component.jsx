import AccountBalanceIcon from "@mui/icons-material/AccountBalance";
import {
  Avatar,
  Box,
  Button,
  CssBaseline,
  Grid,
  MenuItem,
  Paper,
  TextField,
  Typography,
} from "@mui/material";
import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import baseUrl from "../../configs/config";

const initialForm = {
  login: "",
  password: "",
  type: "",
};

const loginTypes = ["Professor", "Aluno", "Empresa"];

const LoginForm = () => {
  const nav = useNavigate();
  const [form, setForm] = useState(initialForm);

  const handleSubmit = async (event) => {
    event.preventDefault();

    let url = "";
    let finalForm = {};
    let redirectPage = "";

    switch (form.type) {
      case "Professor":
        url = `${baseUrl}/professor/auth`;
        finalForm = {
          cpf: form.login,
          senha: form.password,
        };
        redirectPage = "/portalProfessor";
        break;
      case "Aluno":
        url = `${baseUrl}/aluno/auth`;
        finalForm = {
          cpf: form.login,
          senha: form.password,
        };
        redirectPage = "/portalAluno";
        break;
      case "Empresa":
        url = `${baseUrl}/empresa/auth`;
        finalForm = {
          cnpj: form.login,
          senha: form.password,
        };
        redirectPage = "/portalEmpresa";
        break;

      default:
        alert("Houve um erro com a definição da URL");
        break;
    }

    if (url !== "") {
      try {
        const { data } = await axios.post(url, finalForm);
        localStorage.setItem("user", JSON.stringify(data));
        nav(redirectPage);
      } catch (error) {
        alert("Houve um erro ao fazer login, tente mais tarde: " + error);
      }
    }
  };

  const handleChangeForm = (event) => {
    const { name, value } = event.target;
    setForm({ ...form, [name]: value });
  };

  const linkStyle = {
    color: "#7d5113",
  };

  return (
    <Box>
      <Grid container component="main" sx={{ height: "100vh" }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage:
              "url(https://images.pexels.com/photos/1370296/pexels-photo-1370296.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1)",
            backgroundRepeat: "no-repeat",
            backgroundColor: (t) =>
              t.palette.mode === "light"
                ? t.palette.grey[50]
                : t.palette.grey[900],
            backgroundSize: "cover",
            backgroundPosition: "center",
          }}
        />
        <Grid item xs={12} sm={8} md={5} component={Paper} elevation={6} square>
          <Box
            sx={{
              my: 8,
              mx: 4,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "secondary.dark" }}>
              <AccountBalanceIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
              MÉRITO SYSTEM
            </Typography>
            <Box
              component="form"
              noValidate
              onSubmit={handleSubmit}
              sx={{ mt: 1 }}
            >
              <TextField
                margin="normal"
                required
                fullWidth
                id="login"
                label="CNPJ/CPF"
                name="login"
                autoComplete="login"
                helperText="Apenas números"
                autoFocus
                onChange={handleChangeForm}
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                onChange={handleChangeForm}
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="type"
                label="Tipo de login"
                select
                defaultValue=""
                helperText="Por favor, selecione seu tipo de login"
                onChange={handleChangeForm}
              >
                {loginTypes.map((option) => (
                  <MenuItem key={option} value={option}>
                    {option}
                  </MenuItem>
                ))}
              </TextField>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                ENTRAR
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link to={"/recover"} style={linkStyle}>
                    Redefinir senha
                  </Link>
                </Grid>
                <Grid item>
                  <Link to={"/register"} style={linkStyle}>
                    Não tem uma conta? Registrar!
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default LoginForm;
