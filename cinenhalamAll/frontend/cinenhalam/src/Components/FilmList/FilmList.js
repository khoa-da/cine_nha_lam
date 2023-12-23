import React, { useState, useEffect } from 'react';
import { Grid, Paper, Typography, Button, Link as MaterialLink } from '@mui/material';
import './FilmList.scss';
import FilmAPI from '../../Api/FilmAPI';
import YouTubeLogo from '../../YouTube-Logo.svg';
import { useParams, Link } from 'react-router-dom';

function FilmList() {
  const [films, setFilms] = useState([]);
  const [openModal, setOpenModal] = useState(false);
  const [selectedFilm, setSelectedFilm] = useState(null);
  const [category, setCategory] = useState('nowShowing'); // Define category

  const placeholderData = [
    {
      id: 99,
      name: 'Placeholder Film 1',
      img: 'https://cafefcdn.com/203337114487263232/2021/5/27/a1-16220924934001175865983.jpg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...',
      trailerUrl: 'placeholder_trailer_url_1',
    },
    {
      id: 100,
      name: 'Placeholder Film 1',
      img: 'https://cafefcdn.com/203337114487263232/2021/5/27/a1-16220924934001175865983.jpg',
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit...',
      trailerUrl: 'placeholder_trailer_url_1',
    },
  ];

  const fetchFilms = async (category) => {
    try {
      let response;

      if (category === 'nowShowing') {
        response = await FilmAPI.getAllShowing();
      } else if (category === 'comingSoon') {
        response = await FilmAPI.getAllComing();
      } else {
        response = await FilmAPI.getAllShowing(); // Default to nowShowing category
      }

      setFilms(response.listResult || placeholderData);
    } catch (error) {
      console.error('Error fetching films:', error);
      setFilms(placeholderData);
    }
  };

  useEffect(() => {
    fetchFilms(category); // Initial fetch with default category
  }, [category]);

  const handleCategoryChange = (newCategory) => {
    setCategory(newCategory);
  };

  const handleOpenModal = (film, buttonType) => {
    setSelectedFilm(film);
    setOpenModal(true);

    // If the Trailer button is clicked, open the YouTube link in a new tab
    if (buttonType === 'trailer') {
      window.open(`https://www.youtube.com/watch?v=${film.trailerUrl}`, '_blank');
    }
  };

  const handleCloseModal = () => {
    setOpenModal(false);
  };

  return (
    <div className="cate-all">
      {/* Category List */}
      <div className="cate-container">
      <button
        className={`category-button ${category === 'nowShowing' ? 'active' : ''}`}
        onClick={() => handleCategoryChange('nowShowing')}
      >
        Đang Chiếu
      </button>
      <button
        className={`category-button ${category === 'comingSoon' ? 'active' : ''}`}
        onClick={() => handleCategoryChange('comingSoon')}
      >
        Sắp Chiếu
      </button>
      </div>
      {/* Film Grid */}
      <Grid
        container spacing={4}
        direction="row"
        justifyContent="center"
        alignItems="stretch"
        className="film-list-container"
      >
        {films.map((film) => (
          <Grid item key={film.id} xs={12} sm={6} md={4} lg={6}>
            {/* Additional container */}
            <div className="film-container">
              <Paper elevation={3} className="film-item">
                <Grid container spacing={1} alignItems="stretch" className="film-content">
                  <Grid item xs={12} md={4} className="film-image">
                    {/* Ảnh phim và link */}
                    <MaterialLink href={`/filmDetail/${film.id}`} underline="none">
                      <img src={film.img} alt={film.name} />
                    </MaterialLink>
                  </Grid>
                  <Grid item xs={12} md={8}>
                    {/* Wrapping the film-info Grid in a div for styling */}
                    <div className="film-info">
                      {/* Thông tin khác của bộ phim và link */}
                      <Typography variant="h5" style={{ fontWeight: 'bold' }}>
                        <MaterialLink color="inherit" href={`/filmDetail/${film.id}`} underline="none">
                          {film.name}
                        </MaterialLink>
                      </Typography>
                      <div variant="body1" className="description" style={{ padding: 'auto' }}>
                        {film.description.length > 200
                          ? `${film.description.slice(0, 200)}...`
                          : film.description}
                      </div>
                    </div>
                    {/* Button container */}
                    <div className="button-container">
                      {/* Trailer Button */}
                      <Button
                        className="button"
                        variant="contained"
                        color="primary"
                        onClick={() => handleOpenModal(film, 'trailer')} // Open modal and link YouTube when Trailer button is clicked
                        style={{
                          backgroundColor: 'white',
                          color: '#fff',
                        }}
                      >
                        {/* YouTube Logo */}
                        <img src={YouTubeLogo} alt={`${film.trailerUrl}`} style={{ width: '71px', marginRight: '10px' }} />
                      </Button>
                      {/* Buy Tickets Button */}
                      <Button
                        className="button"
                        variant="contained"
                        color="secondary"
                        component={Link}
                        to={`/book/${film.id}`}  // Sử dụng to để chỉ định đường dẫn động
                        style={{
                          marginLeft: '0px',
                          marginTop: '0px',
                          padding: '18px',
                          backgroundColor: 'black',
                        }}
                      >
                        Mua Vé
                      </Button>
                    </div>
                  </Grid>
                </Grid>
              </Paper>
            </div>
          </Grid>
        ))}
      </Grid>
    </div>
  );
}

export default FilmList;
