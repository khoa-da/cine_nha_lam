import React from 'react';
import FilmCategories from '../FilmCategories/FilmCategories';
import './FilmInfo.scss';

function FilmInfo({ film }) {
  // Destructure properties from the film object
  const { name, releaseDate, director, actor, description, rating } = film;

  return (
    <div className="film-info">
      <h1 className="title">{name}</h1>

      <div className="additional-details">
        <div>
          <b>Khởi chiếu:</b> {releaseDate}
        </div>

        {/* Assuming FilmCategories handles the filmId from film object internally */}
        <FilmCategories film={film} />

        <div>
          <b>Đạo diễn:</b> {director}
        </div>

        <div>
          <b>Diễn viên:</b> {actor}
        </div>
      </div>

      <p>{description}</p>

      <p>
        <b>Đánh giá:</b> {rating}
      </p>
    </div>
  );
}

export default FilmInfo;
