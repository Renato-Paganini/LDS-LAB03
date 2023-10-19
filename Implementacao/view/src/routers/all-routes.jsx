import { Route, Routes } from "react-router-dom";
import CadastroUsuarioPage from "../pages/cadastro-usuario/cadastro-usuario.page";
import ExtratoProfessor from "../pages/extrato-by-id-professor/extrato-by-id-professor.page";
import HomePage from "../pages/home/home.page";
import LoginPage from "../pages/login/login.page";
import NotFoundPage from "../pages/not-found/notfound.page";

const AllRoutes = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="auth" element={<LoginPage />}></Route>
        <Route path="register" element={<CadastroUsuarioPage />}></Route>
        <Route
          path="professor/extratoProfessor"
          element={<ExtratoProfessor />}
        ></Route>
        <Route path="*" element={<NotFoundPage />}></Route>
      </Routes>
    </div>
  );
};

export default AllRoutes;
