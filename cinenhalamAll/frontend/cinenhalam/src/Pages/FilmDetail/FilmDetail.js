import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import FilmInfo from 'components/FilmInfo';

function FilmDetailPage() {

  const { id } = useParams();

  const [film, setFilm] = useState(null);

  useEffect(() => {
    async function fetchData() {
      const response = await fetch(`/api/films/${id}`); 
      const data = await response.json();
      setFilm(data);
    }

    fetchData();
  }, [id]);

  if (!film) {
    return <div>Loading...</div>
  }

  return (
    <div className="film-detail-page">
      <FilmInfo film={film} />
      
      {/* other components */}
      
    </div>
  )
}

export default FilmDetailPage;