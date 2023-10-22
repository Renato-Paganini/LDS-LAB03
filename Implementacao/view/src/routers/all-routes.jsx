import { Route, Routes } from "react-router-dom";
import CadastroUsuarioPage from "../pages/cadastro-usuario/cadastro-usuario.page";
import ExtratoProfessor from "../pages/extrato-by-id-professor/extrato-by-id-professor.page";
import HomePage from "../pages/home/home.page";
import LoginPage from "../pages/login/login.page";
import NotFoundPage from "../pages/not-found/notfound.page";
import PortalAlunoPage from "../pages/portal-usuario/portal-aluno.page";
import PortalEmpresaPage from "../pages/portal-usuario/portal-empresa.page";
import PortalProfessorPage from "../pages/portal-usuario/portal-professor.page";


const AllRoutes = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="auth" element={<LoginPage />} />
        <Route path="register" element={<CadastroUsuarioPage />} />
        <Route path="extratoProfessor" element={<ExtratoProfessor/>}/>
        <Route path="portalAluno" element={<PortalAlunoPage />} />
        <Route path="portalEmpresa" element={<PortalEmpresaPage />} />
        <Route path="portalProfessor" element={<PortalProfessorPage />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </div>
  );
};

export default AllRoutes;
