import NotFoundPage from "../pages/not-found/notfound.page";
import { Route,Routes } from "react-router-dom";
import HomePage from "../pages/home/home.page";
import LoginPage from "../pages/login/login.page";

const AllRoutes = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<HomePage/>}></Route>
        <Route path="auth" element={<LoginPage/>}></Route>
        <Route path="*" element={<NotFoundPage />}></Route>
      </Routes>
    </div>
  );
};

export default AllRoutes;
