// CinemaList.js

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CinemaAPI from '../../Api/CinemaAPI';
import './CinemaList.scss';

const CinemaList = ({ filmId, selectedProvince, selectedDate }) => {
  const [cinemas, setCinemas] = useState(null);
  const cinemasMap = {};
  

  useEffect(() => {
    const fetchCinemas = async () => {
      try {
        const response = await CinemaAPI.getCinemaThatShowThisFilm(filmId, selectedProvince, selectedDate);
        setCinemas(response);
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
  
      {/* Duyệt qua danh sách các rạp chiếu phim để cộng dồn thông tin */}
      {cinemas.forEach((cinema) => {
        const cinemaName = cinema[0];
  
        if (!cinemasMap[cinemaName]) {
          // Nếu rạp chưa được thêm vào đối tượng, thêm mới nó với showtime đầu tiên
          cinemasMap[cinemaName] = {
            name: cinemaName,
            showtimes: cinema.slice(1),
          };
        } else {
          // Nếu rạp đã tồn tại, cộng dồn thêm showtime vào mảng showtimes
          cinemasMap[cinemaName].showtimes.push(...cinema.slice(1));
        }
      })}
  
      {/* Duyệt qua đối tượng cinemasMap để hiển thị thông tin */}
      {Object.values(cinemasMap).map((cinema, index) => (
        <div key={index} className="cinema-item">
          <h3>{cinema.name}</h3>
          <div className="showtimes-container">
            {cinema.showtimes.map((showtime, showtimeIndex) => (
              <div key={showtimeIndex} className="showtime">{showtime}</div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
  
};

export default CinemaList;
