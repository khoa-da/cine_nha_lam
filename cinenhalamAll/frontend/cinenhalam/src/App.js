import { Route, Routes } from "react-router-dom";
import Register from "./Pages/Register/Register";
import Login from "./Pages/Login/Login";
import Navbar from "./Parts/Navbar/Navbar";
import FilmInfo from "./Components/FilmInfo/FilmInfo";
import NotFound from "./Components/NotFound/NotFound";
import HomePage from "./Pages/HomePage/HomePage";
import Slider from "./Components/Slider/Slider";
import FilmDetail from "./Pages/FilmDetail/FilmDetail";
function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/slider" element={<Slider />} />
        <Route path="/" element={<HomePage />} />
        <Route path="/filmInfo" element={<FilmInfo />} />
        <Route path="/filmDetail" element={<FilmDetail />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default App;
