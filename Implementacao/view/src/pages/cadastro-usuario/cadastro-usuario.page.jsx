import { Typography, Box, TextField, MenuItem } from "@mui/material";
import CadastroAlunoComponent from "../../components/cadastro-usuario/cadastro-aluno.component";
import { useState } from "react";

const loginTypes = ["Aluno", "Empresa"];
const CadastroUsuarioPage = () => {
  const [type, setType] = useState("");

  const onChange = () => {};
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        backgroundImage:
          'url("https://images.pexels.com/photos/159490/yale-university-landscape-universities-schools-159490.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")',
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundRepeat: "no-repeat",
        backdropFilter: "blur(8px)",
      }}
    >
      <Box
        sx={{
          minHeight: "500px",
          width: "600px",
          outline: "thin solid #5c0c06",
          padding: 2,
          borderRadius: 2,
          backgroundColor: "white",
          opacity: "0.9",
        }}
      >
        <Typography>Cadastro</Typography>
        <TextField
          margin="normal"
          required
          fullWidth
          name="type"
          label="Tipo de cadastro"
          select
          defaultValue=""
          helperText="Por favor, selecione seu tipo de cadastro"
        >
          {loginTypes.map((option) => (
            <MenuItem key={option} value={option}>
              {option}
            </MenuItem>
          ))}
        </TextField>

        <CadastroAlunoComponent />
      </Box>
    </Box>
  );
};

export default CadastroUsuarioPage;
