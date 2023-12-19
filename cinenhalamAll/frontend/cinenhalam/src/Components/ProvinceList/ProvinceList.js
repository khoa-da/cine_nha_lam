import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProvinceAPI from '../../Api/ProvinceAPI';

const ProvinceList = ({ filmId, screeningDate }) => {
  const [cities, setCities] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await ProvinceAPI.getAllProvincesByFilmAndDate(filmId, screeningDate)
        setCities(response.data);
        // const response = await axios.get('http://localhost:8086/api/customer/province/1/date/2023-12-30');
        // setCities(response.data);
      } catch (error) {
        console.error('Error fetching related films:', error);
      }
    };

    fetchData();
  }, [filmId, screeningDate]);

  if (!cities) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      {/* Render your cities data here */}
      <ul>
        {cities.map((city, index) => (
          // Split each string into individual characters
          <li key={index}>
            {city.split('').map((char, charIndex) => (
              <span key={`${index}-${charIndex}`}>{char}</span>
            ))}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ProvinceList;
