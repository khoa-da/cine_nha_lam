import React, { useState } from 'react';
import './BookTicket.scss'; // Đường dẫn đến file styles.css hoặc styles.scss
import ProvinceList from '../../Components/ProvinceList/ProvinceList';
import CinemaList from '../../Components/CinemaList/CinemaList';
import Navbar from "../../Parts/Navbar/Navbar";
import Footer from "../../Parts/Footer/Footer";
import DateSelector from '../../Components/DateSelector/DateSelector';
import { useParams } from 'react-router-dom';

function BookTicket() {
  const { filmId } = useParams();
  const defaultFilmId = filmId; // Chú ý ở đây, không cần là {filmId}
  const [selectedDate, setSelectedDate] = useState();
  const [selectedProvince, setSelectedProvince] = useState();

  const handleDateSelect = (date) => {
    setSelectedDate(date);
  };

  const handleProvinceSelect = (province) => {
    setSelectedProvince(province);
  };

  return (
    <div className="all">
      <Navbar />
      <DateSelector onDateSelect={handleDateSelect} />
      <ProvinceList filmId={defaultFilmId} screeningDate={selectedDate} onProvinceSelect={handleProvinceSelect} />
      <CinemaList filmId={defaultFilmId} selectedProvince={selectedProvince} selectedDate={selectedDate} />
      <Footer />
    </div>
  );
}

export default BookTicket;
