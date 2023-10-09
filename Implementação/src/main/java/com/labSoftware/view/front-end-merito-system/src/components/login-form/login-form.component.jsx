import { useEffect, useState } from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Paper from "@mui/material/Paper";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Link } from "react-router-dom";

import AccountBalanceIcon from "@mui/icons-material/AccountBalance";
import Typography from "@mui/material/Typography";
import axios from "axios";

const initialForm = {
  login: "",
  password: "",
};

const LoginForm = () => {
  const [form, setForm] = useState(initialForm);

  const handleSubmit = (event) => {
    event.preventDefault();

    const isCPF = form.login.length == 11 ? true : false;

    if (CPF) {
      try {
        const res = axios.get("http://localhost:8099/customer");
        console.log(res);
      } catch (error) {
        console.log(error);
      }
    } else {
      try {
        const res = axios.get("http://localhost:8099/agent");
        console.log(res);
      } catch (error) {
        console.log(error);
      }
    }
  };

  const handleChangeForm = (event) => {
    const { name, value } = event.target;
    setForm({ ...form, [name]: value });
  };

  // useEffect(() => {
  //   console.log(form);
  // }, [form]);

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
                helperText="Only numbers"
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
                  <Link to={"recover"} style={linkStyle}>
                    Redefinir senha
                  </Link>
                </Grid>
                <Grid item>
                  <Link to={"register"} style={linkStyle}>
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
