import React, { useEffect, useState } from 'react';
import ProvinceList from '../../Components/ProvinceList/ProvinceList';
import Navbar from "../../Parts/Navbar/Navbar";
import Footer from "../../Parts/Footer/Footer";

function BookTicket() {
    return (
      <div>
        <Navbar />
        <ProvinceList filmId={1} screeningDate="2023-12-30" />
        <Footer />
      </div>
    );
  }
  export default BookTicket;