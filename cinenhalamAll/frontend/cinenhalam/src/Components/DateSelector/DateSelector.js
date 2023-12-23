import React, { useState, useEffect } from 'react';
import "./DateSelector.scss";

const DateSelector = ({ onDateSelect }) => {
  const [selectedDate, setSelectedDate] = useState(null);
  const [dates, setDates] = useState([]);

  useEffect(() => {
    const today = new Date();
    const currentMonthDays = new Date(today.getFullYear(), today.getMonth() + 1, 0).getDate();
    const nextMonthDays = new Date(today.getFullYear(), today.getMonth() + 2, 0).getDate();
    const totalDays = currentMonthDays - today.getDate() + nextMonthDays;
    const datesArray = Array.from({ length: totalDays }, (_, i) => {
      const date = new Date();
      date.setDate(today.getDate() + i);
      return date.toISOString().split('T')[0];
    });
    setDates(datesArray);
  }, []);

  const handleDateClick = (date) => {
    console.log(`Selected date: ${date}`);
    setSelectedDate(date);
    onDateSelect(date); // Triggers the onDateSelect function in the parent component
  };

  return (
    <div className="date-container">
      {dates.map((date, index) => (
        <div
          key={index}
          className={`date ${selectedDate === date ? 'selected' : ''}`}
          onClick={() => handleDateClick(date)}
        >
          <div>{date}</div>
        </div>
      ))}
    </div>
  );
};

export default DateSelector;
