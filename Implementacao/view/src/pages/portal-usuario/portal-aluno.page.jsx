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
import MediaCard from "../../components/card-aluno/MediaCard";
import GenericTable from "../../components/generic-table/generic-table.component";
import LoadingComponent from "../../components/loading/loading.component";
import baseUrl from "../../configs/config";

const PortalAlunoPage = () => {
  const [loading, setLoading] = useState(true);
  const [alunoData, setAlunoData] = useState(null);
  const [headersTransacao, setHeadersTransacao] = useState([]);
  const [vantagemData, setVantagemData] = useState(null);
  const [dataTransacao, setDataTransacao] = useState([
    {
      origem: "Professor A",
      valor: 100,
      data: "2023-10-22T10:30:00Z",
    },
    {
      origem: "Professor B",
      valor: 200,
      data: "2023-10-21T14:45:00Z",
    },
    {
      origem: "Professor C",
      valor: 50,
      data: "2023-10-20T16:15:00Z",
    },
  ]);
  const nav = useNavigate();

  const handleLogOut = () => {
    nav("/");
    localStorage.clear();
  };

  useEffect(() => {
    const aluno = JSON.parse(localStorage.getItem("user"));
    setAlunoData(aluno);
    getExtratoDepositos(aluno.id);
    setLoading(false);
  }, []);

  useEffect(() => {
    async function fetchVantagem() {
      try {
        const res = await fetch(`${baseUrl}/vantagem/getAll`);

        if (!res.ok) {
          throw new Error(`Erro ao obter dados da vantagem: ${res.statusText}`);
        }

        const json = await res.json();
        setVantagemData(json);
        console.log(json);
      } catch (error) {
        console.error("Erro na solicitação:", error);
      }
    }
    fetchVantagem();
  }, []);

  const getExtratoDepositos = async (id) => {
    const { data } = await axios.get(
      `${baseUrl}/transacao/retornaTodosDepositos/${id}`
    );
    const headersKeys = Object.keys(data[0]);
    setHeadersTransacao(headersKeys);

    const finalData = data.map((obj) => {
      return {
        id: obj.id,
        aluno: obj.aluno.nome,
        professor: obj.professor.nome,
        description: obj.description,
        valor: obj.valor,
        data: `${obj.data[2]}/${obj.data[1]}/${obj.data[0]}`,
      };
    });

    setDataTransacao(finalData);
  };

  const updateAluno = async () => {
    const { data } = await axios.get(`${baseUrl}/aluno/${alunoData.id}`);
    setAlunoData(data);
    localStorage.setItem("user", JSON.stringify(data));
  };

  if (loading) {
    return <LoadingComponent />;
  }

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

              <Button fullWidth onClick={updateAluno}>
                Atualizar dados do aluno
              </Button>
            </Card>

            <Box
              sx={{
                maxHeight: "100%",
                minHeight: "300px",
                borderRadius: "10px",
                boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
                padding: 2,
                overflowY: "auto",
              }}
            >
              <Typography>Extrato de depósitos de moedas</Typography>
              <GenericTable headers={headersTransacao} data={dataTransacao} />
            </Box>

            <Box
              sx={{
                maxHeight: "100%",
                minHeight: "300px",
                borderRadius: "10px",
                boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)",
                padding: 2,
                overflowY: "auto",
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
              overflowY: "auto",
            }}
          >
            <Typography>
              Lista de vantagens para os alunos escolherem
            </Typography>
            <div
              style={{
                display: "flex",
                gap: "16px",
                flexWrap: "wrap",
                maxHeight: "800px",
                overflowY: "auto",
              }}
            >
              {vantagemData &&
                vantagemData.map((v, index) => (
                  <MediaCard key={index} vantagem={v} />
                ))}
            </div>
          </Box>
        </Grid>
        {/* Este código deve ser substituido por um componente */}
      </Grid>
    </Box>
  );
};

export default PortalAlunoPage;
