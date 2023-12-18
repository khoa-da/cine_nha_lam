import React from "react";
import Navbar from "../../Parts/Navbar/Navbar";
import Slider from "../../Components/Slider/Slider";
import Footer from "../../Parts/Footer/Footer";
import ButtonLoginRegister from "../../Components/ButtonLoginRegister/ButtonLoginRegister";

function HomePage() {
  return (
    <div>
      <Navbar />
      <ButtonLoginRegister />
      <Slider />
      <Footer />
    </div>
  );
}
export default HomePage;
