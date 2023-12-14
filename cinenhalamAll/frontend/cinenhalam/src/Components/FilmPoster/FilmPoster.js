import React from 'react';
import './FilmPoster.scss';

function FilmPoster({ film }) {
  if (!film || !film.id || !film.img) {
    console.error('Invalid film object:', film);
    return null; // or return a placeholder image or message
  }

  return (
    <div className="film-poster">
      <img
        src={film.img}
        alt={`Poster for movie with ID ${film.id}`}
      />
    </div>
  );
}

export default FilmPoster;
