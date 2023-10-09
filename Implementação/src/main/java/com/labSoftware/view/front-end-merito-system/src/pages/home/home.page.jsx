import { Box, Grid } from "@mui/material";
import "./home.page.css";

const HomePage = () => {
  return (
    <Box>
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <Box sx={{ textAlign: "center" }}>
            <p>esqueda</p>
          </Box>
        </Grid>
        <Grid item xs={6}>
          <Box
            sx={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              backgroundImage:
                'url("https://images.pexels.com/photos/159775/library-la-trobe-study-students-159775.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")',
              backgroundSize: "cover",
              backgroundPosition: "center",
              backgroundRepeat: "no-repeat",
              minHeight: "100vh",
              transition: "transform 0.3s ease",
              "&:hover": {
                transform: "scale(1.1)",
              },
            }}
          >
            <p>Direita</p>
          </Box>
        </Grid>
      </Grid>
    </Box>
  );
};

export default HomePage;
