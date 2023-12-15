import React from "react";

function FilmInfo({ film }) {
  // Destructure properties from the film object
  const { name, releaseDate, director, actor, description, rating } = film;

  return (
    <div className="film-info">
      <h2 className="title">{film.title}</h2>

      <div className="poster">
        <img src={film.posterUrl} />
      </div>

      <p className="description">{film.description}</p>

      <div className="additional-details">
        <div>
          <b>Release Date:</b> {film.releaseDate}
        </div>

        <div>
          <b>Duration:</b> {film.duration} mins
        </div>

        <div>
          <b>Genres:</b> {film.genres.join(", ")}
        </div>
      </div>
    </div>
  );
}

export default FilmInfo;
