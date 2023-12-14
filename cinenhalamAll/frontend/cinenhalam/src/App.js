import { Route, Routes } from "react-router-dom";
import Register from "./Pages/Register/Register";
import Login from "./Pages/Login/Login";
import FilmDetail from "./Pages/FilmDetail/FilmDetail";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/filmDetail" element={<FilmDetail />} />
      </Routes>
    </div>
  );
}

export default App;
