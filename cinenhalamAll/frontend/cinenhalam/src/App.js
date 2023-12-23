import { Route, Routes } from "react-router-dom";

// Components
import FilmInfo from "./Components/FilmInfo/FilmInfo";
import NotFound from "./Components/NotFound/NotFound";
import Slider from "./Components/Slider/Slider";

// Part
import Footer from "./Parts/Footer/Footer";
import Navbar from "./Parts/Navbar/Navbar";

// Page
import Register from "./Pages/Register/Register";
import Login from "./Pages/Login/Login";
import HomePage from "./Pages/HomePage/HomePage";
import FilmDetail from "./Pages/FilmDetail/FilmDetail";
import Promotion from "./Pages/Promotion/Promotion";
import BookTicket from "./Pages/BookTicket/BookTicket";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/promotion" element={<Promotion />} />
        <Route path="/" element={<HomePage />} />
        <Route path="/filmDetail/:filmId" element={<FilmDetail />} />
        <Route path="/book/:filmId" element={<BookTicket />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default App;
