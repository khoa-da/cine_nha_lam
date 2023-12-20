// ProvinceList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import "./ProvinceList.scss";
import ProvinceAPI from '../../Api/ProvinceAPI';

const ProvinceList = ({ filmId, screeningDate, onProvinceSelect }) => {
  const [cities, setCities] = useState([]);
  const [selectedCity, setSelectedCity] = useState(null);

  const fetchData = async (date) => {
    try {
      const response = await axios.get(`http://localhost:8086/api/customer/province/${filmId}/date/${date}`);
      setCities(response.data);
    } catch (error) {
      console.error('Error fetching cities:', error);
    }
  };

  useEffect(() => {
    if (screeningDate) {
      fetchData(screeningDate);
    }
  }, [filmId, screeningDate]);

  const handleClick = (city) => {
    setSelectedCity(city);
    onProvinceSelect(city); // Triggers the onProvinceSelect function in the parent component
  };

  if (!cities || !cities.length) {
    return <div>Mời Bạn Chọn Ngày</div>;
  }

  return (
    <div>
      <div className="city-container">
        {cities.map((city, index) => (
          <div key={index} className="city" onClick={() => handleClick(city)}>
            {city}
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProvinceList;
