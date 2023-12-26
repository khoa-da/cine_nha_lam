// FilmInfo.jsx

import React from "react";
import FilmCategories from "../FilmCategories/FilmCategories";
import "./FilmInfo.scss";

function FilmInfo({ film }) {
  // Destructure properties from the film object
  const {
    title,
    name,
    releaseDate,
    duration,
    director,
    actor,
    description,
    rating,
  } = film;

  return (
    <div className="film-info">
      <h2 className="title">{title}</h2>

      <div className="name">
        <h3>{name}</h3>
      </div>

      <div className="additional-details">
        <div>
          <b>Release Date:</b> {releaseDate}
        </div>

        <FilmCategories filmId={film.filmId} />

        <div>
          <b>Thời lượng:</b> {duration} Phút
        </div>

        <div>
          <b>Đạo diễn:</b> {director}
        </div>

        <div>
          <b>Diễn viên:</b> {actor}
        </div>
      </div>

      <p className="description">{description}</p>

      <p>
        <b>Đánh giá:</b> {rating}
      </p>
    </div>
  );
}

export default FilmInfo;
