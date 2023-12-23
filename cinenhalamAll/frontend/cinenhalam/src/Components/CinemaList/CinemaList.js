// CinemaList.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CinemaAPI from '../../Api/CinemaAPI';
import './CinemaList.scss';

const CinemaList = ({ filmId, selectedProvince, selectedDate }) => {
  const [cinemas, setCinemas] = useState(null);

  useEffect(() => {
    const fetchCinemas = async () => {
      try {
        const response = await axios.get(`http://localhost:8086/api/customer/cinema/film/${filmId}/province/${selectedProvince}/date/${selectedDate}`);
        setCinemas(response.data);
        console.log(response.data);
      } catch (error) {
        console.error('Error fetching cinema data:', error);
        setCinemas([]);
      }
    };

    if (selectedProvince && selectedDate) {
      fetchCinemas();
    }
  }, [filmId, selectedProvince, selectedDate]);

  if (cinemas === null) {
    return <div>Loading...</div>;
  }

  if (!cinemas || !Array.isArray(cinemas)) {
    return <div>No cinemas available for the selected city and date.</div>;
  }

  return (
    <div className="cinema-list-container">
      {cinemas.map((cinema, index) => (
        <div key={index} className="cinema-item">
          <h3>{cinema[0]}</h3> {/* Hiển thị tên rạp chiếu phim */}
          <div className="showtimes-container">
            {cinema.slice(1).map((showtime, showtimeIndex) => (
              <div key={showtimeIndex} className="showtime">{showtime}</div> 
            ))}
          </div>
        </div>
      ))}
    </div>
  );
};

export default CinemaList;
