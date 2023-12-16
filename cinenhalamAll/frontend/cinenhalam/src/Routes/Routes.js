import { Route, Routes } from "react-router-dom";
import Register from "./Pages/Register/Register";
import Login from "./Pages/Login/Login";
import FilmInfo from "./Components/FilmInfo/FilmInfo";
import NotFound from "./Components/NotFound/NotFound";
import HomePage from "./Pages/HomePage/HomePage";
import Slider from "./Components/Slider/Slider";
import FilmDetail from "./Pages/FilmDetail/FilmDetail";

const publicRoutes = [
  { path: "/", page: HomePage },
  { path: "/slider", component: Slider },
];
export { publicRoutes };
