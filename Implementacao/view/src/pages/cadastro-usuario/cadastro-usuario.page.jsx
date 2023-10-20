import { Typography, Box, TextField, MenuItem } from "@mui/material";
import CadastroAlunoComponent from "../../components/cadastro-usuario/cadastro-aluno.component";
import CadastroEmpresaComponent from "../../components/cadastro-usuario/cadastro-empresa.component";
import { useEffect, useState } from "react";

const loginTypes = ["Aluno", "Empresa"];
const CadastroUsuarioPage = () => {
  const [type, setType] = useState("Aluno");

  const onChangeType = (event) => {
    event.preventDefault();
    const { value } = event.target;
    setType(value);
  };

  useEffect(() => {
    console.log(type);
  }, [type]);
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
          maxHeight: "800px",
          width: "600px",
          outline: "thin solid #5c0c06",
          padding: 2,
          borderRadius: 2,
          backgroundColor: "white",
          opacity: "0.9",
          overflowY: "auto",
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
          value={type}
          helperText="Por favor, selecione o tipo de cadastro"
          onChange={onChangeType}
        >
          {loginTypes.map((option) => (
            <MenuItem key={option} value={option}>
              {option}
            </MenuItem>
          ))}
        </TextField>
        {type === "Aluno" ? <CadastroAlunoComponent /> : <CadastroEmpresaComponent />}
      </Box>
    </Box>
  );
};

export default CadastroUsuarioPage;
