import React from "react";
import Member from "../../Components/Member/Member";
import Navbar from "../../Parts/Navbar/Navbar";
import Slider from "../../Components/Slider/Slider";
import Footer from "../../Parts/Footer/Footer";
import ButtonLoginRegister from "../../Components/ButtonLoginRegister/ButtonLoginRegister";

function Promotion() {
  return (
    <div className="">
      <Navbar />
      <ButtonLoginRegister />
      <Slider />
      <Member />
      <Footer />
    </div>
  );
}
export default Promotion;
