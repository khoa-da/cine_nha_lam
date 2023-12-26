import React from "react";
import Navbar from "../../Parts/Navbar/Navbar";
import Slider from "../../Components/Slider/Slider";
import FilmList from "../../Components/FilmList/FilmList";
import Footer from "../../Parts/Footer/Footer";
import ButtonLoginRegister from "../../Components/ButtonLoginRegister/ButtonLoginRegister";
import { Category } from "@mui/icons-material";

function HomePage() {
  return (
    <div>
      <Navbar />
      <ButtonLoginRegister />
      <Slider />
      <FilmList />
      <Footer />
    </div>
  );
}
export default HomePage;
