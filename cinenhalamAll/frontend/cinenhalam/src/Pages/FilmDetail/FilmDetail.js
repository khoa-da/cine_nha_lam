import React, { useEffect, useState } from 'react';
import { Grid , Typography } from '@mui/material';
import FilmInfo from '../../Components/FilmInfo/FilmInfo';
import FilmPoster from '../../Components/FilmPoster/FilmPoster';
import FilmTrailer from '../../Components/FilmTrailer/FilmTrailer';
import FilmRelated from '../../Components/FilmRelated/FilmRelated';
import axios from 'axios';
import './FilmDetail.scss';
import Navbar from "../../Parts/Navbar/Navbar";
import Footer from "../../Parts/Footer/Footer";
import FilmAPI from '../../Api/FilmAPI';

function FilmDetail() {
  const [film, setFilm] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const response =  await FilmAPI.getFilmDetail(filmId);
        const data = response;
        console.log(data.listResult);
        if (data.listResult.length > 0) {
          setFilm(data.listResult[0]);
        } else {
          console.error('Film details not found.');
        }
      } catch (error) {
        console.error('Error fetching film details:', error);
      }
    }

    fetchData();
  }, [filmId]);

  if (!film) {
    return <div>Loading...</div>;
  }

  return (
    <>
      <Navbar />
      <Grid container spacing={3} className="yellowBackground">
        {/* Left half for FilmPoster */}
        <Grid item xs={12} md={6}>
          <FilmPoster film={film} />
        </Grid>
  
        {/* Right half for FilmInfo and Trailer */}
        <Grid item xs={12} md={6}>
          <FilmInfo film={film} />
        </Grid>
        {/* Centered YouTube video using FilmTrailer component */}
        <Grid item xs={12}>
          <FilmTrailer videoId={film.trailerUrl} />
        </Grid>
        
        <Grid item xs={12}>
          <FilmRelated />
        </Grid>
        <Footer />
      </Grid>
    </>
  );

  
}

export default FilmDetail;
